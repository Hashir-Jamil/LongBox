package org.longbox.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;

import static org.junit.jupiter.api.Assertions.*;
public class ComicBookServiceTest {
    ComicBookService service;

    @BeforeEach
    void setup() {
        service = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));
    }

    @Test
    void getComicBookBySeriesNameTest() {
        ComicBookDto comic = service.getComicBookBySeriesName("Zot!");
        ComicBookDto comicBook1 = new ComicBookDto();
        comicBook1.setSeriesTitle("Zot!");
        comicBook1.setAuthor("Scott McCloud");
        comicBook1.setArtist("Scott McCloud");
        comicBook1.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBook1.setDescription("Description");
        comicBook1.setNumberOfIssues(36);
        comicBook1.setPublisher("Eclipse");
        comicBook1.setYearPublished(1984);
        assertEquals(comicBook1, comic);
    }

    @Test
    void saveComicBookTest() {
        ComicBookDto comic = new ComicBookDto();
        comic.setSeriesTitle("Not!");
        comic.setAuthor("Scott McCloud");
        comic.setArtist("Scott McCloud");
        comic.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comic.setDescription("Description");
        comic.setNumberOfIssues(36);
        comic.setPublisher("Eclipse");
        comic.setYearPublished(1984);
        service.saveComicBook(comic);
    }

    @Test
    void getComicBookByIdTest() {
        ComicBookDto comicBook1 = new ComicBookDto();
        comicBook1.setSeriesTitle("Zot!");
        comicBook1.setAuthor("Scott McCloud");
        comicBook1.setArtist("Scott McCloud");
        comicBook1.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBook1.setDescription("Description");
        comicBook1.setNumberOfIssues(36);
        comicBook1.setPublisher("Eclipse");
        comicBook1.setYearPublished(1984);
        ComicBookDto comic = service.getComicBookById(1L);
        assertEquals(comicBook1, comic);
    }

    @Test
    void getAllComicTest() {
        service.getAllComicBook();
    }
}
