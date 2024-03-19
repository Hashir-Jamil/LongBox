package org.longbox.businesslogic.service;

import org.longbox.businesslogic.comparators.ComicBookDateAddedComparator;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.ComicBookDao;
import org.longbox.persistence.dao.ComicBookDaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationService {

    ComicBookDao comicBookDao;

    public RecommendationService(ComicBookDao dao) {
        this.comicBookDao = dao;
    }

    public List<ComicBookDto> getRecommendations(UserDto dto) {
        String[] genres = {"Action"};
        //genres = dto.getGenres
        return comicBookDao.getRecommendationsByGenre(genres);
    }
}
