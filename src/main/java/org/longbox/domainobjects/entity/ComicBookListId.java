package org.longbox.domainobjects.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComicBookListId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "comic_book_id")
    private Long comicBookId;
}
