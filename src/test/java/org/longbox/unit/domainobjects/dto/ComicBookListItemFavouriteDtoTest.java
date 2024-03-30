package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookListItemFavouriteDto;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComicBookListItemFavouriteDtoTest {
    @Test
    void equalsAndHashTest() {
        ComicBookListItemFavouriteDto dto = new ComicBookListItemFavouriteDto(1L,1L);
        ComicBookListItemFavouriteDto dto2 = new ComicBookListItemFavouriteDto(1L,1L);
        assertEquals(dto,dto2);
        assertEquals(dto.hashCode(),dto2.hashCode());
    }

    @Test
    void testToString() {
        ComicBookListItemFavouriteDto c = new ComicBookListItemFavouriteDto();
        c.setUserId(1L);
        c.setComicBookId(1L);
        c.setDateAdded(new Date());
        String expected = "ComicBookListItemFavouriteDto(super=ComicBookListItemDto{comicBookId=1, userId=1})";
        assertEquals(expected, c.toString());
    }
}
