import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameOverWindow extends JFrame implements ActionListener {
    JFrame frame = new JFrame("GAME OVER!");
    JButton backButton = new JButton("Back to Main Menu");
    JLabel label = new JLabel();
    ImageIcon logo = new ImageIcon("resources/Pieces/black king.png");

    static String whitePlayer;
    static String blackPlayer;
    static String winner;
    static String timeElapsed;
    static boolean whiteLost=false;


    GameOverWindow() {
        GameTimer.TimeElapsedCalculator();
        timeElapsed=GameTimer.remainingTimeToString();
        whitePlayer=GameInfo.whitePlayerField.getText();
        blackPlayer=GameInfo.blackPlayerField.getText();
        if (GameTimer.isWhitePlayerTimeOver || whiteLost) {
            frame.setTitle("Game Over, Black wins!");
            winner=blackPlayer;
            User.WriteDataToFile();
        } else if (GameTimer.isBlackPlayerTimeOver || !whiteLost){
            frame.setTitle("Game Over, White wins!");
            winner=whitePlayer;
            User.WriteDataToFile();
        }
        setLocationAndSize();
        addComponentsToFrame();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.setIconImage(logo.getImage());
        frame.setVisible(true);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
    }

    public void setLocationAndSize(){
        frame.setSize(350,100);
        backButton.setBounds(90,20,150,30);
    }

    public void addComponentsToFrame(){
        frame.add(backButton);
        frame.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new LaunchPage();

        }
    }
    public static void main (String[] args) {
        new GameOverWindow();
    }
}