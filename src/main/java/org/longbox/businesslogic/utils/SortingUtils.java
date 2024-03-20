package org.longbox.businesslogic.utils;

import org.longbox.businesslogic.comparators.ComicBookDateAddedComparator;
import org.longbox.businesslogic.comparators.ComicBookNameComparator;
import org.longbox.businesslogic.comparators.ComicBookYearPublishedComparator;
import org.longbox.businesslogic.comparators.CommentDateComparator;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;

import java.util.Collections;
import java.util.List;

public class SortingUtils {

    public static List<ComicBookDto> sortComicsAToZ(List<ComicBookDto> comicBookList) {
        Collections.sort(comicBookList, new ComicBookNameComparator());
        return comicBookList;
    }

    public static List<ComicBookDto> sortComicsDateAdded(List<ComicBookDto> comicBookList) {
        Collections.sort(comicBookList, new ComicBookDateAddedComparator());
        return comicBookList;
    }

    public static List<ComicBookDto> sortComicsYearPublished(List<ComicBookDto> comicBookList) {
        Collections.sort(comicBookList, new ComicBookYearPublishedComparator());
        return comicBookList;
    }

    public static List<CommentDto> sortCommentsByDateAscending(List<CommentDto> commentsList) {
        Collections.sort(commentsList, new CommentDateComparator());
        return commentsList;
    }

    public static List<CommentDto> sortCommentsByDateDescending(List<CommentDto> commentsList) {
        sortCommentsByDateAscending(commentsList);
        Collections.reverse(commentsList);
        return commentsList;
    }
}
