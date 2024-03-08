package org.longbox.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookReadingList;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;

import java.util.Collections;
import java.util.List;

public class ComicBookReadingListDaoImpl implements ComicBookReadingListDao {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public boolean saveToReading(User user, ComicBook comicBook) throws UserIDDoesNotExistException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            user = (User) session.merge(user);
            comicBook = (ComicBook) session.merge(comicBook);
            ComicBookReadingList readingListItem = new ComicBookReadingList(user,comicBook);
            session.persist(readingListItem);
            transaction.commit();
            return true;
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
        return false;
    }

    @Override
    public void removeFromReading(Long userId, Long comicBookId) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM ComicBookReadingList WHERE id.userId = :userId and id.comicBookId = :comicBookId");
            query.setParameter("userId", userId);
            query.setParameter("comicBookId", comicBookId);
            query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<ComicBook> getUsersReadingList(Long userId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Query<ComicBook> readingComicBooksList = session.createQuery("SELECT cbr.comicBook FROM ComicBookReadingList cbr WHERE cbr.user.id = :userId");
            readingComicBooksList.setParameter("userId", userId);
            return readingComicBooksList.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getListOfUsersReading(Long comicBookId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Query<User> allUsersWhoAreReading = session.createQuery("SELECT cbr.user FROM ComicBookReadingList cbr WHERE cbr.comicBook.id = :comicBookId");
            allUsersWhoAreReading.setParameter("comicBookId", comicBookId);
            return allUsersWhoAreReading.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return Collections.emptyList();
    }
}
