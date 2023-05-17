import javax.swing.*;
import java.util.*;
public class Tiles {
    public String white="white";
    public String black="black";
    public DefaultListCellRenderer button;
    String color;
    String not="";
    Piece piece ;
    Piece p=this.piece;
    int x;
    int y;
    boolean highlight;
    Tiles(){

    }

    Tiles(int i,int j ,boolean color){
        this.x=i;
        this.y=j;
        this.highlight=false;

        //Tiles-color
        if(color)
            this.color=white;
        else
            this.color=black;

        //Tiles-Notation
        this.not=Character.toString((char)('a'+x));
        this.not+=((char)(y+'1'));
        // System.out.println("square not"+not);
        this.piece=null;

    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece removePiece(){
        Piece p=this.piece;
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Piece getPiece(){
        return piece;
    }
    public static void promotep(){
        Pawn pawn = null;
    }

    /*public static void main(String[] args) {
        for (int i=0;i<8;)
        new Tiles();
    }*/
}