package org.longbox.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.Date;
import java.util.Set;

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

    @Column(name = "genres")
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

    @OneToMany(mappedBy = "comicBook")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "favoritesList")
    private Set<User> usersFavorited;

    @ManyToMany(mappedBy = "finishedList")
    private Set<User> usersFinished;

    @ManyToMany(mappedBy = "readingList")
    private Set<User> usersReading;

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

    public ComicBook(ComicBookDTO comicBookDTO) {
        this.seriesTitle = comicBookDTO.getSeriesTitle();
        this.author = comicBookDTO.getAuthor();
        this.artist = comicBookDTO.getArtist();
        this.genres = ComicBookDTO.genreListToString(comicBookDTO.getGenres());
        this.description = comicBookDTO.getDescription();
        this.numberOfIssues = comicBookDTO.getNumberOfIssues();
        this.publisher = comicBookDTO.getPublisher();
        this.yearPublished = comicBookDTO.getYearPublished();
        this.dateAdded = new Date();
    }
}
