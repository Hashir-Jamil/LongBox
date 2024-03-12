package org.longbox.businesslogic.service;

import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.dao.CommentDao;

import java.util.List;

public class CommentService {

    CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getCommentsByComic(Long comicId) {
        return commentDao.getCommentsByComic(comicId);
    }

    public void saveComment(CommentDto commentDto) {
        commentDao.saveComment(commentDto);
    }

    public List<CommentDto> getCommentsByUser(Long userId) {
        return commentDao.getCommentsByUser(userId);
    }

}
