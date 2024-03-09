package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.entity.ComicBook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComicBookDaoImplTest {

    ComicBook comicBook, comicBook2;
    ComicBookDto comicBookDto1, comicBookDto2;
    ComicBookDaoImpl comicBookDaoImpl;

    @BeforeEach
    void init(){
        comicBookDaoImpl = new ComicBookDaoImpl();

        comicBookDto1 = new ComicBookDto(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984,
                new Date()
        );

        comicBookDto2 = new ComicBookDto(
                "The Amazing Spider-Man",
                "Stan Lee",
                "Steve Ditko",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Most famous superhoero of all time",
                900,
                "Marvel Comics",
                1963,
                new Date()
        );

        comicBook = new ComicBook(
                comicBookDto1.getSeriesTitle(),
                comicBookDto1.getAuthor(),
                comicBookDto1.getArtist(),
                ComicBookDto.genreListToString(comicBookDto1.getGenres()),
                comicBookDto1.getDescription(),
                comicBookDto1.getNumberOfIssues(),
                comicBookDto1.getPublisher(),
                comicBookDto1.getYearPublished(),
                comicBookDto1.getDateAdded()
        );

        comicBook2 = new ComicBook(
                comicBookDto2.getSeriesTitle(),
                comicBookDto2.getAuthor(),
                comicBookDto2.getArtist(),
                ComicBookDto.genreListToString(comicBookDto2.getGenres()),
                comicBookDto2.getDescription(),
                comicBookDto2.getNumberOfIssues(),
                comicBookDto2.getPublisher(),
                comicBookDto2.getYearPublished(),
                comicBookDto2.getDateAdded()
        );

    }

    @Test
    void getAllComicsTestBasic() {
        List<ComicBookDto> comicBookRecordsDTO = new ArrayList<>();
        comicBookRecordsDTO = comicBookDaoImpl.getAllComicBooks();
        assertTrue(comicBookRecordsDTO.size() > 0);
    }

    @Test
    void saveComicBook() {
        comicBookDaoImpl.saveComicBook(comicBookDto2);
    }

    @Test
    void getComicBookBySeriesNameTest() {
        ComicBook spiderMan = comicBookDaoImpl.getComicBookBySeriesName("The Amazing Spider-Man");
        assertEquals(comicBookDto2.getSeriesTitle(), spiderMan.getSeriesTitle());
        assertEquals(comicBookDto2.getAuthor(), spiderMan.getAuthor());
        assertEquals(comicBookDto2.getArtist(), spiderMan.getArtist());
        assertEquals(ComicBookDto.genreListToString(comicBookDto2.getGenres()), spiderMan.getGenres());
        assertEquals(comicBookDto2.getDescription(), spiderMan.getDescription());
        assertEquals(comicBookDto2.getNumberOfIssues(), spiderMan.getNumberOfIssues());
        assertEquals(comicBookDto2.getPublisher(), spiderMan.getPublisher());
        assertEquals(comicBookDto2.getYearPublished(), spiderMan.getYearPublished());
    }
}
