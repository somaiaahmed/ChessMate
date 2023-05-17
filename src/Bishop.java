import java.util.ArrayList;
import java.util.HashMap;

public class Bishop extends Piece {

    Bishop(String id,int x,int y,String colour){
        this.X = x;
        this.Y = y;
        this.not = "B";
        this.id = id;
        possibleMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        this.colour = colour;

        if(colour.equals(WHITE)){
            setIdToPath(id,"resources/pieces/white bishop.png");
        }
        else
            setIdToPath(id,"resources/pieces/black bishop.png");
    }

    @Override
    public ArrayList<Tiles> move(Tiles[][] currentState) {

        possibleMoves.clear();
        //Moving diagonally left upwards
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
            i++;
            j++;
        }
        // changing diagonal left
        i = this.X;
        j = this.Y-1;
        if( j >=0 && j<= 7 && currentState[i][j].getPiece() == null)
        {
            currentState[i][j].highlight = true;
            possibleMoves.add(currentState[i][j]);
        }
        // changing diagonal right
        i = this.X;
        j = this.Y+1;
        if( j >=0 && j<= 7 && currentState[i][j].getPiece() == null)
        {
            currentState[i][j].highlight = true;
            possibleMoves.add(currentState[i][j]);
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