package org.longbox.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComicBookDaoImplTest {

    ComicBook comicBook, comicBook2;
    ComicBookDTO comicBookDTO1, comicBookDTO2;
    ComicBookDaoImpl comicBookDaoImpl;

    @BeforeEach
    void init(){
        comicBookDaoImpl = new ComicBookDaoImpl();

        comicBookDTO1 = new ComicBookDTO(
                "Zot!",
                "Scott McCloud",
                "Scott McCloud",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Description",
                36,
                "Eclipse",
                1984,
                new Date()
        );

        comicBookDTO2 = new ComicBookDTO(
                "The Amazing Spider-Man",
                "Stan Lee",
                "Steve Ditko",
                new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"},
                "Most famous superhoero of all time",
                900,
                "Marvel Comics",
                1963,
                new Date()
        );

        comicBook = new ComicBook(
                comicBookDTO1.getSeriesTitle(),
                comicBookDTO1.getAuthor(),
                comicBookDTO1.getArtist(),
                ComicBookDTO.genreListToString(comicBookDTO1.getGenres()),
                comicBookDTO1.getDescription(),
                comicBookDTO1.getNumberOfIssues(),
                comicBookDTO1.getPublisher(),
                comicBookDTO1.getYearPublished(),
                comicBookDTO1.getDateAdded()
        );

        comicBook2 = new ComicBook(
                comicBookDTO2.getSeriesTitle(),
                comicBookDTO2.getAuthor(),
                comicBookDTO2.getArtist(),
                ComicBookDTO.genreListToString(comicBookDTO2.getGenres()),
                comicBookDTO2.getDescription(),
                comicBookDTO2.getNumberOfIssues(),
                comicBookDTO2.getPublisher(),
                comicBookDTO2.getYearPublished(),
                comicBookDTO2.getDateAdded()
        );

    }

    @Test
    void getAllComicsTestBasic() {
        List<ComicBookDTO> comicBookRecordsDTO = new ArrayList<>();
        comicBookRecordsDTO = comicBookDaoImpl.getAllComicBooks();
        assertTrue(comicBookRecordsDTO.size() > 0);
    }

    @Test
    void saveComicBook() {
        comicBookDaoImpl.saveComicBook(comicBook2);
    }


}
