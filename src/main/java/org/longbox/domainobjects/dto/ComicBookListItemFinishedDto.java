package org.longbox.domainobjects.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ComicBookListItemFinishedDto extends ComicBookListItemDto {

    public ComicBookListItemFinishedDto(Long userId, Long comicBookId) {
        super(userId, comicBookId);
    }
}
