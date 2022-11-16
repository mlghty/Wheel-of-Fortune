package com.game.wheeloffortune;

import java.awt.*;
import java.awt.image.BufferedImage;
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


    public static String WINDOWS_SET_TITLE = "cmd /c title %s";
    public static String STRING_LINUX_SET_TITLE = "echo -ne \"\\033]0;WOF\\007\"";

    public static void printWOFLogo() {
        BufferedImage bufferedImage = new BufferedImage(
                terminalWidth, terminalHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics WOF = bufferedImage.getGraphics();
        //setting font style and size
        WOF.setFont(new Font("Arial", Font.BOLD, fontSize));

        Graphics2D WOFGraphic = (Graphics2D) WOF;
        WOFGraphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        WOF.drawString("WHEEL OF FORTUNE", x_offset, y_offset);

        for (int y = 0; y < terminalHeight; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < terminalWidth; x++) {

                //all colors -16777216 are replaced by " "
//            sb.append(bufferedImage.getRGB(x, y) == -16777216 ? " ": "$");

                sb.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(ANSI_GREEN + sb + ANSI_RESET);
        }
    }

    public static void clearAsciiArt() throws InterruptedException {
        try {
            new ProcessBuilder("cmd", "/c", "cls", "clear").inheritIO().start().waitFor();
        } catch (final Exception e) {
            //System.out.println("Clearing manually!");
            //TimeUnit.SECONDS.sleep(3);
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

            setTitle("Wheel of Fortune", WINDOWS_SET_TITLE);

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

            //("Wheel of Fortune", STRING_LINUX_SET_TITLE);
            try {

                String command = STRING_LINUX_SET_TITLE;
                Process proc = Runtime.getRuntime().exec(command);

                //System.out.flush();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }

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

        String ansiColor = null;
        StringBuilder sb = null;
        //constructor new buffered image
        BufferedImage bufferedImage = new BufferedImage(
                terminalWidth, terminalHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics WOF = bufferedImage.getGraphics();
        //setting font style and size
        for (int i = 0; i < 10; i++) {
            ansiColor = new Wheel().getRandomColor();

            WOF.setFont(new Font("Arial", Font.BOLD, fontSize));
            Graphics2D WOFGraphic = (Graphics2D) WOF;
            WOFGraphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            WOF.drawString("WHEEL OF FORTUNE", x_offset, y_offset);

            for (int y = 0; y < terminalHeight; y++) {
                sb = new StringBuilder();
                for (int x = 0; x < terminalWidth; x++) {
                    sb.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
                }
                if (sb.toString().trim().isEmpty()) {
                    continue;
                }
                System.out.println(ansiColor + sb + ANSI_RESET);
            }

            TimeUnit.MILLISECONDS.sleep(50L);
            clearAsciiArt();
            TimeUnit.MILLISECONDS.sleep(50L);

        }

        printWOFLogo();
    }

    public static void winningPlayerMessage() {

        //x axis
        int width = 170;
        //y axis
        int height = 15;


        //constructor new buffered image
        BufferedImage bufferedImage = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics winningMessage = bufferedImage.getGraphics();
        //setting font style and size
        winningMessage.setFont(new Font("Futura", Font.PLAIN, 15));

        Graphics2D winningMessageGraphics
                = (Graphics2D) winningMessage;
        winningMessageGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        winningMessage.drawString( "CONGRATULATIONS", 5, 13);

        for (int y = 0; y < height; y++) {
            StringBuilder sb1 = new StringBuilder();
            for (int x = 0; x < width; x++) {

                //all colors -16777216 are replaced by " "
                sb1.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "$");

                // sb.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
            }
            if (sb1.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(ANSI_GREEN + sb1 + ANSI_RESET);
        }
    }
    public static void printOutBankruptMessage() {

        int width = 170;
        int height = 15;

        BufferedImage bufferedImage = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics bankruptMessage = bufferedImage.getGraphics();
        //setting font style and size
        bankruptMessage.setFont(new Font("Roboto", Font.PLAIN, 15));

        Graphics2D bankruptMessage1 = (Graphics2D) bankruptMessage;
        bankruptMessage1.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        bankruptMessage.drawString("Bankrupt", x_offset, y_offset);

        for (int y = 0; y < terminalHeight; y++) {
            StringBuilder sb2 = new StringBuilder();
            for (int x = 0; x < terminalWidth; x++) {

                //all colors -16777216 are replaced by " "
           sb2.append(bufferedImage.getRGB(x, y) == -16777216 ? " ": "$");

     //           sb.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
            }
            if (sb2.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(ANSI_RED + sb2 + ANSI_RESET);
        }
    }
}

// for testing

//class Main1 {
//    public static void main(String[] args) {
//
//        WOFLogoAsciiArt.printWOFLogo();
//
//    }
//}
