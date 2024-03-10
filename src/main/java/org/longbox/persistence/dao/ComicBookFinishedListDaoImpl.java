package org.longbox.persistence.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookFinishedList;
import org.longbox.persistence.entity.User;
import org.longbox.config.HibernateUtils;

import java.util.Collections;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ComicBookFinishedListDaoImpl implements ComicBookFinishedListDao{

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    @Override
    public void saveToFinished(Long userId, Long comicBookId) throws UserIDDoesNotExistException {
        Session session = null;
        Transaction transaction = null;
        UserDaoImpl userDao = new UserDaoImpl();
        ComicBookDaoImpl comicBookDao = new ComicBookDaoImpl();

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = userDao.getUserById(userId);
            ComicBook comicBook = comicBookDao.getComicBookById(comicBookId);
            user = (User) session.merge(user);
            comicBook = (ComicBook) session.merge(comicBook);
            ComicBookFinishedList finishedItem = new ComicBookFinishedList(user, comicBook);
            session.persist(finishedItem);
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
    public void removeFromFinished(Long userId, Long comicBookId) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(
                    "delete from ComicBookFinishedList where id.userId = :userId and id.comicBookId = :comicBookId");
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

    public boolean doesRecordExist(Long userId, Long comicBookId) {
        Session session = sessionFactory.openSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(*) FROM ComicBookFinishedList c WHERE c.user.id = :userId AND c.comicBook.id = :comicBookId", Long.class);
        query.setParameter("userId", userId);
        query.setParameter("comicBookId", comicBookId);
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }

    public ComicBookFinishedList getFinishedComicBook(Long userId, Long comicBookId) {
        Session session = sessionFactory.openSession();
        Query<ComicBookFinishedList> query = session.createQuery(
                "FROM ComicBookFinishedList c WHERE c.user.id = :userId AND c.comicBook.id = :comicBookId", ComicBookFinishedList.class);
        query.setParameter("userId", userId);
        query.setParameter("comicBookId", comicBookId);
        return query.uniqueResult();
    }

    @Override
    public List<ComicBook> getUsersFinishedList(Long userId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Query<ComicBook> finishedComicBooksList = session.createQuery(
                    "SELECT cbf.comicBook FROM ComicBookFinishedList cbf WHERE cbf.user.id = :userId", ComicBook.class);
            finishedComicBooksList.setParameter("userId", userId);
            return finishedComicBooksList.getResultList();
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
    public List<User> getListOfUsersFinished(Long comicBookId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Query<User> allUsersWhoFinished = session.createQuery(
                    "SELECT cbf.user FROM ComicBookFinishedList cbf WHERE cbf.comicBook.id = :comicBookId", User.class);
            allUsersWhoFinished.setParameter("comicBookId", comicBookId);
            return allUsersWhoFinished.getResultList();
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
