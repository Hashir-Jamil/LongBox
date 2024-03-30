package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comic_book_favourites_list")
public class ComicBookFavouritesList {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBookFavouritesList that = (ComicBookFavouritesList) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ComicBookFavouritesList{" +
                "id=" + id +
                '}';
    }
    public ComicBookFavouritesList(User u, ComicBook cb) {
        this.user = u;
        this.comicBook = cb;
        this.id.setUserId(this.user.getId());
        this.id.setComicBookId(this.comicBook.getId());
        this.dateAdded = new Date();
    }
}

