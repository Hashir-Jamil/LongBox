package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "country")
	private String country;

	@Column(name = "join_date")
	private Date joinDate;
	
	@Column(name = "comics_reading")
	private int comicsReading;

	@Column(name = "comics_finished")
	private int comicsFinished;

	public User(String userName, String firstName, String lastName, Date dob, String email, String password,
			String country) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.country = country;
		this.joinDate = new Date();
		this.comicsFinished = 0;
		this.comicsReading = 0;
	}

	

}
