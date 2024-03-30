package org.longbox.domainobjects.dto;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ComicBookListItemDto {
    private Long comicBookId;
    private Long userId;
    private Date dateAdded;

    public ComicBookListItemDto(Long userId, Long comicBookId) {
        this.userId = userId;
        this.comicBookId = comicBookId;
        this.dateAdded = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBookListItemDto that = (ComicBookListItemDto) o;
        return Objects.equals(getComicBookId(), that.getComicBookId()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComicBookId(), getUserId());
    }

    @Override
    public String toString() {
        return "ComicBookListItemDto{" +
                "comicBookId=" + comicBookId +
                ", userId=" + userId +
                '}';
    }
}
