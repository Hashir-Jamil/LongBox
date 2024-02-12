package org.longbox.persistence.dao;

import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

public interface CommentDao {

    User getCommentById(long userId, long comicId);

    User getCommentsByComic(long comicID);

    User saveComicBook(ComicBook comicBook);

    boolean deleteComicBook(ComicBook comicBook);

    boolean modifyComicBook(ComicBook comicBook);

}
