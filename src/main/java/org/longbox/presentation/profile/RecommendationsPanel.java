package org.longbox.presentation.profile;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.RecommendationService;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

@Getter
@Setter
public class RecommendationsPanel extends JPanel {
	private static final String PAGE_TITLE = "Recommended Comic Books";
	private JPanel panel;
	private JLabel recommendationsTitleLabel;
	private JSeparator titleSeparator;
	private JTable recommendationsTable;
	private JScrollPane recommendationsTableScrollPane;
	
	public RecommendationsPanel(UserSession currentUser) {
		initTrendingPanel(currentUser);
	}
	
	private void initTrendingPanel(UserSession currentUser) {
			setBounds(10, 47, 1164, 803);
			setLayout(new BorderLayout());
			
			panel = new JPanel();
			add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			recommendationsTitleLabel = new JLabel(PAGE_TITLE);
			recommendationsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			recommendationsTitleLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
			recommendationsTitleLabel.setBounds(396, 11, 372, 43);
			panel.add(recommendationsTitleLabel);
			
			titleSeparator = new JSeparator();
			titleSeparator.setBounds(10, 92, 1144, 14);
			panel.add(titleSeparator);

			RecommendationService recommendationService = new RecommendationService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

			recommendationsTable = new JTable(new ComicBookTableModel(recommendationService.getRecommendations(currentUser.getUser())));
			recommendationsTable.setBounds(0, 0, 1, 1);
			panel.add(recommendationsTable);
			
			recommendationsTableScrollPane = new JScrollPane(recommendationsTable);
			recommendationsTableScrollPane.setBounds(10, 139, 1144, 653);
			panel.add(recommendationsTableScrollPane);
	}
}
