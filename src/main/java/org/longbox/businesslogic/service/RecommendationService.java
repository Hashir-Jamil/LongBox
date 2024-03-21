package org.longbox.businesslogic.service;

import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.ComicBookDao;

import java.util.List;

public class RecommendationService {

    ComicBookDao comicBookDao;

    public RecommendationService(ComicBookDao dao) {
        this.comicBookDao = dao;
    }

    public List<ComicBookDto> getRecommendations(UserDto dto) {
        return comicBookDao.getRecommendationsByGenre(dto.getPreferredGenre());
    }
}
