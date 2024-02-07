package org.longbox.authentication.GUI;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddComicPage extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField titleField;
    private JTextField issueNumberField;
    private JTextField publisherField;
    private JTextField seriesField;
    private JTextField writerField;
    private JTextField artistField;
    private JButton addButton;
    private JLabel messageLabel;
    private JButton backButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddComicPage frame = new AddComicPage();
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
    public AddComicPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 809, 554);
        setTitle("Add Comic Book");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Labels for various input fields
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
        titleLabel.setBounds(117, 70, 94, 16);
        contentPane.add(titleLabel);

        // Text fields for user input
        titleField = new JTextField();
        titleField.setBounds(107, 86, 267, 26);
        contentPane.add(titleField);
        titleField.setColumns(10);

        // Similar labels and text fields for other input fields...

        // Button to add comic to collection
        addButton = new JButton("Add Comic to Collection");
        addButton.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
        addButton.setEnabled(false);
        addButton.setBounds(107, 422, 608, 29);
        contentPane.add(addButton);

        // Message label for displaying validation messages
        messageLabel = new JLabel("");
        messageLabel.setVerticalTextPosition(SwingConstants.TOP);
        messageLabel.setBounds(117, 370, 588, 16);
        messageLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        messageLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
        contentPane.add(messageLabel);

        // Button to go back
        backButton = new JButton("Back");
        backButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
        backButton.setBounds(451, 458, 264, 29);
        contentPane.add(backButton);

        // Action listeners for text fields and button
        titleField.addActionListener(fieldsListener);
        issueNumberField.addActionListener(fieldsListener);
        publisherField.addActionListener(fieldsListener);
        seriesField.addActionListener(fieldsListener);
        writerField.addActionListener(fieldsListener);
        artistField.addActionListener(fieldsListener);
        addButton.addActionListener(this);

        // Focus listener for text fields
        titleField.addFocusListener(focusAdapter);
        issueNumberField.addFocusListener(focusAdapter);
        publisherField.addFocusListener(focusAdapter);
        seriesField.addFocusListener(focusAdapter);
        writerField.addFocusListener(focusAdapter);
        artistField.addFocusListener(focusAdapter);

        // Document listener for text fields
        titleField.getDocument().addDocumentListener(documentListener);
        issueNumberField.getDocument().addDocumentListener(documentListener);
        publisherField.getDocument().addDocumentListener(documentListener);
        seriesField.getDocument().addDocumentListener(documentListener);
        writerField.getDocument().addDocumentListener(documentListener);
        artistField.getDocument().addDocumentListener(documentListener);

        // Update button state initially
        updateButtonState();
    }

    // Action listener for text fields
    ActionListener fieldsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateButtonState();
        }
    };

    // Focus adapter for text fields
    FocusAdapter focusAdapter = new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            updateButtonState();
        }
    };

    // Document listener for text fields
    DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateButtonState();
        }
    };

    // Method to update button state based on text field values
    private void updateButtonState() {
        boolean enableButton = !titleField.getText().isEmpty() &&
                !issueNumberField.getText().isEmpty() &&
                !publisherField.getText().isEmpty() &&
                !seriesField.getText().isEmpty() &&
                !writerField.getText().isEmpty() &&
                !artistField.getText().isEmpty();

        addButton.setEnabled(enableButton);
    }

    // Action performed method for button
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton) {
            // Logic to add comic to collection
            // Retrieve values from text fields
            String title = titleField.getText();
            String issueNumber = issueNumberField.getText();
            String publisher = publisherField.getText();
            String series = seriesField.getText();
            String writer = writerField.getText();
            String artist = artistField.getText();
        } else if(e.getSource() == backButton) {
            // This section is logic to navigate back to the previous page which will be filled later
        }
    }
}
