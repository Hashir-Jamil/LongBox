package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookListItemDto;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookListItemDtoTest {

    @Test
    void equalsAndHashTest() {
        ComicBookListItemDto dto = new ComicBookListItemDto(1L,1L);
        ComicBookListItemDto dto2 = new ComicBookListItemDto(1L,1L);
        assertEquals(dto,dto2);
        assertEquals(dto.hashCode(),dto2.hashCode());
    }

    @Test
    void testToString() {
        ComicBookListItemDto c = new ComicBookListItemDto();
        c.setUserId(1L);
        c.setComicBookId(1L);
        c.setDateAdded(new Date());
        String expected = "ComicBookListItemDto{comicBookId=1, userId=1}";
        assertEquals(expected, c.toString());
    }
}
