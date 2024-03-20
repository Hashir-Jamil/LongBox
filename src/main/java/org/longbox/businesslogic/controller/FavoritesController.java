package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.service.UserComicBookCollectionService;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.*;
import org.longbox.presentation.profile.FavoritesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FavoritesController implements ActionListener {
    private FavoritesPanel favoritesPanel;
    private UserComicBookCollectionService userComicBookCollectionService;

    public FavoritesController(FavoritesPanel favoritesPanel) {
        this.favoritesPanel = favoritesPanel;
        userComicBookCollectionService = new UserComicBookCollectionService(
                new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory()),
                new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory()),
                new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory()));
        addListners();
    }

    private void addListners() {
        this.favoritesPanel.getUnfavoriteButton().addActionListener(this);
        this.favoritesPanel.getRefreshButton().addActionListener(this);
        this.favoritesPanel.getTextField().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==  this.favoritesPanel.getUnfavoriteButton()) {
            int selectedRow = this.favoritesPanel.getComicBookTable().getSelectedRow();
            if (selectedRow != -1) {
                int confirmUnfavorite = JOptionPane.showConfirmDialog(
                        this.favoritesPanel,
                        "Are you sure you want to unfavorite this comic?",
                        "Unfavorite Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmUnfavorite == JOptionPane.YES_OPTION) {
                    long userId = this.favoritesPanel.getUserSession().getUser().getId();
                    long comicId = this.favoritesPanel.getComicBookTableModel().getComicIdAtRow(selectedRow);
                    boolean removed = userComicBookCollectionService.removeFromFavorites(userId, comicId);
                    if (removed) {
                        // Removal successful
                        this.favoritesPanel.getComicBookTableModel().removeRow(selectedRow);
                        JOptionPane.showMessageDialog(
                                this.favoritesPanel,
                                "Comic has been unfavorited.",
                                "Unfavorited",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.favoritesPanel.reloadData();
                    } else {
                        // Removal failed
                        JOptionPane.showMessageDialog(
                                this.favoritesPanel,
                                "Failed to remove comic from favorites.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == this.favoritesPanel.getRefreshButton()) {
            this.favoritesPanel.reloadData();
        } else if (e.getSource() == this.favoritesPanel.getTextField() && ! this.favoritesPanel.getTextField().getText().isEmpty()) {
            String searchBy = this.favoritesPanel.getTypeSelection().getSelectedItem().toString();
            String target = this.favoritesPanel.getTextField().getText();
            List<ComicBookDto> searchResults = null;
            ComicBookSearchUtils.comicAdvancedSearch(
                    searchBy,
                    target,
                    searchResults,
                    userComicBookCollectionService.getAllFavoritesComicBooks(),
                    this.favoritesPanel.getUserSession());
        }
    }
}
