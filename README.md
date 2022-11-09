# Wheel of Fortune


[//]: # (## Table of contents)

[//]: # (1. Team Members)

[//]: # (2. Requirements)

[//]: # (3. Some Image Here)

<!-- TABLE OF CONTENTS -->
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#team-members">Team Members</a>
    </li>
    <li>
      <a href="#description">Description</a>
    </li>
    <li><a href="#classes">Classes</a></li>
    <li><a href="#class-diagram">Class Diagram</a></li>
  </ol>

### Team Members
+ Cindy Pottin
+ Joe Racke
+ Joe Gonzalez


### Description
Game basics: 
- 3 Players (we will start wth one player)
- puzzle with a theme as an overall clue
- when it's the players turn they will spin, buy a vowel or solve.
- 

Team will create 2 classes.
- Up to 3 Players (we will start wth one player)
- Word puzzles with a theme 
- Players turn: players will be able to interact with the game by spinning the wheel, buy a vowel, solve the puzzle or exit.

At this time the team feels we will create 2 classes.

Players class:
1. Name
2. Prize Amount for round
3. Overall prize amount for the game

Game class:
1. Game Puzzle (up to 52 spaces)
2. Game Theme - Java Themed
3. Choices of spin, guess a constant, buy a vowel or exit
4. The spinning wheel with prizes, bankrupt, and lose a turn.


Wheel with cash prizes from $500 to $900.
We will need to include Bankrupt and Lose a Turn wedge.


Our idea is to create a Wheel of Fortune type game in Java, our game will be Terminal based and draw on the screen the game board. We initially plan on drawing the game on the terminal prompt and prompt the user to press "X" to start a game. Once the user has entered the game, they will be prompted to start and the follow the basic WOF instructions like, spinning the wheel, then choosing a letter that is not a vowel, followed by receiving whether they got the letter correct. Once they have guessed one letter, they then have an option to "Solve","Spin","Buy a Vowel" options. The player must then continue to try to accumulate as much money as possible to win the game. 

Once we have incorporated the basics of the game and have a working prototype then we plan to implement multiplayer support by displaying what players turn it is on the terminal. As well as trying to incorporate some more details in WOF. Our plan is two have 2-3 classes one for the player, one for the game, and possibly one for the drawing of the game on the terminal/console.  
## Classes 
+ Player Class
  + Name
  + Total Winnings
  + Round Winnings
  1. spinWheel()
  2. buyVowel()
  3. solvePuzzle()

+ Game Class
  + Map of Puzzles and hints
  + Map with final round puzzles/hints (These are usually smaller than the normal ones)
  + Current puzzle and hint
  + Letters guessed already
  + Round number
  + List of Players
  + Current Players turn
  + Wheel class
  1. startRound()
  2. pickPuzzle()
  3. revealLetterGuessed()
     1. calculateEarningFromLetterGuess()
  
+ Drawing Class?
+ + display Puzzle board with Hint
+ + display Current Player name and round winnings
+ + display player options

### Class Diagram
![Class-Diagram](https://i.imgur.com/mtoSbUl.png)

### Requirements
+ Req1
+ Req2

### Some Image Here
![Wheel-Of-Fortune](https://i.imgur.com/7rujzV5.png)