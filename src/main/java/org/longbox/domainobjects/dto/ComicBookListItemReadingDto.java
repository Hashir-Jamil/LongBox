package org.longbox.domainobjects.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ComicBookListItemReadingDto extends ComicBookListItemDto {

    public ComicBookListItemReadingDto(Long userId, Long comicBookId) {
        super(userId, comicBookId);
    }
}
