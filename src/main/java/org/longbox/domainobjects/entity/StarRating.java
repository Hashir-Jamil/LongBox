package org.longbox.domainobjects.entity;

import org.longbox.domainobjects.dto.StarRatingDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.longbox.domainobjects.mapper.ComicBookMapper;
import org.longbox.domainobjects.mapper.UserMapper;

@Entity
@Table(name = "starRatings")
@Getter
@Setter

public class StarRating {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating")
    private int rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comic_book_id", referencedColumnName = "id")
    private ComicBook comicBook;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public StarRating() {
    }

    public StarRating(int rating, ComicBook comicBook, User user) {
    	this.rating = rating;
    	this.comicBook = comicBook;
    	this.user = user;
    }

    public StarRating(StarRatingDto s){
    	this.user = UserMapper.toEntity(s.getUser());
    	this.comicBook = ComicBookMapper.toEntity(s.getComicBook());
    	this.rating = s.getRating();
    }

    public StarRating(StarRatingDto s, User u, ComicBook cb){
    	this.user = u;
    	this.comicBook = cb;
    	this.rating = s.getRating();
    }

    public long getComicBookId(){
    	return this.comicBook.getId();
    }

    public long getUserId(){
    	return this.user.getId();
    }
}
