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
    private String userName;
    private UserDto user;
    private ComicBookDto comicBook;
    private Date commentDate;

    public CommentDto(String message, UserDto user, ComicBookDto comicBook) {
        this.message = message;
        this.userName = user.getUserName();
        this.comicBookId = comicBook.getId();
        this.userId = user.getId();
        this.user = user;
        this.comicBook = comicBook;
        commentDate = new Date();
    }

    public CommentDto(long userId, long comicBookId, String message, String userName) {
        this.userId = userId;
        this.comicBookId = comicBookId;
        this.message = message;
        this.userName = userName;
        this.commentDate = new Date();
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
        this.userName = c.getUserName();
        this.comicBookId = c.getComicBookId();
        this.commentDate = c.getCommentDate();
        this.comicBookId = c.getComicBookId();
    }
    
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

    public String getUserName() {
        return this.userName;
    }

    public Date getCommentDate() {
        return this.commentDate;
    }
}
