package org.longbox.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "comic_book_list")
public class ComicBookList {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Id
    @Column(name = "comic_book_id")
    private long comicBookId;

    @Column(name = "date_added")
    private Date dateAdded;


}
