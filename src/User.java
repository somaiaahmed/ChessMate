import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class User {

    protected static String whitePlayer= GameInfo.whitePlayerField.getText();
    protected static String blackPlayer= GameInfo.blackPlayerField.getText();
    protected static String winner; //get from game class
    protected static String timeElapsed; //get from game/timer class
    protected static List<String> allGamesData = new ArrayList<>(); //A list to store all games data with no separation
    protected static String[][] allGamesDataSeparated;
    //A 2D [x][y] array where, x is the array that contains a single game's data
    //and y is the number of games

    //The list is created because as the file reads, we don't know the number of games,
    //and we can't determine the size of the 2D array yet
    //So we throw all the games data in the list and then divide it into the array of arrays

    User(){
        whitePlayer=GameInfoPanel.whitePlayer;
        blackPlayer = GameInfoPanel.blackPlayer;
    }

    public static boolean LogInData (String username, String password){
        boolean isLoggedIn = false;
        try {
            File myObj = new File("resources/UsersAccounts.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String storedUser = myReader.nextLine();
                if (!storedUser.isEmpty()) {
                    String[] userData = storedUser.split("#");
                    String storedUsername = userData[0];
                    String storedPassword = userData[1];
                    if (storedUsername.equals(username)) {
                        if (storedPassword.equals(password)) {
                            isLoggedIn = true;
                            break;
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ep) {
            ep.printStackTrace();
        }
        return isLoggedIn;
    }
//This method checks the credentials of a user when they try to log-in
//If the given username and password match a set in the UsersACounts file,
//the value isLoggedIn is returned true to LoginPage class and
//the user is allowed into their account

    public static boolean checkUsernameValidity(String username){
        boolean isTaken=false;
        try {
            File myObj = new File("resources/UsersAcCounts.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String storedUser = myReader.nextLine();
                if (!storedUser.isEmpty()) {
                    String[] userData = storedUser.split("#");
                    String storedUsername = userData[0];
                    if (storedUsername.equals(username)) {
                        isTaken = true;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ep) {
            ep.printStackTrace();
        }
        return isTaken;
    }
//This method checks whether a username is previously taken when a user tries to sign-up
//If the username is taken, it returns the boolean isTaken true to SignupPage class,
//which then prevents the user from using that username

    public static void signUpData(String username, String password){
        try {
            FileWriter myWriter = new FileWriter("resources/UsersAccounts.txt", true);
            myWriter.write("\n"+ username+"#"+password+"\n");
            myWriter.flush();
            myWriter.close();
        } catch (IOException ep) {
            ep.printStackTrace();
        }
    }
//This method adds the credentials of the new user that signed-up
//to the UsersAccounts file, so they can log in again later
//even after the game is terminated

    public static void CreateNewGameFile() {
        try {
            File myObj = new File("resources/Game Data Files",SignupPage.userText);
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//This method creates a new file for every user that signs up successfully
// So we can store all their game data (players name, winner, time elapsed)
// And allow the user to access it at any time

    public static void WriteDataToFile(){
        whitePlayer=GameOverWindow.whitePlayer;
        blackPlayer=GameOverWindow.blackPlayer;
        winner=GameOverWindow.winner;
        timeElapsed=GameOverWindow.timeElapsed;
        //Get all game data from GameOverWindow
        try {
            String filePath="resources/Game Data Files/";
            FileWriter myWriter = new FileWriter(filePath+LoginPage.loggedInUser,true);
            myWriter.write(whitePlayer+"#"+blackPlayer+"#"+winner+"#"+timeElapsed+"\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//This method writes the game data to the logged-in user's file.
//Each line in the file represents one game played.
//The data is written in the form "whitePlayer#blackPlayer#winner#timeElapsed"
//this data is later separated in ReadDataFromFile method

    public static void ReadDataFromFile(){
        try {
            String filePath="resources/Game Data Files/";
            File myObj = new File(filePath+LoginPage.loggedInUser);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String gameData = myReader.nextLine();
                String[] gameDataSplit = gameData.split("#");
                Collections.addAll(allGamesData, gameDataSplit);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int gamesCount=0; //represents the number of rows in the table or [x] in the array allGamesDataSeparated[x][y]
        int listSize= allGamesData.size();
        while(listSize%4==0 && listSize!=0){
            gamesCount++;
            listSize-=4;
        } //This loop returns the number of games so that each one is stored in a row
        allGamesDataSeparated= new String[gamesCount][4]; //Since one game always has 4 attributes
        int k=0; //represents index in the large list "allGamesData"
        for (int i = 0; i < gamesCount; i++) {
            for (int j = 0; j < 4; j++) {
                allGamesDataSeparated[i][j] = allGamesData.get(k); //fills up the 2D array respectively of the games data
                k++;
            }
        }
    }
    //This method reads data from the file and converts each row into an array with the game data stored in it
    //Then adds the arrays in an array of arrays which represents a list of rows or a list of games
    //After the data is stored in an array of array (2D Array), this 2D array is represented in the table of
    //previous games that the user can access after logging in
}