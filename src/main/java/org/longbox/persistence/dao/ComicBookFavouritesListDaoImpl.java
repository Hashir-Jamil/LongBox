package org.longbox.persistence.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.ComicBookList;
import org.longbox.persistence.entity.User;
import org.longbox.utils.HibernateUtils;

import java.time.ZoneId;
import java.util.Date;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComicBookFavouritesListDaoImpl implements ComicBookFavouritesListDao {
    Session session = null;
    Transaction transaction = null;
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final ComicBookDao comicBookDao = new ComicBookDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void saveToFavorites(long userId, long comicBookId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        ComicBookList userFavourite = new ComicBookList(userId, comicBookId, date);

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(userFavourite);
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
    public void removeFromFavorites(long userId, long comicBookId) {
        // Implement removing comic book from user's favorites list in the database
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
                            "SELECT cb FROM ComicBook cb JOIN ComicBookList cbl ON cb.id = cbl.comicBookId WHERE cbl.userId = :userId",
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