package com.game.wheeloffortune;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WOFAsciiArt {

    private static int terminalWidth;
    private static int terminalHeight;
    private static int fontSize;
    private static int x_offset;
    private static int y_offset;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static String STRING_WINDOWS_SET_TITLE = "cmd /c title %s";
    public static String STRING_LINUX_SET_TITLE = "echo -ne \"\\033]0;WOF\\007\"";

    public static void printWOFLogo() {
        BufferedImage bufferedImage = new BufferedImage(
                terminalWidth,
                terminalHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics logoGraphics = bufferedImage.getGraphics();
        logoGraphics.setFont(new Font("Arial", Font.BOLD, fontSize));

        Graphics2D logo = (Graphics2D) logoGraphics;
        logo.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        logoGraphics.drawString("WHEEL OF FORTUNE", x_offset, y_offset);

        for (int y = 0; y < terminalHeight; y++) {
            StringBuilder logoStringBuilder = new StringBuilder();
            for (int x = 0; x < terminalWidth; x++) {
                logoStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
            }
            if (logoStringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(ANSI_GREEN + logoStringBuilder + ANSI_RESET);
        }
    }

    public static void clearAsciiArt() throws InterruptedException {
        try {
            new ProcessBuilder("cmd", "/c", "cls", "clear").inheritIO().start().waitFor();
        } catch (final Exception e) {
            TimeUnit.MILLISECONDS.sleep(100L);
            System.out.print("\033\143");
        }
    }

    public static void windowSizes() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            terminalWidth = 120;
            terminalHeight = 30;
            fontSize = 11;
            x_offset = 10; // move logo left or right
            y_offset = 20; // move logo up or down

            System.out.println("Windows Detected");

            System.out.println("Font Size: " + fontSize);
            System.out.println("Terminal Width: " + terminalWidth);
            System.out.println("Terminal Height: " + terminalHeight);
            System.out.println("x Offset: " + terminalWidth);
            System.out.println("y Offset: " + terminalHeight);

            setTitle("Wheel of Fortune", STRING_WINDOWS_SET_TITLE);

        } else if (os.contains("mac")) {
            // 80 x 24 default
            // for mac

            terminalWidth = 120;
            terminalHeight = 30;
            fontSize = 10;
            x_offset = 10; // move logo left or right
            y_offset = 20; // move logo up or down

            System.out.println("OSX Detected");
            System.out.println("Font Size: " + fontSize);
            System.out.println("Terminal Width: " + terminalWidth);
            System.out.println("Terminal Height: " + terminalHeight);
            System.out.println("x Offset: " + terminalWidth);
            System.out.println("y Offset: " + terminalHeight);

            setTitle("Wheel of Fortune", STRING_LINUX_SET_TITLE);
        }
    }

    public static void setTitle(final String title, String OS) {
        try {
            new ProcessBuilder(String.format(OS,
                    title.replaceAll("\"", "")).split(" ")).inheritIO().start().waitFor();
            System.out.flush();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void printWOFLogoBlink() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            String ansiColor = new Wheel().getRandomColor();

            BufferedImage bufferedImage = new BufferedImage(
                    terminalWidth,
                    terminalHeight,
                    BufferedImage.TYPE_INT_RGB);

            Graphics logoBlinkGraphic = bufferedImage.getGraphics();


            logoBlinkGraphic.setFont(new Font(
                    "Arial",
                    Font.BOLD,
                    fontSize));

            Graphics2D logoBlink = (Graphics2D) logoBlinkGraphic;

            logoBlink.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            logoBlinkGraphic.drawString("WHEEL OF FORTUNE", x_offset, y_offset);

            for (int y = 0; y < terminalHeight; y++) {
                StringBuilder logoBlinkStringBuilder = new StringBuilder();
                for (int x = 0; x < terminalWidth; x++) {
                    logoBlinkStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
                }
                if (logoBlinkStringBuilder.toString().trim().isEmpty()) {
                    continue;
                }
                System.out.println(ansiColor + logoBlinkStringBuilder + ANSI_RESET);
            }

            TimeUnit.MILLISECONDS.sleep(50L);
            clearAsciiArt();
            TimeUnit.MILLISECONDS.sleep(50L);
        }
        printWOFLogo();
    }

    public static void printAsciiMessage(String message, String playerColor){

        BufferedImage bufferedImage = new BufferedImage(
                terminalWidth,
                terminalHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics asciiMessageGraphic = bufferedImage.getGraphics();
        asciiMessageGraphic.setFont(new Font("Arial", Font.PLAIN, 11));

        Graphics2D winningMessageGraphics = (Graphics2D) asciiMessageGraphic;
        winningMessageGraphics.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        asciiMessageGraphic.drawString(message, 2, 13);

        for (int y = 0; y < terminalHeight; y++) {

            StringBuilder asciiMessageStringBuilder = new StringBuilder();

            for (int x = 0; x < terminalWidth; x++) {
                asciiMessageStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "$");
            }
            if (asciiMessageStringBuilder.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(playerColor + asciiMessageStringBuilder + ANSI_RESET);
        }
    }

    public static void printSpaces(int spaces) {
        for (int i = 0; i < spaces; ++i) System.out.println();
    }

    public static void printOutBankruptMessage() {

        try {
            clearAsciiArt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // i = 8 is the smallest font that is readable
        // and 14 is the biggest
        for (int i = 8; i < 14; i++) {
            printSpaces(10);
            BufferedImage bufferedImage = new BufferedImage(
                    terminalWidth, terminalHeight,
                    BufferedImage.TYPE_INT_RGB);

            Graphics bankruptGraphics = bufferedImage.getGraphics();
            bankruptGraphics.setFont(new Font("TimesRoman", Font.PLAIN, i));

            Graphics2D bankruptBanner = (Graphics2D) bankruptGraphics;

            bankruptBanner.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            bankruptGraphics.drawString("B A N K R U P T!", 30 - i, 15);

            for (int y = 0; y < terminalHeight; y++) {
                StringBuilder bankruptStringBuilder = new StringBuilder();
                for (int x = 0; x < terminalWidth; x++) {
                    bankruptStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "$");
                }
                if (bankruptStringBuilder.toString().trim().isEmpty()) {
                    continue;
                }
                System.out.println(ANSI_RED + bankruptStringBuilder + ANSI_RESET);
            }

            // If the index is at 13 then stops clearing as
            // we want bankrupt to stay at the end for
            // a couple of seconds
            if (i != 13) {
                try {

                    TimeUnit.MILLISECONDS.sleep(200L);
                    clearAsciiArt();
                    TimeUnit.MILLISECONDS.sleep(200L);

                } catch (InterruptedException e) {
                    System.out.println("Error: " + e);
                }
            } else {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        printSpaces(10);
    }

    public static void printWOFBanner(String playerColor, int spaces) {

        BufferedImage bufferedImage = new BufferedImage(
                terminalWidth,
                terminalHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics banner = bufferedImage.getGraphics();
        //setting font style and size
        banner.setFont(new Font(
                "Arial",
                Font.PLAIN,
                fontSize));

        Graphics2D bannerGraphic = (Graphics2D) banner;
        bannerGraphic.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        banner.drawString(
                "WHEEL OF FORTUNE",
                x_offset,
                10);

        for (int y = 0; y < terminalHeight; y++) {
            StringBuilder bannerStringBuilder = new StringBuilder();
            for (int x = 0; x < terminalWidth; x++) {
                bannerStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "$");
            }
            if (bannerStringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(playerColor + bannerStringBuilder + ANSI_RESET);
        }
        printSpaces(spaces);
    }

    public static void printStarryNight() {

        Random random = new Random();

        int spaceBetweenChars = 80;     // up decrease down decrease
        int length = 200; // down
        int width = 120; // width
        StringBuilder eachLineOfStars = new StringBuilder();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                int number = random.nextInt(spaceBetweenChars);
//                System.out.println("Number: " + number);
                if (number < 3) {
                    eachLineOfStars.append("+");
                } else if (number == 3) {
                    eachLineOfStars.append("`");
                } else if (number == 4) {
                    eachLineOfStars.append(".");
                } else if (number == 5) {
                    eachLineOfStars.append("*");
                } else {
                    eachLineOfStars.append(" ");
                }
            }

            System.out.println(eachLineOfStars);
            // reset star line
            eachLineOfStars = new StringBuilder();

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public static void printReadyPlayer() {
        printSpaces(18);

        BufferedImage bufferedImage = new BufferedImage(
                terminalWidth,
                terminalHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics readyBanner = bufferedImage.getGraphics();

        readyBanner.setFont(new Font("Arial", Font.BOLD, 8));
        Graphics2D readyGraphic = (Graphics2D) readyBanner;
        readyGraphic.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        readyBanner.drawString("R E A D Y", 40, 15);

        for (int y = 0; y < terminalHeight; y++) {
            StringBuilder readyStringBuilder = new StringBuilder();
            for (int x = 0; x < terminalWidth; x++) {
                readyStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "$");
            }
            if (readyStringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(ANSI_GREEN + readyStringBuilder + ANSI_RESET);
        }

        printPlayer();
    }

    public static void printPlayer() {
        BufferedImage bufferedImage = new BufferedImage(
                terminalWidth,
                terminalHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics playerBanner = bufferedImage.getGraphics();
        playerBanner.setFont(new Font("Arial", Font.BOLD, 10));
        Graphics2D playerGraphic = (Graphics2D) playerBanner;

        playerGraphic.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        playerBanner.drawString("P L A Y E R S", 25, 25);

        for (int y = 0; y < terminalHeight; y++) {
            StringBuilder playerStringBuilder = new StringBuilder();
            for (int x = 0; x < terminalWidth; x++) {
                playerStringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "$");
            }
            if (playerStringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(ANSI_GREEN + playerStringBuilder + ANSI_RESET);
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }

    }

    public static void setWindowSizesManually(){
        terminalWidth = 120;
        terminalHeight = 30;
        fontSize = 11;
        x_offset = 10;
        y_offset = 20;
    }

}

