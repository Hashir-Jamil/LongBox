package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class ComicBookDtoTest {

    ComicBookDto comicBook1 = new ComicBookDto(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
            "Description",
            36,
            "Eclipse",
            1984
    );

    ComicBookDto comicBook2 = new ComicBookDto(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {},
            "Description",
            36,
            "Eclipse",
            1984
    );

    ComicBookDto comicBook3 = new ComicBookDto(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {"Superhero"},
            "Description",
            36,
            "Eclipse",
            1984
    );

    @Test
    public void multipleGenresListToString() {
        String expected = "Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama";
        assertEquals(expected, GenreUtils.genreListToString(comicBook1.getGenres()));
    }

    @Test
    public void onlyOneGenreListToString() {
        String expected = "Superhero";
        assertEquals(expected, GenreUtils.genreListToString(comicBook3.getGenres()));
    }

    @Test
    public void noGenresListToString() {
        String expected = "";
        assertEquals("", GenreUtils.genreListToString(comicBook2.getGenres()));
    }

    @Test
    public void multipleGenresStringToList() {
        String[] expected = {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"};
        String input = "Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama";
        assertArrayEquals(expected, GenreUtils.genreStringToList(input));
    }

    @Test void noGenresStringToList() {
        String[] expected = {""};
        assertArrayEquals(expected, GenreUtils.genreStringToList(""));
    }

    @Test void oneGenresStringToList() {
        String[] expected = {"Fantasy"};
        assertArrayEquals(expected, GenreUtils.genreStringToList("Fantasy"));
    }

    @Test
    public void testConstructorWithAllFields() {
        ComicBookDto comicBookDto = new ComicBookDto(
                1,
                "Series Title",
                "Author",
                "Artist",
                new String[]{"Genre1", "Genre2"},
                "Description",
                10,
                "Publisher",
                2022
        );

        assertEquals(1, comicBookDto.getId());
        assertEquals("Series Title", comicBookDto.getSeriesTitle());
        assertEquals("Author", comicBookDto.getAuthor());
        assertEquals("Artist", comicBookDto.getArtist());
        assertArrayEquals(new String[]{"Genre1", "Genre2"}, comicBookDto.getGenres());
        assertEquals("Description", comicBookDto.getDescription());
        assertEquals(10, comicBookDto.getNumberOfIssues());
        assertEquals("Publisher", comicBookDto.getPublisher());
        assertEquals(2022, comicBookDto.getYearPublished());
    }

    @Test
    public void testEqualsAndHashCode() {
        ComicBookDto comicBookDto1 = new ComicBookDto(
                1,
                "Series Title",
                "Author",
                "Artist",
                new String[]{"Genre1", "Genre2"},
                "Description",
                10,
                "Publisher",
                2022
        );

        ComicBookDto comicBookDto2 = new ComicBookDto(
                1,
                "Series Title",
                "Author",
                "Artist",
                new String[]{"Genre1", "Genre2"},
                "Description",
                10,
                "Publisher",
                2022
        );

        assertTrue(comicBookDto1.equals(comicBookDto2));
        assertEquals(comicBookDto1.hashCode(), comicBookDto2.hashCode());
    }

    @Test
    public void testToString() {
        ComicBookDto comicBookDto = new ComicBookDto(
                1,
                "Series Title",
                "Author",
                "Artist",
                new String[]{"Genre1", "Genre2"},
                "Description",
                10,
                "Publisher",
                2022
        );

        String expectedToString = "ComicBookDTO{id=1, seriesTitle='Series Title', author='Author', artist='Artist', genres=[Genre1, Genre2], description='Description', numberOfIssues=10, publisher='Publisher', yearPublished=2022, dateAdded=" + comicBookDto.getDateAdded() + ", favoritesCount=" + comicBookDto.getFavoritesCount() + '}';
        assertEquals(expectedToString, comicBookDto.toString());
    }

    @Test
    public void testComicBookDtoConstructor() {
        ComicBook comicBookRecord = new ComicBook();
        comicBookRecord.setId(1L);
        comicBookRecord.setAuthor("Author");
        comicBookRecord.setSeriesTitle("Series Title");
        comicBookRecord.setArtist("Artist");
        comicBookRecord.setGenres("Genre1, Genre2");
        comicBookRecord.setDescription("Description");
        comicBookRecord.setNumberOfIssues(10);
        comicBookRecord.setPublisher("Publisher");
        comicBookRecord.setYearPublished(2022);
        comicBookRecord.setDateAdded(new Date());
        comicBookRecord.setNorthAmericaFavoritesCount(1);
        comicBookRecord.setSouthAmericaFavoritesCount(2);
        comicBookRecord.setEuropeFavoritesCount(3);
        comicBookRecord.setAsiaFavoritesCount(4);
        comicBookRecord.setAfricaFavoritesCount(5);
        comicBookRecord.setOceaniaFavoritesCount(6);
        comicBookRecord.setAntarcticaFavoritesCount(0);
        comicBookRecord.setFavoritesCount(21);

        ComicBookDto comicBookDto = new ComicBookDto(comicBookRecord);

        assertEquals(1L, comicBookDto.getId());
        assertEquals("Series Title", comicBookDto.getSeriesTitle());
        assertEquals("Author", comicBookDto.getAuthor());
        assertEquals("Artist", comicBookDto.getArtist());
        assertArrayEquals(new String[]{"Genre1", "Genre2"}, comicBookDto.getGenres());
        assertEquals("Description", comicBookDto.getDescription());
        assertEquals(10, comicBookDto.getNumberOfIssues());
        assertEquals("Publisher", comicBookDto.getPublisher());
        assertEquals(2022, comicBookDto.getYearPublished());
        assertEquals(comicBookRecord.getDateAdded(), comicBookDto.getDateAdded());
        assertEquals(1, comicBookDto.getNorthAmericaFavoritesCount());
        assertEquals(2, comicBookDto.getSouthAmericaFavoritesCount());
        assertEquals(3, comicBookDto.getEuropeFavoritesCount());
        assertEquals(4, comicBookDto.getAsiaFavoritesCount());
        assertEquals(5, comicBookDto.getAfricaFavoritesCount());
        assertEquals(6, comicBookDto.getOceaniaFavoritesCount());
        assertEquals(0, comicBookDto.getAntarcticaFavoritesCount());
        assertEquals(21, comicBookDto.getFavoritesCount());
    }
}
