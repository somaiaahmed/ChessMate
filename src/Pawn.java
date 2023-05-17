import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Pawn extends Piece  {

    //int count;
    Piece piece;
    public static boolean canPromote = false;

    public static void promote(Pawn pawn) {
        if (pawn.isEligibleForPromotion()) {
            String[] options = {"Queen", "Rook", "Bishop", "Knight"};
            int choice = JOptionPane.showOptionDialog(null, "Choose a piece to promote to", "Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choice == JOptionPane.CLOSED_OPTION) {
                // Player closed the dialog box without choosing a piece to promote to
                return;
            }
            Piece newPiece = null;
            switch (choice) {
                case 0:
                    newPiece = new Queen(pawn.getId() + "q", pawn.getX(), pawn.getY(), pawn.getColour());
                    break;
                case 1:
                    newPiece = new Rook(pawn.getId() + "r", pawn.getX(), pawn.getY(), pawn.getColour());
                    break;
                case 2:
                    newPiece = new Bishop(pawn.getId() + "b", pawn.getX(), pawn.getY(), pawn.getColour());
                    break;
                case 3:
                    newPiece = new Knight(pawn.getId() + "k", pawn.getX(), pawn.getY(), pawn.getColour());
                    break;
            }
            Board.squares[pawn.getX()][pawn.getY()].setPiece(newPiece);
            Board.squares[pawn.getX()][pawn.getY()].highlight = false;
            Board.squares[pawn.getX()][pawn.getY()].button.repaint();

        }
    }

    public static boolean pColor;
    public void getPColour(){
        pColor= !Objects.equals(this.getColour(), "black");
    }
    Pawn(String id,int x,int y,String colour){
        this.X = x;
        this.Y = y;
        this.not = "P";
        //count = 0;
        this.id = id;
        possibleMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        this.colour = colour;
        this.getPColour();
      /*  if(Promotion.done){
            //promote();
        }*/

        if(colour.equals(WHITE)){
            setIdToPath(id,"resources/pieces/white pawn.png");
        }
        else
            setIdToPath(id,"resources/pieces/black pawn.png");

    }


    @Override
    public ArrayList<Tiles> move(Tiles[][] currentState) {

        possibleMoves.clear();
        //WHITE PAWNS
        if(this.getColour().equals(WHITE))
        {
            //PAWNS MOVING FIRST TIME
            if(this.X == 6)
            {
                if(currentState[this.X-1][this.Y].getPiece() == null && currentState[this.X-2][this.Y].getPiece() == null)
                {
                    currentState[this.X-2][this.Y].highlight = true;
                    possibleMoves.add(currentState[this.X-2][this.Y]);
                }
            }

            //Move straight upwards
            if(this.X-1>=0 && currentState[this.X-1][this.Y].getPiece() != null) {
                if (!currentState[this.X - 1][this.Y].getPiece().getColour().equals(this.getColour())) {
                    currentState[this.X - 1][this.Y].highlight = true;
                    possibleMoves.add(currentState[this.X - 1][this.Y]);
                }
            }else if (this.X-1>=0 && currentState[this.X-1][this.Y].getPiece() == null){
                currentState[this.X-1][this.Y].highlight =  true;
                possibleMoves.add(currentState[this.X-1][this.Y]);

            }

            //Move diagonally left upwards
            if(this.X-1>=0 && this.Y-1>=0 && currentState[this.X-1][this.Y-1].getPiece()!=null)
            {
                if(!currentState[this.X-1][this.Y-1].getPiece().getColour().equals(this.getColour()))
                {
                    currentState[this.X-1][this.Y-1].highlight =  true;
                    possibleMoves.add(currentState[this.X-1][this.Y-1]);
                }
            }

            //Move diagonally right upwards
            if(this.X-1>=0 && this.Y+1<=7 && currentState[this.X-1][this.Y+1].getPiece()!=null)
            {
                if(!currentState[this.X-1][this.Y+1].getPiece().getColour().equals(this.getColour()))
                {
                    currentState[this.X-1][this.Y+1].highlight =  true;
                    possibleMoves.add(currentState[this.X-1][this.Y+1]);
                }
            }
        }

        //////////////////////////

        //BLACK PAWNS
        else
        {
            //Moving first time
            if(this.X == 1)
            {
                if(currentState[this.X+1][this.Y].getPiece() == null && currentState[this.X+2][this.Y].getPiece() == null)
                {
                    currentState[this.X+2][this.Y].highlight = true;
                    possibleMoves.add(currentState[this.X+2][this.Y]);
                }
            }

            //Move straight downwards
            if(this.X+1<=7 && currentState[this.X+1][this.Y].getPiece() != null) {
                if (!currentState[this.X + 1][this.Y].getPiece().getColour().equals(this.getColour())) {
                    currentState[this.X + 1][this.Y].highlight = true;
                    possibleMoves.add(currentState[this.X + 1][this.Y]);
                }
            }else if (this.X+1<=7 && currentState[this.X+1][this.Y].getPiece() == null){
                currentState[this.X+1][this.Y].highlight =  true;
                possibleMoves.add(currentState[this.X+1][this.Y]);
            }

            //Move diagonally left downwards
            if(this.X+1<=7 && this.Y-1>=0 && currentState[this.X+1][this.Y-1].getPiece()!=null)
            {
                if(!currentState[this.X+1][this.Y-1].getPiece().getColour().equals(this.getColour()))
                {
                    currentState[this.X+1][this.Y-1].highlight =  true;
                    possibleMoves.add(currentState[this.X+1][this.Y-1]);
                }
            }

            //Move diagonally right downwards
            if(this.X+1<=7 && this.Y+1<=7 && currentState[this.X+1][this.Y+1].getPiece()!=null)
            {
                if(!currentState[this.X+1][this.Y+1].getPiece().getColour().equals(this.getColour()))
                {
                    currentState[this.X+1][this.Y+1].highlight =  true;
                    possibleMoves.add(currentState[this.X+1][this.Y+1]);
                }
            }
        }


        return possibleMoves;
    }
    //check if promotion is applicable
    public boolean isEligibleForPromotion() {
        boolean isOnLastTile = ((Objects.equals(this.getColour(), WHITE) && this.getX() == 0) || Objects.equals(this.getColour(), BLACK) && this.getX() == 7);
        return isOnLastTile && possibleMoves.contains(Board.squares[this.getX()][this.getY()]);
    }
    public static boolean getPromote() {
        return canPromote;
    }

    @Override
    public String getIdToPath(String id) {

        return this.idToPath.get(id);
    }

    @Override
    public void setIdToPath(String id,String imageName) {
        this.idToPath.put(id,imageName);
    }



}