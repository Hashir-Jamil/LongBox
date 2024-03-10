package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class ComicBookDaoImpl implements ComicBookDao {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

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
            query.setParameter("seriesTitle", seriesTitle);
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
    public Long saveComicBook(ComicBookDto comicBookDTO) {
        Session session = null;
        Transaction transaction = null;
        ComicBook comicBook = new ComicBook(comicBookDTO);

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(comicBook);
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
        return comicBook.getId();
    }

    @Override
    public boolean deleteComicBook(ComicBook comicBook) {
        return false;
    }

    @Override
    public boolean modifyComicBook(ComicBook comicBook) {
        return false;
    }

    @Override
    public List<ComicBookDto> getAllComicBooks() {
        Session session = null;
        Transaction transaction = null;
        List<ComicBook> comicBookList = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            comicBookList = session.createQuery(
                    "SELECT cb FROM ComicBook cb",
                    ComicBook.class).list();
        }
        catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        List<ComicBookDto> comicBookDtoList = new ArrayList<>();
        for (ComicBook c : comicBookList) {
            ComicBookDto comicBookDTO = new ComicBookDto(c);
            comicBookDtoList.add(comicBookDTO);
        }
        return comicBookDtoList;
    }
}
