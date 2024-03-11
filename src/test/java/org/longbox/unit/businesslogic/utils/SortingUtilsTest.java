package org.longbox.unit.businesslogic.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.SortingUtils;
import org.longbox.domainobjects.dto.ComicBookDto;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

public class SortingUtilsTest {

    ComicBookDto comicBook1, comicBook2, comicBook3;
    @BeforeEach
    void init(){

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
                "Chronicles of Corum",
                "Mike Baron",
                "Mike Mignola",
                new String[] {"Action", "Adventure", "Sword and Socery"},
                "Description",
                12,
                "First Comics",
                1987
        );

        comicBook3 = new ComicBookDto(
                "Trekker (1988)",
                "Ron Randall",
                "Ron Randall",
                new String[] {"Action", "Crime", "Vigilantes", "Dystopian"},
                "Description",
                6,
                "Dark Horse",
                1988
        );
    }

    @Test
    void test_sorting_1(){
        List<ComicBookDto> expected = new ArrayList<>();
        expected.add(comicBook2);
        expected.add(comicBook3);
        expected.add(comicBook1);

        List<ComicBookDto> listToTest = new ArrayList<>();
        listToTest.add(comicBook3);
        listToTest.add(comicBook1);
        listToTest.add(comicBook2);

        List<ComicBookDto> actual = SortingUtils.sortComicsAToZ(listToTest);

        assertIterableEquals(expected,actual);
    }

    @Test
    void test_sorting_2(){
        List<ComicBookDto> expected = new ArrayList<>();
        expected.add(comicBook3);
        expected.add(comicBook1);
        expected.add(comicBook2);

        List<ComicBookDto> listToTest = new ArrayList<>();
        listToTest.add(comicBook3);
        listToTest.add(comicBook1);
        listToTest.add(comicBook2);

        List<ComicBookDto> actual = SortingUtils.sortComicsDateAdded(listToTest);

        assertIterableEquals(expected,actual);
    }

    @Test
    void test_sorting_3(){
        List<ComicBookDto> expected = new ArrayList<>();
        expected.add(comicBook1);
        expected.add(comicBook2);
        expected.add(comicBook3);

        List<ComicBookDto> listToTest = new ArrayList<>();
        listToTest.add(comicBook2);
        listToTest.add(comicBook1);
        listToTest.add(comicBook3);

        List<ComicBookDto> actual = SortingUtils.sortComicsYearPublished(listToTest);

        assertIterableEquals(expected,actual);
    }

}
