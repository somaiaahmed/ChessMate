import java.util.ArrayList;
import java.util.HashMap;

public class King extends Piece {

    boolean moved;

    King(String id,int x,int y,String colour){
        this.X = x;
        this.Y = y;
        this.id = "K";
        this.moved = false;//needed to check if king can do castling
        this.id = id;
        possibleMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        this.colour = colour;

        if(colour.equals(WHITE)){
            setIdToPath(id,"resources/pieces/white king.png");
        }
        else
            setIdToPath(id,"resources/pieces/black king.png");
    }

    //this function will be used while castling
    private boolean isMoved(King k){
        if(k.moved)
            return true;

        return false;
    }

    private boolean canCastle(Tiles[][] currentState){

        if(isMoved(this))
        {
            return false;
        }

        else
        {
            //WHITE KING
            if(this.getColour().equals("white"))
            {
                //short castling
                if(currentState[7][5].getPiece()==null && currentState[7][6]==null && currentState[7][7] !=null)
                {
                    if(currentState[7][7].getPiece().getId().equals("WR2"))
                    {
                        return true;
                    }
                }

                //long castling
                if(currentState[7][1].getPiece()==null && currentState[7][2] == null &&
                        currentState[7][3] == null && currentState[7][0] != null)
                {
                    if(currentState[7][7].getPiece().getId().equals("WR1"))
                    {
                        return true;
                    }
                }

                return false;
            }

            //BLACK KING
            else
            {
                //short castling
                if(currentState[0][5].getPiece()==null && currentState[0][6]==null && currentState[0][7] !=null)
                {
                    if(currentState[0][7].getPiece().getId().equals("BR2"))
                    {
                        return true;
                    }
                }

                //long castling
                if(currentState[0][1].getPiece()==null && currentState[0][2] == null &&
                        currentState[0][3] == null && currentState[0][0] != null)
                {
                    if(currentState[0][0].getPiece().getId().equals("BR1"))
                    {
                        return true;
                    }
                }

                return false;
            }

        }

    }

    public ArrayList<Tiles> move(Tiles[][] currentState) {

        possibleMoves.clear();
        int x = this.X;
        int y = this.Y;
        int Xpos[] = {x,x-1,x-1,x-1,x,x+1,x+1,x+1};
        int Ypos[] = {y-1,y-1,y,y+1,y+1,y+1,y,y-1};

        for(int i=0;i<8;i++)
        {
            if(Xpos[i]>=0 && Xpos[i]<=7 && Ypos[i]>=0 && Ypos[i]<=7)
            {
                if(currentState[Xpos[i]][Ypos[i]].getPiece() == null && !isInDanger(currentState,Xpos[i],Ypos[i]))
                {
                    currentState[Xpos[i]][Ypos[i]].highlight = true;
                    possibleMoves.add(currentState[Xpos[i]][Ypos[i]]);
                }

                else if(currentState[Xpos[i]][Ypos[i]].getPiece() != null &&
                        !currentState[Xpos[i]][Ypos[i]].getPiece().getColour().equals(this.getColour()))
                {
                    if(!isInDanger(currentState,Xpos[i],Ypos[i])){
                        currentState[Xpos[i]][Ypos[i]].highlight = true;
                        possibleMoves.add(currentState[Xpos[i]][Ypos[i]]);
                    }
                }
            }

        }
        return possibleMoves;
    }

