package org.longbox.domainobjects;

import lombok.NoArgsConstructor;
import org.longbox.businesslogic.comparators.ComicBookDateAddeddComparator;
import org.longbox.businesslogic.comparators.ComicBookNameComparator;
import org.longbox.businesslogic.comparators.ComicBookYearPublishedComparator;

import java.util.*;

@NoArgsConstructor
public class UserDTO {

	private String userName;
	private String firstName;
	private String lastName;
	private Date dob;
	private String email;
	private String password;
	private String country;
	private Date joinDate;
	private List<ComicBookDTO> comicBookList = new ArrayList<>();

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserDTO userDTO)) return false;
		return Objects.equals(getUserName(), userDTO.getUserName()) && Objects.equals(getFirstName(), userDTO.getFirstName()) && Objects.equals(getLastName(), userDTO.getLastName()) && Objects.equals(getDob(), userDTO.getDob()) && Objects.equals(getEmail(), userDTO.getEmail()) && Objects.equals(getCountry(), userDTO.getCountry()) && Objects.equals(getJoinDate(), userDTO.getJoinDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserName(), getFirstName(), getLastName(), getDob(), getEmail(), getCountry(), getJoinDate());
	}

	public boolean addComicBookToList(ComicBookDTO comicBook) {
		if (comicBookList.contains(comicBook)) {
			return false;
		}
		else {
			comicBookList.add(comicBook);
			return true;
		}
	}

	public void sortComicsAToZ() {
		Collections.sort(comicBookList, new ComicBookNameComparator());
	}

	public void sortComicsDateAdded() {
		Collections.sort(comicBookList, new ComicBookDateAddeddComparator());
	}

	public void sortComicsYearPublished() {
		Collections.sort(comicBookList, new ComicBookYearPublishedComparator());
	}

}
