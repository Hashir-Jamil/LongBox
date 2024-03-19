package org.longbox.presentation.authentication;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationFrame extends JFrame {
    private static final String LOGIN = "login";
    private static final String REGISTRATION = "registration";

    private JPanel cardPanel;
	private CardLayout cardLayout;
    private LoginPanel loginPanel = new LoginPanel();
    private RegistrationPanel registrationPanel = new RegistrationPanel();

	public AuthenticationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        setTitle("LongBox");
        setLocationRelativeTo(null);
        
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(loginPanel, LOGIN);
        cardPanel.add(registrationPanel, REGISTRATION);

        // Set the default panel to login page
        cardLayout.show(cardPanel, LOGIN);
        add(cardPanel);
        setVisible(true);
	}

	public void showLogin(){
		cardLayout.show(cardPanel, LOGIN);
	}

	public void showRegistration(){
		cardLayout.show(cardPanel, REGISTRATION);
	}
}
