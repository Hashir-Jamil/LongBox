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
import java.util.Date;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    @Override
    public Comment getCommentById(long userId, long comicId) { return null; }

    @Override
    public List<CommentDTO> getCommentsByComic(long comicID) {
        Session session = null;
        Transaction transaction = null;
        List<Comment> commentList = null;

        try {
            session = sessionFactory.openSession();
            commentList = session.createQuery(
                            "SELECT c FROM Comment c WHERE c.comicBook.id = :comicID ORDER BY c.commentDate DESC",
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

        UserDaoImpl userDao = new UserDaoImpl();
        ComicBookDaoImpl comicBookDao = new ComicBookDaoImpl();

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            User user = userDao.getUserById(commentDTO.getUserId());
            ComicBook comicBook = comicBookDao.getComicBookById(commentDTO.getComicBookId());

            user = (User) session.merge(user);
            comicBook = (ComicBook) session.merge(comicBook);

            Comment comment = new Comment(commentDTO, user, comicBook);

            session.persist(comment);
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
                            "SELECT c FROM Comment c WHERE c.user.id = :userID",
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
