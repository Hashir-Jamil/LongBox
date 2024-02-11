package org.longbox.domainobjects;


import org.longbox.businesslogic.comparators.ComicBookDateAddeddComparator;
import org.longbox.businesslogic.comparators.ComicBookNameComparator;
import org.longbox.businesslogic.comparators.ComicBookYearPublishedComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComicBookRepository {

    private List<ComicBookDTO> comicBookList = new ArrayList<>();

    public void populateComicBookList() {
        //TO-DO
    }

    public void sortComicsAToZ() {
        Collections.sort(comicBookList, new ComicBookNameComparator());
    }

    public void sortComicsDateAdded() {
        Collections.sort(comicBookList, new ComicBookDateAddeddComparator());
    }

    public void sortComicsYearPublished() {
        Collections.sort(comicBookList, new ComicBookYearPublishedComparator());
    }

    public List<ComicBookDTO> recentComicsList(int trendingComicsListSize) {

        List<ComicBookDTO> trendingComics = new ArrayList<>();
        Collections.sort(comicBookList, new ComicBookDateAddeddComparator());
        trendingComics = comicBookList.subList(0, trendingComicsListSize-1);
        return trendingComics;
    }

}
