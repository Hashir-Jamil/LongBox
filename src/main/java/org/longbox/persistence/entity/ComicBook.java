package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comic_book")
@Getter
@Setter
public class ComicBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series_title")
    private String seriesTitle;

    @Column(name = "author")
    private String author;
    
    @Column(name = "artist")
    private String artist;

    @Column(name = "genres", columnDefinition = "text[]")
    private String genres;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "number_of_issues")
    private int numberOfIssues;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "year_published")
    private int yearPublished;

    @Column(name = "date_added")
    private Date dateAdded;

    public ComicBook(
        String seriesTitle,
        String author,
        String artist,
        String genres,
        String description,
        int numberOfIssues,
        String publisher,
        int yearPublished,
        Date dateAdded
    )
    {
        this.seriesTitle = seriesTitle;
        this.author = author;
        this.artist = artist;
        this.genres = genres;
        this.description = description;
        this.numberOfIssues = numberOfIssues;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.dateAdded = new Date(dateAdded.getTime());
    }

    public ComicBook() {
    }
}
