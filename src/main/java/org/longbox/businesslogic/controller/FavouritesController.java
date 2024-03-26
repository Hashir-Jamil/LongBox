package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.service.UserComicBookCollectionService;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.*;
import org.longbox.presentation.profile.FavouritesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FavouritesController implements ActionListener {
    private FavouritesPanel favouritesPanel;
    private UserComicBookCollectionService userComicBookCollectionService;

    public FavouritesController(FavouritesPanel favouritesPanel) {
        this.favouritesPanel = favouritesPanel;
        userComicBookCollectionService = new UserComicBookCollectionService(
                new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory()),
                new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory()),
                new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory()));
        addListeners();
    }

    private void addListeners() {
        this.favouritesPanel.getUnfavouriteButton().addActionListener(this);
        this.favouritesPanel.getRefreshButton().addActionListener(this);
        this.favouritesPanel.getTextField().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==  this.favouritesPanel.getUnfavouriteButton()) {
            int selectedRow = this.favouritesPanel.getComicBookTable().getSelectedRow();
            if (selectedRow != -1) {
                int confirmUnfavourite = JOptionPane.showConfirmDialog(
                        this.favouritesPanel,
                        "Are you sure you want to unfavourite this comic?",
                        "Unfavourite Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmUnfavourite == JOptionPane.YES_OPTION) {
                    long userId = this.favouritesPanel.getUserSession().getUser().getId();
                    long comicId = this.favouritesPanel.getComicBookTableModel().getComicIdAtRow(selectedRow);
                    boolean removed = userComicBookCollectionService.removeFromFavourites(userId, comicId);
                    if (removed) {
                        // Removal successful
                        this.favouritesPanel.getComicBookTableModel().removeRow(selectedRow);
                        JOptionPane.showMessageDialog(
                                this.favouritesPanel,
                                "Comic has been unfavourited.",
                                "Unfavourited",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.favouritesPanel.reloadData();
                    } else {
                        // Removal failed
                        JOptionPane.showMessageDialog(
                                this.favouritesPanel,
                                "Failed to remove comic from favourites.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == this.favouritesPanel.getRefreshButton()) {
            this.favouritesPanel.reloadData();
        } else if (e.getSource() == this.favouritesPanel.getTextField() && ! this.favouritesPanel.getTextField().getText().isEmpty()) {
            String searchBy = this.favouritesPanel.getTypeSelection().getSelectedItem().toString();
            String target = this.favouritesPanel.getTextField().getText();
            List<ComicBookDto> searchResults = null;
            ComicBookSearchUtils.comicAdvancedSearch(
                    searchBy,
                    target,
                    searchResults,
                    userComicBookCollectionService.getAllFavouritesComicBooks(),
                    this.favouritesPanel.getUserSession());
        }
    }
}
