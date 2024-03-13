package org.longbox.domainobjects.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ComicBookListItemDto {
    private Long comicBookId;
    private Long userId;
    private Date dateAdded;

    public ComicBookListItemDto(Long userId, Long comicBookId) {
        this.userId = userId;
        this.comicBookId = comicBookId;
        this.dateAdded = new Date();
    }

}
