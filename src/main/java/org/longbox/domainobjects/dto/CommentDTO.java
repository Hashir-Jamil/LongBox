package org.longbox.domainobjects.dto;

import java.util.Date;
public class CommentDTO {

    private long userId;
    private long comicBookId;
    private String message;
    private UserDTO user;
    private ComicBookDTO comicBook;
    private Date dateAdded;

    public CommentDTO(String message, UserDTO user, ComicBookDTO comicBook) {
        this.message = message;
        this.user = user;
        this.comicBook = comicBook;
        dateAdded = new Date();
    }

    public CommentDTO(String message, String userName, String comicBook) {
        this.message = message;
        this.user = new UserDTO();
        this.comicBook = new ComicBookDTO();
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
}
