package com.company.aemss.listener;

import com.company.aemss.entity.QrCode;
import com.company.aemss.entity.Student;
import com.company.aemss.service.QrCodeService;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StudentQrCodeListener {
    private static final Logger logger = LoggerFactory.getLogger(StudentQrCodeListener.class);

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private DataManager dataManager;

    @EventListener
    @Transactional
    public void onStudentSaved(EntityChangedEvent<Student> event) {
        if (event.getType() != EntityChangedEvent.Type.CREATED) {
            return;
        }

        try {
            Student student = dataManager.load(Student.class)
                    .id(event.getEntityId())
                    .one();

            // Check if QR code already exists
            QrCode existingQrCode = dataManager.load(QrCode.class)
                    .query("select q from QrCode q where q.student = :student")
                    .parameter("student", student)
                    .optional()
                    .orElse(null);

            if (existingQrCode == null) {
                // Generate QR code data with only the student ID
                String qrCodeData = student.getId().toString();

                // Generate QR code image
                byte[] qrCodeImage = qrCodeService.generateQrCode(qrCodeData, 500, 500);

                // Create and save QR code
                QrCode qrCode = dataManager.create(QrCode.class);
                qrCode.setStudent(student);
                qrCode.setQrCodeData(qrCodeData);
                qrCode.setQrCodeImage(qrCodeImage);

                dataManager.save(qrCode);

                logger.info("QR Code generated for student: {} {}", student.getFirstName(), student.getId());
            } else {
                logger.info("QR Code already exists for student: {} {}", student.getFirstName(), student.getId());
            }
        } catch (Exception e) {
            logger.error("Error generating QR code for student", e);
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
}