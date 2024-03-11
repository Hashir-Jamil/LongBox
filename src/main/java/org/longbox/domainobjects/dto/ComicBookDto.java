package org.longbox.domainobjects.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.longbox.persistence.entity.ComicBook;

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
    private int favoritesCount = 0;

    public ComicBookDto(
        long id,
        String seriesTitle,
        String author,
        String artist,
        String[] genres,
        String description,
        int numberOfIssues,
        String publisher,
        int yearPublished
    ) {
        this.id = id;
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.artist = artist;
        this.genres = genres;
        this.description = description;
        this.numberOfIssues = numberOfIssues;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.dateAdded = new Date();
    }

    public ComicBookDto(
        String seriesTitle,
        String author,
        String artist,
        String genres,
        String description,
        int numberOfIssues,
        String publisher,
        int yearPublished
    ) {
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.artist = artist;
        this.genres = genreStringToList(genres);
        this.description = description;
        this.numberOfIssues = numberOfIssues;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.dateAdded = new Date();
    };

    public ComicBookDto(
        String seriesTitle,
        String author,
        String artist,
        String genres,
        String description,
        int numberOfIssues,
        String publisher,
        int yearPublished,
        Date date
    ) {
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.artist = artist;
        this.genres = genreStringToList(genres);
        this.description = description;
        this.numberOfIssues = numberOfIssues;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.dateAdded = new Date(date.getTime());
    };

    public ComicBookDto(
        String seriesTitle,
        String author,
        String artist,
        String[] genres,
        String description,
        int numberOfIssues,
        String publisher,
        int yearPublished
    ) {
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.artist = artist;
        this.genres = genres;
        this.description = description;
        this.numberOfIssues = numberOfIssues;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.dateAdded = new Date();
    }

    ;

    public ComicBookDto(
        String seriesTitle,
        String author,
        String artist,
        String[] genres,
        String description,
        int numberOfIssues,
        String publisher,
        int yearPublished,
        Date date
    ) {
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.artist = artist;
        this.genres = genres;
        this.description = description;
        this.numberOfIssues = numberOfIssues;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.dateAdded = date;
    };

    public ComicBookDto(ComicBook comicBookRecord) {
        this(
            comicBookRecord.getSeriesTitle(),
            comicBookRecord.getAuthor(),
            comicBookRecord.getArtist(),
            comicBookRecord.getGenres(),
            comicBookRecord.getDescription(),
            comicBookRecord.getNumberOfIssues(),
            comicBookRecord.getPublisher(),
            comicBookRecord.getYearPublished(),
            comicBookRecord.getDateAdded()
        );
        this.setId(comicBookRecord.getId());
    }

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

    public static String[] genreStringToList(String genres) {
        return genres.split(",\\s*");
    }

    public static String genreListToString(String[] genresList) {
        String genres = "";
        if (genresList.length == 0) {
            return genres;
        }
        if (genresList.length == 1) {
            return genresList[0];
        }
        for (int i = 0; i < genresList.length - 1; i++) {
            genres = genres + genresList[i] + ", ";
        }
        genres = genres + genresList[genresList.length - 1];
        return genres;
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
                ", favoritesCount=" + favoritesCount +
                '}';
    }
}
