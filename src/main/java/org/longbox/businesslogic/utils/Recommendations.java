package org.longbox.businesslogic.utils;

import org.longbox.businesslogic.comparators.ComicBookDateAddeddComparator;
import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recommendations {

    public static List<ComicBookDTO> recentComicsList(
            List<ComicBookDTO> comicBookList,
            int trendingComicsListSize
    ) {
        List<ComicBookDTO> trendingComics = new ArrayList<>();
        Collections.sort(comicBookList, new ComicBookDateAddeddComparator());
        trendingComics = comicBookList.subList(0, trendingComicsListSize-1);
        return trendingComics;
    }

}
