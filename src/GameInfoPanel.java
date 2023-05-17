import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GameInfoPanel  {
    protected static JPanel timerPanel = new JPanel();
    protected static JPanel deadBlackPiecesPanel = new JPanel();
    protected static JPanel deadWhitePiecesPanel = new JPanel();
    protected static String whitePlayer = GameInfo.whitePlayerField.getText();
    protected static String blackPlayer = GameInfo.blackPlayerField.getText();
    protected static JLabel counterLabel1 = new JLabel("00:00");
    protected static JLabel counterLabel2 = new JLabel("00:00");
    protected static JLabel playerLabel1;
    protected static JLabel playerLabel2;
    protected static JLabel whitePlayerName = new JLabel(whitePlayer);
    protected static JLabel blackPlayerName = new JLabel(blackPlayer);
    protected static JLabel whiteTitle = new JLabel("WHITE");
    protected static JLabel blackTitle = new JLabel("BLACK");

    public static JLabel []Dead_pieces_white= new JLabel[15];
    public static JLabel []Dead_pieces_black= new JLabel[15];

    Font arialBold = new Font("Arial", Font.BOLD, 30);
    Font arialPlain = new Font("Arial", Font.PLAIN, 30);

    TitledBorder title1, title2;
    Border greenLine = BorderFactory.createLineBorder(new Color(118, 150, 86));
    Border compound = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(), BorderFactory.createLoweredSoftBevelBorder());


    public GameInfoPanel() {
        timerPanel.setLayout(null);
        timerPanel.setVisible(true);
        timerPanel.setSize(200, 600);
        GameTimer.setInitialValues();
        GameTimer.countdownTimer();
        setLocationAndSize();
        addComponentsToPanel();
        DeadPieces();
        timerPanel.setBackground(new Color(246, 246, 231));

        whitePlayer = GameInfo.whitePlayerName;
        blackPlayer = GameInfo.blackPlayerName;
        whitePlayerName.setText(whitePlayer);
        blackPlayerName.setText(blackPlayer);
        //Display the players' names the user entered in GameInfo

        System.out.println(whitePlayer);
        System.out.println(blackPlayer);


    }

    public void setLocationAndSize() {

        //Game Info Panel
        timerPanel.setPreferredSize(new Dimension(400, 1300));
        timerPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(118, 150, 86),5)));

        //Black Title
        blackTitle.setBounds(192, 50, 120, 50);
        blackTitle.setFont(new Font("Algerian", Font.BOLD, 35));

        //Black Player Logo
        playerLabel2 = new JLabel(new ImageIcon("resources/Pieces/black pawn.png"));
        playerLabel2.setBounds(102, 90, 100, 100);
        playerLabel2.setHorizontalAlignment(JLabel.CENTER);
        playerLabel2.setFont(arialBold);

        //Black Player Name
        blackPlayerName.setBounds(217, 110, 150, 30);
        blackPlayerName.setBackground(new Color(200, 200, 200));
        blackPlayerName.setFont(new Font("Arial", Font.BOLD, 15));

        //Black Player Counter
        counterLabel1.setBounds(177, 155, 150, 100);
        counterLabel1.setHorizontalAlignment(JLabel.CENTER);
        counterLabel1.setFont(arialPlain);
        title1 = BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(greenLine, compound), blackPlayerName.getText());
        title1.setTitleJustification(TitledBorder.LEFT);
        counterLabel1.setBorder(title1);


        // Dead Black Pieces Panel
        deadBlackPiecesPanel.setBounds(37, 260, 450, 120);
        deadBlackPiecesPanel.setBackground(new Color(118, 150, 86));

        //White Title
        whiteTitle.setBounds(192, 455, 120, 50);
        whiteTitle.setFont(new Font("Algerian", Font.BOLD, 35));

        //White Player Logo
        playerLabel1 = new JLabel(new ImageIcon("resources/Pieces/white pawn.png"));
        playerLabel1.setBounds(102, 495, 100, 100);
        playerLabel1.setHorizontalAlignment(JLabel.CENTER);
        playerLabel1.setFont(arialBold);

        //White Player Name
        whitePlayerName.setBounds(217, 515, 150, 30);
        whitePlayerName.setBackground(new Color(200, 200, 200));
        whitePlayerName.setFont(new Font("Arial", Font.BOLD, 15));

        //White Player Counter
        counterLabel2.setBounds(177, 560, 150, 100);
        counterLabel2.setHorizontalAlignment(JLabel.CENTER);
        counterLabel2.setFont(arialPlain);
        title2 = BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(greenLine, compound), whitePlayerName.getText());
        title2.setTitleJustification(TitledBorder.LEFT);
        counterLabel2.setBorder(title2);

        // White Black Pieces Panel
        deadWhitePiecesPanel.setBounds(37, 665, 450, 120);
        deadWhitePiecesPanel.setBackground(new Color(238, 238, 210));


    }

    public void addComponentsToPanel() {
        timerPanel.add(playerLabel1);
        timerPanel.add(playerLabel2);
        timerPanel.add(counterLabel1);
        timerPanel.add(counterLabel2);
        timerPanel.add(whitePlayerName);
        timerPanel.add(blackPlayerName);
        timerPanel.add(whiteTitle);
        timerPanel.add(blackTitle);
        timerPanel.add(deadBlackPiecesPanel);
        timerPanel.add(deadWhitePiecesPanel);

    }

    public static void DeadPieces(){
        //Dead White Pieces
        for(int i=0;i<15;i++)
        {
            Dead_pieces_white[i]=new JLabel();
            Dead_pieces_white[i].setPreferredSize(new Dimension(48,45));
            deadWhitePiecesPanel.add(Dead_pieces_white[i]);
        }

        //Dead Black Pieces
        for(int i=0;i<15;i++)
        {
            Dead_pieces_black[i]=new JLabel();
            Dead_pieces_black[i].setPreferredSize(new Dimension(48,45));
            deadBlackPiecesPanel.add(Dead_pieces_black[i]);
        }

    }
    public final JComponent getTimerPanel() {
        return timerPanel;
    }

}