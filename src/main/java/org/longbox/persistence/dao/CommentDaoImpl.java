package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.Comment;
import org.longbox.domainobjects.entity.User;
import org.longbox.config.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private SessionFactory sessionFactory;

    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Comment getCommentById(long userId, long comicId) { return null; }

    @Override
    public List<CommentDto> getCommentsByComic(Long comicId) {
        Session session = null;
        Transaction transaction = null;
        List<Comment> commentList;

        try {
            session = sessionFactory.openSession();
            commentList = session.createQuery(
                            "SELECT c FROM Comment c WHERE c.comicBook.id = :comicID ORDER BY c.commentDate DESC",
                            Comment.class)
                    .setParameter("comicID", comicId)
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
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment c : commentList) {
            CommentDto commentDTO = new CommentDto(c);
            commentDtoList.add(commentDTO);
        }
        return commentDtoList;
    }

    @Override
    public void saveComment(CommentDto commentDto) {
        Session session = null;
        Transaction transaction = null;

        UserDaoImpl userDao = new UserDaoImpl(HibernateUtils.getSessionFactory());
        ComicBookDaoImpl comicBookDao = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            User user = userDao.getUserById(commentDto.getUserId());
            ComicBook comicBook = comicBookDao.getComicBookById(commentDto.getComicBookId());

            user = (User) session.merge(user);
            comicBook = (ComicBook) session.merge(comicBook);

            Comment comment = new Comment(commentDto, user, comicBook);

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
    public List<CommentDto> getCommentsByUser(Long userId) {
        Session session = null;
        List<Comment> commentList = new ArrayList<>();

        try {
            session = sessionFactory.openSession();
            commentList = session.createQuery(
                            "SELECT c FROM Comment c WHERE c.user.id = :userID",
                            Comment.class)
                    .setParameter("userID", userId)
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
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment c : commentList) {
            CommentDto commentDTO = new CommentDto(c);
            commentDtoList.add(commentDTO);
        }
        return commentDtoList;
    }
}
