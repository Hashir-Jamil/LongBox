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
        ComicBook entity = new ComicBook(
                "Series Title",
                "Author",
                "Artist",
                "Genre1,Genre2",
                "Description",
                10,
                "Publisher",
                2022,
                new Date()
        );
        entity.setId(0L);

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
        ComicBookDto dto = new ComicBookDto(
                "Series Title",
                "Author",
                "Artist",
                new String[]{"Genre1", "Genre2"},
                "Description",
                10,
                "Publisher",
                2022
        );

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
        entityList.add(new ComicBook(
                "Series Title 1",
                "Author 1",
                "Artist 1",
                "Genre1,Genre2",
                "Description 1",
                10,
                "Publisher 1",
                2022,
                new Date()
        ));
        entityList.add(new ComicBook(
                "Series Title 2",
                "Author 2",
                "Artist 2",
                "Genre3,Genre4",
                "Description 2",
                20,
                "Publisher 2",
                2023,
                new Date()
        ));
        entityList.get(0).setId(0L);
        entityList.get(1).setId(1L);

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
        dtoList.add(new ComicBookDto(
                "Series Title 1",
                "Author 1",
                "Artist 1",
                new String[]{"Genre1", "Genre2"},
                "Description 1",
                10,
                "Publisher 1",
                2022
        ));
        dtoList.add(new ComicBookDto(
                "Series Title 2",
                "Author 2",
                "Artist 2",
                new String[]{"Genre3", "Genre4"},
                "Description 2",
                20,
                "Publisher 2",
                2023
        ));

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
