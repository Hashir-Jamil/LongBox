package org.longbox.persistence.dao;

import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

public class CommentDaoImpl implements CommentDao {
    @Override
    public User getCommentById(long userId, long comicId) {
        return null;
    }

    @Override
    public User getCommentsByComic(long comicID) {
        return null;
    }

    @Override
    public User saveComicBook(ComicBook comicBook) {
        return null;
    }

    @Override
    public boolean deleteComicBook(ComicBook comicBook) {
        return false;
    }

    @Override
    public boolean modifyComicBook(ComicBook comicBook) {
        return false;
    }
}
