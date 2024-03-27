package org.longbox.persistence.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.domainobjects.entity.User;
import org.longbox.domainobjects.mapper.StarRatingMapper;

public class StarRatingDaoImpl implements StarRatingDao {
	private SessionFactory sessionFactory;

	public StarRatingDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	 }

	@Override
	public StarRating getStarRatingById(long userId, long comicId) { 
		Session session = null;
        StarRating starRating;
        
        try {
            session = sessionFactory.openSession();
            starRating = session.createQuery(
                            "SELECT s FROM StarRating s WHERE s.comicBook.id = :comicID AND s.user.id = :userID",
                            StarRating.class)
            		.setParameter("userID", userId)
                    .setParameter("comicID", comicId)
                    .uniqueResult();
        }
        catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return starRating;
	}

	@Override
	public List<StarRatingDto> getStarRatingsByComic(Long comicId) {
		Session session = null;
        List<StarRating> starRatingList;

        try {
            session = sessionFactory.openSession();
            starRatingList = session.createQuery(
                            "SELECT s FROM StarRating s WHERE s.comicBook.id = :comicID",
                            StarRating.class)
                    .setParameter("comicID", comicId)
                    .list();
        }
        catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return StarRatingMapper.toDtoList(starRatingList);
	}

	@Override
	public void saveStarRating(StarRatingDto StarRatingDto) {
		Session session = null;
		Transaction transaction = null;
		
		UserDaoImpl userDao = new UserDaoImpl(HibernateUtils.getSessionFactory());
		ComicBookDaoImpl comicBookDao = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
		
		try {
		    session = sessionFactory.openSession();
		    transaction = session.beginTransaction();
		
		    User user = userDao.getUserById(StarRatingDto.getUserId());
		    ComicBook comicBook = comicBookDao.getComicBookById(StarRatingDto.getComicBookId());
		
		    user = (User) session.merge(user);
		    comicBook = (ComicBook) session.merge(comicBook);
		
		    StarRating starRating = new StarRating(StarRatingDto, user, comicBook);
		
		    session.persist(starRating);
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
	public List<StarRatingDto> getStarRatingsByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
