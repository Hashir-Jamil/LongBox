package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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

    public ComicBookReadingList(User user, ComicBook comicBook) {
        this.user = user;
        this.comicBook = comicBook;
        this.id.setUserId(user.getId());
        this.id.setComicBookId(comicBook.getId());
        this.dateAdded = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBookReadingList that = (ComicBookReadingList) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ComicBookReadingList{" +
                "id=" + id +
                '}';
    }
}
