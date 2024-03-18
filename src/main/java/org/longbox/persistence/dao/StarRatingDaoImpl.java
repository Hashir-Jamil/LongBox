package org.longbox.persistence.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;

public class StarRatingDaoImpl implements StarRatingDao {
	
	 private SessionFactory sessionFactory;

	    public StarRatingDaoImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }


	@Override
	public StarRating getStarRatingById(long userId, long comicId) {
		return null;
	}

	@Override
	public List<StarRatingDto> getStarRatingsByComic(Long comicID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveStarRating(StarRatingDto StarRatingDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StarRatingDto> getStarRatingsByUser(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
