package com.game.wheeloffortune;

import java.util.*;

public class Puzzles {

    Map<String, String> hintPuzzles = new HashMap<>();

    {
        hintPuzzles.put("Objects In Memory", "REFERENCE VARIABLE");
        hintPuzzles.put("Data Type", "PRIMITIVE");
        hintPuzzles.put("Food", "HOT DOGS ARE NOT TACOS");
        hintPuzzles.put("Something Thrown", "EXCEPTIONS");


        // TODO method public static Map.Entry<K,V> getRandomPuzzle()
        //Talk to the team about this
    Random randomKVPairIndex = new Random();
    int upperbound = hintPuzzles.size();
    int randomNumber = randomKVPairIndex.nextInt(upperbound);

    List keys = new ArrayList(hintPuzzles.keySet());
    List values = new ArrayList(hintPuzzles.values());
    String gameHint = (String)keys.get(randomNumber);
    String gamePuzzle = (String) values.get(randomNumber);


    }
}

