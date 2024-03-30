package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookListItemReadingDto;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComicBookListItemReadingDtoTest {
    @Test
    void equalsAndHashTest() {
        ComicBookListItemReadingDto dto = new ComicBookListItemReadingDto(1L,1L);
        ComicBookListItemReadingDto dto2 = new ComicBookListItemReadingDto(1L,1L);
        assertEquals(dto,dto2);
        assertEquals(dto.hashCode(),dto2.hashCode());
    }

    @Test
    void testToString() {
        ComicBookListItemReadingDto c = new ComicBookListItemReadingDto();
        c.setUserId(1L);
        c.setComicBookId(1L);
        c.setDateAdded(new Date());
        String expected = "ComicBookListItemReadingDto{comicBookId=1, userId=1}";
        assertEquals(expected, c.toString());
    }
}
