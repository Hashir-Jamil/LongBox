package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.businesslogic.service.RecommendationService;
import org.longbox.businesslogic.service.UserService;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.profile.HomeFrame;
import org.longbox.presentation.profile.ProfilePanel;
import org.longbox.presentation.profile.SocialPanel;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HomeController implements ActionListener {

    private HomeFrame homeFrame;
    private UserSession userSession;
    private ComicRepositoryController comicRepsoitoryController;
    private TrendingController trendingController;
    private ProfileController profileController;
    private SocialController socialController;
    private RecommendationsController recommendationsController;

    public HomeController(HomeFrame homeFrame) {
        this.homeFrame = homeFrame;
        this.userSession = homeFrame.getUserSession();
        this.comicRepsoitoryController = new ComicRepositoryController(this.homeFrame.getComicRepoPanel());
        this.trendingController = new TrendingController(this.homeFrame.getTrendingComicsPanel());
        this.profileController = new ProfileController(this.homeFrame.getProfilePanel());
        this.socialController = new SocialController(this.homeFrame.getSocialPanel());
        this.recommendationsController = new RecommendationsController(this.homeFrame.getRecommendationsPanel());
        addListeners();
    }

    private void addListeners() {
        this.homeFrame.getLogOutButton().addActionListener(this);
        this.homeFrame.getAddComicToRepoPanel().getEnterComicBookButton().addActionListener(this);
        this.homeFrame.getComicRepoButton().addActionListener(this);
        this.homeFrame.getFavouritesButton().addActionListener(this);
        this.homeFrame.getProfileButton().addActionListener(this);
        this.homeFrame.getAddComicButton().addActionListener(this);
        this.homeFrame.getTrendingButton().addActionListener(this);
        this.homeFrame.getRecommendationsButton().addActionListener(this);
        this.homeFrame.getSocialButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.homeFrame.getLogOutButton()) {
            logoutUser();
        }

        if (e.getSource() == this.homeFrame.getAddComicToRepoPanel().getEnterComicBookButton()) {
            try {
                saveAddComicBookFormInput();
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == this.homeFrame.getComicRepoButton()) {
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getCOMIC_REPO_PANEL());
            comicRepsoitoryController.reloadTable();
            
        }

        if (e.getSource() == this.homeFrame.getFavouritesButton()) {
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getFAVOURITES_PANEL());
            this.homeFrame.getFavouritesPanel().reloadData();
            FavouritesController favouritesController = new FavouritesController(this.homeFrame.getFavouritesPanel());
        }

        if (e.getSource() == this.homeFrame.getProfileButton()) {
            ((ProfilePanel) this.homeFrame.getProfilePanel()).getPanel().remove(((ProfilePanel) this.homeFrame.getProfilePanel()).getReadingPane());
            ((ProfilePanel) this.homeFrame.getProfilePanel()).getPanel().remove(((ProfilePanel) this.homeFrame.getProfilePanel()).getReadPane());
            try {
                ((ProfilePanel) this.homeFrame.getProfilePanel()).reloadTable();
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getPROFILE_PANEL());
        }

        if (e.getSource() == this.homeFrame.getAddComicButton()) {
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getADD_COMIC_TO_REPO());
        }

        if (e.getSource() == this.homeFrame.getTrendingButton()) {
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getTRENDING_PANEL());
            this.trendingController.reloadTrending();
        }

        if(e.getSource() == this.homeFrame.getRecommendationsButton()) {
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getRECCOMENDEDATIONS_PANEL());
        }
        
        if (e.getSource() == this.homeFrame.getSocialButton()) {
        	this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getSOCIAL_PANEL());
            this.socialController.reloadTable(new UserService(new UserDaoImpl(HibernateUtils.getSessionFactory())).getAllUsers());
        }
       
    }

    private void saveAddComicBookFormInput() throws UserIDDoesNotExistException {

        //Create data transfer object for comic book & first set string fields
        ComicBookDto comicBook = new ComicBookDto();
        comicBook.setSeriesTitle(this.homeFrame.getAddComicToRepoPanel().getComicSeriesTitleTextField().getText());
        comicBook.setAuthor(this.homeFrame.getAddComicToRepoPanel().getComicBookAuthorTextField().getText());
        comicBook.setArtist(this.homeFrame.getAddComicToRepoPanel().getComicBookArtistTextField().getText());
        comicBook.setGenres(GenreUtils.genreStringToList(this.homeFrame.getAddComicToRepoPanel().getGenresTextField().getText()));
        comicBook.setDescription(this.homeFrame.getAddComicToRepoPanel().getDescriptionTextField().getText());
        comicBook.setPublisher(this.homeFrame.getAddComicToRepoPanel().getPublisherTextField().getText());

        //Handle edge cases for number to string mappings
        comicBook.setNumberOfIssues(
            !Objects.equals(this.homeFrame.getAddComicToRepoPanel().getNumberOfIssuesTextField().getText(), "") ?
            Integer.parseInt(this.homeFrame.getAddComicToRepoPanel().getNumberOfIssuesTextField().getText()) : 0
        );
        comicBook.setYearPublished(
            !Objects.equals(this.homeFrame.getAddComicToRepoPanel().getYearPublishedTextField().getText(), "") ?
            Integer.parseInt(this.homeFrame.getAddComicToRepoPanel().getYearPublishedTextField().getText()) : 0
        );

        // Reset Text
        this.homeFrame.getAddComicToRepoPanel().getComicSeriesTitleTextField().setText("");
        this.homeFrame.getAddComicToRepoPanel().getComicBookAuthorTextField().setText("");
        this.homeFrame.getAddComicToRepoPanel().getComicBookArtistTextField().setText("");
        this.homeFrame.getAddComicToRepoPanel().getGenresTextField().setText("");
        this.homeFrame.getAddComicToRepoPanel().getDescriptionTextField().setText("");
        this.homeFrame.getAddComicToRepoPanel().getNumberOfIssuesTextField().setText("");
        this.homeFrame.getAddComicToRepoPanel().getPublisherTextField().setText("");
        this.homeFrame.getAddComicToRepoPanel().getYearPublishedTextField().setText("");

        ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));
        Long comicId = comicBookService.saveComicBook(comicBook);

        boolean isFavourite = this.homeFrame.getAddComicToRepoPanel().getFavouriteCheckbox().isSelected();
        if (isFavourite) {
            this.homeFrame.getFavouritesPanel().update(this.userSession.getUser().getId(), comicId);
        }
        JOptionPane.showMessageDialog(this.homeFrame, "Comic book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logoutUser(){
        int confirmLogOut = JOptionPane.showConfirmDialog(this.homeFrame, "Are you sure you want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmLogOut == JOptionPane.YES_OPTION) {
            userSession.clearUserSession();
            UserSession.setActiveUser(null);

            // close all active windows when logging out
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window != null && window.isShowing() && window != homeFrame) {
                    window.dispose();
                }
            }
            UserDaoImpl userDaoImpl = new UserDaoImpl(HibernateUtils.getSessionFactory());
            SwingUtilities.invokeLater(() -> {
                AuthenticationController authenticationController = new AuthenticationController(userDaoImpl);
            });
            this.homeFrame.dispose();
        }
    }
}
