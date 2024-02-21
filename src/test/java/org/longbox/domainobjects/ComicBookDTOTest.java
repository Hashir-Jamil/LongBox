package org.longbox.domainobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDTO;

import static org.junit.jupiter.api.Assertions.*;
public class ComicBookDTOTest {

    ComicBookDTO comicBook1 = new ComicBookDTO(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
            "Description",
            36,
            "Eclipse",
            1984,
            false
    );

    ComicBookDTO comicBook2 = new ComicBookDTO(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {},
            "Description",
            36,
            "Eclipse",
            1984,
            false
    );

    ComicBookDTO comicBook3 = new ComicBookDTO(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {"Superhero"},
            "Description",
            36,
            "Eclipse",
            1984,
            false
    );

    @Test
    public void multipleGenresListToString() {
        String expected = "Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama";
        assertEquals(expected, ComicBookDTO.genreListToString(comicBook1.getGenres()));
    }

    @Test
    public void onlyOneGenreListToString() {
        String expected = "Superhero";
        assertEquals(expected, ComicBookDTO.genreListToString(comicBook3.getGenres()));
    }

    @Test
    public void noGenresListToString() {
        String expected = "";
        assertEquals("", ComicBookDTO.genreListToString(comicBook2.getGenres()));
    }

    @Test
    public void multipleGenresStringToList() {
        String[] expected = {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"};
        String input = "Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama";
        assertArrayEquals(expected, ComicBookDTO.genreStringToList(input));
    }

    @Test void noGenresStringToList() {
        String[] expected = {""};
        assertArrayEquals(expected, ComicBookDTO.genreStringToList(""));
    }

    @Test void oneGenresStringToList() {
        String[] expected = {"Fantasy"};
        assertArrayEquals(expected, ComicBookDTO.genreStringToList("Fantasy"));
    }

}
