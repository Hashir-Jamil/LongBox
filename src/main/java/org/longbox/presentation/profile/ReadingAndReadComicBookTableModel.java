package org.longbox.presentation.profile;

import org.longbox.domainobjects.dto.ComicBookDTO;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ReadingAndReadComicBookTableModel extends DefaultTableModel{
	
	List<ComicBookDTO> comicBookList;
	private String[] columnNames =
        {"Series Title",
        "Author"};

	public ReadingAndReadComicBookTableModel(List<ComicBookDTO> comicBookList) {
        this.comicBookList = comicBookList;

        for (String columnName : columnNames) {
            addColumn(columnName);
        }

        for (ComicBookDTO comicBook : comicBookList) {
            addRow(new Object[]{
                    comicBook.getSeriesTitle(),
                    comicBook.getAuthor()
            });
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
