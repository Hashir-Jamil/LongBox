package org.longbox.domainobjects.dto;

import org.longbox.persistence.entity.Comment;

import java.util.Date;
import java.util.Objects;
public class CommentDTO {

	private long id;
    private long userId;
    private long comicBookId;
    private String message;
    private String username;
    private UserDTO user;
    private ComicBookDTO comicBook;
    private Date dateAdded;

    public CommentDTO(String message, UserDTO user, ComicBookDTO comicBook) {
        this.message = message;
        this.username = user.getUserName();
        this.comicBookId = comicBook.getId();
        this.userId = user.getId();
        this.user = user;
        this.comicBook = comicBook;
        dateAdded = new Date();
    }

    public CommentDTO(long userId, long comicBookId, String message, String username) {
        this.userId = userId;
        this.comicBookId = comicBookId;
        this.message = message;
        this.username = username;
        this.dateAdded = new Date();
    }

    public CommentDTO(String message, String userName, String comicBook) {
        this.message = message;
        this.user = new UserDTO();
        this.comicBook = new ComicBookDTO();
    }

    public CommentDTO(Comment c) {
        this.message = c.getMessage();
        this.userId = c.getUserId();
        this.username = c.getUserName();
        this.id = c.getId();
        this.comicBookId = c.getComicBookId();
        this.dateAdded = c.getCommentDate();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getComicBookId() {
        return comicBookId;
    }

    public void setComicBookId(long comicBookId) {
        this.comicBookId = comicBookId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ComicBookDTO getComicBook() {
        return comicBook;
    }

    public void setComicBook(ComicBookDTO comicBook) {
        this.comicBook = comicBook;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUsername(){ return this.username; }
    
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
		CommentDTO other = (CommentDTO) obj;
		return comicBookId == other.comicBookId && id == other.id && Objects.equals(message, other.message)
				&& userId == other.userId && Objects.equals(username, other.username);
	}
	
	
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", userId=" + userId + ", comicBookId=" + comicBookId + ", message=" + message
				+ ", username=" + username + ", user=" + user + ", comicBook=" + comicBook + ", dateAdded=" + dateAdded
				+ "]";
	}

    
}
