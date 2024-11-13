package com.company.aemss.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

@JmixEntity
@Table(name = "students")
@Entity
public class Student {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @JoinColumn(name = "event_id") // Add this line to specify the foreign key column
    @ManyToOne(fetch = FetchType.LAZY) // Define the relationship to the Event entity
    private Event event; // Add this field to link the Event entity

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

    @Column(name = "section")
    private String section;

    // Getters and Setters for the new event field
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
}