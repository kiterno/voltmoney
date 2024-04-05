package com.volt.money.repository;

import com.volt.money.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByOperatorIdAndDate(String operatorId, LocalDate date);
    List<Appointment> findByOperatorIdAndDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(String operatorId, LocalDate date, int startTime, int endTime);
}
