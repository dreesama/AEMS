package com.company.aemss.listener;

import com.company.aemss.entity.QrCode;
import com.company.aemss.entity.Student;
import com.company.aemss.service.QrCodeService;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onStudentSaved(EntityChangedEvent<Student> event) {
        if (event.getType() != EntityChangedEvent.Type.CREATED) {
            return;
        }

        Student student = dataManager.load(Student.class)
                .id(event.getEntityId())
                .one();

        // Check if QR code already exists for this student
        QrCode existingQrCode = dataManager.load(QrCode.class)
                .query("select e from QrCode e where e.student = :student")
                .parameter("student", student)
                .optional()
                .orElse(null);

        if (existingQrCode == null) {
            // Generate QR code content (you can customize this format)
            String qrContent = String.format("STUDENT:%s,ID:%s",
                    student.getId().toString(),
                    student.getId().toString());

            // Generate QR code image
            String qrCodeImage = qrCodeService.generateQrCode(qrContent, 250, 250);

            // Create new QR code entity
            QrCode qrCode = dataManager.create(QrCode.class);
            qrCode.setStudent(student);
            qrCode.setQrCodeData(qrCodeImage);

            // Save QR code
            dataManager.save(qrCode);
        }
    }
}
