package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.ComponentOrientation;
@Getter
@Setter
public class HomeFrame extends JFrame {
    private final String SEARCH_COMIC_BOOK = "Search Comics Panel";
    private final String COMIC_COLLECTAION_PANEL = "Comic Collection Panel";
    private final String FAVORITES_PANEL = "User Favorites Panel";
    private final String PROFILE_PANEL = "Profile Panel";
    private final String ADD_COMIC_TO_REPO = "Add Comic To Repo";
    private final String TRENDING_COMICS = "Trending Comics";
    private JButton logOutButton;
    private JButton searchButtonNexus;
    private JButton addComicButton;
    private JButton comicCollectionButton;
    private JButton profileButton;
    private JButton favoritesButton;
    private JButton trendingButton;
    private JPanel nexusPanel;
    private JPanel activityPanel;
    private FavoritesPanel favoritesPanel;
    private TrendingPanel trendingComicsPanel;
    private JPanel comicCollectionPanel;
    private ProfilePanel profilePanel;
    private AddComicToRepoPanel addComicToRepoPanel;
    private CardLayout cardLayout;
    private UserSession userSession;
    private JLabel userNameLabel;

    public HomeFrame() {
        buildHomeFrame();
    }
    
    public HomeFrame(UserSession user) {
    	this.userSession = user;
    	buildHomeFrame();
    	((ComicRepositoryPanel) comicCollectionPanel).setUserSession(this.userSession);
    	favoritesPanel.setUserSession(this.userSession);
        userNameLabel = new JLabel(user.getUser().getUserName());
        userNameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        userNameLabel.setBounds(811, 19, 181, 16);
        nexusPanel.add(userNameLabel);
    }

    private void buildHomeFrame() {
        //initializing the home frame and its parent container
        setTitle("LongBox - Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 900);
        nexusPanel = new JPanel();
        nexusPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(nexusPanel);

        // adding elements to the ContentPane
        nexusPanel.setLayout(null);
        profilePanel  = new ProfilePanel(this.userSession);
        favoritesPanel = new FavoritesPanel();
        comicCollectionPanel = new ComicRepositoryPanel();
        addComicToRepoPanel = new AddComicToRepoPanel();
        trendingComicsPanel = new TrendingPanel();
        activityPanel = new JPanel();
        activityPanel.setBounds(10, 47, 1164, 803);
        nexusPanel.add(activityPanel);
        cardLayout = new CardLayout();
        activityPanel.setLayout(cardLayout);
        activityPanel.add(trendingComicsPanel, TRENDING_COMICS);
        activityPanel.add(comicCollectionPanel, COMIC_COLLECTAION_PANEL);
        activityPanel.add(favoritesPanel, FAVORITES_PANEL);
        activityPanel.add(profilePanel, PROFILE_PANEL);
        activityPanel.add(addComicToRepoPanel, ADD_COMIC_TO_REPO);

        //adding buttons
        comicCollectionButton = new JButton("Comic Repository");
        comicCollectionButton.setBounds(10, 11, 170, 25);
        nexusPanel.add(comicCollectionButton);

        addComicButton = new JButton("Add Comic");
        addComicButton.setBounds(190, 11, 170, 25);
        nexusPanel.add(addComicButton);

        favoritesButton = new JButton("Favorites");
        favoritesButton.setBounds(372, 11, 170, 25);
        nexusPanel.add(favoritesButton);
        
        profileButton = new JButton("Profile");
        profileButton.setBounds(554, 11, 170, 25);
        nexusPanel.add(profileButton);
        
        trendingButton = new JButton("Trending");
        trendingButton.setBounds(738, 11, 170, 25);
        nexusPanel.add(trendingButton);

        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(1004, 11, 170, 25);
        nexusPanel.add(logOutButton);
    }
}