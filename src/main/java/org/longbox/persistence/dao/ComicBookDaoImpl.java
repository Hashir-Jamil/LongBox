package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.mapper.ComicBookMapper;

import java.util.ArrayList;
import java.util.List;

public class ComicBookDaoImpl implements ComicBookDao {
    private SessionFactory sessionFactory;
    public ComicBookDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ComicBook getComicBookById(Long comicId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();;
            return session.get(ComicBook.class, comicId);
                    }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return new ComicBook();
    }

    @Override
    public ComicBook getComicBookBySeriesTitle(String seriesTitle) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM ComicBook WHERE seriesTitle = :seriesTitle", ComicBook.class);
            query.setParameter("seriesTitle", seriesTitle);
            return (ComicBook) query.uniqueResult();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return new ComicBook();
    }

    @Override
    public Long saveComicBook(ComicBook comicBook) {
        Session session = null;
        Transaction transaction = null;

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
        List<ComicBook> comicBookList = new ArrayList<>();

        try {
            session = sessionFactory.openSession();
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
        return ComicBookMapper.toDtoList(comicBookList);
    }

    public List<ComicBookDto> getRecommendationsByGenre(String[] genres) {
        if (genres == null) {
            return new ArrayList<ComicBookDto>();
        }
        Session session = null;
        List<ComicBook> comicBookList = new ArrayList<>();
        session = sessionFactory.openSession();
        String currentGenre = "";
        for (int i = 0; i < genres.length; i++) {
            currentGenre = genres[i];
            try {
                String hql = "SELECT cb FROM ComicBook cb WHERE cb.genres LIKE :currentGenre";
                Query<ComicBook> query = session.createQuery(hql, ComicBook.class);
                query.setParameter("currentGenre", "%" + currentGenre + "%");
                comicBookList.addAll(query.getResultList());
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        if (session != null) {
            session.close();
        }
        return ComicBookMapper.toDtoList(comicBookList);
    }
}
