import java.awt.*;
import javax.swing.*;
public class Board {
    public final String WHITE = "white";
    public final String BLACK = "black";
    public static Tiles[][] squares;

    Board() {
        // squares=new Tiles[8][8];
        squares = new Tiles[8][8];
        initializeBoard();
        initializePieces();
        /* squares[5][7].setPiece(new Queen("WQ",5,7,WHITE));
        System.out.println(Board.squares[0][0].getPiece().getColour());
        System.out.println(Board.squares[7][3].getPiece());*/

    }

    //square color
    public void initializeBoard() {

        boolean squareColour ;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j % 2 == 1 && i % 2 == 1)
                        || (j % 2 == 0 && i % 2 == 0)) {
                    squareColour=true;
                    squares[i][j]=new Tiles(i, j, squareColour);
                } else {
                    squareColour=false;
                    squares[i][j] = new Tiles(i, j, squareColour);
                }

                squares[i][j].setPiece(null);

                // System.out.println(squares[i][j].getPiece());
            }

        }
    }

    public static King wk=new King("WK",7,4, "white");
    public static King bk=new King("BK",0,4, "black");

    public void initializePieces() {
        // Initialize white pieces
        squares[7][4].setPiece(wk);
        squares[7][3].setPiece(new Queen("WQ", 7, 3, WHITE));
        squares[7][2].setPiece(new Bishop("WB1", 7, 2, WHITE));
        squares[7][5].setPiece(new Bishop("WB2", 7, 5, WHITE));
        squares[7][1].setPiece(new Knight("WN1", 7, 1, WHITE));
        squares[7][6].setPiece(new Knight("WN2", 7, 6, WHITE));
        squares[7][0].setPiece(new Rook("WR1", 7, 0, WHITE));
        squares[7][7].setPiece(new Rook("WR2", 7, 7, WHITE));
        for (int i = 0; i < 8; i++) {
            squares[6][i].setPiece(new Pawn("WP" + (i + 1), 6, i, WHITE));
        }

        // Initialize black pieces
        squares[0][4].setPiece(bk);
        squares[0][3].setPiece(new Queen("BQ", 0, 3, BLACK));
        squares[0][2].setPiece(new Bishop("BB1", 0, 2, BLACK));
        squares[0][5].setPiece(new Bishop("BB2", 0, 5, BLACK));
        squares[0][1].setPiece(new Knight("BN1", 0, 1, BLACK));
        squares[0][6].setPiece(new Knight("BN2", 0, 6, BLACK));
        squares[0][0].setPiece(new Rook("BR1", 0, 0, BLACK));
        squares[0][7].setPiece(new Rook("BR2", 0, 7, BLACK));
        for (int i = 0; i < 8; i++) {
            squares[1][i].setPiece(new Pawn("BP" + (i + 1), 1, i, BLACK));
        }
    }



    public static Tiles getSquares(int x,int y) {
        return squares[x][y];

    }
    public static Piece getPiece(int x,int y){
        return squares[y][x].getPiece();
    }


   /* public static void main(String[] args) {
        Board board = new Board();

        System.out.println(squares[0][0].getPiece());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tiles tile = squares[i][j];
                Piece piece = tile.getPiece();
                if (piece != null) {
                    System.out.println("Piece at (" + i + ", " + j + ") is a " + piece.getClass().getSimpleName());
                } else {
                    System.out.println("No piece at (" + i + ", " + j + ")");
                }
            }
        }
    }*/
}

