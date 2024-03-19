package org.longbox.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.businesslogic.service.RecommendationService;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.persistence.dao.ComicBookDaoImpl;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendationServiceTest {

    UserDto userDto;
    ComicBook comicBook, comicBook2;
    ComicBookDto comicBookDto1, comicBookDto2;
    RecommendationService recommendationService;

    @BeforeEach
    void init(){
        recommendationService = new RecommendationService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

        userDto = new UserDto();
        String[] genres = {"Adventure","Action","Political"};
        userDto.setPreferredGenre(genres);

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
                GenreUtils.genreListToString(comicBookDto1.getGenres()),
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
                GenreUtils.genreListToString(comicBookDto2.getGenres()),
                comicBookDto2.getDescription(),
                comicBookDto2.getNumberOfIssues(),
                comicBookDto2.getPublisher(),
                comicBookDto2.getYearPublished(),
                comicBookDto2.getDateAdded()
        );

    }

    @Test
    void getRecommendationsByGenresTest() {
        List<ComicBookDto> recommendations = recommendationService.getRecommendations(userDto);
        assertTrue(recommendations.contains(comicBookDto1));
    }
}
