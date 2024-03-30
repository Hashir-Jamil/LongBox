package org.longbox.domainobjects.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comic_book_finished_list")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBookFinishedList that = (ComicBookFinishedList) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ComicBookFinishedList{" +
                "id=" + id +
                '}';
    }

    public ComicBookFinishedList(User user, ComicBook comicBook) {
        this.user = user;
        this.comicBook = comicBook;
        this.id.setUserId(user.getId());
        this.id.setComicBookId(comicBook.getId());
        this.dateAdded = new Date();
    }
}
