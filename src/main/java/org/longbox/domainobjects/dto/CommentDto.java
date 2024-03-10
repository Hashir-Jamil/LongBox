package org.longbox.domainobjects.dto;

import lombok.Getter;
import lombok.Setter;
import org.longbox.persistence.entity.Comment;

import java.util.Date;
import java.util.Objects;
@Getter
@Setter
public class CommentDto {

	private long id;
    private long userId;
    private long comicBookId;
    private String message;
    private String username;
    private UserDto user;
    private ComicBookDto comicBook;
    private Date dateAdded;

    public CommentDto(String message, UserDto user, ComicBookDto comicBook) {
        this.message = message;
        this.username = user.getUserName();
        this.comicBookId = comicBook.getId();
        this.userId = user.getId();
        this.user = user;
        this.comicBook = comicBook;
        dateAdded = new Date();
    }

    public CommentDto(long userId, long comicBookId, String message, String username) {
        this.userId = userId;
        this.comicBookId = comicBookId;
        this.message = message;
        this.username = username;
        this.dateAdded = new Date();
    }

    public CommentDto(String message, String userName, String comicBook) {
        this.message = message;
        this.user = new UserDto();
        this.comicBook = new ComicBookDto();
    }

    public CommentDto(Comment c) {
        this.id = c.getId();
        this.message = c.getMessage();
        this.userId = c.getUserId();
        this.username = c.getUserName();
        this.comicBookId = c.getComicBookId();
        this.dateAdded = c.getCommentDate();
        this.comicBookId = c.getComicBookId();
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(comicBookId, id, message, userId, username);
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
				&& userId == other.userId && Objects.equals(username, other.username);
	}
	
	
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", userId=" + userId + ", comicBookId=" + comicBookId + ", message=" + message
				+ ", username=" + username + ", user=" + user + ", comicBook=" + comicBook + ", dateAdded=" + dateAdded
				+ "]";
	}

    public String getUserName() {
        return this.username;
    }

    public Date getCommentDate() {
        return this.dateAdded;
    }
}
