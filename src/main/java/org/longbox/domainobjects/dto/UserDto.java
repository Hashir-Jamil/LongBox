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
	private String[] preferredGenre;

	public UserDto() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDto userDto = (UserDto) o;
		return Objects.equals(getId(), userDto.getId()) && Objects.equals(getUserName(), userDto.getUserName()) && Objects.equals(getFirstName(), userDto.getFirstName()) && Objects.equals(getLastName(), userDto.getLastName()) && Objects.equals(getEmail(), userDto.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUserName(), getFirstName(), getLastName(), getEmail());
	}

	public UserDto getUser(){
		return this;
	}

	public void setDefaults(){
		this.setComicsReading(0);
		this.setComicsFinished(0);
		this.setJoinDate(new Date());
	}
}
