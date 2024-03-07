package org.longbox.businesslogic.utils;

import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.presentation.comicbook.ComicBookFrame;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit;

public class ComicBookSearch {

    public static ComicBookDTO searchComicBook(List<ComicBookDTO> comicBookList, String searchQuery) {

        ComicBookDTO comicBook = new ComicBookDTO();

        for (ComicBookDTO comic : comicBookList) {
            if (comic.getSeriesTitle().toUpperCase().contains(searchQuery.toUpperCase())) {
                System.out.println(comic);
                return comic;
            }
        }
        return comicBook;
    }
    
    public static List<ComicBookDTO> searchComicBookByPublisher(List<ComicBookDTO> comicBookList, String publisher) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();

    	for (ComicBookDTO comic : comicBookList) {
            if (comic.getPublisher().toUpperCase().contains(publisher.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDTO> searchComicBookByTitle(List<ComicBookDTO> comicBookList, String title) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();

    	for (ComicBookDTO comic : comicBookList) {
            if (comic.getSeriesTitle().toUpperCase().contains(title.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDTO> searchComicBookByAuthor(List<ComicBookDTO> comicBookList, String author) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();

    	for (ComicBookDTO comic : comicBookList) {
            if (comic.getAuthor().toUpperCase().contains(author.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDTO> searchComicBookByArtist(List<ComicBookDTO> comicBookList, String artist) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();

    	for (ComicBookDTO comic : comicBookList) {
            if (comic.getArtist().toUpperCase().contains(artist.toUpperCase())) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    public static List<ComicBookDTO> searchComicBookByYear(List<ComicBookDTO> comicBookList, String year) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();

    	for (ComicBookDTO comic : comicBookList) {
            if (String.valueOf(comic.getYearPublished()).equals(year)) {
            	resultList.add(comic);
            }
        }
    	return resultList;
    }
    
    public static List<ComicBookDTO> searchComicBookByGenre(List<ComicBookDTO> comicBookList, String genre) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();
    	String comicGenre;
   	
    	for (ComicBookDTO comic : comicBookList) {
    		for(int j = 0; j < comic.getGenres().length; j++) {
    			comicGenre = comic.getGenres()[j].toUpperCase().replaceAll("[^a-zA-Z ]+", "");
    			if ((comicGenre.toUpperCase()).contains(genre.toUpperCase())) {
            		resultList.add(comic);
            		break;
            	}
    		}
        }
    	return resultList;
    }
    
    public static void loadComicBookPage(ComicBookDTO comicBook, UserSession user) {
		ComicBookFrame comicBookFrame = new ComicBookFrame(comicBook, user);
		comicBookFrame.setVisible(true);
//		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
//		comicBookFrame.getComicBookInfoPane().getComicBookInfoTextPane().setEditorKit(htmlEditorKit);
//		comicBookFrame.getComicBookInfoPane().getComicBookInfoTextPane().setText(ComicBookSearch.generateComicBookHTML(comicBook));
	}
    
    public static List<ComicBookDTO> comicAdvancedSearch(String searchBy, String target, List<ComicBookDTO> searchResults, List<ComicBookDTO> searchList, UserSession user) {
    	switch (searchBy) {
			case "Title":
				searchResults = searchComicBookByTitle(searchList, target);
				break;
			case "Author":
				searchResults = searchComicBookByAuthor(searchList, target);
				break;
			case "Artist":
				searchResults = searchComicBookByArtist(searchList, target);
				break;
			case "Genre":
				searchResults = searchComicBookByGenre(searchList, target);
				break;
			case "Publisher":
				searchResults = searchComicBookByPublisher(searchList, target);
				break;
			case "Year":
				searchResults = searchComicBookByYear(searchList, target);
				break;
			default:
				searchResults = searchComicBookByPublisher(searchList, "");
				break;
    	}
    	loadComicBookResultsPage(searchResults, target, searchBy, user);
    	return searchResults;
    }
    
    private static void loadComicBookResultsPage(List<ComicBookDTO> displayResults, String target, String searchBy, UserSession user) {
		ComicBookSearchResultsFrame resultsPage = new ComicBookSearchResultsFrame(displayResults, target, searchBy, user);
		resultsPage.setVisible(true);
	}

    public static String generateComicBookHTML(ComicBookDTO comicBook) {
        String htmlContent = String.format(
                "<html><body><div align='center'><h1>Series Title: %s</h1><br" +
                        "<h2>Author Name: %s</h2><br" +
                        "<h2>Artist Name: %s</h2><br" +
                        "<h2>Genres: %s</h2><br" +
                        "<h2>Description: %s</h2><br" +
                        "<h2>Number of Issues %d</h2><br" +
                        "<h2>Year Published %d</h2><br" +
                        "<h2>Publisher: %s</h2><br" +
                        "<h2>Date Added To LongBox: %s</h2><br" +
                        "</div></body></html><br",
                comicBook.getSeriesTitle(),
                comicBook.getAuthor(),
                comicBook.getArtist(),
                ComicBookDTO.genreListToString(comicBook.getGenres()),
                comicBook.getDescription(),
                comicBook.getNumberOfIssues(),
                comicBook.getYearPublished(),
                comicBook.getPublisher(),
                comicBook.getDateAdded().toString()
        );
        return htmlContent;
    }
}
