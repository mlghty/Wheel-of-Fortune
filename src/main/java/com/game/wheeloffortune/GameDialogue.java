package com.game.wheeloffortune;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameDialogue {


    private Integer numOfPlayer;
    private Scanner myObj;
    private static List<Player> players;


    public GameDialogue() {
        players = new ArrayList<>();
        myObj = new Scanner(System.in);
        numOfPlayer = 1;
    }


    public boolean startGame() {

        boolean startGame = false;

        System.out.println("Press 'X' to Start Game: ");
        System.out.println("Press 'Q' to Exit Game: ");

        String pressedKey = myObj.nextLine();

        while (!startGame) {

            if (pressedKey.equalsIgnoreCase("x")) {
                System.out.println("Pressed x");
                startGame = true;
            } else if (pressedKey.equalsIgnoreCase("q")) {
                System.out.println("Pressed q");
                System.out.println("Exiting game...");
                break;
            } else {
                System.out.println("Error valid options are 'X' & 'Q'" );
                System.out.println("Press 'X' to Start Game: ");
                System.out.println("Press 'Q' to Exit Game: ");
                pressedKey = myObj.nextLine();  // Read user input

            }
        }


        return startGame;
    }



    public int numberOfPlayers(){

        int numPlayers = 0;
        System.out.println("Select number of players [1-3]");

        Integer playerCount = Integer.parseInt(myObj.nextLine());

        while (playerCount < 0 || playerCount > 3){
            System.out.println("Error please select between 1-3 players...");
            playerCount = Integer.parseInt(myObj.nextLine());

        }

        System.out.println("Number of Players: " + playerCount);

        numOfPlayer = numPlayers;
        return numPlayers;
    }



    public void setPlayerNames(){

        for (int i = 1; i <= numOfPlayer; i++){
            System.out.println("Please enter name for Player " + i);
            String playerName = myObj.nextLine();
            players.add(new Player(playerName));
        }

        System.out.println(players);

    }

}


class Main1 {
    public static void main(String[] args) {
        GameDialogue gameDialogue = new GameDialogue();

//        gameDialogue.startGame();
//        gameDialogue.numberOfPlayers();
        gameDialogue.setPlayerNames();


    }
}