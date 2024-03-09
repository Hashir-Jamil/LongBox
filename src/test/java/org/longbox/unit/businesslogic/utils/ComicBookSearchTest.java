package org.longbox.unit.businesslogic.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.ComicBookSearch;
import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookSearchTest {

    ComicBookDto comicBook1;
    ComicBookDto comicBook2;
    ComicBookDto comicBook3;
    ComicBookDto comicBook4;
    ComicBookDto comicBook5;
    ComicBookDto comicBook6;
    ComicBookDto comicBook7;
    ComicBookDto comicBook8;
    List<ComicBookDto> comicBookDtoList;

    @BeforeEach
    public void setup() {

        comicBookDtoList = new ArrayList<>();

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
        comicBookDtoList.add(comicBook1);

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
        comicBookDtoList.add(comicBook2);

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
        comicBookDtoList.add(comicBook3);

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
        comicBookDtoList.add(comicBook4);

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
        comicBookDtoList.add(comicBook5);

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
        comicBookDtoList.add(comicBook6);

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
        comicBookDtoList.add(comicBook7);

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
        comicBookDtoList.add(comicBook8);
    }

    @Test
    public void comicBookExactMatch() {
        ComicBookDto expected = new ComicBookDto(
                "The Maxx",
                "Sam Keith",
                "Sam Keith",
                new String[]{"Fantasy", "Drama", "Comedy", "Superhero"},
                "Description",
                35,
                "Image",
                1993
        );
        Assertions.assertEquals(expected, ComicBookSearch.searchComicBook(comicBookDtoList, "The Maxx"));
    }

    @Test
    public void comicBookPartialMatch() {
        ComicBookDto expected = new ComicBookDto(
                "Nexus (1981)",
                "Mike Baron",
                "Steve Rude",
                new String[]{"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"},
                "Description",
                3,
                "Capital",
                1981
        );
        assertEquals(expected, ComicBookSearch.searchComicBook(comicBookDtoList,"Nexus"));
    }

    @Test
    public void comicBookNoMatch() {
        ComicBookDto expected = comicBook1 = new ComicBookDto(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984
        );
        assertNotEquals(expected, ComicBookSearch.searchComicBook(comicBookDtoList,"Nexus"));
    }
    
    @Test
    public void comicBookSearchCaseInsensitive() {
    	ComicBookDto expected = comicBook1;
    	ComicBookDto actual = ComicBookSearch.searchComicBook(comicBookDtoList, "zot");
    	assertEquals(expected, actual, "case insensitive comic book search");
    }
    
    @Test
    public void comicBookSearchCaseInsensitiveNoMatch() {
    	ComicBookDto expected = comicBook1;
    	ComicBookDto actual = ComicBookSearch.searchComicBook(comicBookDtoList, "nexus");
    	assertNotEquals(expected, actual, "case insensitive comic book search no match");
    }

    @Test
    public void comicBookSearchByAuthorCaseInsensitive() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	expected.add(comicBook1);
    	expected.add(comicBook5);
    	List<ComicBookDto> actual = ComicBookSearch.searchComicBookByAuthor(comicBookDtoList, "scott mccloud");
    	assertEquals(expected, actual, "case insensitive search by author");	
    }
    
    @Test
    public void comicBookSearchByAuthorCaseInsensitiveNoMatch() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	List<ComicBookDto> actual = ComicBookSearch.searchComicBookByAuthor(comicBookDtoList, "scoot mccloud");
    	assertEquals(expected, actual, "case insensitive search by author no match");	
    }
    
    @Test
    public void comicBookSearchByArtistCaseInsensitive() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	expected.add(comicBook2);
    	expected.add(comicBook6);
    	List<ComicBookDto> actual = ComicBookSearch.searchComicBookByArtist(comicBookDtoList, "ryoichi ikegami");
    	assertEquals(expected, actual, "case insensitive search by artist");
    }

    @Test
    public void comicBookSearchByArtistCaseInsensitiveNoMatch() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	List<ComicBookDto> actual = ComicBookSearch.searchComicBookByArtist(comicBookDtoList, "ryooichi ikegami");
    	assertEquals(expected, actual, "case insensitive search by artist no match");
    }
    
    @Test
    public void nullException() {
        List<ComicBookDto> nullList = null;
        assertThrows(NullPointerException.class, () -> ComicBookSearch.searchComicBook(nullList, "Zot!"));
    }
}
