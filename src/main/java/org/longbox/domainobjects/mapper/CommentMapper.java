package org.longbox.domainobjects.mapper;

import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.entity.Comment;
import org.longbox.persistence.dao.ComicBookDaoImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {
	
	static ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

    public static CommentDto toDto(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setComicBookId(entity.getComicBookId());
        dto.setMessage(entity.getMessage());
        dto.setUserName(entity.getUserName());
        dto.setCommentDate(entity.getCommentDate());
        return dto;
    }
    
    public static CommentDto toDtoWithComicBook(Comment entity) {
    	ComicBookDto comicBookDto = comicBookService.getComicBookById(entity.getComicBookId());
    	CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setComicBookId(entity.getComicBookId());
        dto.setMessage(entity.getMessage());
        dto.setUserName(entity.getUserName());
        dto.setCommentDate(entity.getCommentDate());
        dto.setComicBook(comicBookDto);
        return dto;
    }

    public static List<CommentDto> toDtoList(List<Comment> entityList) {
        return entityList.stream()
                .map(CommentMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public static List<CommentDto> toDtoListWithComicBook(List<Comment> entityList) {
        return entityList.stream()
                .map(CommentMapper::toDtoWithComicBook)
                .collect(Collectors.toList());
    }

    public static Comment toEntity(CommentDto dto) {
        Comment entity = new Comment();
        entity.setId(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setCommentDate(dto.getCommentDate());
        entity.setUserName(dto.getUserName());
        return entity;
    }
}