    public boolean isInDanger(Tiles[][] currentState,int x,int y) {

        //check for all horizontal positions
        //ROOK's MOVES

        //HORIZONTAL LEFT NAVIGATION OF QUEEN
        for(int j=y-1;j>=0;j--)
        {
            if(currentState[x][j].getPiece() == null)
            {
                continue;
            }

            else if(currentState[x][j].getPiece() != null)
            {
                if(!currentState[x][j].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[x][j].getPiece() instanceof Rook || currentState[x][j].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }

        }

        //HORIZONTAL RIGHT NAVIGATION OF QUEEN
        for(int j=y+1;j<=7;j++)
        {
            if(currentState[x][j].getPiece() == null)
            {
                continue;
            }

            else if(currentState[x][j].getPiece() != null)
            {
                if(!currentState[x][j].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[x][j].getPiece() instanceof Rook || currentState[x][j].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }

        }

        //VERTICAL UP NAVIGATION OF QUEEN
        for(int i=x-1;i>=0;i--)
        {
            if(currentState[i][y].getPiece() == null)
            {
                continue;
            }

            else if(currentState[i][y].getPiece() != null)
            {
                if(!currentState[i][y].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[i][y].getPiece() instanceof Rook || currentState[i][y].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }
        }

        //VERTICAL DOWN NAVIGATION OF QUEEN
        for(int i=x+1;i<=7;i++)
        {
            if(currentState[i][y].getPiece() == null)
            {
                continue;
            }

            else
            {
                if(!currentState[i][y].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[i][y].getPiece() instanceof Rook || currentState[i][y].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }
        }

        /******************************************************************/

        //BISHOP's MOVES
        int i = x-1;
        int j = y-1;

        //Moving diagonally left upwards
        while(i>=0 && j>=0)
        {
            if(currentState[i][j].getPiece() == null)
            {

            }

            else if(currentState[i][j].getPiece() != null)
            {
                if(!currentState[i][j].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[i][j].getPiece() instanceof Bishop || currentState[i][j].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }
            i--;
            j--;
        }

        //Moving diagonally left downwards
        i = x+1;
        j = y-1;

        while(i<=7 && j>=0)
        {
            if(currentState[i][j].getPiece() == null)
            {

            }

            else if(currentState[i][j].getPiece() != null)
            {
                if(!currentState[i][j].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[i][j].getPiece() instanceof Bishop || currentState[i][j].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }
            i++;
            j--;
        }

        //Moving diagonally right upwards
        i = x-1;
        j = y+1;

        while(i>=0 && j<=7)
        {
            if(currentState[i][j].getPiece() == null)
            {

            }

            else if(currentState[i][j].getPiece() != null)
            {
                if(!currentState[i][j].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[i][j].getPiece() instanceof Bishop || currentState[i][j].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }
            i--;
            j++;
        }

        //Moving diagonally right downwards
        i = x+1;
        j = y+1;

        while(i<=7 && j<=7)
        {
            if(currentState[i][j].getPiece() == null)
            {

            }

            else if(currentState[i][j].getPiece() != null)
            {
                if(!currentState[i][j].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[i][j].getPiece() instanceof Bishop || currentState[i][j].getPiece() instanceof Queen))
                {
                    return true;
                }

                else
                {
                    break;
                }
            }
            i++;
            j++;
        }

        /*************************************************************/
        //KNIGHT MOVES

        int Xpos[] = {x-2,x-3,x-3,x-2,x+2,x+3,x+3,x+2};
        int Ypos[] = {y-3,y-2,y+2,y+3,y+3,y+2,y-2,y-3};

        for(i=0;i<8;i++)
        {
            if(Xpos[i]>=0 && Xpos[i]<=7 && Ypos[i]>=0 && Ypos[i]<=7)
            {
                if(currentState[Xpos[i]][Ypos[i]].getPiece() == null)
                {
                    continue;
                }

                else if(!currentState[Xpos[i]][Ypos[i]].getPiece().getColour().equals(this.getColour()) &&
                        (currentState[Xpos[i]][Ypos[i]].getPiece() instanceof Knight))
                {
                    return true;
                }
            }
        }

        //PAWN MOVES

        //WHITE KING
        if(this.getColour().equals("white"))
        {
            if(x-1>=0 && y-1>=0 && currentState[x-1][y-1].getPiece()!=null && currentState[x-1][y-1].getPiece().getColour().equals("black")
                    && (currentState[x-1][y-1].getPiece() instanceof Pawn))
            {
                return true;
            }

            if(x-1>=0 && y+1<=7 && currentState[x-1][y+1].getPiece()!=null && currentState[x-1][y+1].getPiece().getColour().equals("black")
                    && (currentState[x-1][y+1].getPiece() instanceof Pawn))
            {
                return true;
            }
            if(x-1>=0 && y>=0 && currentState[x-1][y].getPiece()!=null && currentState[x-1][y].getPiece().getColour().equals("black")
                    && (currentState[x-1][y].getPiece() instanceof Pawn))
            {
                return true;
            } //if pawn is in front of king
        }

        //BLACK KING
        else
        {
            if(x+1<=7 && y-1>=0 && currentState[x+1][y-1].getPiece()!=null && currentState[x+1][y-1].getPiece().getColour().equals("white")
                    && (currentState[x+1][y-1].getPiece() instanceof Pawn))
            {
                return true;
            }

            if(x+1<=7 && y+1<=7 && currentState[x+1][y+1].getPiece()!=null && currentState[x+1][y+1].getPiece().getColour().equals("white")
                    && (currentState[x+1][y+1].getPiece() instanceof Pawn))
            {
                return true;
            }
            if(x+1>=0 && y>=0 && currentState[x+1][y].getPiece()!=null && currentState[x+1][y].getPiece().getColour().equals("white")
                    && (currentState[x+1][y].getPiece() instanceof Pawn))
            {
                return true;
            } //if pawn is in front of king
        }
        return false;
    }

    //YET TO BE MODIFIED******************************////
   

           public boolean isCheckMate(Tiles[][] currentState, int x, int y) {
        // Generate all possible moves for the king
        ArrayList<Tiles> possibleMoves = move(currentState);
        if(isInDanger(currentState,x,y))
        {
            if(possibleMoves.isEmpty())
            {
                return true;
            }
        }

        return false;
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
