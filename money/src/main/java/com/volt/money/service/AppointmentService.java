package com.volt.money.service;

import com.volt.money.model.Appointment;
import com.volt.money.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment bookAppointment(String operatorId, LocalDate date, int startTime, int endTime, String userId) {
        List<Appointment> existingAppointments = appointmentRepository.findByOperatorIdAndDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(operatorId, date, startTime, endTime);
        if (!existingAppointments.isEmpty()) {
            throw new RuntimeException("Slot is already booked");
        }

        Appointment appointment = new Appointment();
        appointment.setOperatorId(operatorId);
        appointment.setDate(date);
        appointment.setStartTime(startTime);
        appointment.setEndTime(endTime);
        appointment.setUserId(userId);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment rescheduleAppointment(String appointmentId, int newStartTime, int newEndTime) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            List<Appointment> existingAppointments = appointmentRepository.findByOperatorIdAndDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(appointment.getOperatorId(), appointment.getDate(), newStartTime, newEndTime);
            if (!existingAppointments.isEmpty() && !existingAppointments.get(0).getId().equals(appointmentId)) {
                throw new RuntimeException("Slot is already booked by another appointment");
            }
            appointment.setStartTime(newStartTime);
            appointment.setEndTime(newEndTime);
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }

    @Transactional
    public void cancelAppointment(String appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    public List<String> getOpenSlots(String operatorId, LocalDate date, int dayStart, int dayEnd) {
        if (dayStart < 0 || dayStart >= 24 || dayEnd < 0 || dayEnd >= 24 || dayStart >= dayEnd) {
            throw new IllegalArgumentException("Invalid day start or end times");
        }

        List<Appointment> appointments = appointmentRepository.findByOperatorIdAndDate(operatorId, date);

        List<String> openSlots = new ArrayList<>();

        int start = dayStart;
        int end = dayStart;

        for (Appointment appointment : appointments) {
            if (appointment.getStartTime() > end) {
                openSlots.add(start + "-" + end);
                start = appointment.getStartTime();
                end = appointment.getEndTime();
            } else {
                end = Math.max(end, appointment.getEndTime());
            }
        }

        if (end <= dayEnd) {
            openSlots.add(start + "-" + Math.min(end, dayEnd));
        }

        return openSlots;
    }

    public List<Appointment> getOperatorAppointments(String operatorId, LocalDate date) {
        return appointmentRepository.findByOperatorIdAndDate(operatorId, date);
    }
}
