package org.longbox.businesslogic.utils;

import org.longbox.businesslogic.comparators.ComicBookDateAddedComparator;
import org.longbox.businesslogic.comparators.ComicBookNameComparator;
import org.longbox.businesslogic.comparators.ComicBookYearPublishedComparator;
import org.longbox.businesslogic.comparators.CommentDateComparator;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.domainobjects.dto.CommentDTO;

import java.util.Collections;
import java.util.List;

public class SortingUtils {

    public static List<ComicBookDTO> sortComicsAToZ(List<ComicBookDTO> comicBookList) {
        Collections.sort(comicBookList, new ComicBookNameComparator());
        return comicBookList;
    }

    public static List<ComicBookDTO> sortComicsDateAdded(List<ComicBookDTO> comicBookList) {
        Collections.sort(comicBookList, new ComicBookDateAddedComparator());
        return comicBookList;
    }

    public static List<ComicBookDTO> sortComicsYearPublished(List<ComicBookDTO> comicBookList) {
        Collections.sort(comicBookList, new ComicBookYearPublishedComparator());
        return comicBookList;
    }

    public static List<CommentDTO> sortCommentsByDateAscending(List<CommentDTO> commentsList) {
        Collections.sort(commentsList, new CommentDateComparator());
        return commentsList;
    }

    public static List<CommentDTO> sortCommentsByDateDescending(List<CommentDTO> commentsList) {
        sortCommentsByDateAscending(commentsList);
        Collections.reverse(commentsList);
        return commentsList;
    }

}
