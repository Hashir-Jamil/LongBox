package org.longbox.unit.businesslogic.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.comparators.ComicBookYearPublishedComparator;
import org.longbox.domainobjects.dto.ComicBookDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComicBookYearPublishedComparatorTest {

    ComicBookDto comicBook1;
    ComicBookDto comicBook2;
    ComicBookDto comicBook3;
    ComicBookDto comicBook4;
    ComicBookDto comicBook5;
    ComicBookDto comicBook6;
    ComicBookDto comicBook7;
    ComicBookDto comicBook8;
    ComicBookYearPublishedComparator comparator;

    @BeforeEach
    public void setup() {
        comparator = new ComicBookYearPublishedComparator();

        comicBook1 = new ComicBookDto(
                "Batman",
                "Frank Miller",
                "David Mazzucchelli",
                new String[]{"Action", "Crime"},
                "A classic Batman story",
                4,
                "DC Comics",
                1987
        );

        comicBook2 = new ComicBookDto(
                "Spider-Man",
                "Stan Lee",
                "Steve Ditko",
                new String[]{"Action", "Adventure"},
                "The first appearance of Spider-Man",
                1,
                "Marvel Comics",
                1962
        );

        comicBook3 = new ComicBookDto(
                "X-Men",
                "Chris Claremont",
                "Jim Lee",
                new String[]{"Action", "Sci-Fi"},
                "The iconic '90s X-Men series",
                30,
                "Marvel Comics",
                1991
        );

        comicBook4 = new ComicBookDto(
                "Wonder Woman",
                "George Perez",
                "George Perez",
                new String[]{"Action", "Fantasy"},
                "Revitalizing Wonder Woman in the '80s",
                6,
                "DC Comics",
                1991
        );

        comicBook5 = new ComicBookDto(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[]{"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );

        comicBook6 = new ComicBookDto(
                "Sanctuary",
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[]{"Political",
                        "Crime", "Thriller", "Manga"},
                "Description",
                108,
                "Viz",
                1990
        );

        comicBook7 = new ComicBookDto(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[]{"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981
        );

        comicBook8 = new ComicBookDto(
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
        ComicBookDto otherComicBook1 = new ComicBookDto("Batman", "Frank Miller", "David Mazzucchelli", new String[]{"Action", "Crime"}, "A classic Batman story", 4, "DC Comics", 1987);
        int actual = comparator.compare(comicBook1, otherComicBook1);
        assertEquals(expected, actual);
    }

    @Test
    public void test_CompareDifferentObjects_DifferentYear() {
        int expected = 1;
        ComicBookDto otherComicBook1 = new ComicBookDto("Batman", "Frank Miller", "David Mazzucchelli", new String[]{"Action", "Crime"}, "A classic Batman story", 4, "DC Comics", 1986);
        int actual = comparator.compare(comicBook1, otherComicBook1);
        assertEquals(expected, actual);
    }


}
