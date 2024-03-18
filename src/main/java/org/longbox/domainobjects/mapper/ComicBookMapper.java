package org.longbox.domainobjects.mapper;

import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.List;
import java.util.stream.Collectors;

public class ComicBookMapper {

    public static ComicBookDto toDto(ComicBook entity) {
        ComicBookDto dto = new ComicBookDto();
        dto.setId(entity.getId());
        dto.setSeriesTitle(entity.getSeriesTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setArtist(entity.getArtist());
        dto.setGenres(entity.getGenres() != null ? GenreUtils.genreStringToList(entity.getGenres()) : new String[0]);
        dto.setDescription(entity.getDescription());
        dto.setNumberOfIssues(entity.getNumberOfIssues());
        dto.setPublisher(entity.getPublisher());
        dto.setYearPublished(entity.getYearPublished());
        dto.setDateAdded(entity.getDateAdded());
        dto.setFavoritesCount(entity.getFavoritesCount());
        dto.setNorthAmericaFavoritesCount((entity.getNorthAmericaFavoritesCount()));
        dto.setSouthAmericaFavoritesCount((entity.getSouthAmericaFavoritesCount()));
        dto.setEuropeFavoritesCount((entity.getEuropeFavoritesCount()));
        dto.setAsiaFavoritesCount((entity.getAsiaFavoritesCount()));
        dto.setAfricaFavoritesCount((entity.getAfricaFavoritesCount()));
        dto.setOceaniaFavoritesCount((entity.getOceaniaFavoritesCount()));
        dto.setAntarcticaFavoritesCount((entity.getAntarcticaFavoritesCount()));
        return dto;
    }

    public static List<ComicBookDto> toDtoList(List<ComicBook> entityList) {
        return entityList.stream()
                .map(ComicBookMapper::toDto)
                .collect(Collectors.toList());
    }

    public static ComicBook toEntity(ComicBookDto dto) {
        ComicBook entity = new ComicBook();
        entity.setSeriesTitle(dto.getSeriesTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setArtist(dto.getArtist());
        entity.setGenres(GenreUtils.genreListToString(dto.getGenres()));
        entity.setDescription(dto.getDescription());
        entity.setNumberOfIssues(dto.getNumberOfIssues());
        entity.setPublisher(dto.getPublisher());
        entity.setYearPublished(dto.getYearPublished());
        entity.setDateAdded(dto.getDateAdded());
        entity.setFavoritesCount(dto.getFavoritesCount());
        entity.setNorthAmericaFavoritesCount(dto.getNorthAmericaFavoritesCount());
        entity.setSouthAmericaFavoritesCount((dto.getSouthAmericaFavoritesCount()));
        entity.setEuropeFavoritesCount((dto.getEuropeFavoritesCount()));
        entity.setAsiaFavoritesCount((dto.getAsiaFavoritesCount()));
        entity.setAfricaFavoritesCount((dto.getAfricaFavoritesCount()));
        entity.setOceaniaFavoritesCount((dto.getOceaniaFavoritesCount()));
        entity.setAntarcticaFavoritesCount((dto.getAntarcticaFavoritesCount()));
        return entity;
    }

    public static List<ComicBook> toEntityList(List<ComicBookDto> dtoList) {
        return dtoList.stream()
                .map(ComicBookMapper::toEntity)
                .collect(Collectors.toList());
    }
}
