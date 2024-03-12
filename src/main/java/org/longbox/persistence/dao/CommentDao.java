package org.longbox.persistence.dao;

import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.entity.Comment;

import java.util.List;

public interface CommentDao {

    Comment getCommentById(long userId, long comicId);

    public List<CommentDto> getCommentsByComic(Long comicID);

    void saveComment(CommentDto commentDTO);

    public List<CommentDto> getCommentsByUser(Long userID);

}
