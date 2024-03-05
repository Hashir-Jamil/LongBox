package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comic_book_finished_list")
@Getter
@Setter
public class ComicBookFinishedList {
    @EmbeddedId
    private ComicBookListId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("comicBookId")
    private ComicBook comicBook;

    @Column(name = "date_added_user_list")
    private Date dateAdded;
}
