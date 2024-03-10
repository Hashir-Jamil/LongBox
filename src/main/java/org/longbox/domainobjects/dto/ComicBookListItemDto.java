package org.longbox.domainobjects.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public abstract class ComicBookListItemDto {

    private Long comicBookId;
    private Long userId;
    private Date dateAdded;

}
