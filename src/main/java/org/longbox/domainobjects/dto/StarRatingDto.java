package org.longbox.domainobjects.dto;

import java.util.Objects;
import org.longbox.domainobjects.entity.StarRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

public class StarRatingDto {
	private long id;
	private long userId;
    private long comicBookId;
    private int rating;
    private UserDto user;
    private ComicBookDto comicBook;

    public StarRatingDto(int rating, UserDto user, ComicBookDto comicBook) {
    	this.rating = rating;
    	this.comicBookId = comicBook.getId();
    	this.userId = user.getId();
    	this.user = user;
    	this.comicBook = comicBook;
    }

    public StarRatingDto(long userId, long comicBookId, int rating) {
    	this.userId = userId;
    	this.comicBookId = comicBookId;
    	this.rating = rating;
    }

	public StarRatingDto(int rating, String comicBook) {
	    this.rating = rating;
	    this.user = new UserDto();
	    this.comicBook = new ComicBookDto();
	}
	
	public StarRatingDto(StarRating s) {
	    this.id = s.getId();
	    this.rating = s.getRating();
	    this.userId = s.getUserId();
	    this.comicBookId = s.getComicBookId();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(comicBookId, id, rating, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StarRatingDto other = (StarRatingDto) obj;
		return comicBookId == other.comicBookId && id == other.id && Objects.equals(rating, other.rating)
				&& userId == other.userId;
	}
	
	
	@Override
	public String toString() {
		return "StarRatingDTO [id=" + id + ", userId=" + userId + ", comicBookId=" + comicBookId + ", rating=" + rating
				+ ", user=" + user + ", comicBook=" + comicBook
				+ "]";
	}
}
