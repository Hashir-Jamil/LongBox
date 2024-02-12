package org.longbox.domainobjects.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
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

}
