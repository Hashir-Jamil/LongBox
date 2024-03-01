package org.longbox.persistence.entity;

import jakarta.persistence.*;
import org.longbox.domainobjects.dto.CommentDTO;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "comment_date")
    private Date commentDate;

    @Column(name = "comic_book_id")
    private long comicBookId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;
    
    public Comment() {}

    public Comment(String message, long comicBookId, long userId, String userName) {
        this.message = message;
        this.comicBookId = comicBookId;
        this.userId = userId;
        this.userName = userName;
        this.commentDate = new Date();
    }

    public Comment(CommentDTO c){
        this(c.getMessage(),
                c.getComicBookId(),
                c.getUserId(),
                c.getUsername()
        );
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public long getComicBookId() {
        return comicBookId;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
