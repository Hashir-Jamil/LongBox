package org.longbox.persistence.dao;

import java.util.List;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;

public interface StarRatingDao {

	StarRating getStarRatingById(long userId, long comicId);

    public List<StarRatingDto> getStarRatingsByComic(Long comicId);

    void saveStarRating(StarRatingDto StarRatingDTO);

    public List<StarRatingDto> getStarRatingsByUser(Long userId);

}