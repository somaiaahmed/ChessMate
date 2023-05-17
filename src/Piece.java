
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Piece {
 String colour;
 int X;//x- coordinate (from 0-7)
 int Y;//y-coordinate (from 0-7)
 String not="";
 Board board;
 String Type;
 String id="";
 ArrayList<Tiles>possibleMoves;
 HashMap<String,String> idToPath;
 public final String WHITE = "white";
 public final String BLACK = "black";

 public Piece(int x, int y, int color) {
 }

 protected Piece() {
 }

 public int getX() {
  return X;
 }

 public void setX(int X) {
  this.X = X;
 }

 public int getY() {
  return Y;
 }

 public void setY(int Y) {
  this.Y = Y;
 }

 public String getColour() {
  return colour;
 }

 public void setColour(String colour) {
  this.colour = colour;
 }

 public ArrayList<Tiles> move(Tiles boardState[][]){

  return null;
  //whenever you move a piece make sure you change the hasPiece attribute of the square
 }

 public String getId() {
  return id;
 }

 public String getIdToPath(String id){
  return null;
 }

 public void setIdToPath(String id,String imageName){

 }
 public String getType(){
  return Type;
 }

}