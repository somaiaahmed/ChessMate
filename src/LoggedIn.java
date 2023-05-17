import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedIn extends JFrame implements ActionListener {
    JFrame accountFrame = new JFrame("Welcome Back, " + LoginPage.loggedInUser + "!");
    JLabel logoLabel= new JLabel();
    JButton newGameButton = new JButton("New Game");
    JButton previousGamesButton = new JButton("Previous Games");
    JButton backButton = new JButton("Back");

    ImageIcon logo =new ImageIcon("resources/pieces/black king.png");
    LoggedIn() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToFrame();
        addActionEvent();
        accountFrame.setLocationRelativeTo(null);
    }

    public void setLayoutManager() {
        accountFrame.setLayout(null);
        accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accountFrame.setResizable(false);
        accountFrame.setVisible(true);
        accountFrame.setIconImage(logo.getImage());
        logoLabel.setIcon(logo);
        newGameButton.setFocusable(false);
        previousGamesButton.setFocusable(false);
        backButton.setFocusable(false);
    }

    public void setLocationAndSize() {
        accountFrame.setSize(350, 350);
        logoLabel.setBounds(140,25,100,100);
        newGameButton.setBounds(120, 130, 100, 30);
        previousGamesButton.setBounds(95, 180, 150, 30);
        backButton.setBounds(120, 230, 100, 30);
    }

    public void addComponentsToFrame() {
        accountFrame.add(newGameButton);
        accountFrame.add(previousGamesButton);
        accountFrame.add(backButton);
        accountFrame.add(logoLabel);
    }

    public static void main(String[] args) {
        new LoggedIn();
    }
    public void addActionEvent(){
        newGameButton.addActionListener(this);
        previousGamesButton.addActionListener(this);
        backButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            accountFrame.dispose();
            new GameInfo();
        }
        if (e.getSource() == previousGamesButton) {
            accountFrame.dispose();
            new PreviousGames();
        }
        if (e.getSource() == backButton) {
            accountFrame.dispose();
            new LaunchPage();
        }
    }
}