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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendationServiceTest {

    UserDto userDto;
    ComicBookDto comicBookDto1, comicBookDto2;
    RecommendationService recommendationService;

    @BeforeEach
    void init(){
        recommendationService = new RecommendationService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

        userDto = new UserDto();
        String[] genres = {"Adventure","Action","Political"};
        userDto.setPreferredGenre(genres);

        comicBookDto1 = new ComicBookDto();
        comicBookDto1.setSeriesTitle("Zot!");
        comicBookDto1.setAuthor("Scott McCloud");
        comicBookDto1.setArtist("Scott McCloud");
        comicBookDto1.setGenres(new String[] {"Superhero", "Superpower", "Adventure", "Science Fiction", "Futuristic", "Romance", "Drama"});
        comicBookDto1.setDescription("Description");
        comicBookDto1.setNumberOfIssues(36);
        comicBookDto1.setPublisher("Eclipse");
        comicBookDto1.setYearPublished(1984);
        comicBookDto1.setDateAdded(new Date());

        comicBookDto2 = new ComicBookDto();
        comicBookDto1.setSeriesTitle("Sanctuary");
        comicBookDto1.setAuthor("Sho Fumimura");
        comicBookDto1.setArtist("Ryoichi Ikegami");
        comicBookDto1.setGenres(new String[] {"Polital", "Crime", "Thriller", "Manga"});
        comicBookDto1.setDescription("Empty");
        comicBookDto1.setNumberOfIssues(108);
        comicBookDto1.setPublisher("Viz");
        comicBookDto1.setYearPublished(1990);
        comicBookDto1.setDateAdded(new Date());
    }

    @Test
    void getRecommendationsByGenresTest() {
        List<ComicBookDto> recommendations = recommendationService.getRecommendations(userDto);
        for (int i = 0; i < recommendations.size(); i++) {
            List<String> genres = Arrays.stream(recommendations.get(i).getGenres()).toList();
            assertTrue(genres.contains("Adventure") || genres.contains("Action") || genres.contains("Political"));
        }
    }
}
