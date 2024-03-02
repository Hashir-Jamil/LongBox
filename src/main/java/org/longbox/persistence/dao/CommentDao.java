package org.longbox.persistence.dao;

import org.longbox.domainobjects.dto.CommentDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.Comment;
import org.longbox.persistence.entity.User;

import java.util.List;

public interface CommentDao {

    Comment getCommentById(long userId, long comicId);

    public List<CommentDTO> getCommentsByComic(long comicID);

    void saveComment(CommentDTO commentDTO);

    public List<CommentDTO> getCommentsByUser(long userID);

}
