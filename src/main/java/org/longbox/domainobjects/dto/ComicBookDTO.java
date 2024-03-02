package org.longbox.domainobjects.dto;

import org.longbox.businesslogic.comparators.CommentDateComparator;
import org.longbox.persistence.entity.ComicBook;

import java.util.*;

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

    public ComicBookDTO() {
    }

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

    public ComicBookDTO(
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

    public ComicBookDTO(ComicBook comicBookRecord) {
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
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfIssues() {
        return numberOfIssues;
    }

    public void setNumberOfIssues(int numberOfIssues) {
        this.numberOfIssues = numberOfIssues;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}


