package com.game.wheeloffortune;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our Wheel of Fortune project!\nPlease Select 1 for the ASCII Art Console Client\n" +
                "Or Select 2 for the Java Swing GUI Client");
        selection = scanner.nextInt();
        if (selection == 1) {
            GameDialogue gameDialogue = new GameDialogue();
            gameDialogue.gameLoop();
        } else if (selection == 2){
            GUIClient.runClient();
        }
    }
}
