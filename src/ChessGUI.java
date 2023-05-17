
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;


public class ChessGUI {

    protected static final JPanel gui = new JPanel(new BorderLayout());//This is the BASE Panel
    public final JButton[][] chessBoardSquares = new JButton[8][8];//8*8 matrix of CHESS_BOARD CELLS buttons
    private JPanel chessBoard;//Panel for BOARD


    protected final JLabel message = new JLabel("Chessmate is ready to play!");

    public static final String col = "abcdefgh";


    //private Image img;

    ChessGUI() {
        initializeGui();
        initializeChessBoardSquares(new Board());

    }



    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 0));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        gui.setPreferredSize(new Dimension(1000, 600));
        ////




//new button action
        Action newGame = new AbstractAction("Start Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupNewGame();
            }
        };
        Action backToMenu = new AbstractAction("Back to Main Menu") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gui);
                frame.dispose();
                new LaunchPage();
                GameTimer.timer1.stop();
                GameTimer.timer2.stop();
            }
        };
//toolbar of buttons and message
        tools.add(newGame);
        JButton saveButton = new JButton("Save");
        tools.add(saveButton);
        saveButton.setFocusable(false);
        tools.add(backToMenu);
        tools.addSeparator();
        tools.add(message);
        /////////////////////////


//chessBoard size
        chessBoard = new JPanel(new GridLayout(0, 9)){
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize ;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int) d.getWidth(), (int) d.getHeight());
                } else if (
                        c.getWidth() > d.getWidth() &&
                                c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (Math.min(w, h));
                return new Dimension(700,700);
            }
        };

        chessBoard.setPreferredSize(new Dimension(1300, 1300));
        chessBoard.setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8),
                new LineBorder(new Color(118, 150, 86),5)));
        // Color ground = new Color(200, 200, 200);
        chessBoard.setBackground(new Color(246, 246, 231));

        Image backgroundImage = new ImageIcon("resources/background.jpg").getImage();
//board that contains chessboard and background
        JPanel board = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        //board.setBackground(ground);
        board.setOpaque(false);
        // board.drawImage(backgroundImage, 0, 0, null);

        board.add(chessBoard);
        gui.add(board);
        gui.setBackground(new Color(246, 246, 231));

    }


    Button_Handler[][] square=new Button_Handler[8][8];

    private void initializeChessBoardSquares(Board ChessBoard){

        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                square[i][j] = new Button_Handler(i, j, ChessBoard);
                Image img = null;
                // JButton b = new JButton();
                square[i][j].button.setMargin(new Insets(0, 0, 0, 0));
                square[i][j].button.setIcon(new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
                if ((j % 2 == 1 && i % 2 == 1)
                        || (j % 2 == 0 && i % 2 == 0)) {
                    square[i][j].button.setBackground(new Color(238, 238, 210));
                } else {
                    square[i][j].button.setBackground(new Color(118, 150, 86));
                }
                chessBoardSquares[j][i] = square[i][j].button;}}

        //letters above columns
        chessBoard.add(new JLabel(""));

        for (int i = 0; i < 8; i++) {
            JLabel colLabel = new JLabel(col.substring(i, i + 1), SwingConstants.CENTER);
            Font font = colLabel.getFont();
            Font boldFont = new Font("Arial", Font.BOLD, 25);
            colLabel.setFont(boldFont);
            colLabel.setForeground(new Color(118, 150, 86));
            chessBoard.add(colLabel);
        }

        //digits beside rows
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    JLabel colLabel = new JLabel("" + (i + 1), SwingConstants.CENTER);
                    Font font = colLabel.getFont();
                    Font boldFont = new Font("Arial", Font.BOLD, 25);
                    colLabel.setFont(boldFont);
                    colLabel.setForeground(new Color(118, 150, 86));
                    chessBoard.add(colLabel);

                }
                // chessBoard.add(chessBoardSquares[j][i]);
                chessBoard.add(chessBoardSquares[i][j]);


            }

        }

    }




    public final JComponent getGui() {
        return gui;
    }

    private void SetupNewGame() {

        message.setText("Make your move!");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++)
            {
                Piece p = Board.squares[i][j].getPiece();
                if(p!=null){
                    // Piece s = Board.squares[7][7].getPiece();

                    String currentImage= p.getIdToPath(p.id);
                    //System.out.println(i+" "+j+" " +p+" "+p.getColour());
                    square[j][i].button.setIcon(new ImageIcon(currentImage));}

                square[i][j].setListener(i, j, this,square);
            }
        }
        GameTimer.timer2.start();
    }

    public static void runGame(Board ChessBoad) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ChessGUI newGame = new ChessGUI();
                JFrame f = new JFrame("CHESSMATE");
                GameInfoPanel time =new GameInfoPanel();
                f.add(time.getTimerPanel(),BorderLayout.EAST);
                f.add(newGame.getGui(),BorderLayout.WEST);
                f.add(time.getTimerPanel());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                f.setPreferredSize(screenSize);
                f.pack();
                f.setResizable(false);
                f.setLocationByPlatform(true);
                ImageIcon logo = new ImageIcon("resources/pieces/black king.png");
                f.setIconImage(logo.getImage());
                f.setVisible(true);
                f.setLocationRelativeTo(null);


            }
        };
        SwingUtilities.invokeLater(r);
    }

    public static void main(String[] args) {
        ChessGUI csc=new ChessGUI();
        csc.SetupNewGame();
    }

}