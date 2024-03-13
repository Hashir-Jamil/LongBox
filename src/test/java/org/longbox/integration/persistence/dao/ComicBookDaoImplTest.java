package org.longbox.integration.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComicBookDaoImplTest {

    ComicBook comicBook, comicBook2;
    ComicBookDto comicBookDto1, comicBookDto2;
    ComicBookDaoImpl comicBookDaoImpl;

    @BeforeEach
    void init(){
        comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

        comicBookDto1 = new ComicBookDto(
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

        comicBookDto2 = new ComicBookDto(
                "Sanctuary",
                "Sho Fumimura",
                "Ryoichi Ikegami",
                new String[] {"Polital", "Crime", "Thriller", "Manga"},
                "Empty",
                108,
                "Viz",
                1990,
                new Date()
        );

        comicBook = new ComicBook(
                comicBookDto1.getSeriesTitle(),
                comicBookDto1.getAuthor(),
                comicBookDto1.getArtist(),
                ComicBookDto.genreListToString(comicBookDto1.getGenres()),
                comicBookDto1.getDescription(),
                comicBookDto1.getNumberOfIssues(),
                comicBookDto1.getPublisher(),
                comicBookDto1.getYearPublished(),
                comicBookDto1.getDateAdded()
        );

        comicBook2 = new ComicBook(
                comicBookDto2.getSeriesTitle(),
                comicBookDto2.getAuthor(),
                comicBookDto2.getArtist(),
                ComicBookDto.genreListToString(comicBookDto2.getGenres()),
                comicBookDto2.getDescription(),
                comicBookDto2.getNumberOfIssues(),
                comicBookDto2.getPublisher(),
                comicBookDto2.getYearPublished(),
                comicBookDto2.getDateAdded()
        );

    }

    @Test
    void getAllComicsTestBasic() {
        List<ComicBookDto> comicBookRecordsDTO = new ArrayList<>();
        comicBookRecordsDTO = comicBookDaoImpl.getAllComicBooks();
        assertFalse(comicBookRecordsDTO.isEmpty());
    }

    @Test
    void saveComicBook() {
        comicBookDaoImpl.saveComicBook(new ComicBook(comicBookDto2));
    }
}
