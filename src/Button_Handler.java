import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class Button_Handler {
    public final String WHITE = "white";
    public final String BLACK = "black";
    JButton button;
    int X;
    int Y;
    ChessGUI cbcr;

    Board chessBoard;
    //Button_Handler handler;
    static String currentImage;
    Image img;
    static int previousX;
    static int previousY;

    static Piece previousPiece;
    static ArrayList<Tiles> previousPossibleMoves;
    ArrayList<Tiles> possibleMoves;
    static boolean whiteTurn = true;
    static int whitePiece = 0;
    static int blackPiece = 0;


    //CONSTRUCTOR
    public Button_Handler() {
        chessBoard = null;
        currentImage = null;


    }

    public Button_Handler(int x, int y, Board chessBoard) {
        this.button = new JButton();
        this.X = x;
        this.Y = y;
        this.chessBoard = chessBoard;
        currentImage = null;


    }


    public void setListener(int jj, int ii, ChessGUI cbcr, Button_Handler[][] handler) {
        cbcr.chessBoardSquares[jj][ii].addActionListener(e -> {
            int x = handler[jj][ii].getX();
            int y = handler[jj][ii].getY();

            System.out.println("xy " + x + " " + y);

            Piece p = Board.squares[x][y].getPiece();

            if (p != null) {
                currentImage = p.getIdToPath(p.id);

            }


//************** SOURCE SELECTED ***************

            if (sourceSelected(Board.squares)) {
                //System.out.println("source");

                previousX = x;
                previousY = y;
                previousPiece = p;
                if (p instanceof Pawn && Pawn.getPromote()) {
//                    Promotion.runPromote();
                }

                if (p == null) {
                    DialogMessage("NO PIECE ON THE SELECTED SQUARE");

                } else {
                    if (whiteTurn && p.getColour().equals(BLACK)) {
                        DialogMessage("WHITE'S TURN");
                        return;
                    } else if (!whiteTurn && p.getColour().equals(WHITE)) {
                        DialogMessage("BLACK'S TURN");
                        return;
                    }

                    if (kingInDanger(cbcr)) {
                        if (!(p instanceof King)) {
                            DialogMessage("KING UNDER CHECK!");
                            System.out.println("check");
                            clearAllHighlight(Board.squares);
                            return;
                        } else if (p instanceof King) {
                            possibleMoves = p.move(Board.squares);

                            if (possibleMoves.isEmpty()) {
                                if(p.getColour().equals("white")) {
                                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ChessGUI.gui);
                                    frame.dispose();
                                    GameOverWindow.whiteLost=true;
                                    new GameOverWindow();
                                } else {
                                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ChessGUI.gui);
                                    frame.dispose();
                                    GameOverWindow.whiteLost=false;
                                    new GameOverWindow();
                                }
                                return;
                            } else {
                                Border emptyBorder = BorderFactory.createEmptyBorder();
                                cbcr.chessBoardSquares[x][y].setBorder(emptyBorder);
                                clearAllHighlight(Board.squares);
                            }
                        }
                    }


                    currentImage = p.getIdToPath(p.id);
                    System.out.println(currentImage);
                    possibleMoves = p.move(Board.squares);
                    addHighLight(cbcr, possibleMoves);
                    previousPossibleMoves = possibleMoves;
                }
            }

//****************** DESTINATION SELECTED **************

            else {
                System.out.println("dest");
                if (p == null) {
                    // set and remove image from buttons
                    if (previousPossibleMoves.contains(Board.squares[x][y])) {
                        //set and remove Piece Image from squares
                        SetandRemoveImage(handler, x, y);

                        //set and remove Piece object from squares
                        SetandRemovePiece(x, y);

                        //remove highlight of buttons
                        removeHighlight(cbcr, previousPossibleMoves, Board.squares);
                        Player.timerChange();
                        changeTurn();


                    }
                } else//Attacking opponent piece
                {
                    if (x == previousX && y == previousY) {
                        removeHighlight(cbcr, previousPossibleMoves, Board.squares);
                        return;
                    }

                    if (previousPossibleMoves.contains(Board.squares[x][y])) {
                        currentImage = previousPiece.getIdToPath(previousPiece.getId());

                        String attackedPieceImage = p.getIdToPath(p.getId());

                        //set and remove Piece Image from squares
                        SetandRemoveImage(handler, x, y);

                        //set and remove Piece object from squares
                        SetandRemovePiece(x, y);

                        //set the dead piece image
                        setDeadPiece(p);

                        removeHighlight(cbcr, previousPossibleMoves, Board.squares);
                        previousX = x;
                        previousY = y;
                        Player.timerChange();
                        changeTurn();


                    }

                }

                if (whiteTurn)
                    cbcr.message.setText("WHITE'S TURN");

                else
                    cbcr.message.setText("BLACK'S TURN'");
                System.out.println("White turn = " + whiteTurn);
            }
        });
    }

    public boolean sourceSelected(Tiles[][] squares) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].highlight)
                    return false;
            }
        }

        return true;
    }

    public void addHighLight(ChessGUI cbcr, ArrayList<Tiles> possibleMoves) {
        Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
        for (Tiles sq : possibleMoves) {
            int x = sq.getX();
            int y = sq.getY();
            // Border border = sq.getPiece() == null ? greenBorder : BorderFactory.createLineBorder(Color.RED, 3);
            Border border;
            // if (sq.getPiece() == null) {
            border = greenBorder;
            if (sq.getPiece() != null) {
                if (whiteTurn && sq.getPiece().getColour().equals(WHITE)) {
                    border = BorderFactory.createLineBorder(Color.RED, 3);
                } else if (!whiteTurn && sq.getPiece().getColour().equals(BLACK)) {
                    border = BorderFactory.createLineBorder(Color.RED, 3);
                }
            }
            cbcr.chessBoardSquares[x][y].setBorder(border);
            sq.highlight = true;
        }
    }

    public void removeHighlight(ChessGUI cbcr, ArrayList<Tiles> possibleMoves, Tiles[][] squares) {
        for (Tiles sq : possibleMoves) {
            int x = sq.getX();
            int y = sq.getY();
            Border emptyBorder = BorderFactory.createEmptyBorder();
            cbcr.chessBoardSquares[x][y].setBorder(emptyBorder);
            sq.highlight = false;
        }
    }

    public void SetandRemoveImage(Button_Handler[][] handler, int x, int y) {

        try {
            img = ImageIO.read(new File((currentImage)));
            // img = ImageIO.read(getClass().getResource(previousPiece.getIdToPath(previousPiece.id)));
        } catch (IOException ex) {
            Logger.getLogger(Button_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }

        handler[y][x].button.setIcon(new ImageIcon(img));

        //remove image of moved piece from its previous position
        handler[previousY][previousX].button.setIcon(new ImageIcon());

    }

    public void SetandRemovePiece(int x, int y) {

        previousPiece.setX(x);
        previousPiece.setY(y);
        Board.squares[x][y].setPiece(previousPiece);

        Board.squares[previousX][previousY].setPiece(null);
    }

    public void setDeadPiece(Piece eatenPiece) {
        currentImage = eatenPiece.getIdToPath(eatenPiece.id); // image of eaten piece
        if (eatenPiece.getColour().equals(WHITE)) {
            try {
                img = ImageIO.read(new File((currentImage)));
                GameInfoPanel.Dead_pieces_white[whitePiece].setIcon(new ImageIcon(img));
                //adds image to label[] of index whitePiece
                whitePiece++; //counter for the white eaten pieces JLabel[] array size
            } catch (IOException ex) {
                Logger.getLogger(Button_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Adds eaten white piece to the side panel

        } else {
            try {

                img = ImageIO.read(new File((currentImage)));
                GameInfoPanel.Dead_pieces_black[blackPiece].setIcon(new ImageIcon(img));
                //adds image to label[] of index blackPiece
                blackPiece++; //counter for the black eaten pieces JLabel[] array size
            } catch (IOException ex) {
                Logger.getLogger(Button_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Adds eaten black piece to the side panel
        }


    }

    public void changeTurn() {
        whiteTurn = !whiteTurn;
    }

    public boolean kingInDanger(ChessGUI cbcr) {
        King k;
        if (whiteTurn)
            k = getWhiteKing();

        else
            k = getBlackKing();


        if (k.isInDanger(Board.squares, k.getX(), k.getY())) {
            Border redBorder = new LineBorder(Color.RED, 5);
            cbcr.chessBoardSquares[k.getX()][k.getY()].setBorder(redBorder);
            Board.squares[k.getX()][k.getY()].highlight = true;
            return true;
        }

        return false;
    }

    public void clearAllHighlight(Tiles[][] squares) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].highlight = false;
            }
        }
    }

    public King getWhiteKing() {
        return Board.wk;
    }

    public King getBlackKing() {
        return Board.bk;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void DialogMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }


}