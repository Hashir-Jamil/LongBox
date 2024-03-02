package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.domainobjects.dto.CommentDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.Comment;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    @Override
    public Comment getCommentById(long userId, long comicId) { return null; }

    @Override
    public List<CommentDTO> getCommentsByComic(long comicID) {
        Session session = null;
        Transaction transaction = null;
        List<Comment> commentList = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            commentList = session.createQuery(
                            "SELECT c FROM Comment c WHERE c.comicBookId = :comicID ORDER BY c.commentDate DESC",
                            Comment.class)
                    .setParameter("comicID", comicID)
                    .list();
        }
        catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment c : commentList) {
            CommentDTO commentDTO = new CommentDTO(c);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    @Override
    public void saveComment(CommentDTO commentDTO) {
        Session session = null;
        Transaction transaction = null;
        Comment comment = new Comment(commentDTO);

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(comment);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CommentDTO> getCommentsByUser(long userID) {
        Session session = null;
        Transaction transaction = null;
        List<Comment> commentList = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            commentList = session.createQuery(
                            "SELECT c FROM Comment c WHERE c.userId = :userID",
                            Comment.class)
                    .setParameter("userID", userID)
                    .list();
        }
        catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment c : commentList) {
            CommentDTO commentDTO = new CommentDTO(c);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

}
