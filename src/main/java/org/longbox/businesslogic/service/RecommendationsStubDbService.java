package org.longbox.businesslogic.service;

import org.longbox.businesslogic.comparators.ComicBookDateAddedComparator;
import org.longbox.domainobjects.dto.ComicBookDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationsStubDbService implements RecommendationsService {

    public List<ComicBookDto> userRecentComicsList(
            List<ComicBookDto> comicBookList,
            int trendingComicsListSize
    ) {
        List<ComicBookDto> trendingComics = new ArrayList<>();
        Collections.sort(comicBookList, new ComicBookDateAddedComparator());
        trendingComics = comicBookList.subList(0, trendingComicsListSize-1);
        return trendingComics;
    }

}
