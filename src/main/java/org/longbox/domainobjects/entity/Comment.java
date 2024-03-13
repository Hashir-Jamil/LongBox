package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.longbox.domainobjects.dto.CommentDto;

import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "comment_date")
    private Date commentDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comic_book_id", referencedColumnName = "id")
    private ComicBook comicBook;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "user_name")
    private String userName;

    public Comment() {
    }

    public Comment(String message, ComicBook comicBook, User user, String userName) {
        this.message = message;
        this.comicBook = comicBook;
        this.user = user;
        this.userName = userName;
        this.commentDate = new Date();
    }

    public Comment(CommentDto c){
        this.user = new User(c.getUser());
        this.userName = c.getUser().getUserName();
        this.comicBook = new ComicBook(c.getComicBook());
        this.message = c.getMessage();
        this.commentDate = c.getCommentDate();
    }

    public Comment(CommentDto c, User u, ComicBook cb){
        this.user = u;
        this.userName = u.getUserName();
        this.comicBook = cb;
        this.message = c.getMessage();
        this.commentDate = c.getCommentDate();
    }

    public long getComicBookId(){
        return this.comicBook.getId();
    }

    public long getUserId(){
        return this.user.getId();
    }
}
