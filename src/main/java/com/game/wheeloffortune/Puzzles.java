package com.game.wheeloffortune;

import java.util.*;

public class Puzzles {

    private static final Map<String, String> hintPuzzles = new HashMap<>();

    static {
        hintPuzzles.put("REFERENCE VARIABLE", "Objects In Memory");
        hintPuzzles.put("PRIMITIVE", "Data Type");
        hintPuzzles.put("HOT DOGS ARE NOT TACOS", "Food");
        hintPuzzles.put("EXCEPTIONS", "Something Thrown");
        hintPuzzles.put("DATA ABSTRACTION", "Hiding Data");
        hintPuzzles.put("POLYMORPHISM", "Many Different Functions");
        hintPuzzles.put("ENUMERATED TYPE", "Unchanged Type");
        hintPuzzles.put("LUXURY SUITES", "Places");
        hintPuzzles.put("SNACK PACK", "Rhyme Time");
        hintPuzzles.put("FOREVER YOUNG", "Phrase");
    }

    public static ArrayList<String> getRandomPuzzle() {
        Random randomKVPairIndex = new Random();
        List<String> keys = new ArrayList<>(hintPuzzles.keySet());
        String randomPuzzle = keys.get(randomKVPairIndex.nextInt(keys.size()));
        String hintMatchedToPuzzle = hintPuzzles.get(randomPuzzle);

        ArrayList<String> puzzleHintPair = new ArrayList<>();
        puzzleHintPair.add(randomPuzzle);
        puzzleHintPair.add(hintMatchedToPuzzle);
        return puzzleHintPair;
    }
}
