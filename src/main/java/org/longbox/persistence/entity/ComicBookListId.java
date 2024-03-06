package org.longbox.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ComicBookListId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "comic_book_id")
    private Long comicBookId;

    public ComicBookListId() {}

    public ComicBookListId(Long userId, Long comicBookId) {
        this.userId = userId;
        this.comicBookId = comicBookId;
    }
}
