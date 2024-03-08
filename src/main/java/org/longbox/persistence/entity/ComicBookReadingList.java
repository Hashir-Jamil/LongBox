package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comic_book_reading_list")
@Getter
@Setter
@NoArgsConstructor
public class ComicBookReadingList {
    @EmbeddedId
    private ComicBookListId id = new ComicBookListId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("comicBookId")
    @JoinColumn(name = "comic_book_id",
            insertable = false, updatable = false)
    private ComicBook comicBook;

    @Column(name = "date_started")
    private Date dateAdded;

    public ComicBookReadingList(
            User user,
            ComicBook comicBook) {
        this.user = user;
        this.comicBook = comicBook;
        this.id.setUserId(user.getId());
        this.id.setComicBookId(comicBook.getId());
    }
}
