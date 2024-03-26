package org.longbox.unit.businesslogic.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookSearchUtilsTest {

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

        comicBook1 = new ComicBookDto();
        comicBook1.setSeriesTitle("Zot!");
        comicBook1.setAuthor("Scott McCloud");
        comicBook1.setArtist("Scott McCloud");
        comicBook1.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBook1.setDescription("Description");
        comicBook1.setNumberOfIssues(36);
        comicBook1.setPublisher("Eclipse");
        comicBook1.setYearPublished(1984);
        comicBookDtoList.add(comicBook1);

        comicBook2 = new ComicBookDto();
        comicBook2.setSeriesTitle("Sanctuary");
        comicBook2.setAuthor("Sho Fumimura");
        comicBook2.setArtist("Ryoichi Ikegami");
        comicBook2.setGenres(new String[] {"Political", "Crime", "Thriller", "Manga"});
        comicBook2.setDescription("Description");
        comicBook2.setNumberOfIssues(108);
        comicBook2.setPublisher("Viz");
        comicBook2.setYearPublished(1990);
        comicBookDtoList.add(comicBook2);

        comicBook3 = new ComicBookDto();
        comicBook3.setSeriesTitle("Nexus (1981)");
        comicBook3.setAuthor("Mike Baron");
        comicBook3.setArtist("Steve Rude");
        comicBook3.setGenres(new String[] {"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"});
        comicBook3.setDescription("Description");
        comicBook3.setNumberOfIssues(3);
        comicBook3.setPublisher("Capital");
        comicBook3.setYearPublished(1981);
        comicBookDtoList.add(comicBook3);

        comicBook4 = new ComicBookDto();
        comicBook4.setSeriesTitle("The Maxx");
        comicBook4.setAuthor("Sam Keith");
        comicBook4.setArtist("Sam Keith");
        comicBook4.setGenres(new String[] {"Fantasy", "Drama", "Comedy", "Superhero"});
        comicBook4.setDescription("Description");
        comicBook4.setNumberOfIssues(35);
        comicBook4.setPublisher("Image");
        comicBook4.setYearPublished(1993);
        comicBookDtoList.add(comicBook4);

        comicBook5 = new ComicBookDto();
        comicBook5.setSeriesTitle("");
        comicBook5.setAuthor("Scott McCloud");
        comicBook5.setArtist("Scott McCloud");
        comicBook5.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBook5.setDescription("Description");
        comicBook5.setNumberOfIssues(36);
        comicBook5.setPublisher("Eclipse");
        comicBook5.setYearPublished(1984);
        comicBookDtoList.add(comicBook5);

        comicBook6 = new ComicBookDto();
        comicBook6.setSeriesTitle(null);
        comicBook6.setAuthor("Sho Fumimura");
        comicBook6.setArtist("Ryoichi Ikegami");
        comicBook6.setGenres(new String[] {"Political", "Crime", "Thriller", "Manga"});
        comicBook6.setDescription("Description");
        comicBook6.setNumberOfIssues(108);
        comicBook6.setPublisher("Viz");
        comicBook6.setYearPublished(1990);
        comicBookDtoList.add(comicBook6);

        comicBook7 = new ComicBookDto();
        comicBook7.setSeriesTitle("Nexus (1981)");
        comicBook7.setAuthor("Mike Baron");
        comicBook7.setArtist("Steve Rude");
        comicBook7.setGenres(new String[] {"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"});
        comicBook7.setDescription("Description");
        comicBook7.setNumberOfIssues(3);
        comicBook7.setPublisher("Capital");
        comicBook7.setYearPublished(1981);
        comicBookDtoList.add(comicBook7);

        comicBook8 = new ComicBookDto();
        comicBook8.setSeriesTitle("The Maxx");
        comicBook8.setAuthor("Sam Keith");
        comicBook8.setArtist("Sam Keith");
        comicBook8.setGenres(new String[]{"Fantasy", "Drama", "Comedy", "Superhero"});
        comicBook8.setDescription("Description");
        comicBook8.setNumberOfIssues(35);
        comicBook8.setPublisher("Image");
        comicBook8.setYearPublished(1993);
        comicBookDtoList.add(comicBook8);
    }

    @Test
    public void comicBookExactMatch() {
        ComicBookDto expected = new ComicBookDto();
        expected.setSeriesTitle("The Maxx");
        expected.setAuthor("Sam Keith");
        expected.setArtist("Sam Keith");
        expected.setGenres(new String[] {"Fantasy", "Drama", "Comedy", "Superhero"});
        expected.setDescription("Description");
        expected.setNumberOfIssues(35);
        expected.setPublisher("Image");
        expected.setYearPublished(1993);
        Assertions.assertEquals(expected, org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookDtoList, "The Maxx"));
    }

    @Test
    public void comicBookPartialMatch() {
        ComicBookDto expected = new ComicBookDto();
        expected.setSeriesTitle("Nexus (1981)");
        expected.setAuthor("Mike Baron");
        expected.setArtist("Steve Rude");
        expected.setGenres(new String[] {"Superhero", "Planetary Romance", "Superpower", "Science Fiction", "Adventure", "Fantasy"});
        expected.setDescription("Description");
        expected.setNumberOfIssues(3);
        expected.setPublisher("Capital");
        expected.setYearPublished(1981);
        assertEquals(expected, org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookDtoList,"Nexus"));
    }

    @Test
    public void comicBookNoMatch() {
        ComicBookDto expected = new ComicBookDto();
        expected.setSeriesTitle("Zot!");
        expected.setAuthor("Scott McCloud");
        expected.setArtist("Scott McCloud");
        expected.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        expected.setDescription("Description");
        expected.setNumberOfIssues(36);
        expected.setPublisher("Eclipse");
        expected.setYearPublished(1984);
        assertNotEquals(expected, org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookDtoList,"Nexus"));
    }
    
    @Test
    public void comicBookSearchCaseInsensitive() {
    	ComicBookDto expected = comicBook1;
    	ComicBookDto actual = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookDtoList, "zot");
    	assertEquals(expected, actual, "case insensitive comic book search");
    }
    
    @Test
    public void comicBookSearchCaseInsensitiveNoMatch() {
    	ComicBookDto expected = comicBook1;
    	ComicBookDto actual = ComicBookSearchUtils.searchComicBook(comicBookDtoList, "nexus");
    	assertNotEquals(expected, actual, "case insensitive comic book search no match");
    }

    @Test
    public void comicBookSearchByAuthorCaseInsensitive() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	expected.add(comicBook1);
    	expected.add(comicBook5);
    	List<ComicBookDto> actual = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBookByAuthor(comicBookDtoList, "scott mccloud");
    	assertEquals(expected, actual, "case insensitive search by author");	
    }
    
    @Test
    public void comicBookSearchByAuthorCaseInsensitiveNoMatch() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	List<ComicBookDto> actual = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBookByAuthor(comicBookDtoList, "scoot mccloud");
    	assertEquals(expected, actual, "case insensitive search by author no match");	
    }
    
    @Test
    public void comicBookSearchByArtistCaseInsensitive() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	expected.add(comicBook2);
    	expected.add(comicBook6);
    	List<ComicBookDto> actual = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBookByArtist(comicBookDtoList, "ryoichi ikegami");
    	assertEquals(expected, actual, "case insensitive search by artist");
    }

    @Test
    public void comicBookSearchByArtistCaseInsensitiveNoMatch() {
    	List<ComicBookDto> expected = new ArrayList<ComicBookDto>();
    	List<ComicBookDto> actual = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBookByArtist(comicBookDtoList, "ryooichi ikegami");
    	assertEquals(expected, actual, "case insensitive search by artist no match");
    }
    
    @Test
    public void nullException() {
        List<ComicBookDto> nullList = null;
        assertThrows(NullPointerException.class, () -> org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(nullList, "Zot!"));
    }
}
