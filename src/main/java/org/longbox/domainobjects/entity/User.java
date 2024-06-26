package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.domainobjects.dto.UserDto;

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
	
	@Column(name = "continent")
	private String continent;

	@Column(name = "join_date")
	private Date joinDate;
	
	@Column(name = "comics_reading")
	private Integer comicsReading;

	@Column(name = "comics_finished")
	private Integer comicsFinished;

	@Column(name = "about_me")
	private String aboutMe;

	@Column(name = "preferred_genre",  columnDefinition = "text[]")
	private String preferredGenre;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<ComicBookFavouritesList> favouriteComicBooks = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<ComicBookFinishedList> finishedComicBooks = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<ComicBookReadingList> readingComicBooks = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private Set<StarRating> starRatings = new HashSet<>();

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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dob=" + dob +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", country='" + country + '\'' +
				", continent='" + continent + '\'' +
				", joinDate=" + joinDate +
				", comicsReading=" + comicsReading +
				", comicsFinished=" + comicsFinished +
				", aboutMe=" + aboutMe +
				'}';
	}

	public void setDefaults(){
		this.setComicsReading(0);
		this.setComicsFinished(0);
		this.setJoinDate(new Date());
	}
}
