package com.company.aemss.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "students")
@Entity
public class Student {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "department_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Department department;

    @Column(name = "student_id", nullable = false, unique = true)
    @Lob
    private String student;

    @InstanceName
    @Column(name = "first_name", nullable = false)
    @Lob
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Lob
    private String lastName;

    @NotNull
    @Column(name = "section", nullable = false)
    private String section;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Automatically generate email and password when creating a new Student
    @PrePersist
    public void generateEmailAndPassword() {
        updateEmailAndPassword();
    }

    // Update email and password before updating the entity
    @PreUpdate
    public void updateEmailAndPassword() {
        this.email = this.getLastName().toLowerCase() + "." + this.getStudent() + "@ormoc.sti.edu.ph";
        this.password = this.getStudent();
    }
}