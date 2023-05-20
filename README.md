# finalproject_chessgame-chessmate
# Chess Mate

<p align="justify"> The Chess Game is a two-player strategy game where each player has a set of pieces with different movements. The objective of the game is to capture the opponent's king and avoid being captured in return. This project is a Java implementation of the Chess Game that allows users to play the game and keep track of their game history. </p>

## Rules of the Game

* The game is played between two opponents with different colors of pieces (commonly black and white).
* White moves first, after which the players alternate turns under fixed rules until the end of the game. A player can't make two successive moves.
* <p align="justify"> When a player selects a piece, all possible legal moves should be highlighted in green, while moves that are not allowed should be highlighted in red. A move is not allowed if it would result in the player's own pieces being in conflict with each other, such as moving a piece to a square where another of the player's pieces is already located (this is referred to as 'blocking' and is not permitted under the rules of chess). Additionally, a move is not allowed if it would result in the player's king being in check (i.e., threatened with capture by an opponent's piece). If the king is threatened with checkmate, the move is considered an illegal move. </p>
* The eaten pieces of each participant should be displayed on the sidebar after they have successfully eaten a piece.
* The win condition of chess is to force your opponent's king into checkmate (a position where it is unable to avoid capture).

## Features

### Piece Moves

#### Pawn
![white pawn](https://github.com/somaiaahmed/ChessMate/assets/52898207/b4e24d59-e03c-4bc2-be3a-8afcbcb50b28)

#### Bishop
![black bishop](https://github.com/somaiaahmed/ChessMate/assets/52898207/3a7135a5-42cc-44a4-be23-351817987162)

#### Knight
![white knight](https://github.com/somaiaahmed/ChessMate/assets/52898207/5def52c0-da5d-4594-934c-a3d0dced3786)

#### Rook
![white rook](https://github.com/somaiaahmed/ChessMate/assets/52898207/c91a56e9-2068-4cff-8daa-87be481a2483)

#### Queen
![white queen](https://github.com/somaiaahmed/ChessMate/assets/52898207/f9379adc-d495-412d-8feb-730972ce18b2)

#### King
![white king](https://github.com/somaiaahmed/ChessMate/assets/52898207/b67c8878-f5d9-4e3d-90b4-e96b7a33fcd4)

### King under check 
![black king check](https://github.com/somaiaahmed/ChessMate/assets/52898207/1d2f6bcb-78ce-40ca-8a6d-ea2cacc82f8a)




### Timer

<p align="justify"> The game includes a timer that counts down from a set time before starting the game. Each player will have a set amount of time to complete their moves. When a player starts their turn, their timer will begin to count down. The timer will stop when the player makes their move, and then the opponent's timer will start counting down. If a player's timer reaches 0 before they make their move, they will lose the game. It's important for the player to keep track of the time remaining and make strategic moves to ensure that they don't run out of time. </p>

### User Account
<p align="justify"> The game also includes a user account system that allows users to create new account credentials (username and password). If the credentials are incorrect, an error message is displayed. When a user logs in, they can either play a new game or retrieve the scores of previous games, including the players' names and the winner, as well as the time elapsed to complete the game. Each account has its own recorded games, which are saved as files for easy retrieval. There is no need for a database to keep track of the game history. </p>

![Game Data](https://github.com/somaiaahmed/ChessMate/assets/52898207/780a77e0-4837-470c-957c-5757192a63f4)

### Game Demo

![Game Demo](https://github.com/sbme-tutorials/finalproject_chessgame-chessmate/assets/124778473/311804f7-4e4f-4b28-bcd6-c141b7ddc53d)





## Conclusion

<p align="justify"> The Chess Game is a classic strategy game that requires skill and foresight to win. This Java implementation of the game includes several bonus features, such as a timer and a user account system, to enhance the gameplay experience. With this project, you can learn more about programming in Java and create a fun and engaging game that you can share with others. </p>


