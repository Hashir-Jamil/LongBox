package org.longbox.presentation.comicbook;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.controller.ComicBookInfoController;
import org.longbox.domainobjects.dto.ComicBookDto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

@Getter
@Setter
public class ComicBookFrame extends JFrame {
	private final String FRAME_TITLE = "Search Result";
	private final String COMIC_BOOK_INFO = "Comic Book Info";
	private JPanel comicBookPane;
	private ComicBookInfoPanel comicBookInfoPane;
	private ComicBookInfoController comicBookInfoController;
	private CardLayout cardLayout;
	private ComicBookDto comicBookResult;
	private UserSession userSession;

	public ComicBookFrame(ComicBookDto comicBook, UserSession user) {
		this.userSession = user;
		this.comicBookResult = comicBook;
		initComicBookPage(); 
	}
	
	private void initComicBookPage() {
		comicBookInfoPane = new ComicBookInfoPanel(this.comicBookResult, this.userSession);
		comicBookInfoController = new ComicBookInfoController(comicBookInfoPane);

		setTitle(FRAME_TITLE);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(100, 100, 1200, 900);
	    comicBookPane = new JPanel();
	    comicBookPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setLocationRelativeTo(null);
	    setContentPane(comicBookPane);

	    // adding elements to the pane
	    cardLayout = new CardLayout();
	    comicBookPane.setLayout(cardLayout);	
	    comicBookPane.add(comicBookInfoPane, COMIC_BOOK_INFO);
	}
}
