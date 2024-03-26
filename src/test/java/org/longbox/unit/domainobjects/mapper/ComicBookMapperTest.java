package org.longbox.unit.domainobjects.mapper;

import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.mapper.ComicBookMapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ComicBookMapperTest {

    @Test
    public void testToDto() {
        ComicBook entity = new ComicBook();
        entity.setId(0L);
        entity.setSeriesTitle("Series Title");
        entity.setAuthor("Author");
        entity.setArtist("Artist");
        entity.setGenres("Genre1,Genre2");
        entity.setDescription("Description");
        entity.setNumberOfIssues(10);
        entity.setPublisher("Publisher");
        entity.setYearPublished(2022);
        entity.setDateAdded(new Date());

        ComicBookDto dto = ComicBookMapper.toDto(entity);
        assertEquals(0L,dto.getId());
        assertEquals("Series Title", dto.getSeriesTitle());
        assertEquals("Author", dto.getAuthor());
        assertEquals("Artist", dto.getArtist());
        assertArrayEquals(new String[]{"Genre1", "Genre2"}, dto.getGenres());
        assertEquals("Description", dto.getDescription());
        assertEquals(10, dto.getNumberOfIssues());
        assertEquals("Publisher", dto.getPublisher());
        assertEquals(2022, dto.getYearPublished());
    }

    @Test
    public void testToEntity() {
        ComicBookDto dto = new ComicBookDto();
        dto.setSeriesTitle("Series Title");
        dto.setAuthor("Author");
        dto.setArtist("Artist");
        dto.setGenres(new String[]{"Genre1", "Genre2"});
        dto.setDescription("Description");
        dto.setNumberOfIssues(10);
        dto.setPublisher("Publisher");
        dto.setYearPublished(2022);

        ComicBook entity = ComicBookMapper.toEntity(dto);

        assertEquals("Series Title", entity.getSeriesTitle());
        assertEquals("Author", entity.getAuthor());
        assertEquals("Artist", entity.getArtist());
        assertEquals("Genre1, Genre2", entity.getGenres());
        assertEquals("Description", entity.getDescription());
        assertEquals(10, entity.getNumberOfIssues());
        assertEquals("Publisher", entity.getPublisher());
        assertEquals(2022, entity.getYearPublished());
    }

    @Test
    public void testToDtoList() {
        List<ComicBook> entityList = new ArrayList<>();
        ComicBook comicBook = new ComicBook();
        comicBook.setId(0L);
        comicBook.setSeriesTitle("Series Title 1");
        comicBook.setAuthor("Author 1");
        comicBook.setArtist("Artist 1");
        comicBook.setGenres("Genre1,Genre2");
        comicBook.setDescription("Description 1");
        comicBook.setNumberOfIssues(10);
        comicBook.setPublisher("Publisher 1");
        comicBook.setYearPublished(2022);
        comicBook.setDateAdded(new Date());
        entityList.add(comicBook);

        ComicBook comicBook2 = new ComicBook();
        comicBook2.setId(1L);
        comicBook2.setSeriesTitle("Series Title 2");
        comicBook2.setAuthor("Author 2");
        comicBook2.setArtist("Artist 2");
        comicBook2.setGenres("Genre3,Genre4");
        comicBook2.setDescription("Description 2");
        comicBook2.setNumberOfIssues(20);
        comicBook2.setPublisher("Publisher 2");
        comicBook2.setYearPublished(2023);
        comicBook2.setDateAdded(new Date());
        entityList.add(comicBook2);

        List<ComicBookDto> dtoList = ComicBookMapper.toDtoList(entityList);

        assertEquals(2, dtoList.size());
        assertEquals("Series Title 1", dtoList.get(0).getSeriesTitle());
        assertEquals("Author 1", dtoList.get(0).getAuthor());
        assertEquals("Artist 1", dtoList.get(0).getArtist());
        assertArrayEquals(new String[]{"Genre1", "Genre2"}, dtoList.get(0).getGenres());
        assertEquals("Description 1", dtoList.get(0).getDescription());
        assertEquals(10, dtoList.get(0).getNumberOfIssues());
        assertEquals("Publisher 1", dtoList.get(0).getPublisher());
        assertEquals(2022, dtoList.get(0).getYearPublished());

        assertEquals("Series Title 2", dtoList.get(1).getSeriesTitle());
        assertEquals("Author 2", dtoList.get(1).getAuthor());
        assertEquals("Artist 2", dtoList.get(1).getArtist());
        assertArrayEquals(new String[]{"Genre3", "Genre4"}, dtoList.get(1).getGenres());
        assertEquals("Description 2", dtoList.get(1).getDescription());
        assertEquals(20, dtoList.get(1).getNumberOfIssues());
        assertEquals("Publisher 2", dtoList.get(1).getPublisher());
        assertEquals(2023, dtoList.get(1).getYearPublished());
    }

    @Test
    public void testToEntityList() {
        List<ComicBookDto> dtoList = new ArrayList<>();

        ComicBookDto cb1 = new ComicBookDto();
        cb1.setSeriesTitle("Series Title 1");
        cb1.setAuthor("Author 1");
        cb1.setArtist("Artist 1");
        cb1.setGenres(new String[]{"Genre1", "Genre2"});
        cb1.setDescription("Description 1");
        cb1.setNumberOfIssues(10);
        cb1.setPublisher("Publisher 1");
        cb1.setYearPublished(2022);
        dtoList.add(cb1);

        ComicBookDto cb2 = new ComicBookDto();
        cb2.setSeriesTitle("Series Title 2");
        cb2.setAuthor("Author 2");
        cb2.setArtist("Artist 2");
        cb2.setGenres(new String[]{"Genre3", "Genre4"});
        cb2.setDescription("Description 2");
        cb2.setNumberOfIssues(20);
        cb2.setPublisher("Publisher 2");
        cb2.setYearPublished(2023);
        dtoList.add(cb2);

        List<ComicBook> entityList = ComicBookMapper.toEntityList(dtoList);

        assertEquals(2, entityList.size());
        assertEquals("Series Title 1", entityList.get(0).getSeriesTitle());
        assertEquals("Author 1", entityList.get(0).getAuthor());
        assertEquals("Artist 1", entityList.get(0).getArtist());
        assertEquals("Genre1, Genre2", entityList.get(0).getGenres());
        assertEquals("Description 1", entityList.get(0).getDescription());
        assertEquals(10, entityList.get(0).getNumberOfIssues());
        assertEquals("Publisher 1", entityList.get(0).getPublisher());
        assertEquals(2022, entityList.get(0).getYearPublished());

        assertEquals("Series Title 2", entityList.get(1).getSeriesTitle());
        assertEquals("Author 2", entityList.get(1).getAuthor());
        assertEquals("Artist 2", entityList.get(1).getArtist());
        assertEquals("Genre3, Genre4", entityList.get(1).getGenres());
        assertEquals("Description 2", entityList.get(1).getDescription());
        assertEquals(20, entityList.get(1).getNumberOfIssues());
        assertEquals("Publisher 2", entityList.get(1).getPublisher());
        assertEquals(2023, entityList.get(1).getYearPublished());
    }
}
