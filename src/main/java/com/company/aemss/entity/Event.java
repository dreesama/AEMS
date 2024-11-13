package com.company.aemss.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@JmixEntity
@Table(name = "events")
@Entity
public class Event {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIME_STARTS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStarts;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "location")
    @Lob
    private String location;

    @InstanceName
    @Column(name = "name", nullable = false)
    @Lob
    private String name;

    public Date getTimeStarts() {
        return timeStarts;
    }

    public void setTimeStarts(Date timeStarts) {
        this.timeStarts = timeStarts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        if (timeStarts != null) {
            return timeStarts.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null; // or LocalDate.now() or throw an exception based on your needs
    }
}