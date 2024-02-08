package org.longbox.presentation;

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

public class HomePage extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton logOutButton;
    private JPanel contentPane;
    private static JFrame frame;

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

    public void initiateRegUI() {
        //initializing the main frame
        setTitle("LongBox - Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 809, 554);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


        setLocationRelativeTo(null);
        setContentPane(contentPane);


        //Header label
        JLabel HeaderLabel = new JLabel("This is the LongBox Home Page!");
        HeaderLabel.setBounds(275, 20, 259, 16);
        HeaderLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));

        //Log Out label
        logOutButton = new JButton("Log Out");
        logOutButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
        logOutButton.setBounds(690, 10, 100, 20);
        logOutButton.addActionListener(this);

        // adding elements to the pane
        contentPane.setLayout(null);
        contentPane.add(HeaderLabel);
        contentPane.add(logOutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOutButton) {
            int confirmLogOut = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmLogOut == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "HELLO");
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
            }
        }

    }
}

