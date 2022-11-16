package com.game.wheeloffortune;


// These said they were unused and my IDE was showing an error loading the Jupiter one
//import org.junit.jupiter.api.Test;
//import org.sonatype.guice.plexus.config.Hints;

import java.util.*;

public class    Puzzles {

    private static Map<String, String> hintPuzzles = new HashMap<String, String>();
    static {
        hintPuzzles.put("REFERENCE VARIABLE", "Objects In Memory");
        hintPuzzles.put("PRIMITIVE", "Data Type");
        hintPuzzles.put("HOT DOGS ARE NOT TACOS", "Food");
        hintPuzzles.put("EXCEPTIONS", "Something Thrown");
        hintPuzzles.put("DATA ABSTRACTION","Hiding Data");
        hintPuzzles.put("POLYMORPHISM", "Many Different Functions");
        hintPuzzles.put("ENUMERATED TYPE","Unchanged Type");
        hintPuzzles.put("LUXURY SUITES","Places");
        hintPuzzles.put("SNACK PACK","Rhyme Time");
        hintPuzzles.put("FOREVER YOUNG","Phrase");

    }


    public static ArrayList<String> getRandomPuzzle() {
        Random randomKVPairIndex = new Random();
        int randomIndex = randomKVPairIndex.nextInt(10);
        List<String> keys = new ArrayList<String>(hintPuzzles.keySet());
        String randomPuzzle = keys.get(randomKVPairIndex.nextInt(keys.size()));
        String hintMatchedtoPuzzle = hintPuzzles.get(randomPuzzle);

        ArrayList<String> puzzleHintPair = new ArrayList<String>();
        puzzleHintPair.add(randomPuzzle);
        puzzleHintPair.add(hintMatchedtoPuzzle);
        return puzzleHintPair;

    }
}

//        public static String getRandomPuzzle() {
//        Random randomKVPairIndex = new Random();
//        List<String> keys = new ArrayList<String>(hintPuzzles.keySet());
//        String randomPuzzle = keys.get(randomKVPairIndex.nextInt(keys.size()));
//        return randomPuzzle;
//
//        return (randomPuzzle + " " + hintMatchedtoPuzzle);

//        public static List<String> getRandomPuzzle() {
//        Random randomKVPairIndex = new Random();
//        List<String> keys = new ArrayList<String>(hintPuzzles.keySet());
//        return keys;
