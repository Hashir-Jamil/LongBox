package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.service.CommentService;
import org.longbox.businesslogic.service.StarRatingService;
import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.persistence.dao.*;
import org.longbox.presentation.comicbook.ComicBookInfoPanel;
import org.longbox.presentation.otheruser.OtherUserProfileFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ComicBookInfoController implements ActionListener, MouseListener {
    private ComicBookInfoPanel comicBookInfoPanel;
    private CommentDaoImpl commentDaoImpl = new CommentDaoImpl(HibernateUtils.getSessionFactory());
    private CommentService commentService = new CommentService(commentDaoImpl);
    private StarRatingDao starRatingDao = new StarRatingDaoImpl(HibernateUtils.getSessionFactory());
    private StarRatingService starRatingService = new StarRatingService(starRatingDao);
    private UserService userService = new UserService(new UserDaoImpl(HibernateUtils.getSessionFactory()));
    public ComicBookInfoController(ComicBookInfoPanel comicBookInfoPanel){
        this.comicBookInfoPanel = comicBookInfoPanel;
        addListeners();
    }

    private void addListeners(){
        this.comicBookInfoPanel.getAddCommentButton().addActionListener(this);

        this.comicBookInfoPanel.getAddToFinishedButton().addActionListener(this);
        this.comicBookInfoPanel.getAddToFavouritesButton().addActionListener(this);
        this.comicBookInfoPanel.getAddToReadingButton().addActionListener(this);

        this.comicBookInfoPanel.getRemoveFromFavouritesButton().addActionListener(this);
        this.comicBookInfoPanel.getRemoveFromToReadingButton().addActionListener(this);
        this.comicBookInfoPanel.getRemoveFromFinishedButton().addActionListener(this);
        this.comicBookInfoPanel.getCommentList().addMouseListener(this);
        
        this.comicBookInfoPanel.getRatingsDropdown().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.comicBookInfoPanel.getAddCommentButton()){
            commentService.saveComment(this.comicBookInfoPanel.getComment());
            this.comicBookInfoPanel.displayComments();

        } else if (e.getSource() == this.comicBookInfoPanel.getAddToFavouritesButton()) {
            ComicBookFavouritesListDaoImpl favouritesListDaoImpl = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                favouritesListDaoImpl.saveToFavourites(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getAddToFavouritesButton().setEnabled(false); // Disable the button after adding
            this.comicBookInfoPanel.favouriteButtonStates();
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
        else if (e.getSource() == this.comicBookInfoPanel.getRemoveFromFavouritesButton()) {
            ComicBookFavouritesListDaoImpl favouritesListDaoImpl = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
            try {
                favouritesListDaoImpl.removeFromFavourites(this.comicBookInfoPanel.getUserSession().getUser().getId(), this.comicBookInfoPanel.getComicBookDTO().getId());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            this.comicBookInfoPanel.getRemoveFromFavouritesButton().setEnabled(false);
            this.comicBookInfoPanel.favouriteButtonStates();
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
        else if (e.getSource() == this.comicBookInfoPanel.getRatingsDropdown()) {
        	saveRating((String) this.comicBookInfoPanel.getRatingsDropdown().getSelectedItem());
        	//update avgRating and userRating
        	this.comicBookInfoPanel.displayAvgRating();
        	this.comicBookInfoPanel.displayUserRating();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() ==  this.comicBookInfoPanel.getCommentList() && e.getClickCount() == 2) {
            UserDto userDto = null;
            try {
                userDto = userService.getUserById(this.comicBookInfoPanel.getCommentList().getSelectedValue().getUserId());
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
            new OtherUserProfileFrame(userDto);
        }
    }

    private void saveRating(String rating) {
    	StarRatingDto starRatingDto = new StarRatingDto();
    	starRatingDto.setRating(Integer.parseInt(rating));
    	starRatingDto.setUserId(this.comicBookInfoPanel.getUserSession().getUser().getId());
    	starRatingDto.setComicBookId(this.comicBookInfoPanel.getComicBookDTO().getId());
    	starRatingService.saveStarRating(starRatingDto);
  
    	System.out.println(rating);
    	System.out.println(starRatingDto);
    	System.out.println("test");
    }
    
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
