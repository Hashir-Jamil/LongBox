package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookDaoImplTest {

    ComicBook comicBook, comicBook2;
    ComicBookDto comicBookDto1, comicBookDto2;
    ComicBookDaoImpl comicBookDaoImpl;

    @BeforeEach
    void init(){
        comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

        comicBookDto1 = new ComicBookDto();
        comicBookDto1.setSeriesTitle("Zot!");
        comicBookDto1.setAuthor("Scott McCloud");
        comicBookDto1.setArtist("Scott McCloud");
        comicBookDto1.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBookDto1.setDescription("Description");
        comicBookDto1.setNumberOfIssues(36);
        comicBookDto1.setPublisher("Eclipse");
        comicBookDto1.setYearPublished(1984);
        comicBookDto1.setDateAdded(new Date());

        comicBookDto2 = new ComicBookDto();
        comicBookDto1.setSeriesTitle("Sanctuary");
        comicBookDto1.setAuthor("Sho Fumimura");
        comicBookDto1.setArtist("Ryoichi Ikegami");
        comicBookDto1.setGenres(new String[] {"Polital", "Crime", "Thriller", "Manga"});
        comicBookDto1.setDescription("Empty");
        comicBookDto1.setNumberOfIssues(108);
        comicBookDto1.setPublisher("Viz");
        comicBookDto1.setYearPublished(1990);
        comicBookDto1.setDateAdded(new Date());

        comicBook = new ComicBook(
                comicBookDto1.getSeriesTitle(),
                comicBookDto1.getAuthor(),
                comicBookDto1.getArtist(),
                GenreUtils.genreListToString(comicBookDto1.getGenres()),
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
                GenreUtils.genreListToString(comicBookDto2.getGenres()),
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
        assertFalse(comicBookRecordsDTO.isEmpty());
    }

    @Test
    void getComicBookBySeriesTitleTest() {
        ComicBook zot = comicBookDaoImpl.getComicBookBySeriesTitle("Zot!");
        assertEquals("Zot!", zot.getSeriesTitle());
        assertEquals("Scott McCloud", zot.getAuthor());
        assertEquals("Scott McCloud", zot.getArtist());
        assertEquals("Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama", zot.getGenres());
        assertEquals("Empty", zot.getDescription());
        assertEquals(36, zot.getNumberOfIssues());
        assertEquals("Eclipse", zot.getPublisher());
        assertEquals(1984, zot.getYearPublished());
    }

    @Test
    void saveComicBook() {
        comicBookDaoImpl.saveComicBook(new ComicBook(comicBookDto2));
    }

    @Test
    void getRecommendationsByGenresTest() {
        String[] genres = {"Adventure","Action","Political"};
        List<ComicBookDto> recommendations = comicBookDaoImpl.getRecommendationsByGenre(genres);
        assertTrue(recommendations.contains(comicBookDto1));
    }
}
