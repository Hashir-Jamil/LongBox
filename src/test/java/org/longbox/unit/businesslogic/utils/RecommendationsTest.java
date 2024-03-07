package org.longbox.unit.businesslogic.utils;

import org.longbox.businesslogic.comparators.ComicBookDateAddedComparator;
import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationsTest {

    public static List<ComicBookDTO> recentComicsList(
            List<ComicBookDTO> comicBookList,
            int trendingComicsListSize
    ) {
        List<ComicBookDTO> trendingComics = new ArrayList<>();
        Collections.sort(comicBookList, new ComicBookDateAddedComparator());
        trendingComics = comicBookList.subList(0, trendingComicsListSize-1);
        return trendingComics;
    }

}
