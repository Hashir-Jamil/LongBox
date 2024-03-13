package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;

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

}
