package org.longbox.domainobjects.mapper;

import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

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

    public static List<CommentDto> toDtoList(List<Comment> entityList) {
        return entityList.stream()
                .map(CommentMapper::toDto)
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
