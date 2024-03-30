package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookListItemFinishedDto;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComicBookListItemFinishedDtoTest {
    @Test
    void equalsAndHashTest() {
        ComicBookListItemFinishedDto dto = new ComicBookListItemFinishedDto(1L,1L);
        ComicBookListItemFinishedDto dto2 = new ComicBookListItemFinishedDto(1L,1L);
        assertEquals(dto,dto2);
        assertEquals(dto.hashCode(),dto2.hashCode());
    }

    @Test
    void testToString() {
        ComicBookListItemFinishedDto c = new ComicBookListItemFinishedDto();
        c.setUserId(1L);
        c.setComicBookId(1L);
        c.setDateAdded(new Date());
        String expected = "ComicBookListItemFinishedDto(super=ComicBookListItemDto{comicBookId=1, userId=1})";
        assertEquals(expected, c.toString());
    }
}
