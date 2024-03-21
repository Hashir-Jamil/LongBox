package org.longbox.presentation.tablemodels;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.longbox.domainobjects.dto.ComicBookDto;

public class TrendingRegionalTableModel extends DefaultTableModel {

private static final long serialVersionUID = 1L;
	
	List<ComicBookDto> comicBookList;
    private String[] columnNames =
            {"Favourites Count",
            "Series Title",
            "Author",
            "Artist",
            "Number of Issues",
            "Publisher"};

	public TrendingRegionalTableModel(List<ComicBookDto> comicBookList, String region) {	
		if(region.equals("North America")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getNorthAmericaFavouritesCount() > comicBookList.get(min).getNorthAmericaFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("South America")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getSouthAmericaFavouritesCount() > comicBookList.get(min).getSouthAmericaFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Europe")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getEuropeFavouritesCount() > comicBookList.get(min).getEuropeFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Asia")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getAsiaFavouritesCount() > comicBookList.get(min).getAsiaFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Africa")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getAfricaFavouritesCount() > comicBookList.get(min).getAfricaFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Oceania")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getOceaniaFavouritesCount() > comicBookList.get(min).getOceaniaFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else if(region.equals("Antarctica")) {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getAntarcticaFavouritesCount() > comicBookList.get(min).getAntarcticaFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		} else {
			for (int i = 0; i < comicBookList.size() - 1; i++) {
				int min = i;
				for (int j = i + 1; j < comicBookList.size(); j++) {
					if (comicBookList.get(j).getFavouritesCount() > comicBookList.get(min).getFavouritesCount()) {
						min = j;
					}
				}
				ComicBookDto temp = comicBookList.get(min);
				comicBookList.set(min, comicBookList.get(i));
				comicBookList.set(i, temp);		
			}
		}
		
		this.comicBookList = comicBookList;

        for (String columnName : columnNames) {
            addColumn(columnName);
        }

        for (ComicBookDto comicBook : comicBookList) {      	
        	if(region.equals("North America")) {
        		addRow(new Object[]{
	            		comicBook.getNorthAmericaFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("South America")) {
        		addRow(new Object[]{
	            		comicBook.getSouthAmericaFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Europe")) {
        		addRow(new Object[]{
	            		comicBook.getEuropeFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Asia")) {
        		addRow(new Object[]{
	            		comicBook.getAsiaFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Africa")) {
        		addRow(new Object[]{
	            		comicBook.getAfricaFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Oceania")) {
        		addRow(new Object[]{
	            		comicBook.getOceaniaFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else if(region.equals("Antarctica")) {
        		addRow(new Object[]{
	            		comicBook.getAntarcticaFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	} else {
        		addRow(new Object[]{
	            		comicBook.getFavouritesCount(),
	                    comicBook.getSeriesTitle(),
	                    comicBook.getAuthor(),
	                    comicBook.getArtist(),
	                    comicBook.getNumberOfIssues(),
	                    comicBook.getPublisher()
	            });
        	}
        }
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public long getComicIdAtRow(int row) {
        return comicBookList.get(row).getId();
    }

//    public void updateData(List<ComicBookDto> newData) {
//        comicBookList = newData;
//        setRowCount(0); // Clear existing rows
//        for (ComicBookDto comicBook : comicBookList) {
//            addRow(new Object[]{
//            		comicBook.getFavouritesCount(),
//                    comicBook.getSeriesTitle(),
//                    comicBook.getAuthor(),
//                    comicBook.getArtist(),
//                    comicBook.getNumberOfIssues(),
//                    comicBook.getPublisher()
//            });
//        }
//    }
}
