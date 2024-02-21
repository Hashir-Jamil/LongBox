package org.longbox.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

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

	@Column(name = "passowrd")
	private String password;

	@Column(name = "country")
	private String country;

	@Column(name = "join_date")
	private Date joinDate;
	
	@Column(name = "comics_reading")
	private int comicsReading;

	@Column(name = "comics_finished")
	private int comicsFinished;
	
	public User() {

	}

	public User(String userName, String firstName, String lastName, Date dob, String email, String password,
			String country, Date joinDate, int comicsReading, int comicsFinished) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.country = country;
		this.joinDate = new Date();
		this.comicsReading = comicsReading;
		this.comicsFinished = comicsFinished;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getCountry() {
		return country;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public int getcomicsReading() {
		return comicsReading;
	}
	
	public int getcomicsFinished() {
		return comicsFinished;
	}
		
}
