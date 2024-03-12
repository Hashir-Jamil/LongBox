package org.longbox.unit.persistence.stubdatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.stubdatabase.ComicBookStubDb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookStubDbTest {

    ComicBookStubDb comicBookStubDao;
    List<ComicBookDto> comics;
    ComicBookDto c1;

    @BeforeEach
    void setup() {
        comicBookStubDao = new ComicBookStubDb();
        comics = comicBookStubDao.deserializeStubData(comicBookStubDao.getABSOLUTE_FILE_PATH());
        c1 = new ComicBookDto(
            1L,
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
            "Description",
            36,
            "Eclipse",
            1984
        );
    }

    @Test
    void serializeTest() {
        comicBookStubDao.setComicBookStubData(comicBookStubDao.deserializeStubData(comicBookStubDao.getABSOLUTE_FILE_PATH()));
        comicBookStubDao.serializeStubData();
    }

    @Test
    void deserializeTest() {
        assertNotNull(comics);
        assertEquals(38,comics.size());
        assertEquals(comics.get(0),c1);
        assertNotEquals(comics.get(1),c1);
        Set<Long> ids = new HashSet<>();
        for (ComicBookDto comic : comics) {
            ids.add(comic.getId());
        }
        assertEquals(38,ids.size());
    }

    @Test
    void getComicBookByIdTest() {
        assertEquals(c1.getId(),comicBookStubDao.getComicBookById(1L).getId());
        assertEquals(c1.getAuthor(),comicBookStubDao.getComicBookById(1L).getAuthor());
        assertEquals(c1.getArtist(),comicBookStubDao.getComicBookById(1L).getArtist());
        assertEquals(c1.getSeriesTitle(),comicBookStubDao.getComicBookById(1L).getSeriesTitle());
        assertEquals(c1.getPublisher(),comicBookStubDao.getComicBookById(1L).getPublisher());
        assertEquals(c1.getYearPublished(),comicBookStubDao.getComicBookById(1L).getYearPublished());

        assertNull(comicBookStubDao.getComicBookById(100L).getId());
        assertNull(comicBookStubDao.getComicBookById(100L).getAuthor());
        assertNull(comicBookStubDao.getComicBookById(100L).getArtist());
        assertNull(comicBookStubDao.getComicBookById(100L).getSeriesTitle());
        assertNull(comicBookStubDao.getComicBookById(100L).getPublisher());
        assertEquals(0,comicBookStubDao.getComicBookById(100L).getYearPublished());
        assertNull(comicBookStubDao.getComicBookById(100L).getGenres());
        assertEquals(0,comicBookStubDao.getComicBookById(100L).getNumberOfIssues());
        assertEquals(0,comicBookStubDao.getComicBookById(100L).getFavoritesCount());
        assertNull(comicBookStubDao.getComicBookById(100L).getDescription());
        assertNull(comicBookStubDao.getComicBookById(100L).getDateAdded());
    }

    @Test
    void getComicBookBySeriesNameTest() {
        assertEquals(c1.getId(),comicBookStubDao.getComicBookBySeriesTitle("Zot!").getId());
        assertEquals(c1.getAuthor(),comicBookStubDao.getComicBookBySeriesTitle("Zot!").getAuthor());
        assertEquals(c1.getArtist(),comicBookStubDao.getComicBookBySeriesTitle("Zot!").getArtist());
        assertEquals(c1.getSeriesTitle(),comicBookStubDao.getComicBookBySeriesTitle("Zot!").getSeriesTitle());
        assertEquals(c1.getPublisher(),comicBookStubDao.getComicBookBySeriesTitle("Zot!").getPublisher());
        assertEquals(c1.getYearPublished(),comicBookStubDao.getComicBookBySeriesTitle("Zot!").getYearPublished());

        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getId());
        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getAuthor());
        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getArtist());
        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getSeriesTitle());
        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getPublisher());
        assertEquals(0,comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getYearPublished());
        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getGenres());
        assertEquals(0,comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getNumberOfIssues());
        assertEquals(0,comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getFavoritesCount());
        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getDescription());
        assertNull(comicBookStubDao.getComicBookBySeriesTitle("Not Zot!").getDateAdded());
    }
}
