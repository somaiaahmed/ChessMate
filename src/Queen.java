import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Queen extends Piece {

    Queen(String id,int x,int y,String colour){
        this.X = x;
        this.Y = y;
        this.not = "Q";
        this.id = id;
        possibleMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        this.colour = colour;

        if(colour.equals(WHITE)){
            setIdToPath(id,"resources/pieces/white queen.png");
        }
        else
            setIdToPath(id,"resources/pieces/black queen.png");
    }

    @Override
    public ArrayList<Tiles> move(Tiles[][] currentState) {

        possibleMoves.clear();
        boolean flag =false;

        //ROOK's MOVES
        int x=  this.X;

        //HORIZONTAL LEFT NAVIGATION OF ROOK
        for(int j=this.Y-1;j>=0;j--)
        {
            if(currentState[x][j].getPiece() == null)
            {
                currentState[x][j].highlight = true;
                possibleMoves.add(currentState[x][j]);
            }

            else if(currentState[x][j].getPiece() != null)
            {
                if(currentState[x][j].getPiece().getColour().equals(this.getColour()))
                {
                    flag = true;
                    break;
                }

                else
                {
                    currentState[x][j].highlight = true;
                    possibleMoves.add(currentState[x][j]);
                    break;
                }
            }

        }

        //HORIZONTAL RIGHT NAVIGATION OF ROOK
        for(int j=this.Y+1;j<=7;j++)
        {
            if(currentState[x][j].getPiece() == null)
            {
                currentState[x][j].highlight = true;
                possibleMoves.add(currentState[x][j]);
            }

            else if(currentState[x][j].getPiece() != null)
            {
                if(currentState[x][j].getPiece().getColour().equals(this.getColour()))
                {
                    flag = true;
                    break;
                }

                else
                {
                    currentState[x][j].highlight = true;
                    possibleMoves.add(currentState[x][j]);
                    break;
                }
            }

        }

        flag = false;
        int y = this.Y;

        //VERTICAL UP NAVIGATION OF ROOK
        for(int i=this.X-1;i>=0;i--)
        {
            if(currentState[i][y].getPiece() == null)
            {
                currentState[i][y].highlight = true;
                possibleMoves.add(currentState[i][y]);
            }

            else if(currentState[i][y].getPiece() != null)
            {
                if(currentState[i][y].getPiece().getColour().equals(this.getColour()))
                {
                    flag = true;
                    break;
                }

                else
                {
                    currentState[i][y].highlight = true;
                    possibleMoves.add(currentState[i][y]);
                    break;
                }
            }
        }

        //VERTICAL DOWN NAVIGATION OF ROOK
        for(int i=this.X+1;i<=7;i++)
        {
            if(currentState[i][y].getPiece() == null)
            {
                currentState[i][y].highlight = true;
                possibleMoves.add(currentState[i][y]);
            }

            else
            {
                if(currentState[i][y].getPiece().getColour().equals(this.getColour()))
                {
                    flag = true;
                    break;
                }

                else
                {
                    currentState[i][y].highlight = true;
                    possibleMoves.add(currentState[i][y]);
                    break;
                }
            }
        }

        /////////////////////////////////
        //BISHOP's MOVES
        // Moving diagonally left upwards
        int i = this.X-1;
        int j = this.Y-1;

        while(i>=this.X-3 && j>=this.Y-3 && i>=0 && j>=0)
        {
            if(currentState[i][j].getPiece() == null)
            {
                currentState[i][j].highlight = true;
                possibleMoves.add(currentState[i][j]);
            }

            else if(currentState[i][j].getPiece() != null)
            {
                if(currentState[i][j].getPiece().getColour().equals(this.getColour()))
                {
                    break;
                }

                else
                {
                    currentState[i][j].highlight = true;
                    possibleMoves.add(currentState[i][j]);
                    break;
                }
            }
            i--;
            j--;
        }

        //Moving diagonally left downwards
        i = this.X+1;
        j = this.Y-1;

        while(i<=7 && j>=0 && i<=this.X+3 && j>=this.Y-3 )
        {
            if(currentState[i][j].getPiece() == null)
            {
                currentState[i][j].highlight = true;
                possibleMoves.add(currentState[i][j]);
            }

            else
            {
                if(currentState[i][j].getPiece().getColour().equals(this.getColour()))
                {
                    break;
                }

                else
                {
                    currentState[i][j].highlight = true;
                    possibleMoves.add(currentState[i][j]);
                    break;
                }
            }
            i++;
            j--;
        }

        //Moving diagonally right upwards
        i = this.X-1;
        j = this.Y+1;

        while(i>=0 && j<=7 && i>=this.X-3 && j<=this.Y+3)
        {
            if(currentState[i][j].getPiece() == null)
            {
                currentState[i][j].highlight = true;
                possibleMoves.add(currentState[i][j]);
            }

            else
            {
                if(currentState[i][j].getPiece().getColour().equals(this.getColour()))
                {
                    break;
                }

                else
                {
                    currentState[i][j].highlight = true;
                    possibleMoves.add(currentState[i][j]);
                    break;
                }
            }
            i--;
            j++;
        }

        //Moving diagonally right downwards
        i = this.X+1;
        j = this.Y+1;

        while(i<=7 && j<=7 && i<=this.X+3 && j<=this.Y+3)
        {
            if(currentState[i][j].getPiece() == null)
            {
                currentState[i][j].highlight = true;
                possibleMoves.add(currentState[i][j]);
            }

            else
            {
                if(currentState[i][j].getPiece().getColour().equals(this.getColour()))
                {
                    break;
                }

                else
                {
                    currentState[i][j].highlight = true;
                    possibleMoves.add(currentState[i][j]);
                    break;
                }
            }
            i++;
            j++;
        }
        return possibleMoves;
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