package org.longbox.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comic_book")
public class ComicBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series_title")
    private String seriesTitle;

    @Column(name = "authors")
    private String author;
    
    @Column(name = "artists")
    private String artist;

    @Column(name = "genres")
    private String[] genres;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "number_of_issues")
    private int numberOfIssues;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "yearPublished")
    private int yearPublished;

    @Column(name = "date_added")
    private Date dateAdded;

    @Column(name = "favorite")
    private boolean favorite;

}
