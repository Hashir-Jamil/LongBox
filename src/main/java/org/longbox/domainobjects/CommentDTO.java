package org.longbox.domainobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class CommentDTO {

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

}
