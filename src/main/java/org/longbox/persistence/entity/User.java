package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.domainobjects.dto.UserDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "country")
	private String country;

	@Column(name = "join_date")
	private Date joinDate;
	
	@Column(name = "comics_reading")
	private Integer comicsReading;

	@Column(name = "comics_finished")
	private Integer comicsFinished;

	@OneToMany(mappedBy = "user")
	private Set<ComicBookFavoritesList> favoriteComicBooks = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private Set<ComicBookFinishedList> finishedComicBooks = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private Set<ComicBookReadingList> readingComicBooks = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private Set<Comment> comments = new HashSet<>();

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
}
