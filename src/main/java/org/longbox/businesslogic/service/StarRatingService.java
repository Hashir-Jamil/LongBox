package org.longbox.businesslogic.service;

import java.util.List;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.persistence.dao.StarRatingDao;

public class StarRatingService {

	StarRatingDao starRatingDao;

	public StarRatingService(StarRatingDao starRatingDao) {
		this.starRatingDao = starRatingDao;
	}

	public List<StarRatingDto> getStarRatingsByComic(Long comicId) {
		return starRatingDao.getStarRatingsByComic(comicId);
	}

	public void saveStarRating(StarRatingDto starRatingDto) {
		starRatingDao.saveStarRating(starRatingDto);
	}
	
	public StarRating getStarRatingByID(long userId, Long comicId) {
		return starRatingDao.getStarRatingById(userId, comicId);
	}
}
