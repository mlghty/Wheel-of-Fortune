package com.game.wheeloffortune;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        GameDialogue gameDialogue = new GameDialogue();
        gameDialogue.gameLoop();
    }
}
