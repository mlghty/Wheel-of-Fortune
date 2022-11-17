# Wheel of Fortune

<p align="center">
  <img width="260" height="200" src="https://i.imgur.com/7rujzV5.png">
</p>

[//]: # (## Table of contents)

[//]: # (1. Team Members)

[//]: # (2. Requirements)

[//]: # (3. User Stores)

[//]: # (4. Some Image Here)

<!-- TABLE OF CONTENTS -->
## Table of Contents
[//]: # (  <summary>Table of Contents</summary>)
  <ol>
    <li>
      <a href="#team-members">Team Members</a>
    </li>
    <li>
      <a href="#description">Description</a>
    </li>
    <li><a href="#classes">Classes</a></li>
    <li><a href="#class-diagram">Class Diagram</a></li>
    <li><a href="#user-stories">User Stories</a></li>
    <li><a href="#user-stories"><a href="https://docs.google.com/presentation/d/15D6eHSgaPes-T5kfZW9MUirDGpUcAEPs_v0GowhEqVU/edit#slide=id.p">PowerPoint</a></a></li>
  </ol>

### Team Members
+ Cindy Pottin
+ Joe Racke
+ Joe Gonzalez


### Description
Game basics: 
- 3 Rounds of a Word Puzzle Game with 3 Players 
- Puzzle with a theme as an overall clue
- During the player's turn they will spin then guess a consonant, buy a vowel or solve.

Team will create several classes to support needed functionality.

Players class:
1. Name
2. Prize Amount for round
3. Overall prize amount for the game

Game class:
1. Game Puzzle
2. Game Theme - Mostly Java Themed
3. Choices of spin, guess a constant, buy a vowel or exit
4. The spinning wheel with prizes, bankrupt, and lose a turn.


Wheel with cash prizes from $200 to $1250.
We will need to include Bankrupt and Lose a Turn wedge.


Our idea is to create a Wheel of Fortune type game in Java, our game will be Terminal based and draw on the screen the game board. We initially plan on drawing the game on the terminal prompt and prompt the user to press "X" to start a game. Once the user has entered the game, they will be prompted to start and the follow the basic WOF instructions like, spinning the wheel, then choosing a letter that is not a vowel, followed by receiving whether they got the letter correct. Once they have guessed one letter, they then have an option to "Solve","Spin","Buy a Vowel" options. The player must then continue to try to accumulate as much money as possible to win the game. 

Once we have incorporated the basics of the game and have a working prototype then we plan to implement multiplayer support by displaying what players turn it is on the terminal. As well as trying to incorporate some more details in WOF. Our plan is two have 2-3 classes one for the player, one for the game, and possibly one for the drawing of the game on the terminal/console.  
## Classes 
+ Player Class
+ Game Class
+ GameBoard Class
+ Client
+ GUIClient
+ GameDialogue
+ Puzzles
+ Wheel
+ WOFAsciiArt

### Class Diagram
![Class-Diagram](https://i.imgur.com/508wCGo.png)

### Requirements
+ Java 11
+ Terminal window 120(Width) x 30(Height)


### User Stories
+ User wants to play single round of word guessing game
+ User wants to play a game that they can solve a puzzle
+ User wants to compete against other players for cash prize by solving word puzzles
+ User wants to compete against other players for cash prizes by solving word puzzles, would like to see ascii art 
+ User wants to compete against 2 other players for cash prizes by solving word puzzles, game should have color in a GUI
