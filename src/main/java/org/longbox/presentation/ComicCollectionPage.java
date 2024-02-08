package org.longbox.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollBar;

public class ComicCollectionPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComicCollectionPage frame = new ComicCollectionPage();
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
	public ComicCollectionPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel comicCollectionTitle = new JLabel("Comic Collection");
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(212, 11, 372, 43);
		contentPane.add(comicCollectionTitle);
		
		JLabel lblNewLabel_1 = new JLabel("Sort By:");
		lblNewLabel_1.setBounds(551, 65, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 86, 773, 14);
		contentPane.add(separator);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(621, 60, 162, 22);
		contentPane.add(comboBox);
		
		comboBox.addItem("A-Z");
		comboBox.addItem("Z-A");
		comboBox.addItem("Date Added (Recent)");
		comboBox.addItem("Date Added (Oldest)");
		comboBox.addItem("Publication Date (Recent)");
		comboBox.addItem("Publication Date (Oldest)");
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(766, 111, 17, 393);
		contentPane.add(scrollBar);
		
		JList list = new JList();
		list.setBounds(10, 111, 773, 393);
		contentPane.add(list);
	}
}
