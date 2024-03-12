package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.service.CommentService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;
import org.longbox.persistence.dao.ComicBookReadingListDaoImpl;
import org.longbox.persistence.dao.CommentDaoImpl;
import org.longbox.presentation.comicbook.ComicBookInfoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComicBookInfoController implements ActionListener {
    private ComicBookInfoPanel comicBookInfoPanel;
    private CommentDaoImpl commentDaoImpl = new CommentDaoImpl(HibernateUtils.getSessionFactory());
    private CommentService commentService = new CommentService(commentDaoImpl);
    public ComicBookInfoController(ComicBookInfoPanel comicBookInfoPanel){
        this.comicBookInfoPanel = comicBookInfoPanel;
        addListeners();
    }

    private void addListeners(){
        this.comicBookInfoPanel.getAddCommentButton().addActionListener(this);

        this.comicBookInfoPanel.getAddToFinishedButton().addActionListener(this);
        this.comicBookInfoPanel.getAddToFavoritesButton().addActionListener(this);
        this.comicBookInfoPanel.getAddToReadingButton().addActionListener(this);

        this.comicBookInfoPanel.getRemoveFromFavoritesButton().addActionListener(this);
        this.comicBookInfoPanel.getRemoveFromToReadingButton().addActionListener(this);
        this.comicBookInfoPanel.getRemoveFromFinishedButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.comicBookInfoPanel.getAddCommentButton()){

            String message = this.comicBookInfoPanel.getCommentBox().getText();
            CommentDto newComment = new CommentDto(message, this.comicBookInfoPanel.getUserSession().getUser(), this.comicBookInfoPanel.getComicBookDTO());
            commentService.saveComment(newComment);
            this.comicBookInfoPanel.displayComments();

        } else if (e.getSource() == this.comicBookInfoPanel.getAddToFavoritesButton()) {
            ComicBookFavouritesListDaoImpl favoritesListDaoImpl = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                favoritesListDaoImpl.saveToFavorites(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getAddToFavoritesButton().setEnabled(false); // Disable the button after adding
            this.comicBookInfoPanel.favoriteButtonStates();
        }
        else if (e.getSource() == this.comicBookInfoPanel.getAddToFinishedButton()) {
            ComicBookFinishedListDaoImpl comicBookFinishedListDaoImpl = new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                comicBookFinishedListDaoImpl.saveToFinished(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getAddToFinishedButton().setEnabled(false); // Disable the button after adding
            this.comicBookInfoPanel.finishedButtonStates();
        }
        else if (e.getSource() == this.comicBookInfoPanel.getAddToReadingButton()) {
            ComicBookReadingListDaoImpl comicBookReadingListDaoImpl = new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                comicBookReadingListDaoImpl.saveToReading(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getAddToReadingButton().setEnabled(false); // Disable the button after adding
            this.comicBookInfoPanel.readingButtonStates();
        }
        else if (e.getSource() == this.comicBookInfoPanel.getRemoveFromFavoritesButton()) {
            ComicBookFavouritesListDaoImpl favoritesListDaoImpl = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                favoritesListDaoImpl.removeFromFavorites(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getRemoveFromFavoritesButton().setEnabled(false);
            this.comicBookInfoPanel.favoriteButtonStates();
        }
        else if (e.getSource() == this.comicBookInfoPanel.getRemoveFromFinishedButton()) {
            ComicBookFinishedListDaoImpl comicBookFinishedListDaoImpl = new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                comicBookFinishedListDaoImpl.removeFromFinished(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getRemoveFromFinishedButton().setEnabled(false);
            this.comicBookInfoPanel.finishedButtonStates();
        }
        else if (e.getSource() == this.comicBookInfoPanel.getRemoveFromToReadingButton()) {
            ComicBookReadingListDaoImpl comicBookReadingListDaoImpl = new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                comicBookReadingListDaoImpl.removeFromReading(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getRemoveFromToReadingButton().setEnabled(false);
            this.comicBookInfoPanel.readingButtonStates();
        }
    }
}
