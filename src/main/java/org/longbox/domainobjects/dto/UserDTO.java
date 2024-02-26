package org.longbox.domainobjects.dto;

import org.longbox.persistence.entity.User;

import java.util.*;

public class UserDTO {

	private long id;
	private String userName;
	private String firstName;
	private String lastName;
	private Date dob;
	private String email;
	private String password;
	private String country;
	private Date joinDate;
	private List<ComicBookDTO> comicBookList = new ArrayList<>();
	private int comicsReading;
	private int comicsFinished;

	public UserDTO() {
	}

	public UserDTO(
		String userName,
		String firstName,
		String lastName,
		Date dob,
		String email,
		String password,
		String country
	) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.country = country;
		this.joinDate = new Date();
		this.comicsReading = 0;
		this.comicsFinished = 0;
	}

	public UserDTO(
			long id,
			String userName,
			String firstName,
			String lastName,
			Date dob,
			String email,
			String password,
			String country,
			int comicsReading,
			int comicsFinished
	) {
		super();
		this.id = id;
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

	public UserDTO(User user){
		this(
				user.getId(),
				user.getUserName(),
				user.getFirstName(),
				user.getLastName(),
				user.getDob(),
				user.getEmail(),
				user.getPassword(),
				user.getCountry(),
				user.getComicsReading(),
				user.getComicsFinished()
		);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserDTO userDTO)) return false;
		return Objects.equals(getUserName(),
				userDTO.getUserName()) && Objects.equals(getFirstName(),
				userDTO.getFirstName()) && Objects.equals(getLastName(),
				userDTO.getLastName()) && Objects.equals(getDob(),
				userDTO.getDob()) && Objects.equals(getEmail(), userDTO.getEmail()) && Objects.equals(getCountry(),
				userDTO.getCountry()) && Objects.equals(getJoinDate(),
				userDTO.getJoinDate()
		);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserName(), getFirstName(), getLastName(), getDob(), getEmail(), getCountry(), getJoinDate(), getComicsReading(), getComicsFinished());
	}

	@Override
	public String toString() {
		return "Username: " + userName + "\n" +
				"First Name: " + firstName + "\n" +
				"Lastname: " + lastName + "\n" +
				"Date of Birth: " + dob + "\n" +
				"Email Address: " + email + "\n" +
				"Country: " + country + "\n" +
				"Join Date: " + joinDate + "\n" +
				"Comics Reading: " + comicsReading + "\n" +
				"Comics Finished: " + comicsFinished + "\n";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public List<ComicBookDTO> getComicBookList() {
		return comicBookList;
	}

	public void setComicBookList(List<ComicBookDTO> comicBookList) {
		this.comicBookList = comicBookList;
	}

	public int getComicsReading() {
		return comicsReading;
	}

	public void setComicsReading(int comicsReading) {
		this.comicsReading = comicsReading;
	}

	public int getComicsFinished() {
		return comicsFinished;
	}

	public void setComicsFinished(int comicsFinished) {
		this.comicsFinished = comicsFinished;
	}
}
