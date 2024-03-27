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
