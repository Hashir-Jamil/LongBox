package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class ComicBookDtoTest {

    ComicBookDto comicBook1 = new ComicBookDto(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
            "Description",
            36,
            "Eclipse",
            1984
    );

    ComicBookDto comicBook2 = new ComicBookDto(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {},
            "Description",
            36,
            "Eclipse",
            1984
    );

    ComicBookDto comicBook3 = new ComicBookDto(
            "Zot!",
            "Scott McCloud",
            "Scott McCloud",
            new String[] {"Superhero"},
            "Description",
            36,
            "Eclipse",
            1984
    );

    @Test
    public void multipleGenresListToString() {
        String expected = "Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama";
        assertEquals(expected, GenreUtils.genreListToString(comicBook1.getGenres()));
    }

    @Test
    public void onlyOneGenreListToString() {
        String expected = "Superhero";
        assertEquals(expected, GenreUtils.genreListToString(comicBook3.getGenres()));
    }

    @Test
    public void noGenresListToString() {
        String expected = "";
        assertEquals("", GenreUtils.genreListToString(comicBook2.getGenres()));
    }

    @Test
    public void multipleGenresStringToList() {
        String[] expected = {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"};
        String input = "Superhero, Superpower, Adventure, Science Fiction, Futuristic, Romance, Drama";
        assertArrayEquals(expected, GenreUtils.genreStringToList(input));
    }

    @Test void noGenresStringToList() {
        String[] expected = {""};
        assertArrayEquals(expected, GenreUtils.genreStringToList(""));
    }

    @Test void oneGenresStringToList() {
        String[] expected = {"Fantasy"};
        assertArrayEquals(expected, GenreUtils.genreStringToList("Fantasy"));
    }

    @Test
    public void testEqualsAndHashCode() {
        ComicBookDto comicBookDto1 = new ComicBookDto();
        comicBookDto1.setId(1L);
        comicBookDto1.setSeriesTitle("Series Title");
        comicBookDto1.setAuthor("Author");
        comicBookDto1.setArtist("Artist");
        comicBookDto1.setGenres(new String[]{"Genre1", "Genre2"});
        comicBookDto1.setDescription("Description");
        comicBookDto1.setNumberOfIssues(10);
        comicBookDto1.setPublisher("Publisher");
        comicBookDto1.setYearPublished(2022);
        comicBookDto1.setDateAdded(new Date());

        ComicBookDto comicBookDto2 = new ComicBookDto();
        comicBookDto2.setId(1L);
        comicBookDto2.setSeriesTitle("Series Title");
        comicBookDto2.setAuthor("Author");
        comicBookDto2.setArtist("Artist");
        comicBookDto2.setGenres(new String[]{"Genre1", "Genre2"});
        comicBookDto2.setDescription("Description");
        comicBookDto2.setNumberOfIssues(10);
        comicBookDto2.setPublisher("Publisher");
        comicBookDto2.setYearPublished(2022);
        comicBookDto2.setDateAdded(new Date());

        assertTrue(comicBookDto1.equals(comicBookDto2));
        assertEquals(comicBookDto1.hashCode(), comicBookDto2.hashCode());
    }

    @Test
    public void testToString() {
        ComicBookDto comicBookDto = new ComicBookDto();
        comicBookDto.setId(1L);
        comicBookDto.setSeriesTitle("Series Title");
        comicBookDto.setAuthor("Author");
        comicBookDto.setArtist("Artist");
        comicBookDto.setGenres(new String[]{"Genre1", "Genre2"});
        comicBookDto.setDescription("Description");
        comicBookDto.setNumberOfIssues(10);
        comicBookDto.setPublisher("Publisher");
        comicBookDto.setYearPublished(2022);
        comicBookDto.setDateAdded(new Date());

        String expectedToString = "ComicBookDTO{id=1, seriesTitle='Series Title', author='Author', artist='Artist', genres=[Genre1, Genre2], description='Description', numberOfIssues=10, publisher='Publisher', yearPublished=2022, dateAdded=" + comicBookDto.getDateAdded() + ", favoritesCount=" + comicBookDto.getFavoritesCount() + '}';
        assertEquals(expectedToString, comicBookDto.toString());
    }
    
    @Test
    public void favoritesCountTest() {
    	ComicBookDto EndOfEvangelion = new ComicBookDto();
    	EndOfEvangelion.setFavoritesCount(208);
    	EndOfEvangelion.setNorthAmericaFavoritesCount(31);
    	EndOfEvangelion.setSouthAmericaFavoritesCount(28);
    	EndOfEvangelion.setEuropeFavoritesCount(22);
    	EndOfEvangelion.setAsiaFavoritesCount(98);
    	EndOfEvangelion.setAfricaFavoritesCount(12);
    	EndOfEvangelion.setOceaniaFavoritesCount(16);
    	EndOfEvangelion.setAntarcticaFavoritesCount(1);
    	
    	assertTrue(EndOfEvangelion.getFavoritesCount() >= EndOfEvangelion.getNorthAmericaFavoritesCount());
    	assertTrue(EndOfEvangelion.getFavoritesCount() >= EndOfEvangelion.getSouthAmericaFavoritesCount());
    	assertTrue(EndOfEvangelion.getFavoritesCount() >= EndOfEvangelion.getEuropeFavoritesCount());
    	assertTrue(EndOfEvangelion.getFavoritesCount() >= EndOfEvangelion.getAsiaFavoritesCount());
    	assertTrue(EndOfEvangelion.getFavoritesCount() >= EndOfEvangelion.getAfricaFavoritesCount());
    	assertTrue(EndOfEvangelion.getFavoritesCount() >= EndOfEvangelion.getOceaniaFavoritesCount());
    	assertTrue(EndOfEvangelion.getFavoritesCount() >= EndOfEvangelion.getAntarcticaFavoritesCount());
    	
    	assertEquals(EndOfEvangelion.getFavoritesCount(),
    			EndOfEvangelion.getNorthAmericaFavoritesCount() + 
    			EndOfEvangelion.getSouthAmericaFavoritesCount() +
    			EndOfEvangelion.getEuropeFavoritesCount() +
    			EndOfEvangelion.getAsiaFavoritesCount() +
    			EndOfEvangelion.getAfricaFavoritesCount() +
    			EndOfEvangelion.getOceaniaFavoritesCount() +
    			EndOfEvangelion.getAntarcticaFavoritesCount()
    			);
    }

/*    @Test
    public void testComicBookDtoConstructor() {
        ComicBook comicBookRecord = new ComicBook();
        comicBookRecord.setId(1L);
        comicBookRecord.setAuthor("Author");
        comicBookRecord.setSeriesTitle("Series Title");
        comicBookRecord.setArtist("Artist");
        comicBookRecord.setGenres("Genre1, Genre2");
        comicBookRecord.setDescription("Description");
        comicBookRecord.setNumberOfIssues(10);
        comicBookRecord.setPublisher("Publisher");
        comicBookRecord.setYearPublished(2022);
        comicBookRecord.setDateAdded(new Date());
        comicBookRecord.setNorthAmericaFavoritesCount(1);
        comicBookRecord.setSouthAmericaFavoritesCount(2);
        comicBookRecord.setEuropeFavoritesCount(3);
        comicBookRecord.setAsiaFavoritesCount(4);
        comicBookRecord.setAfricaFavoritesCount(5);
        comicBookRecord.setOceaniaFavoritesCount(6);
        comicBookRecord.setAntarcticaFavoritesCount(0);
        comicBookRecord.setFavoritesCount(21);

        ComicBookDto comicBookDto = new ComicBookDto(comicBookRecord);

        assertEquals(1L, comicBookDto.getId());
        assertEquals("Series Title", comicBookDto.getSeriesTitle());
        assertEquals("Author", comicBookDto.getAuthor());
        assertEquals("Artist", comicBookDto.getArtist());
        assertArrayEquals(new String[]{"Genre1", "Genre2"}, comicBookDto.getGenres());
        assertEquals("Description", comicBookDto.getDescription());
        assertEquals(10, comicBookDto.getNumberOfIssues());
        assertEquals("Publisher", comicBookDto.getPublisher());
        assertEquals(2022, comicBookDto.getYearPublished());
        assertEquals(comicBookRecord.getDateAdded(), comicBookDto.getDateAdded());
        assertEquals(1, comicBookDto.getNorthAmericaFavoritesCount());
        assertEquals(2, comicBookDto.getSouthAmericaFavoritesCount());
        assertEquals(3, comicBookDto.getEuropeFavoritesCount());
        assertEquals(4, comicBookDto.getAsiaFavoritesCount());
        assertEquals(5, comicBookDto.getAfricaFavoritesCount());
        assertEquals(6, comicBookDto.getOceaniaFavoritesCount());
        assertEquals(0, comicBookDto.getAntarcticaFavoritesCount());
        assertEquals(21, comicBookDto.getFavoritesCount());
    }*/
}
