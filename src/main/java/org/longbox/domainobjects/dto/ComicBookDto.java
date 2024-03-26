package org.longbox.domainobjects.dto;

import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.entity.ComicBook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
@NoArgsConstructor
public class ComicBookDto {
    private long id;
    private String seriesTitle;
    private String author;
    private String artist;
    private String[] genres;
    private String description;
    private int numberOfIssues;
    private String publisher;
    private int yearPublished;
    private Date dateAdded;
    private int northAmericaFavouritesCount;
    private int southAmericaFavouritesCount;
    private int europeFavouritesCount;
    private int asiaFavouritesCount;
    private int africaFavouritesCount;
    private int oceaniaFavouritesCount;
    private int antarcticaFavouritesCount;    
    private int favouritesCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBookDto that = (ComicBookDto) o;
        return getYearPublished() == that.getYearPublished() && Objects.equals(getSeriesTitle(), that.getSeriesTitle()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getArtist(), that.getArtist()) && Objects.equals(getPublisher(), that.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeriesTitle(), getAuthor(), getArtist(), getPublisher(), getYearPublished());
    }

    @Override
    public String toString() {
        return "ComicBookDTO{" +
                "id=" + id +
                ", seriesTitle='" + seriesTitle + '\'' +
                ", author='" + author + '\'' +
                ", artist='" + artist + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", description='" + description + '\'' +
                ", numberOfIssues=" + numberOfIssues +
                ", publisher='" + publisher + '\'' +
                ", yearPublished=" + yearPublished +
                ", dateAdded=" + dateAdded +
                ", favouritesCount=" + favouritesCount +
                '}';
    }
}