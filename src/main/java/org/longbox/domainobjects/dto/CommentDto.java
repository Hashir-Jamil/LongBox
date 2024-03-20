package org.longbox.domainobjects.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.domainobjects.entity.Comment;

import java.util.Date;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	private long id;
    private long userId;
    private long comicBookId;
    private String message;
    private String userName;
    private UserDto user;
    private ComicBookDto comicBook;
    private Date commentDate;

    @Override
	public int hashCode() {
		return Objects.hash(comicBookId, id, message, userId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDto other = (CommentDto) obj;
		return comicBookId == other.comicBookId && id == other.id && Objects.equals(message, other.message)
				&& userId == other.userId && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", userId=" + userId + ", comicBookId=" + comicBookId + ", message=" + message
				+ ", username=" + userName + ", user=" + user + ", comicBook=" + comicBook + ", dateAdded=" + commentDate
				+ "]";
	}
}
