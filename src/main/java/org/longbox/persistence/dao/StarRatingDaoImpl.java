package org.longbox.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
        StarRating starRating = new StarRating();
        
        try {
            session = sessionFactory.openSession();
            starRating = session.createQuery(
                            "SELECT s FROM StarRating s WHERE s.user.id = :userID AND s.comicBook.id = :comicID",
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
        List<StarRating> starRatingList = new ArrayList<>();

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
	public void saveStarRating(StarRatingDto starRatingDto) {
		Session session = null;
		Transaction transaction = null;
		
		UserDaoImpl userDao = new UserDaoImpl(HibernateUtils.getSessionFactory());
		ComicBookDaoImpl comicBookDao = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
		int rating = starRatingDto.getRating();
		
		try {
		    session = sessionFactory.openSession();
		    transaction = session.beginTransaction();
		
		    User user = userDao.getUserById(starRatingDto.getUserId());
		    ComicBook comicBook = comicBookDao.getComicBookById(starRatingDto.getComicBookId());
		
		    user = (User) session.merge(user);
		    comicBook = (ComicBook) session.merge(comicBook);
		
		    
		    //create star rating if doesn't exist, otherwise update it
		    
		    
		    if (getStarRatingById(starRatingDto.getUserId(), starRatingDto.getComicBookId()) == null) {
		    	StarRating starRating = new StarRating();
		    	starRating.setRating(starRatingDto.getRating());
		    	starRating.setUser(user);
		    	starRating.setComicBook(comicBook);
		    	
		    	session.persist(starRating);
//		    	Query query = session.createQuery("INSERT INTO StarRating (user.id, comicBook.id, rating) VALUES (:userID, :comicID, :rating)", StarRating.class);
//		    	query.setParameter("rating",  rating);
//		    	query.setParameter("comicID",  starRatingDto.getComicBookId());
//		    	query.setParameter("userID", starRatingDto.getUserId());
//		    	query.executeUpdate();
		    	transaction.commit();
		    }
		    else {
		    	Query query = session.createQuery("UPDATE StarRating s SET rating = :rating WHERE s.user.id = :userID AND s.comicBook.id = :comicID", StarRating.class);
			  
			    query.setParameter("userID", starRatingDto.getUserId());
			    query.setParameter("comicID", starRatingDto.getComicBookId());
			    query.setParameter("rating", rating);
			    query.executeUpdate();
			    transaction.commit();
		    }
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
