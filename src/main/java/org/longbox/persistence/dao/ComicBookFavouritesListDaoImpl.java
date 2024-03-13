package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.ComicBookFavoritesList;
import org.longbox.domainobjects.entity.User;
import org.longbox.config.HibernateUtils;
import java.util.ArrayList;
import java.util.List;

public class ComicBookFavouritesListDaoImpl implements ComicBookFavouritesListDao {

    SessionFactory sessionFactory;

    public ComicBookFavouritesListDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveToFavorites(Long userId, Long comicBookId) throws UserIDDoesNotExistException {

        Session session = null;
        Transaction transaction = null;

        UserDaoImpl userDao = new UserDaoImpl(HibernateUtils.getSessionFactory());
        ComicBookDaoImpl comicBookDao = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            User user = userDao.getUserById(userId);
            ComicBook comicBook = comicBookDao.getComicBookById(comicBookId);

            user = (User) session.merge(user);
            comicBook = (ComicBook) session.merge(comicBook);

            ComicBookFavoritesList userFavourite = new ComicBookFavoritesList(user, comicBook);

            session.persist(userFavourite);
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
    public void removeFromFavorites(Long userId, Long comicBookId) {
        Session session = null;
        Transaction transaction = null;
        int deletedEntities = 0;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ComicBookFavoritesList where id.userId = :userId and id.comicBookId = :comicBookId");
            query.setParameter("userId", userId);
            query.setParameter("comicBookId", comicBookId);
            deletedEntities = query.executeUpdate();
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
                "SELECT COUNT(*) FROM ComicBookFavoritesList c WHERE c.user.id = :userId AND c.comicBook.id = :comicBookId", Long.class);
        query.setParameter("userId", userId);
        query.setParameter("comicBookId", comicBookId);
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }

    @Override
    public List<ComicBook> getFavoritesByUser(Long userId) {
        // Implement retrieving favorite comic books for a given user from the database
        return null;
    }

    @Override
    public List<User> getUsersByComicBook(Long comicBookId) {
        // Implement retrieving users who have added a specific comic book to their favorites list from the database
        return null;
    }

    @Override
    public List<ComicBookDto> getAllFavoritesComicBooks() {
        Session session = null;
        Transaction transaction = null;
        List<ComicBook> favouritesLists = null;
        long userId = UserSession.getActiveUser().getUser().getId();

        try {
            session=sessionFactory.openSession();
            transaction = session.beginTransaction();
            favouritesLists = session.createQuery(
                            "SELECT cb FROM ComicBook cb JOIN ComicBookFavoritesList cbl ON cb.id = cbl.comicBook.id WHERE cbl.user.id = :userId",
                            ComicBook.class
                    ).setParameter("userId", userId)
                    .getResultList();
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
        for (ComicBook c : favouritesLists) {
            ComicBookDto comicBookDTO = new ComicBookDto(c);
            comicBookDtoList.add(comicBookDTO);
        }
        return comicBookDtoList;
    }
}