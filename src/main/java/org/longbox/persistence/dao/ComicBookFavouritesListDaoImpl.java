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
import org.longbox.domainobjects.entity.ComicBookFavouritesList;
import org.longbox.domainobjects.entity.User;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.mapper.ComicBookMapper;

import java.util.ArrayList;
import java.util.List;

public class ComicBookFavouritesListDaoImpl implements ComicBookFavouritesListDao {
    SessionFactory sessionFactory;

    public ComicBookFavouritesListDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveToFavourites(Long userId, Long comicBookId) throws UserIDDoesNotExistException {

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

            ComicBookFavouritesList userFavourite = new ComicBookFavouritesList(user, comicBook);

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
    public boolean removeFromFavourites(Long userId, Long comicBookId) {
        Session session = null;
        Transaction transaction = null;
        int deletedEntities = 0;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ComicBookFavouritesList where id.userId = :userId and id.comicBookId = :comicBookId");
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
            return false; // Operation failed
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

        return deletedEntities > 0; // Return true if at least one entity was deleted, false otherwise
    }


    public boolean doesRecordExist(Long userId, Long comicBookId) {
        Session session = sessionFactory.openSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(*) FROM ComicBookFavouritesList c WHERE c.user.id = :userId AND c.comicBook.id = :comicBookId", Long.class);
        query.setParameter("userId", userId);
        query.setParameter("comicBookId", comicBookId);
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }

    @Override
    public List<ComicBook> getFavouritesByUser(Long userId) {
        // Implement retrieving favourite comic books for a given user from the database
        return null;
    }

    @Override
    public List<User> getUsersByComicBook(Long comicBookId) {
        // Implement retrieving users who have added a specific comic book to their favourites list from the database
        return null;
    }

    @Override
    public List<ComicBookDto> getAllFavouritesComicBooks() {
        Session session = null;
        Transaction transaction = null;
        List<ComicBook> favouritesLists = null;
        long userId = UserSession.getActiveUser().getUser().getId();

        try {
            session=sessionFactory.openSession();
            transaction = session.beginTransaction();
            favouritesLists = session.createQuery(
                            "SELECT cb FROM ComicBook cb JOIN ComicBookFavouritesList cbl ON cb.id = cbl.comicBook.id WHERE cbl.user.id = :userId",
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
        List<ComicBookDto> comicBookDtoList = ComicBookMapper.toDtoList(favouritesLists);
/*        for (ComicBook c : favouritesLists) {
            ComicBookDto comicBookDTO = new ComicBookDto(c);
            comicBookDtoList.add(comicBookDTO);
        }*/
        return comicBookDtoList;
    }
}