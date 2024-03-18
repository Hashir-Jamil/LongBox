package org.longbox.domainobjects.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.longbox.domainobjects.entity.User;

import java.util.*;

@Getter
@Setter
@ToString
public class UserDto {
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private Date dob;
	private String email;
	private String password;
	private String country;
	private String continent;
	private Date joinDate;
	private int comicsReading;
	private int comicsFinished;
	private String aboutMe;

	public UserDto() {
	}

	public UserDto(
		Long id,
		String userName,
		String firstName,
		String lastName,
		Date dob,
		String email,
		String password,
		String country,
		String aboutMe
	) {
		this.id = id;
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

	public UserDto(
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

	public UserDto(
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

	public UserDto(
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

	public UserDto(
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

	public UserDto(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.dob = user.getDob();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.country = user.getCountry();
		this. comicsReading = user.getComicsReading();
		this. comicsFinished = user.getComicsFinished();
		this.aboutMe = user.getAboutMe();
		this.joinDate = user.getJoinDate();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDto userDto = (UserDto) o;
		return getId() == userDto.getId() && Objects.equals(getUserName(), userDto.getUserName()) && Objects.equals(getFirstName(), userDto.getFirstName()) && Objects.equals(getLastName(), userDto.getLastName()) && Objects.equals(getEmail(), userDto.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUserName(), getFirstName(), getLastName(), getEmail());
	}
}
