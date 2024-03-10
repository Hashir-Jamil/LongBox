package org.longbox.domainobjects.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class ComicBookListItemFavoriteDto extends ComicBookListItemDto {

    public ComicBookListItemFavoriteDto(Long userId, Long comicBookId) {
        super(userId, comicBookId);
    }
}
