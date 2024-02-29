package org.longbox.presentation.profile;

import org.longbox.businesslogic.*;
import org.longbox.businesslogic.utils.ComicBookSearch;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.stubdatabase.ComicBookStubDB;
import org.longbox.presentation.authentication.AuthenticationFrame;
import org.longbox.presentation.comicbook.ComicBookFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;

public class HomeFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private UserSession userSession;
    private JButton logOutButton;
    private JPanel nexusPanel;
    private JPanel activityPanel;
    private FavoritesPanel favoritesPanel = new FavoritesPanel();
	private CardLayout cardLayout;
    private static JFrame frame;
    private JPanel comicCollectionPanel = new ComicRepositoryPanel();
    private ProfilePanel profilePanel = new ProfilePanel();
    private AddComicToRepoPanel addComicToRepoPanel = new AddComicToRepoPanel();
    private JButton searchButtonNexus;
    private JButton addComicButton;
    private JButton comicCollectionButton;
    private JButton profileButton;
    private JButton favoritesButton;
    private JLabel userNameLabel;
    private final String SEARCH_COMIC_BOOK = "Search Comics Panel";
    private final String COMIC_COLLECTAION_PANEL = "Comic Collection Panel";
    private final String FAVORITES_PANEL = "User Favorites Panel";
    private final String PROFILE_PANEL = "Profile Panel";
    private final String ADD_COMIC_TO_REPO = "Add Comic To Repo";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new HomeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public HomeFrame() {
        initiateRegUI();
    }
    
    public HomeFrame(UserSession user) {
    	initiateRegUI();
    	this.userSession = user;
        userNameLabel = new JLabel(user.getUser().getUserName());
       // userNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
        userNameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        userNameLabel.setBounds(811, 19, 181, 16);
        nexusPanel.add(userNameLabel);
        HTMLEditorKit kit = new HTMLEditorKit();
        profilePanel.getUserProfileInformationTextPane().setEditorKit(kit);
        profilePanel.getUserProfileInformationTextPane().setText(UserSession.generateUserProfileHTML(user));
        HTMLEditorKit kit1 = new HTMLEditorKit();
        profilePanel.getUserStatsTextPane().setEditorKit(kit1);
        profilePanel.getUserStatsTextPane().setText(UserSession.generateUserStatsHTML(user));
    }

    public void initiateRegUI() {
        //initializing the main frame
        setTitle("LongBox - Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 900);
        nexusPanel = new JPanel();
        nexusPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        setLocationRelativeTo(null);
        setContentPane(nexusPanel);

        // adding elements to the pane
        nexusPanel.setLayout(null);
        
        activityPanel = new JPanel();
        activityPanel.setBounds(10, 47, 1164, 803);
        nexusPanel.add(activityPanel);
        cardLayout = new CardLayout();
        activityPanel.setLayout(cardLayout);
        activityPanel.add(comicCollectionPanel, COMIC_COLLECTAION_PANEL);
        //activityPanel.add(searchPanel, SEARCH_COMIC_BOOK);
        activityPanel.add(favoritesPanel, FAVORITES_PANEL);
        activityPanel.add(profilePanel, PROFILE_PANEL);
        activityPanel.add(addComicToRepoPanel, ADD_COMIC_TO_REPO);
                
        //Log Out label
        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(1004, 11, 170, 25);
        nexusPanel.add(logOutButton);
        //logOutButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

        addComicButton = new JButton("Add Comic");
        addComicButton.addActionListener(this);
        
        addComicButton.setBounds(190, 11, 170, 25);
        nexusPanel.add(addComicButton);
        
        comicCollectionButton = new JButton("Comic Repository");
        comicCollectionButton.addActionListener(this);
        
        comicCollectionButton.setBounds(10, 11, 170, 25);
        nexusPanel.add(comicCollectionButton);
        
        profileButton = new JButton("Profile");
        profileButton.setBounds(370, 11, 170, 25);
        nexusPanel.add(profileButton);

        favoritesButton = new JButton("Favorites");
        favoritesButton.addActionListener(this);
        favoritesButton.setBounds(550, 11, 170, 25);
        nexusPanel.add(favoritesButton);

        profileButton.addActionListener(this);
        logOutButton.addActionListener(this);
        addComicToRepoPanel.getEnterComicBookButton().addActionListener(this);
        //searchButton = searchPanel.getSearchButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == logOutButton) {
            logoutUser();
        }

        if (e.getSource() == addComicToRepoPanel.getEnterComicBookButton()) {
            saveAddComicBookFormInput();
        }

        if (e.getSource() == comicCollectionButton) {
        	((ComicRepositoryPanel) comicCollectionPanel).panel.remove(((ComicRepositoryPanel) comicCollectionPanel).scrollPane);;
        	((ComicRepositoryPanel) comicCollectionPanel).reloadTable();
        	cardLayout.show(activityPanel, COMIC_COLLECTAION_PANEL);
        }

        if (e.getSource() == favoritesButton) {
            cardLayout.show(activityPanel, FAVORITES_PANEL);
        }

        if (e.getSource() == profileButton) {
        	cardLayout.show(activityPanel, PROFILE_PANEL);
        }

        if (e.getSource() == addComicButton) {
        	cardLayout.show(activityPanel, ADD_COMIC_TO_REPO);
        }
    }

    private void saveAddComicBookFormInput() {

        //Create data transfer object for comic book
        ComicBookDTO comicBook = new ComicBookDTO(
                addComicToRepoPanel.getComicSeriesTitleTextField().getText(),
                addComicToRepoPanel.getComicBookAuthorTextField().getText(),
                addComicToRepoPanel.getComicBookArtistTextField().getText(),
                addComicToRepoPanel.getGenresTextField().getText(),
                addComicToRepoPanel.getDescriptionTextField().getText(),
                Integer.parseInt(addComicToRepoPanel.getNumberOfIssuesTextField().getText()),
                addComicToRepoPanel.getPublisherTextField().getText(),
                Integer.parseInt(addComicToRepoPanel.getYearPublishedTextField().getText())
        );

        boolean isFavorite = addComicToRepoPanel.getFavoriteCheckbox().isSelected();
        if (isFavorite) {
            favoritesPanel.update(comicBook);
        }

        // Reset Text
        addComicToRepoPanel.getComicSeriesTitleTextField().setText("");
        addComicToRepoPanel.getComicBookAuthorTextField().setText("");
        addComicToRepoPanel.getComicBookArtistTextField().setText("");
        addComicToRepoPanel.getGenresTextField().setText("");
        addComicToRepoPanel.getDescriptionTextField().setText("");
        addComicToRepoPanel.getNumberOfIssuesTextField().setText("");
        addComicToRepoPanel.getPublisherTextField().setText("");
        addComicToRepoPanel.getYearPublishedTextField().setText("");

        ComicBookDaoImpl comicBookDao = new ComicBookDaoImpl();
        comicBookDao.saveComicBook(comicBook);

        JOptionPane.showMessageDialog(this, "Comic book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logoutUser(){
        int confirmLogOut = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmLogOut == JOptionPane.YES_OPTION) {
            userSession.clearUserSession();
            UserSession.setActiveUser(null);
            AuthenticationFrame loginPage = new AuthenticationFrame();
            loginPage.setVisible(true);
            dispose();
        }
    }

    private ComicBookDTO searchComicBookResults(String searchQuery) {
        ComicBookStubDB comicBookStubDB = new ComicBookStubDB();
        comicBookStubDB.loadComicBooks();
        return ComicBookSearch.searchComicBook(comicBookStubDB.getComicBookStubData(), searchQuery);
    }
}