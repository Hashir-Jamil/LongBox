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
    private List<CommentDTO> commentsList = new ArrayList<>();

    public ComicBookDTO(
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

    public ComicBookDTO(
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
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComicBookDTO that)) return false;
        return getYearPublished() == that.getYearPublished() && Objects.equals(getSeriesTitle(), that.getSeriesTitle()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getPublisher(), that.getPublisher()) && Objects.equals(getDateAdded(), that.getDateAdded());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeriesTitle(), getAuthor(), getPublisher(), getYearPublished(), getDateAdded());
    }

    public void sortCommentsByDateAscending() {
        Collections.sort(this.commentsList, new CommentDateComparator());
    }

    public void sortCommentsByDateDescending() {
        sortCommentsByDateAscending();
        Collections.reverse(this.commentsList);
    }

    public String[] genreStringToList(String genres) {
        return genres.split("[,\\s]+");
    }

    public static String genreListToString(String[] genresList) {
        String genres = "";
        for (int i = 0; i < genresList.length; i++) {
            genres = genres + genresList[i] + ", ";
        }
        return genres;
    }

}


