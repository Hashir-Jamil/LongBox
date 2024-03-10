package org.longbox.businesslogic.service;

import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.List;

public interface RecommendationsService {

    List<ComicBookDto> userRecentComicsList(
            List<ComicBookDto> comicBookList,
            int trendingComicsListSize
    );
}
