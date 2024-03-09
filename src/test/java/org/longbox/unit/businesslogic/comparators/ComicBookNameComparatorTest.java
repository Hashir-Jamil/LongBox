package org.longbox.unit.businesslogic.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.businesslogic.comparators.ComicBookNameComparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookNameComparatorTest {

    ComicBookNameComparator comicBookNameComparator = new ComicBookNameComparator();
    ComicBookDto comicBook1;
    ComicBookDto comicBook2;
    ComicBookDto comicBook3;
    ComicBookDto comicBook4;
    ComicBookDto comicBook5;
    ComicBookDto comicBook6;
    ComicBookDto comicBook7;
    ComicBookDto comicBook8;

    @BeforeEach
    public void setup() {

        comicBook1 = new ComicBookDto(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );

        comicBook2 = new ComicBookDto(
                "Sanctuary",
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[] {"Polital", "Crime", "Thriller", "Manga"},
                "Description",
                108,
                "Viz",
                1990
        );

        comicBook3 = new ComicBookDto(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[] {"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981
        );

        comicBook4 = new ComicBookDto(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[] {"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993
        );

        comicBook5 = new ComicBookDto(
                "",
                "Scott McCloud",
                "Scott McCloud",
                new String[]{"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );

        comicBook6 = new ComicBookDto(
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
    public void testEqual() {
        assertEquals(0, comicBookNameComparator.compare(comicBook3,comicBook7));
    }
    @Test
    public void testLessThan() {
        assertEquals(1, comicBookNameComparator.compare(comicBook1,comicBook8));
    }
    @Test
    public void testEmptyStringThrowsException() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> comicBookNameComparator.compare(comicBook5,comicBook2));
    }
    @Test
    public void testNullPointerException() {
        assertThrows(NullPointerException.class, () -> comicBookNameComparator.compare(comicBook6,comicBook4));
    }
    @Test
    public void testNullPointerException2() {
        assertThrows(NullPointerException.class, () -> comicBookNameComparator.compare(comicBook6,comicBook6));
    }

    @Test
    public void testGreaterThan() {
        assertEquals(-1, comicBookNameComparator.compare(comicBook4,comicBook1));
    }

    @Test
    public void testEqualsSelf() {
        assertEquals(0, comicBookNameComparator.compare(comicBook2,comicBook2));
    }

}
