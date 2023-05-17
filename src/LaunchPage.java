import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class LaunchPage implements ActionListener {
    JFrame launchFrame = new JFrame();

    JLabel welcomeLabel = new JLabel("WELCOME TO CHESSMATE!");
    JButton newGameButton = new JButton("New Game");
    JButton loginButton = new JButton("Login");
    JButton signupButton = new JButton("Signup");
    ImageIcon chessLogo = new ImageIcon("resources/Chessmate Logo.png");
    JLabel logoLabel= new JLabel();

    LaunchPage() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToFrame();
        addActionEvent();
        logoLabel.setIcon(chessLogo);
        launchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchFrame.setVisible(true);
    }

    public void setLayoutManager() {
        launchFrame.setLayout(null);
        launchFrame.setTitle("Welcome to Chessmate!");
        newGameButton.setFocusable(false);
        loginButton.setFocusable(false);
        signupButton.setFocusable(false);

    }

    public void setLocationAndSize() {
        launchFrame.setSize(500, 500);
        launchFrame.setLocationRelativeTo(null);
        welcomeLabel.setBounds(150, 60, 200, 40);
        logoLabel.setBounds(65,60,350,182);
        newGameButton.setBounds(165, 300, 150, 30);
        loginButton.setBounds(165, 350, 150, 30);
        signupButton.setBounds(165, 400, 150, 30);
    }

    public void addComponentsToFrame() {
        // launchFrame.add(welcomeLabel);
        launchFrame.add(newGameButton);
        launchFrame.add(loginButton);
        launchFrame.add(signupButton);
        launchFrame.add(logoLabel);
        ImageIcon logo = new ImageIcon("resources/pieces/black king.png");
        launchFrame.setIconImage(logo.getImage());
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        newGameButton.addActionListener(this);
        signupButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            launchFrame.dispose();
            new LoginPage();
        }
        if (e.getSource() == newGameButton) {
            launchFrame.dispose();
            new GameInfo();
        }
        if (e.getSource() == signupButton) {
            launchFrame.dispose();
           new SignupPage();
        }
    }


}

