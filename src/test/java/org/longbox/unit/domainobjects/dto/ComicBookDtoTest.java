package org.longbox.unit.domainobjects.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class ComicBookDtoTest {

    ComicBookDto comicBook1;
    ComicBookDto comicBook2;
    ComicBookDto comicBook3;
    @BeforeEach
    public void setup() {
        comicBook1 = new ComicBookDto();
        comicBook1.setSeriesTitle("Zot!");
        comicBook1.setAuthor("Scott McCloud");
        comicBook1.setArtist("Scott McCloud");
        comicBook1.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBook1.setDescription("Description");
        comicBook1.setNumberOfIssues(36);
        comicBook1.setPublisher("Eclipse");
        comicBook1.setYearPublished(1984);

        comicBook2 = new ComicBookDto();
        comicBook2.setSeriesTitle("Zot!");
        comicBook2.setAuthor("Scott McCloud");
        comicBook2.setArtist("Scott McCloud");
        comicBook2.setGenres(new String[] {});
        comicBook2.setDescription("Description");
        comicBook2.setNumberOfIssues(36);
        comicBook2.setPublisher("Eclipse");
        comicBook2.setYearPublished(1984);

        comicBook3 = new ComicBookDto();
        comicBook3.setSeriesTitle("Zot!");
        comicBook3.setAuthor("Scott McCloud");
        comicBook3.setArtist("Scott McCloud");
        comicBook3.setGenres(new String[] {"Superhero"});
        comicBook3.setDescription("Description");
        comicBook3.setNumberOfIssues(36);
        comicBook3.setPublisher("Eclipse");
        comicBook3.setYearPublished(1984);
    }

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

    @Test
    void noGenresStringToList() {
        String[] expected = {""};
        assertArrayEquals(expected, GenreUtils.genreStringToList(""));
    }

    @Test
    void oneGenresStringToList() {
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

        String expectedToString = "ComicBookDTO{id=1, seriesTitle='Series Title', author='Author', artist='Artist', genres=[Genre1, Genre2], description='Description', numberOfIssues=10, publisher='Publisher', yearPublished=2022, dateAdded=" + comicBookDto.getDateAdded() + ", favouritesCount=" + comicBookDto.getFavouritesCount() + '}';
        assertEquals(expectedToString, comicBookDto.toString());
    }

    @Test
    public void favouritesCountTest_1() {
        ComicBookDto EndOfEvangelion = new ComicBookDto();
        EndOfEvangelion.setFavouritesCount(208);
        EndOfEvangelion.setNorthAmericaFavouritesCount(31);
        EndOfEvangelion.setSouthAmericaFavouritesCount(28);
        EndOfEvangelion.setEuropeFavouritesCount(22);
        EndOfEvangelion.setAsiaFavouritesCount(98);
        EndOfEvangelion.setAfricaFavouritesCount(12);
        EndOfEvangelion.setOceaniaFavouritesCount(16);
        EndOfEvangelion.setAntarcticaFavouritesCount(1);

        assertTrue(EndOfEvangelion.getFavouritesCount() >= EndOfEvangelion.getNorthAmericaFavouritesCount());
        assertTrue(EndOfEvangelion.getFavouritesCount() >= EndOfEvangelion.getSouthAmericaFavouritesCount());
        assertTrue(EndOfEvangelion.getFavouritesCount() >= EndOfEvangelion.getEuropeFavouritesCount());
        assertTrue(EndOfEvangelion.getFavouritesCount() >= EndOfEvangelion.getAsiaFavouritesCount());
        assertTrue(EndOfEvangelion.getFavouritesCount() >= EndOfEvangelion.getAfricaFavouritesCount());
        assertTrue(EndOfEvangelion.getFavouritesCount() >= EndOfEvangelion.getOceaniaFavouritesCount());
        assertTrue(EndOfEvangelion.getFavouritesCount() >= EndOfEvangelion.getAntarcticaFavouritesCount());
    }

    @Test
    public void favouritesCountTest_2() {
        ComicBookDto EndOfEvangelion = new ComicBookDto();
        EndOfEvangelion.setFavouritesCount(208);
        EndOfEvangelion.setNorthAmericaFavouritesCount(31);
        EndOfEvangelion.setSouthAmericaFavouritesCount(28);
        EndOfEvangelion.setEuropeFavouritesCount(22);
        EndOfEvangelion.setAsiaFavouritesCount(98);
        EndOfEvangelion.setAfricaFavouritesCount(12);
        EndOfEvangelion.setOceaniaFavouritesCount(16);
        EndOfEvangelion.setAntarcticaFavouritesCount(1);

        assertEquals(EndOfEvangelion.getFavouritesCount(),
                EndOfEvangelion.getNorthAmericaFavouritesCount() +
                        EndOfEvangelion.getSouthAmericaFavouritesCount() +
                        EndOfEvangelion.getEuropeFavouritesCount() +
                        EndOfEvangelion.getAsiaFavouritesCount() +
                        EndOfEvangelion.getAfricaFavouritesCount() +
                        EndOfEvangelion.getOceaniaFavouritesCount() +
                        EndOfEvangelion.getAntarcticaFavouritesCount()
        );
    }
}
