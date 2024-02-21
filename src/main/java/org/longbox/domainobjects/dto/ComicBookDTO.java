package org.longbox.domainobjects.dto;


import lombok.*;
import org.longbox.businesslogic.comparators.CommentDateComparator;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ComicBookDTO {

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
    private boolean favorite;
    private List<CommentDTO> commentsList = new ArrayList<>();

    public ComicBookDTO(
      String seriesTitle,
      String author,
      String artist,
      String genres,
      String description,
      int numberOfIssues,
      String publisher,
      int yearPublished,
      boolean favorite
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
        this.favorite = favorite;
    };

    public ComicBookDTO(
            String seriesTitle,
            String author,
            String artist,
            String[] genres,
            String description,
            int numberOfIssues,
            String publisher,
            int yearPublished,
            boolean favorite
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
        this.favorite = favorite;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBookDTO that = (ComicBookDTO) o;
        return getId() == that.getId() && getNumberOfIssues() == that.getNumberOfIssues() && getYearPublished() == that.getYearPublished() && Objects.equals(getSeriesTitle(),
                that.getSeriesTitle()) && Objects.equals(getAuthor(),
                that.getAuthor()) && Objects.equals(getArtist(),
                that.getArtist()) && Objects.equals(getDescription(),
                that.getDescription()) && Objects.equals(getPublisher(),
                that.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSeriesTitle(), getAuthor(), getArtist(), getDescription(), getNumberOfIssues(), getPublisher(), getYearPublished());
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

}


