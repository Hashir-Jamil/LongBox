package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "comic_book_favorites_list")
public class ComicBookFavoritesList {
    @EmbeddedId
    private ComicBookListId id = new ComicBookListId();;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",
            insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("comicBookId")
    @JoinColumn(name = "comic_book_id",
            insertable = false, updatable = false)
    private ComicBook comicBook;

    @Column(name = "date_added_user_list")
    private Date dateAdded;

    public ComicBookFavoritesList(User u, ComicBook cb) {
        this.user = u;
        this.comicBook = cb;
        this.id.setUserId(this.user.getId());
        this.id.setComicBookId(this.comicBook.getId());
        this.dateAdded = new Date();
    }
}

