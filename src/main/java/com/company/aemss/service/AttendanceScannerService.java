package com.company.aemss.service;

import com.company.aemss.entity.Attendance;
import com.company.aemss.entity.Event;
import com.company.aemss.entity.QrCode;
import com.company.aemss.entity.Student;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceScannerService {

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

        // Check if already checked in
        Optional<Attendance> existingAttendance = dataManager.load(Attendance.class)
                .query("select a from Attendance a where a.student = :student and a.event = :event")
                .parameter("student", student)
                .parameter("event", event)
                .optional();

        if (existingAttendance.isPresent()) {
            throw new RuntimeException("Already checked in");
        }

        // Create Attendance Record
        Attendance attendance = dataManager.create(Attendance.class);
        attendance.setStudent(student);
        attendance.setEvent(event);

        // Set scanned time
        Date currentTime = new Date();
        attendance.setScannedAt(currentTime);

        // Determine status
        if (event.getTimeStarts() != null) {
            long timeDifference = currentTime.getTime() - event.getTimeStarts().getTime();
            long fifteenMinutesInMillis = 15 * 60 * 1000; // 15 minutes

            if (timeDifference > fifteenMinutesInMillis) {
                attendance.setStatus("LATE");
            } else {
                attendance.setStatus("ON TIME");
            }
        } else {
            attendance.setStatus("UNKNOWN");
        }

        // Save the attendance record
        return dataManager.save(attendance);
    }

    @Transactional
    public void markAbsentForMissedEvents() {
        // Find events that have passed
        Date currentTime = new Date();

        // Load events that have started and have no attendance record
        List<Event> missedEvents = dataManager.load(Event.class)
                .query("select e from Event e where e.timeStarts < :currentTime " +
                        "and not exists (select a from Attendance a where a.event = e)")
                .parameter("currentTime", currentTime)
                .list();

        for (Event event : missedEvents) {
            // Find all students associated with QR codes for this event
            List<QrCode> eventQrCodes = dataManager.load(QrCode.class)
                    .query("select q from QrCode q where q.event = :event")
                    .parameter("event", event)
                    .list();

            for (QrCode qrCode : eventQrCodes) {
                Attendance attendance = dataManager.create(Attendance.class);
                attendance.setStudent(qrCode.getStudent());
                attendance.setEvent(event);
                attendance.setScannedAt(event.getTimeStarts());
                attendance.setStatus("ABSENT");

                dataManager.save(attendance);
            }
        }
    }
}