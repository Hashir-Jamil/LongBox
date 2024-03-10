package org.longbox.domainobjects.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ComicBookListItemReadingDto extends ComicBookListItemDto {

    public ComicBookListItemReadingDto(Long userId, Long comicBookId) {
        super(userId, comicBookId);
    }

}
