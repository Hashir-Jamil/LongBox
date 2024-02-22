package org.longbox.businesslogic.utils;

import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.presentation.comicbook.ComicBookFrame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit;

public class ComicBookSearch {

    public static ComicBookDTO searchComicBook(List<ComicBookDTO> comicBookList, String searchQuery) {

        ComicBookDTO comicBook = new ComicBookDTO();

        for (ComicBookDTO comic : comicBookList) {
            if (comic.getSeriesTitle().contains(searchQuery)) {
                System.out.println(comic);
                return comic;
            }
        }
        return comicBook;
    }
    
    public static List<ComicBookDTO> searchComicBookByPublisher(List<ComicBookDTO> comicBookList, String publisher) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();
    	System.out.println(publisher);
    	for (ComicBookDTO comic : comicBookList) {
            if (comic.getPublisher().contains(publisher)) {
            	resultList.add(comic);
            }
        }
    	for (ComicBookDTO comic : resultList) {
    		System.out.println(comic.getPublisher());
    	}
    	return resultList;
    }
    
    public static List<ComicBookDTO> searchComicBookByTitle(List<ComicBookDTO> comicBookList, String title) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();
    	System.out.println(title);
    	for (ComicBookDTO comic : comicBookList) {
            if (comic.getSeriesTitle().contains(title)) {
            	resultList.add(comic);
            }
        }
    	for (ComicBookDTO comic : resultList) {
    		System.out.println(comic.getSeriesTitle());
    	}
    	return resultList;
    }
    
    public static List<ComicBookDTO> searchComicBookByYear(List<ComicBookDTO> comicBookList, String year) {
    	
    	List<ComicBookDTO> resultList = new ArrayList<ComicBookDTO>();
    	System.out.println(year);
    	for (ComicBookDTO comic : comicBookList) {
            if (String.valueOf(comic.getYearPublished()).equals(year)) {
            	resultList.add(comic);
            }
        }
    	for (ComicBookDTO comic : resultList) {
    		System.out.println(comic.getYearPublished());
    	}
    	return resultList;
    }
    
    public static void loadComicBookPage(ComicBookDTO comicBook) {
		ComicBookFrame comicBookFrame = new ComicBookFrame();
		comicBookFrame.setVisible(true);
		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
		comicBookFrame.getComicBookInfoPane().getComicBookInfoTextPane().setEditorKit(htmlEditorKit);
		comicBookFrame.getComicBookInfoPane().getComicBookInfoTextPane().setText(ComicBookSearch.generateComicBookHTML(comicBook));
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
