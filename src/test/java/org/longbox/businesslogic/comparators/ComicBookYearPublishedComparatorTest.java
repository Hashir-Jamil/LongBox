package org.longbox.businesslogic.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComicBookYearPublishedComparatorTest {

    ComicBookDTO comicBook1;
    ComicBookDTO comicBook2;
    ComicBookDTO comicBook3;
    ComicBookDTO comicBook4;
    ComicBookDTO comicBook5;
    ComicBookDTO comicBook6;
    ComicBookDTO comicBook7;
    ComicBookDTO comicBook8;
    ComicBookYearPublishedComparator comparator;

    @BeforeEach
    public void setup() {
        comparator = new ComicBookYearPublishedComparator();

        comicBook1 = new ComicBookDTO(
                "Batman",
                "Frank Miller",
                "David Mazzucchelli",
                new String[]{"Action", "Crime"},
                "A classic Batman story",
                4,
                "DC Comics",
                1987
        );

        comicBook2 = new ComicBookDTO(
                "Spider-Man",
                "Stan Lee",
                "Steve Ditko",
                new String[]{"Action", "Adventure"},
                "The first appearance of Spider-Man",
                1,
                "Marvel Comics",
                1962
        );

        comicBook3 = new ComicBookDTO(
                "X-Men",
                "Chris Claremont",
                "Jim Lee",
                new String[]{"Action", "Sci-Fi"},
                "The iconic '90s X-Men series",
                30,
                "Marvel Comics",
                1991
        );

        comicBook4 = new ComicBookDTO(
                "Wonder Woman",
                "George Perez",
                "George Perez",
                new String[]{"Action", "Fantasy"},
                "Revitalizing Wonder Woman in the '80s",
                6,
                "DC Comics",
                1991
        );

        comicBook5 = new ComicBookDTO(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[]{"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );

        comicBook6 = new ComicBookDTO(
                "Sanctuary",
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[]{"Political",
                        "Crime", "Thriller", "Manga"},
                "Description", 108,
                "Viz", 1990
        );

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
    }

    @Test
    public void test_YearPublishedDifferent_1() {
        int expected = 1;
        int actual = comparator.compare(comicBook1, comicBook2);
        assertEquals(expected, actual);
    }

    @Test
    public void test_YearPublishedSame() {
        int expected = 0;
        int actual = comparator.compare(comicBook3, comicBook4);
        assertEquals(expected, actual);
    }

    @Test
    public void test_YearPublishedDifferent_2() {
        int expected = -1;
        int actual = comparator.compare(comicBook2, comicBook3);
        assertEquals(expected, actual);
    }
    @Test
    public void test_CompareSameObject() {
        int expected = 0;
        int actual = comparator.compare(comicBook1, comicBook1);
        assertEquals(expected, actual);
    }

    @Test
    public void test_CompareDifferentObjects_SameYear() {
        int expected = 0;
        ComicBookDTO otherComicBook1 = new ComicBookDTO("Batman", "Frank Miller", "David Mazzucchelli", new String[]{"Action", "Crime"}, "A classic Batman story", 4, "DC Comics", 1987);
        int actual = comparator.compare(comicBook1, otherComicBook1);
        assertEquals(expected, actual);
    }

    @Test
    public void test_CompareDifferentObjects_DifferentYear() {
        int expected = 1;
        ComicBookDTO otherComicBook1 = new ComicBookDTO("Batman", "Frank Miller", "David Mazzucchelli", new String[]{"Action", "Crime"}, "A classic Batman story", 4, "DC Comics", 1986);
        int actual = comparator.compare(comicBook1, otherComicBook1);
        assertEquals(expected, actual);
    }


}
