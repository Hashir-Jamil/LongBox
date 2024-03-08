package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookFavoritesList;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.List;

public class ComicBookFavouritesListDaoImpl implements ComicBookFavouritesListDao {

    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public void saveToFavorites(long userId, long comicBookId) throws UserIDDoesNotExistException {

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
    public int removeFromFavorites(long userId, long comicBookId) {
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
        return deletedEntities;
    }

    @Override
    public List<ComicBook> getFavoritesByUser(long userId) {
        // Implement retrieving favorite comic books for a given user from the database
        return null;
    }

    @Override
    public List<User> getUsersByComicBook(long comicBookId) {
        // Implement retrieving users who have added a specific comic book to their favorites list from the database
        return null;
    }

    @Override
    public List<ComicBookDTO> getAllFavoritesComicBooks() {
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
        List<ComicBookDTO> comicBookDTOList = new ArrayList<>();
        for (ComicBook c : favouritesLists) {
            ComicBookDTO comicBookDTO = new ComicBookDTO(c);
            comicBookDTOList.add(comicBookDTO);
        }
        return comicBookDTOList;
    }
}