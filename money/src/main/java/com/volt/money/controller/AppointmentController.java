package com.volt.money.controller;
import com.volt.money.model.Appointment;
import com.volt.money.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestParam String operatorId,
                                             @RequestParam LocalDate date,
                                             @RequestParam int startTime,
                                             @RequestParam int endTime,
                                             @RequestParam String userId) {
        try {
            Appointment appointment = appointmentService.bookAppointment(operatorId, date, startTime, endTime, userId);
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reschedule/{appointmentId}")
    public ResponseEntity<?> rescheduleAppointment(@PathVariable String appointmentId,
                                                   @RequestParam int newStartTime,
                                                   @RequestParam int newEndTime) {
        try {
            Appointment appointment = appointmentService.rescheduleAppointment(appointmentId, newStartTime, newEndTime);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancel/{appointmentId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable String appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return new ResponseEntity<>("Appointment cancelled successfully", HttpStatus.OK);
    }

    @GetMapping("/operator/{operatorId}")
    public ResponseEntity<?> getOperatorAppointments(@PathVariable String operatorId, @RequestParam LocalDate date) {
        List<Appointment> appointments = appointmentService.getOperatorAppointments(operatorId, date);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/openSlots/{operatorId}")
    public ResponseEntity<?> getOpenSlots(@PathVariable String operatorId, @RequestParam LocalDate date,
                                          @RequestParam int dayStart, @RequestParam int dayEnd) {
        List<String> openSlots = appointmentService.getOpenSlots(operatorId, date, dayStart, dayEnd);
        return new ResponseEntity<>(openSlots, HttpStatus.OK);
    }

}
