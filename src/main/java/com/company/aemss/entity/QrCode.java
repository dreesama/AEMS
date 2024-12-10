package com.company.aemss.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "QR_CODE")
@Entity
public class QrCode {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "STUDENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    // Preserved student information
    @Column(name = "STUDENT_FIRST_NAME")
    private String studentFirstName;

    @Column(name = "STUDENT_LAST_NAME")
    private String studentLastName;

    // Store Department as an entity reference
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "PRESERVED_DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department preservedDepartment;

    @Column(name = "qr_code_data", columnDefinition = "TEXT")
    private String qrCodeData;

    @Column(name = "QR_CODE_IMAGE")
    private byte[] qrCodeImage;

    // Original getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        if (student != null) {
            // Preserve student information when setting the student
            this.studentFirstName = student.getFirstName();
            this.studentLastName = student.getLastName();
            this.preservedDepartment = student.getDepartment();
        }
    }

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    public byte[] getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(byte[] qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    // Getters and setters for preserved student information
    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Department getPreservedDepartment() {
        return preservedDepartment;
    }

    public void setPreservedDepartment(Department preservedDepartment) {
        this.preservedDepartment = preservedDepartment;
    }
}