package org.longbox.authentication.GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.ComponentOrientation;
import java.awt.Container;

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

