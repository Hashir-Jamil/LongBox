package org.longbox.businesslogic.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookSearchTest {

    ComicBookDTO comicBook1;
    ComicBookDTO comicBook2;
    ComicBookDTO comicBook3;
    ComicBookDTO comicBook4;
    ComicBookDTO comicBook5;
    ComicBookDTO comicBook6;
    ComicBookDTO comicBook7;
    ComicBookDTO comicBook8;
    List<ComicBookDTO> comicBookDTOList;

    @BeforeEach
    public void setup() {

        comicBookDTOList = new ArrayList<>();

        comicBook1 = new ComicBookDTO(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );
        comicBookDTOList.add(comicBook1);

        comicBook2 = new ComicBookDTO(
                "Sanctuary",
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[] {"Polital", "Crime", "Thriller", "Manga"},
                "Description",
                108,
                "Viz",
                1990
        );
        comicBookDTOList.add(comicBook2);

        comicBook3 = new ComicBookDTO(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[] {"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981
        );
        comicBookDTOList.add(comicBook3);

        comicBook4 = new ComicBookDTO(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[] {"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993
        );
        comicBookDTOList.add(comicBook4);

        comicBook5 = new ComicBookDTO(
                "",
                "Scott McCloud",
                "Scott McCloud",
                new String[]{"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );
        comicBookDTOList.add(comicBook5);

        comicBook6 = new ComicBookDTO(
                null,
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[]{"Political",
                        "Crime", "Thriller", "Manga"},
                "Description",
                108,
                "Viz",
                1990
        );
        comicBookDTOList.add(comicBook6);

        comicBook7 = new ComicBookDTO(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[]{"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981
        );
        comicBookDTOList.add(comicBook7);

        comicBook8 = new ComicBookDTO(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[]{"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993
        );
        comicBookDTOList.add(comicBook8);
    }

    @Test
    public void comicBookExactMatch() {
        ComicBookDTO expected = new ComicBookDTO(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[]{"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993
        );
        assertEquals(expected, ComicBookSearch.searchComicBook(comicBookDTOList, "The Maxx"));
    }

    @Test
    public void comicBookPartialMatch() {
        ComicBookDTO expected = new ComicBookDTO(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[]{"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981
        );
        assertEquals(expected, ComicBookSearch.searchComicBook(comicBookDTOList,"Nexus"));
    }

    @Test
    public void comicBookNoMatch() {
        ComicBookDTO expected = comicBook1 = new ComicBookDTO(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );
        assertNotEquals(expected, ComicBookSearch.searchComicBook(comicBookDTOList,"Nexus"));
    }

    @Test
    public void nullException() {
        List<ComicBookDTO> nullList = null;
        assertThrows(NullPointerException.class, () -> ComicBookSearch.searchComicBook(nullList, "Zot!"));
    }
}
