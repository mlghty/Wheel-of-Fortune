package com.game.wheeloffortune;

import org.junit.jupiter.api.Test;
import org.sonatype.guice.plexus.config.Hints;

import java.util.*;

public class Puzzles {

    private static Map<String, String> hintPuzzles = new HashMap<String,String>(){
        {

            hintPuzzles.put("REFERENCE VARIABLE", "Objects In Memory");
            hintPuzzles.put("PRIMITIVE", "Data Type");
            hintPuzzles.put("HOT DOGS ARE NOT TACOS", "Food");
            hintPuzzles.put("EXCEPTIONS", "Something Thrown");
        }};


        // TODO method public static Map.Entry<K,V> getRandomPuzzle()
        public static Map.Entry<String,String> getRandomPuzzle() {
            Map.Entry<String, String> hintpuzz = Map.entry("", "");
            for (Map.Entry<String, String> hp : hintPuzzles.entrySet()) {
                hintpuzz = hp;
                break;
            }
            return hintpuzz;

//        Random randomKVPairIndex = new Random();
//        int upperbound = hintPuzzles.size();
//        int randomNumber = randomKVPairIndex.nextInt(upperbound);
//
//        List keys = new ArrayList(hintPuzzles.keySet());
//        List values = new ArrayList(hintPuzzles.values());
//        String gameHint = (String) keys.get(randomNumber);
//        String gamePuzzle = (String) values.get(randomNumber);


        }

    public static void main(String[] args) {
        System.out.println(hintPuzzles.get("REFERENCE VARIABLE"));
    }
}


