package com.company.aemss.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

@JmixEntity
@Table(name = "departments", indexes = {
        @Index(name = "IDX_DEPARTMENTS_RESPONSIBLE", columnList = "RESPONSIBLE_ID")
})
@Entity
public class Department  {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "RESPONSIBLE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User responsible;
    @Column(name = "description")
    @Lob
    private String description;

    @InstanceName
    @Column(name = "name", nullable = false)
    @Lob
    private String name;

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}