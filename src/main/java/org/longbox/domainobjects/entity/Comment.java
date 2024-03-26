package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.mapper.ComicBookMapper;
import org.longbox.domainobjects.mapper.UserMapper;

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

    public long getComicBookId(){
        return this.comicBook.getId();
    }

    public long getUserId(){
        return this.user.getId();
    }
}
