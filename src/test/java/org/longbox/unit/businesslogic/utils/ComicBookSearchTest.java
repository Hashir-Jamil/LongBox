package org.longbox.unit.businesslogic.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.ComicBookSearch;
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
        Assertions.assertEquals(expected, ComicBookSearch.searchComicBook(comicBookDTOList, "The Maxx"));
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
    public void comicBookSearchCaseInsensitive() {
    	ComicBookDTO expected = comicBook1;
    	ComicBookDTO actual = ComicBookSearch.searchComicBook(comicBookDTOList, "zot");
    	assertEquals(expected, actual, "case insensitive comic book search");
    }
    
    @Test
    public void comicBookSearchCaseInsensitiveNoMatch() {
    	ComicBookDTO expected = comicBook1;
    	ComicBookDTO actual = ComicBookSearch.searchComicBook(comicBookDTOList, "nexus");
    	assertNotEquals(expected, actual, "case insensitive comic book search no match");
    }

    @Test
    public void comicBookSearchByAuthorCaseInsensitive() {
    	List<ComicBookDTO> expected = new ArrayList<ComicBookDTO>();
    	expected.add(comicBook1);
    	expected.add(comicBook5);
    	List<ComicBookDTO> actual = ComicBookSearch.searchComicBookByAuthor(comicBookDTOList, "scott mccloud");
    	assertEquals(expected, actual, "case insensitive search by author");	
    }
    
    @Test
    public void comicBookSearchByAuthorCaseInsensitiveNoMatch() {
    	List<ComicBookDTO> expected = new ArrayList<ComicBookDTO>();
    	List<ComicBookDTO> actual = ComicBookSearch.searchComicBookByAuthor(comicBookDTOList, "scoot mccloud");
    	assertEquals(expected, actual, "case insensitive search by author no match");	
    }
    
    @Test
    public void comicBookSearchByArtistCaseInsensitive() {
    	List<ComicBookDTO> expected = new ArrayList<ComicBookDTO>();
    	expected.add(comicBook2);
    	expected.add(comicBook6);
    	List<ComicBookDTO> actual = ComicBookSearch.searchComicBookByArtist(comicBookDTOList, "ryoichi ikegami");
    	assertEquals(expected, actual, "case insensitive search by artist");
    }

    @Test
    public void comicBookSearchByArtistCaseInsensitiveNoMatch() {
    	List<ComicBookDTO> expected = new ArrayList<ComicBookDTO>();
    	List<ComicBookDTO> actual = ComicBookSearch.searchComicBookByArtist(comicBookDTOList, "ryooichi ikegami");
    	assertEquals(expected, actual, "case insensitive search by artist no match");
    }
    
    @Test
    public void nullException() {
        List<ComicBookDTO> nullList = null;
        assertThrows(NullPointerException.class, () -> ComicBookSearch.searchComicBook(nullList, "Zot!"));
    }
}