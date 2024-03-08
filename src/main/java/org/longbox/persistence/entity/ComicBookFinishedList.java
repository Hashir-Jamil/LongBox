package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comic_book_finished_list")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ComicBookFinishedList {
    @EmbeddedId
    private ComicBookListId id = new ComicBookListId();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            insertable = false, updatable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("comicBookId")
    @JoinColumn(
            name = "comic_book_id",
            insertable = false, updatable = false)
    private ComicBook comicBook;

    @Column(name = "date_finished")
    private Date dateAdded;

    public ComicBookFinishedList(User user, ComicBook comicBook) {
            this.user = user;
            this.comicBook = comicBook;
            this.id.setUserId(user.getId());
            this.id.setComicBookId(comicBook.getId());
            this.dateAdded = new Date();
    }

    public ComicBookFinishedList(Long userId, Long comicBookId) {
        this.id.setUserId(userId);
        this.id.setComicBookId(comicBookId);
        this.dateAdded = new Date();
    }
}
