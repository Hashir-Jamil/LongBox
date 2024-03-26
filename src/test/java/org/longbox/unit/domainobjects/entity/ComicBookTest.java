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

        String expectedToString = "ComicBook{id=null, seriesTitle='Series Title', author='Author', artist='Artist', genres='Genre1,Genre2', description='Description', numberOfIssues=10, publisher='Publisher', yearPublished=2022, dateAdded=" + currentDate + ", northAmericaFavouritesCount=0, southAmericaFavouritesCount=0, europeFavouritesCount=0, asiaFavouritesCount=0, africaFavouritesCount=0, oceaniaFavouritesCount=0, antarcticaFavouritesCount=0, favouritesCount=0}";
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
        comicBook.setNorthAmericaFavouritesCount(1);
        comicBook.setSouthAmericaFavouritesCount(2);
        comicBook.setEuropeFavouritesCount(3);
        comicBook.setAsiaFavouritesCount(4);
        comicBook.setAfricaFavouritesCount(5);
        comicBook.setOceaniaFavouritesCount(6);
        comicBook.setAntarcticaFavouritesCount(7);
        comicBook.setFavouritesCount(8);

        String expectedToString = "ComicBook{id=1, seriesTitle='Series Title', author='Author', artist='Artist', genres='Genre1,Genre2', description='Description', numberOfIssues=10, publisher='Publisher', yearPublished=2022, dateAdded=" + comicBook.getDateAdded() + ", northAmericaFavouritesCount=1, southAmericaFavouritesCount=2, europeFavouritesCount=3, asiaFavouritesCount=4, africaFavouritesCount=5, oceaniaFavouritesCount=6, antarcticaFavouritesCount=7, favouritesCount=8}";
        assertEquals(expectedToString, comicBook.toString());
    }
}
