
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Rook extends Piece{

    Rook(String id,int x,int y,String colour){
        this.X = x;
        this.Y = y;
        this.not = "R";
        this.id = id;
        possibleMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        this.colour = colour;

        if(colour.equals(WHITE)){
            setIdToPath(id,"resources/pieces/white rook.png");
        }
        else
            setIdToPath(id,"resources/pieces/black rook.png");
    }

    @Override
    public ArrayList<Tiles> move(Tiles[][] currentState) {

        possibleMoves.clear();
        int x = this.X;
        //System.out.println(this.getId()+" "+x);
        //System.out.println("in rook");
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
                    //currentState[x][j].button.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
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
                System.out.println("count");
                if(currentState[x][j].getPiece().getColour().equals(this.getColour()))
                {
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