package org.longbox.domainobjects.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class ComicBookListItemFavouriteDto extends ComicBookListItemDto {

    public ComicBookListItemFavouriteDto(Long userId, Long comicBookId) {
        super(userId, comicBookId);
    }
}
