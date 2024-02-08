package org.longbox.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "commend_date")
    private String commentDate;

    @Column(name = "comic_book_id")
    private long comicBookId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

}
