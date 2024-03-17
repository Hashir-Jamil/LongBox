package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.HomeFrame;
import org.longbox.presentation.profile.ProfilePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController implements ActionListener {

    private HomeFrame homeFrame;
    private UserSession userSession;
    private UserService userService;
    
    ComicRepositoryController comicRepsoitoryController;
    TrendingController trendingController;

    public HomeController(HomeFrame homeFrame) {
        this.homeFrame = homeFrame;
        this.userSession = homeFrame.getUserSession();
        this.comicRepsoitoryController = new ComicRepositoryController(this.homeFrame.getComicRepoPanel());
        this.trendingController = new TrendingController(this.homeFrame.getTrendingComicsPanel());
        addListeners();
    }

    private void addListeners() {
        this.homeFrame.getLogOutButton().addActionListener(this);
        this.homeFrame.getAddComicToRepoPanel().getEnterComicBookButton().addActionListener(this);
        this.homeFrame.getComicRepoButton().addActionListener(this);
        this.homeFrame.getFavoritesButton().addActionListener(this);
        this.homeFrame.getProfileButton().addActionListener(this);
        this.homeFrame.getAddComicButton().addActionListener(this);
        this.homeFrame.getTrendingButton().addActionListener(this);
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

        if (e.getSource() == this.homeFrame.getFavoritesButton()) {
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getFAVORITES_PANEL());
            this.homeFrame.getFavoritesPanel().reloadData();
            FavoritesController favoritesController = new FavoritesController(this.homeFrame.getFavoritesPanel());
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
            this.homeFrame.getCardLayout().show(this.homeFrame.getActivityPanel(), this.homeFrame.getTRENDING_COMICS());
            this.trendingController.reloadTrending();
        }
    }

    private void saveAddComicBookFormInput() throws UserIDDoesNotExistException {

        //Create data transfer object for comic book
        ComicBookDto comicBook = new ComicBookDto(
                this.homeFrame.getAddComicToRepoPanel().getComicSeriesTitleTextField().getText(),
                this.homeFrame.getAddComicToRepoPanel().getComicBookAuthorTextField().getText(),
                this.homeFrame.getAddComicToRepoPanel().getComicBookArtistTextField().getText(),
                this.homeFrame.getAddComicToRepoPanel().getGenresTextField().getText(),
                this.homeFrame.getAddComicToRepoPanel().getDescriptionTextField().getText(),
                Integer.parseInt(this.homeFrame.getAddComicToRepoPanel().getNumberOfIssuesTextField().getText()),
                this.homeFrame.getAddComicToRepoPanel().getPublisherTextField().getText(),
                Integer.parseInt(this.homeFrame.getAddComicToRepoPanel().getYearPublishedTextField().getText())
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

        boolean isFavorite = this.homeFrame.getAddComicToRepoPanel().getFavoriteCheckbox().isSelected();
        if (isFavorite) {
            this.homeFrame.getFavoritesPanel().update(this.userSession.getUser().getId(), comicId);
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
            AuthenticationController authenticationController = new AuthenticationController();
            this.homeFrame.dispose();
        }
    }
}
