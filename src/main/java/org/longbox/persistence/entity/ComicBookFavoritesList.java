package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "comic_book_favorites_list")
public class ComicBookFavoritesList {
    @EmbeddedId
    private ComicBookListId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("comicBookId")
    @JoinColumn(name = "comic_book_id",
            insertable = false, updatable = false)
    private ComicBook comicBook;

    @Column(name = "date_added_user_list",
            insertable = false, updatable = false)
    private Date dateAdded;

    public ComicBookFavoritesList(User u, ComicBook cb) {
        this.id = new ComicBookListId(u.getId(), cb.getId());
        this.user = u;
        this.comicBook = cb;
        this.dateAdded = new Date();
    }
}

