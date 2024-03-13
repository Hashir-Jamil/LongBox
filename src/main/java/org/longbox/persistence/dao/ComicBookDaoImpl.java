package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;

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
        List<ComicBookDto> comicBookDtoList = new ArrayList<>();
        for (ComicBook c : comicBookList) {
            ComicBookDto comicBookDTO = new ComicBookDto(c);
            comicBookDtoList.add(comicBookDTO);
        }
        return comicBookDtoList;
    }
    
    public void favoriteComicBook(long comicId) {
    	Session session = null;
        Transaction transaction = null;
        
        int prevCount;
        int newCount;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("SELECT favoritesCount FROM ComicBook WHERE id = :comicId");
            query.setParameter("comicId", comicId);
            
            prevCount = (int) query.uniqueResult();
            System.out.println("HERE WE HAVE THE PREV VALUE AT: " + prevCount);
            newCount = (prevCount + 1);
            System.out.println("HERE WE HAVE THE NEW VALUE AT: " + newCount);
            
            Query updateQuery = session.createQuery("UPDATE ComicBook SET favoritesCount = :newCount WHERE id = :comicId");
            updateQuery.setParameter("newCount", newCount);
            updateQuery.setParameter("comicId", comicId);
            
            updateQuery.executeUpdate();
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
    
    public void unfavoriteComicBook(long comicId) {
    	Session session = null;
        Transaction transaction = null;
        
        int prevCount;
        int newCount;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("SELECT favoritesCount FROM ComicBook WHERE id = :comicId");
            query.setParameter("comicId", comicId);
            
            prevCount = (int) query.uniqueResult();
            System.out.println("HERE WE HAVE THE PREV VALUE AT: " + prevCount);
            if(prevCount <= 0) {
            	newCount = 0;
            } else {
            	newCount = (prevCount - 1);
            }
            System.out.println("HERE WE HAVE THE NEW VALUE AT: " + newCount);
            
            Query updateQuery = session.createQuery("UPDATE ComicBook SET favoritesCount = :newCount WHERE id = :comicId");
            updateQuery.setParameter("newCount", newCount);
            updateQuery.setParameter("comicId", comicId);
            
            updateQuery.executeUpdate();
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
}
