import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInfo implements ActionListener, ChangeListener {
    static JFrame gameInfo = new JFrame();
    ImageIcon whitePlayer=new ImageIcon("resources/Pieces/white pawn.png");
    JLabel playerOneLabel = new JLabel(whitePlayer);
    ImageIcon blackPlayer=new ImageIcon("resources/Pieces/black pawn.png");
    JLabel playerTwoLabel = new JLabel(blackPlayer);
    JLabel timerLabel = new JLabel("Timer");
    static JLabel chosenTimerLabel = new JLabel();
    static JTextField whitePlayerField = new JTextField("Player one");
    static JTextField blackPlayerField = new JTextField("Player two");
    static String whitePlayerName;
    static String blackPlayerName;
    static JSlider timerSlider = new JSlider(5, 60, 5);
    JButton doneButton = new JButton("Done");
    JButton backButton = new JButton("Back");
    ImageIcon logo = new ImageIcon("resources/Pieces/black king.png");


    GameInfo() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToFrame();
        doneButton.addActionListener(this);
        timerSlider.addChangeListener(this);
        backButton.addActionListener(this);

        if (timerSlider.getValue() < 10)
            chosenTimerLabel.setText("0" + timerSlider.getValue() + ":00");
        else chosenTimerLabel.setText(timerSlider.getValue() + ":00");

    }

    public void setLayoutManager() {
        gameInfo.setLayout(null);
        gameInfo.setIconImage(logo.getImage());
        gameInfo.setTitle("Game Info");
        gameInfo.setVisible(true);
        gameInfo.setBounds(10, 10, 350, 350);
        gameInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameInfo.setResizable(false);
        gameInfo.setLocationRelativeTo(null);

        doneButton.setFocusable(false);
        backButton.setFocusable(false);

        timerSlider.setPaintTicks(true);
        timerSlider.setMinorTickSpacing(5);
        timerSlider.setPaintTrack(true);
        timerSlider.setPaintLabels(true);

    }

    public void setLocationAndSize() {
        playerOneLabel.setBounds(40, 30, 100, 60);
        playerTwoLabel.setBounds(40, 110, 100, 60);
        whitePlayerField.setBounds(140, 50, 150, 30);
        blackPlayerField.setBounds(140, 120, 150, 30);
        timerLabel.setBounds(55, 190, 150, 30);
        timerSlider.setBounds(140, 190, 150, 30);
        chosenTimerLabel.setBounds(195, 215, 250, 30);
        doneButton.setBounds(40,255,100,30);
        backButton.setBounds(190, 255,100,30);

    }
    public void addComponentsToFrame() {
        gameInfo.add(playerOneLabel);
        gameInfo.add(playerTwoLabel);
        gameInfo.add(whitePlayerField);
        gameInfo.add(blackPlayerField);
        gameInfo.add(timerLabel);
        gameInfo.add(timerSlider);
        gameInfo.add(chosenTimerLabel);
        gameInfo.add(doneButton);
        gameInfo.add(backButton);

    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if (timerSlider.getValue() < 10)
            chosenTimerLabel.setText("0" + timerSlider.getValue() + ":00");
        else chosenTimerLabel.setText(timerSlider.getValue() + ":00");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doneButton) {
            whitePlayerName=whitePlayerField.getText();
            blackPlayerName=blackPlayerField.getText(); //store the name the user entered to use in GameInfoPanel
            gameInfo.dispose();
            ChessGUI.runGame(new Board());
        }
        if (e.getSource() == backButton){
            gameInfo.dispose();
            new LaunchPage();
        }
    }
}