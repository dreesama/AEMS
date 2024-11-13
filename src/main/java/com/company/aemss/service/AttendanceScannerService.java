package com.company.aemss.service;

import com.company.aemss.entity.Attendance;
import com.company.aemss.entity.Event;
import com.company.aemss.entity.QrCode;
import com.company.aemss.entity.Student;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class AttendanceScannerService {

    private static final int DEFAULT_EVENT_DURATION_HOURS = 8;

    @Autowired
    private DataManager dataManager;

    @Transactional
    public Attendance processQrCodeScan(String qrCodeData) {
        // Find QR Code
        Optional<QrCode> qrCodeOptional = dataManager.load(QrCode.class)
                .query("select q from QrCode q where q.qrCodeData = :qrCodeData")
                .parameter("qrCodeData", qrCodeData)
                .optional();

        if (qrCodeOptional.isEmpty()) {
            throw new RuntimeException("Invalid QR Code");
        }

        QrCode qrCode = qrCodeOptional.get();
        Student student = qrCode.getStudent();
        Event event = qrCode.getEvent();

        // Get current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime eventStartTime = LocalDateTime.ofInstant(
                event.getTimeStarts().toInstant(),
                ZoneId.systemDefault()
        );

        // Hardcoded 8-hour duration
        LocalDateTime eventEndTime = eventStartTime.plusHours(DEFAULT_EVENT_DURATION_HOURS);

        // Validate attendance scanning
        validateAttendanceScan(student, event, currentDateTime, eventStartTime, eventEndTime);

        // Create Attendance Record
        Attendance attendance = dataManager.create(Attendance.class);
        attendance.setStudent(student);
        attendance.setEvent(event);
        attendance.setScannedAt(Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant()));

        // Determine attendance status based on new logic
        attendance.setStatus(determineAttendanceStatus(currentDateTime, eventStartTime, eventEndTime));

        // Save the attendance record
        return dataManager.save(attendance);
    }

    private void validateAttendanceScan(Student student, Event event,
                                        LocalDateTime currentDateTime,
                                        LocalDateTime eventStartTime,
                                        LocalDateTime eventEndTime) {
        // Check if student is registered for the event
        if (student == null || event == null) {
            throw new RuntimeException("Invalid student or event");
        }

        // Check if attendance already exists
        Optional<Attendance> existingAttendance = dataManager.load(Attendance.class)
                .query("select a from Attendance a where a.student = :student and a.event = :event")
                .parameter("student", student)
                .parameter("event", event)
                .optional();

        if (existingAttendance.isPresent()) {
            throw new RuntimeException("Already checked in");
        }
    }

    private String determineAttendanceStatus(LocalDateTime currentDateTime,
                                             LocalDateTime eventStartTime,
                                             LocalDateTime eventEndTime) {
        // Too early
        if (currentDateTime.isBefore(eventStartTime)) {
            throw new RuntimeException("Too Early :D Event not yet Started");
        }

        // Too late
        if (currentDateTime.isAfter(eventEndTime)) {
            throw new RuntimeException("Too Late :( Event Ended");
        }

        // Present (during event time)
        return "PRESENT";
    }
    @Transactional
    public void markAbsentForMissedEvents() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Event> missedEvents = dataManager.load(Event.class)
                .query("select e from Event e where e.timeStarts < :currentDateTime " +
                        "and not exists (select a from Attendance a where a.event = e)")
                .parameter("currentDateTime", currentDateTime)
                .list();

        for (Event event : missedEvents) {
            List<QrCode> eventQrCodes = dataManager.load(QrCode.class)
                    .query("select q from QrCode q where q.student.event = :event")
                    .parameter("event", event)
                    .list();

            for (QrCode qrCode : eventQrCodes) {
                Attendance attendance = dataManager.create(Attendance.class);
                attendance.setStudent(qrCode.getStudent());
                attendance.setEvent(event);
                attendance.setScannedAt(event.getTimeStarts());
                attendance.setStatus("MISSED");

                dataManager.save(attendance);
            }
        }
    }
    @Transactional(readOnly = true)
    public List<Attendance> getAllAttendanceRecords() {
        return dataManager.load(Attendance.class)
                .all()
                .list();
    }
}