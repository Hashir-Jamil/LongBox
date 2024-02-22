package org.longbox.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;

public class ComicBookDaoImpl implements ComicBookDao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public ComicBook getComicBookById(long comicId) {
        Session session = null;
        Transaction transaction = null;
        ComicBook comicBook = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            comicBook = session.get(ComicBook.class, comicId);
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
        return comicBook;
    }

    @Override
    public ComicBook getComicBookBySeriesName(String seriesTitle) {
        Session session = null;
        Transaction transaction = null;
        ComicBook comicBook = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM ComicBook WHERE seriesTitle = :seriesTitle");
            comicBook = (ComicBook) query.uniqueResult();
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
        return comicBook;
    }

    @Override
    public void saveComicBook(ComicBook comicBook) {

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
