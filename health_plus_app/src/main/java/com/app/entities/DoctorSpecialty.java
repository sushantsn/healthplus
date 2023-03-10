package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "doctor_specialties")
public class DoctorSpecialty extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    private String name;

    private String description;

    public DoctorSpecialty() {}

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

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
}
