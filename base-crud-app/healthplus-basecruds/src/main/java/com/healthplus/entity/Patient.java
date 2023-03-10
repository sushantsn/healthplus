package com.healthplus.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String aadhaar;

    private String telephoneNumber;

    private Date dateOfBirth;
    
    private String emailId;

    public Patient(String firstName, String lastName, String aadhaar, String telephoneNumber, Date dateOfBirth,
			String emailId, Doctor doctor) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.aadhaar = aadhaar;
		this.telephoneNumber = telephoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.emailId = emailId;
		this.doctor = doctor;
	}


	public Patient() {
		super();
	}

	@ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    
}
