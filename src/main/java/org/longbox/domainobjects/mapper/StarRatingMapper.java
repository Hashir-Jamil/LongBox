package org.longbox.domainobjects.mapper;

import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.entity.StarRating;
import org.longbox.persistence.dao.ComicBookDaoImpl;

import java.util.List;
import java.util.stream.Collectors;

public class StarRatingMapper {
	
	static ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

    public static StarRatingDto toDto(StarRating entity) {
    	StarRatingDto dto = new StarRatingDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setComicBookId(entity.getComicBookId());
        dto.setRating(entity.getRating());
        return dto;
    }
    
    public static StarRatingDto toDtoWithComicBook(StarRating entity) {
    	ComicBookDto comicBookDto = comicBookService.getComicBookById(entity.getComicBookId());
    	StarRatingDto dto = new StarRatingDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setComicBookId(entity.getComicBookId());
        dto.setRating(entity.getRating());
        dto.setComicBook(comicBookDto);
        return dto;
    }

    public static List<StarRatingDto> toDtoList(List<StarRating> entityList) {
        return entityList.stream()
                .map(StarRatingMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public static List<StarRatingDto> toDtoListWithComicBook(List<StarRating> entityList) {
        return entityList.stream()
                .map(StarRatingMapper::toDtoWithComicBook)
                .collect(Collectors.toList());
    }

    public static StarRating toEntity(StarRatingDto dto) {
    	StarRating entity = new StarRating();
        entity.setId(dto.getId());
        entity.setRating(dto.getRating());
        return entity;
    }
}
