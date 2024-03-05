package org.longbox.persistence.entity;

import jakarta.persistence.*;
import org.longbox.domainobjects.dto.UserDTO;

import java.util.Date;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

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

	@ManyToMany
	@JoinTable(
			name = "comic_book_list",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = {
					@JoinColumn(name = "comic_book_id", referencedColumnName = "id"),
			}
	)

	private Set<ComicBook> comicBooks = new HashSet<>();

	public User() {
	}

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

	public User(UserDTO user){
		this(user.getUserName(),
				user.getFirstName(),
				user.getLastName(),
				user.getDob(),
				user.getEmail(),
				user.getPassword(),
				user.getCountry());
	}

	@Override
	public int hashCode() {
		return Objects.hash(comicsFinished, comicsReading, country, dob, email, firstName, id, joinDate, lastName,
				password, userName);
	}
	
	//two users are equal if they have the same name, user name and emails

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return  Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(userName, other.userName);
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
