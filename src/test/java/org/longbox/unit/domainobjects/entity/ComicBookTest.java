package org.longbox.unit.domainobjects.entity;

import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.entity.ComicBook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class ComicBookTest {

    @Test
    public void testEqualsSameObject() {
        ComicBook comicBook = new ComicBook();
        assertTrue(comicBook.equals(comicBook));
    }

    @Test
    public void testEqualsNullObject() {
        ComicBook comicBook = new ComicBook();
        assertFalse(comicBook.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        ComicBook comicBook = new ComicBook();
        assertFalse(comicBook.equals("not a comic book"));
    }

    @Test
    public void testEqualsSameAttributes() {
        ComicBook comicBook1 = new ComicBook();
        comicBook1.setSeriesTitle("Series Title");
        comicBook1.setAuthor("Author");
        comicBook1.setArtist("Artist");
        comicBook1.setGenres("Genre1, Genre2");
        comicBook1.setPublisher("Publisher");
        comicBook1.setYearPublished(2022);

        ComicBook comicBook2 = new ComicBook();
        comicBook2.setSeriesTitle("Series Title");
        comicBook2.setAuthor("Author");
        comicBook2.setArtist("Artist");
        comicBook2.setGenres("Genre1, Genre2");
        comicBook2.setPublisher("Publisher");
        comicBook2.setYearPublished(2022);

        assertTrue(comicBook1.equals(comicBook2));
    }

    @Test
    public void testEqualsDifferentAttributes() {
        ComicBook comicBook1 = new ComicBook();
        comicBook1.setSeriesTitle("Series Title");
        comicBook1.setAuthor("Author");
        comicBook1.setArtist("Artist");
        comicBook1.setGenres("Genre1, Genre2");
        comicBook1.setPublisher("Publisher");
        comicBook1.setYearPublished(2022);

        ComicBook comicBook2 = new ComicBook();
        comicBook2.setSeriesTitle("Different Series Title");
        comicBook2.setAuthor("Different Author");
        comicBook2.setArtist("Different Artist");
        comicBook2.setGenres("Different Genre1, Different Genre2");
        comicBook2.setPublisher("Different Publisher");
        comicBook2.setYearPublished(2023);

        assertFalse(comicBook1.equals(comicBook2));
    }

    @Test
    public void testHashCodeSameAttributes() {
        ComicBook comicBook1 = new ComicBook();
        comicBook1.setSeriesTitle("Series Title");
        comicBook1.setAuthor("Author");
        comicBook1.setArtist("Artist");
        comicBook1.setGenres("Genre1, Genre2");
        comicBook1.setPublisher("Publisher");
        comicBook1.setYearPublished(2022);

        ComicBook comicBook2 = new ComicBook();
        comicBook2.setSeriesTitle("Series Title");
        comicBook2.setAuthor("Author");
        comicBook2.setArtist("Artist");
        comicBook2.setGenres("Genre1, Genre2");
        comicBook2.setPublisher("Publisher");
        comicBook2.setYearPublished(2022);

        assertEquals(comicBook1.hashCode(), comicBook2.hashCode());
    }

    @Test
    public void testHashCodeDifferentAttributes() {
        ComicBook comicBook1 = new ComicBook();
        comicBook1.setSeriesTitle("Series Title");
        comicBook1.setAuthor("Author");
        comicBook1.setArtist("Artist");
        comicBook1.setGenres("Genre1, Genre2");
        comicBook1.setPublisher("Publisher");
        comicBook1.setYearPublished(2022);

        ComicBook comicBook2 = new ComicBook();
        comicBook2.setSeriesTitle("Different Series Title");
        comicBook2.setAuthor("Different Author");
        comicBook2.setArtist("Different Artist");
        comicBook2.setGenres("Different Genre1, Different Genre2");
        comicBook2.setPublisher("Different Publisher");
        comicBook2.setYearPublished(2023);

        assertNotEquals(comicBook1.hashCode(), comicBook2.hashCode());
    }
    @Test
    public void testToString() {
        Date currentDate = new Date();
        ComicBook comicBook = new ComicBook();
        comicBook.setSeriesTitle("Series Title");
        comicBook.setAuthor("Author");
        comicBook.setArtist("Artist");
        comicBook.setGenres("Genre1,Genre2");
        comicBook.setDescription("Description");
        comicBook.setNumberOfIssues(10);
        comicBook.setPublisher("Publisher");
        comicBook.setYearPublished(2022);
        comicBook.setDateAdded(currentDate);

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
