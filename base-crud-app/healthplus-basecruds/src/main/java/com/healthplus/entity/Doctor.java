package com.healthplus.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	private String firstName;
    private String lastName;
    private String emailId;


  @Enumerated(value = EnumType.STRING)
  private Gender gender;

  private String telephoneNumber;

  private Date dateOfBirth;

  private Date startPracticeDate;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;

  private String address;

  private String description;



  private String picturePath;

//  @OneToOne //(optional = false)
//  @JoinColumn(name = "week_schedule_id", referencedColumnName = "id")
//  private WeekSchedule weekSchedule;
//
//  @ManyToOne
//  @JoinColumn(name = "rating_id")
//  private Rating rating;
//
//  @OneToOne(optional = false)
//  @JoinColumn(name = "user_id", referencedColumnName = "id")
//  private User user;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
  private Set<Patient> patients;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
  private Set<Appointment> appointments;

  public Doctor() {
      this.setPatients(new HashSet<>());
  }

public Doctor(String firstName, String lastName, String emailId, Gender gender, String telephoneNumber,
		Date dateOfBirth, Date startPracticeDate, Location location, String address, String description,
		String picturePath, Set<Patient> patients, Set<Appointment> appointments) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.emailId = emailId;
	this.gender = gender;
	this.telephoneNumber = telephoneNumber;
	this.dateOfBirth = dateOfBirth;
	this.startPracticeDate = startPracticeDate;
	this.location = location;
	this.address = address;
	this.description = description;
	this.picturePath = picturePath;
	this.patients = patients;
	this.appointments = appointments;
}

  

}
