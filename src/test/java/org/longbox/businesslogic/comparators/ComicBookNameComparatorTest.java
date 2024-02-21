package org.longbox.businesslogic.comparators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.businesslogic.comparators.ComicBookNameComparator;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookNameComparatorTest {

    ComicBookNameComparator comicBookNameComparator = new ComicBookNameComparator();
    ComicBookDTO comicBook1;
    ComicBookDTO comicBook2;
    ComicBookDTO comicBook3;
    ComicBookDTO comicBook4;
    ComicBookDTO comicBook5;
    ComicBookDTO comicBook6;
    ComicBookDTO comicBook7;
    ComicBookDTO comicBook8;

    @BeforeEach
    public void setup() {

        comicBook1 = new ComicBookDTO(
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

        comicBook2 = new ComicBookDTO(
                "Sanctuary",
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[] {"Polital", "Crime", "Thriller", "Manga"},
                "Description",
                108,
                "Viz",
                1990,
                false
        );

        comicBook3 = new ComicBookDTO(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[] {"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981,
                false
        );

        comicBook4 = new ComicBookDTO(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[] {"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993,
                false
        );

        comicBook5 = new ComicBookDTO(
                "",
                "Scott McCloud",
                "Scott McCloud",
                new String[]{"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984,
                false
        );

        comicBook6 = new ComicBookDTO(
                null,
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[]{"Political",
                        "Crime", "Thriller", "Manga"},
                "Description", 108,
                "Viz", 1990,
                false
        );

        comicBook7 = new ComicBookDTO(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[]{"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981,
                false
        );

        comicBook8 = new ComicBookDTO(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[]{"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993,
                false
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
