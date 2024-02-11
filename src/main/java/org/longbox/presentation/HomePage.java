package org.longbox.presentation;

import org.longbox.businesslogic.*;
import org.longbox.domainobjects.UserDTO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;

public class HomePage extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private UserSession userSession;
    private JButton logOutButton;
    private JPanel nexusPanel;
    private JPanel activityPanel;
    private JPanel searchPanel = new SearchPage();
	private CardLayout cardLayout;
    private static JFrame frame;
    private JPanel comicCollectionPanel = new ComicCollectionPage();
    private JPanel profilePanel = new ProfilePage();
    private JPanel addComicToRepoPanel = new AddComicToRepoPage();
    private JButton searchButton;
    private JButton addComicButton;
    private JButton comicCollectionButton;
    private JButton profileButton;
    private JLabel userNameLabel;
//    private UserSession user;
    private final String SEARCH_COMIC_BOOK = "Search Comics Panel";
    private final String COMIC_COLLECTAION_PANEL = "Comic Collection Panel";
    private final String PROFILE_PANEL = "Profile Panel";
    private final String ADD_COMIC_TO_REPO = "Add Comic To Repo";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new HomePage();
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
    public HomePage() {
        initiateRegUI();
    }
    
    public HomePage(UserSession user) {
    	initiateRegUI();
    	this.userSession = user;
        userNameLabel = new JLabel(user.getUser().getUserName());
        userNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
        userNameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        userNameLabel.setBounds(811, 19, 181, 16);
        nexusPanel.add(userNameLabel);
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
        activityPanel.add(searchPanel, SEARCH_COMIC_BOOK);
        activityPanel.add(profilePanel, PROFILE_PANEL);
        activityPanel.add(addComicToRepoPanel, ADD_COMIC_TO_REPO);
                
        //Log Out label
        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(1004, 11, 170, 25);
        nexusPanel.add(logOutButton);
        logOutButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        
        searchButton.setBounds(376, 12, 170, 25);
        nexusPanel.add(searchButton);

        addComicButton = new JButton("Add Comic");
        addComicButton.addActionListener(this);
        
        addComicButton.setBounds(194, 11, 170, 25);
        nexusPanel.add(addComicButton);
        
        comicCollectionButton = new JButton("Comic Collection");
        comicCollectionButton.addActionListener(this);
        
        comicCollectionButton.setBounds(10, 11, 170, 25);
        nexusPanel.add(comicCollectionButton);
        
        profileButton = new JButton("Profile");
        profileButton.setBounds(567, 12, 170, 25);
        nexusPanel.add(profileButton);
        
       
        
        profileButton.addActionListener(this);
        
        logOutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOutButton) {
            int confirmLogOut = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmLogOut == JOptionPane.YES_OPTION) {
                
            	this.userSession.clearUserSession();
                AuthenticationPage loginPage = new AuthenticationPage();
                loginPage.setVisible(true);
                dispose();
            }
        }
        if(e.getSource() == comicCollectionButton) {
        	cardLayout.show(activityPanel, COMIC_COLLECTAION_PANEL);
        }
        if(e.getSource() == searchButton) {
        	cardLayout.show(activityPanel, SEARCH_COMIC_BOOK);
        }
        if(e.getSource() == profileButton) {
        	cardLayout.show(activityPanel, PROFILE_PANEL);
        }
        if(e.getSource() == addComicButton) {
        	cardLayout.show(activityPanel, ADD_COMIC_TO_REPO);
        }
        
    }
}

