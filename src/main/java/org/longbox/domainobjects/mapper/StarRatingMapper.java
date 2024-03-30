package org.longbox.domainobjects.mapper;

import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.persistence.dao.ComicBookDaoImpl;

import java.util.List;
import java.util.stream.Collectors;

public class StarRatingMapper {
	
	static ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

    public static StarRatingDto toDto(StarRating entity) {
    	StarRatingDto dto = new StarRatingDto();
        dto.setUserId(entity.getUserId());
        dto.setComicBookId(entity.getComicBookId());
        dto.setRating(entity.getRating());
        return dto;
    }

    public static List<StarRatingDto> toDtoList(List<StarRating> entityList) {
        return entityList.stream()
                .map(StarRatingMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public static StarRating toEntity(StarRatingDto dto) {
    	StarRating entity = new StarRating();
        entity.setRating(dto.getRating());
        return entity;
    }
}
