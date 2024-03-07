package org.longbox.domainobjects.dto;

import lombok.Getter;
import lombok.Setter;
import org.longbox.persistence.entity.User;

import java.util.*;

@Getter
@Setter
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
	private String aboutMe;

	public UserDTO() {
	}

	public UserDTO(
			String userName,
			String firstName,
			String lastName,
			Date dob,
			String email,
			String password,
			String country,
			String aboutMe
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
		this.aboutMe = aboutMe;
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
			int comicsFinished,
			String aboutMe
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
		this.aboutMe = aboutMe;
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

	public UserDTO(User user) {
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
				user.getComicsFinished(),
				user.getAboutMe()
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
				"Comics Finished: " + comicsFinished + "\n" +
				"About Me: " + aboutMe + "\n";
	}
	
	public String getUserName() {
		return this.userName;
	}
	
}
