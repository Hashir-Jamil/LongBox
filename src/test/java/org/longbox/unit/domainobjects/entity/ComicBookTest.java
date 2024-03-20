package org.longbox.unit.domainobjects.entity;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class ComicBookTest {

    @Test
    public void testConstructorWithAllFields() {
        Date currentDate = new Date();
        ComicBook comicBook = new ComicBook(
                "Series Title",
                "Author",
                "Artist",
                "Genre1, Genre2",
                "Description",
                10,
                "Publisher",
                2022,
                currentDate
        );

        assertEquals("Series Title", comicBook.getSeriesTitle());
        assertEquals("Author", comicBook.getAuthor());
        assertEquals("Artist", comicBook.getArtist());
        assertEquals("Genre1, Genre2", comicBook.getGenres());
        assertEquals("Description", comicBook.getDescription());
        assertEquals(10, comicBook.getNumberOfIssues());
        assertEquals("Publisher", comicBook.getPublisher());
        assertEquals(2022, comicBook.getYearPublished());
        assertEquals(currentDate, comicBook.getDateAdded());
    }

    @Test
    public void testConstructorWithDto() {
        ComicBookDto comicBookDto = new ComicBookDto(
                "Series Title",
                "Author",
                "Artist",
                new String[]{"Genre1", "Genre2"},
                "Description",
                10,
                "Publisher",
                2022
        );
        ComicBook comicBook = new ComicBook(comicBookDto);

        assertEquals("Series Title", comicBook.getSeriesTitle());
        assertEquals("Author", comicBook.getAuthor());
        assertEquals("Artist", comicBook.getArtist());
        assertEquals("Genre1, Genre2", comicBook.getGenres());
        assertEquals("Description", comicBook.getDescription());
        assertEquals(10, comicBook.getNumberOfIssues());
        assertEquals("Publisher", comicBook.getPublisher());
        assertEquals(2022, comicBook.getYearPublished());
        assertNotNull(comicBook.getDateAdded());
    }

    @Test
    public void testToString() {
        Date currentDate = new Date();
        ComicBook comicBook = new ComicBook(
                "Series Title",
                "Author",
                "Artist",
                "Genre1,Genre2",
                "Description",
                10,
                "Publisher",
                2022,
                currentDate
        );

        String expectedToString = "ComicBook{id=null, seriesTitle='Series Title', author='Author', artist='Artist', genres='Genre1,Genre2', description='Description', numberOfIssues=10, publisher='Publisher', yearPublished=2022, dateAdded=" + currentDate + ", northAmericaFavoritesCount=0, southAmericaFavoritesCount=0, europeFavoritesCount=0, asiaFavoritesCount=0, africaFavoritesCount=0, oceaniaFavoritesCount=0, antarcticaFavoritesCount=0, favoritesCount=0}";
        assertEquals(expectedToString, comicBook.toString());
    }

    @Test
    public void testToString2() {
        ComicBook comicBook = new ComicBook();
        comicBook.setId(1L);
        comicBook.setSeriesTitle("Series Title");
        comicBook.setAuthor("Author");
        comicBook.setArtist("Artist");
        comicBook.setGenres("Genre1,Genre2");
        comicBook.setDescription("Description");
        comicBook.setNumberOfIssues(10);
        comicBook.setPublisher("Publisher");
        comicBook.setYearPublished(2022);
        comicBook.setDateAdded(new Date());
        comicBook.setNorthAmericaFavoritesCount(1);
        comicBook.setSouthAmericaFavoritesCount(2);
        comicBook.setEuropeFavoritesCount(3);
        comicBook.setAsiaFavoritesCount(4);
        comicBook.setAfricaFavoritesCount(5);
        comicBook.setOceaniaFavoritesCount(6);
        comicBook.setAntarcticaFavoritesCount(7);
        comicBook.setFavoritesCount(8);

        String expectedToString = "ComicBook{id=1, seriesTitle='Series Title', author='Author', artist='Artist', genres='Genre1,Genre2', description='Description', numberOfIssues=10, publisher='Publisher', yearPublished=2022, dateAdded=" + comicBook.getDateAdded() + ", northAmericaFavoritesCount=1, southAmericaFavoritesCount=2, europeFavoritesCount=3, asiaFavoritesCount=4, africaFavoritesCount=5, oceaniaFavoritesCount=6, antarcticaFavoritesCount=7, favoritesCount=8}";
        assertEquals(expectedToString, comicBook.toString());
    }

}
