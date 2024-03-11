package org.longbox.presentation.tablemodels;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.longbox.domainobjects.dto.ComicBookDto;

public class TrendingAllTimeTableModel extends DefaultTableModel {
	
	List<ComicBookDto> comicBookList;
    private String[] columnNames =
            {"Favorites Count",
            "Series Title",
            "Author",
            "Artist",
            "Number of Issues",
            "Publisher"};

	public TrendingAllTimeTableModel(List<ComicBookDto> comicBookList) {
		this.comicBookList = comicBookList;

        for (String columnName : columnNames) {
            addColumn(columnName);
        }

        for (ComicBookDto comicBook : comicBookList) {
            
            addRow(new Object[]{
            		comicBook.getFavoritesCount(),
                    comicBook.getSeriesTitle(),
                    comicBook.getAuthor(),
                    comicBook.getArtist(),
                    comicBook.getNumberOfIssues(),
                    comicBook.getPublisher()
            });
        }
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public long getComicIdAtRow(int row) {
        return comicBookList.get(row).getId();
    }

    public void updateData(List<ComicBookDto> newData) {
        comicBookList = newData;
        setRowCount(0); // Clear existing rows
        for (ComicBookDto comicBook : comicBookList) {
            addRow(new Object[]{
            		comicBook.getFavoritesCount(),
                    comicBook.getSeriesTitle(),
                    comicBook.getAuthor(),
                    comicBook.getArtist(),
                    comicBook.getNumberOfIssues(),
                    comicBook.getPublisher()
            });
        }
    }

}
