package org.longbox.domainobjects.mapper;

import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDob(entity.getDob());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setCountry(entity.getCountry());
        dto.setContinent(entity.getContinent());
        dto.setJoinDate(entity.getJoinDate());
        dto.setComicsReading(entity.getComicsReading());
        dto.setComicsFinished(entity.getComicsFinished());
        dto.setAboutMe(entity.getAboutMe());
        dto.setPreferredGenre(entity.getPreferredGenre() != null ? GenreUtils.genreStringToList(entity.getPreferredGenre()) : new String[0]);
        return dto;
    }


    public static List<UserDto> toDtoList(List<User> entityList) {
        return entityList.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public static User toEntity(UserDto dto) {
        User entity = new User();
        entity.setUserName(dto.getUserName());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setDob(dto.getDob());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setCountry(dto.getCountry());
        entity.setContinent(dto.getContinent());
        entity.setJoinDate(dto.getJoinDate());
        entity.setComicsReading(dto.getComicsReading());
        entity.setComicsFinished(dto.getComicsFinished());
        entity.setAboutMe(dto.getAboutMe());
        entity.setPreferredGenre(GenreUtils.genreListToString(dto.getPreferredGenre()));
        return entity;
    }

    public static List<User> toEntityList(List<UserDto> dtoList) {
        return dtoList.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }
}
