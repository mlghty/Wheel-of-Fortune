package com.game.wheeloffortune;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WOFAsciiArt {

    public static void printWOFLogo() {
        //x axis
        int width = 170;
        //y axis
        int height = 15;


        //constructor new buffered image
        BufferedImage bufferedImage = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics WOF = bufferedImage.getGraphics();
        //setting font style and size
        WOF.setFont(new Font("Arial", Font.BOLD, 15));

        Graphics2D WOFGraphic = (Graphics2D) WOF;
        WOFGraphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        WOF.drawString("WHEEL OF FORTUNE", 5, 13);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                //all colors -16777216 are replaced by " "
//            sb.append(bufferedImage.getRGB(x, y) == -16777216 ? " ": "$");

                sb.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(sb);
        }
    }
    public static void winningPlayerMessage(){

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
            winningMessage.drawString( "YOU'VE WON", 5, 13);

            for (int y = 0; y < height; y++) {
                StringBuilder sb1 = new StringBuilder();
                for (int x = 0; x < width; x++) {

                    //all colors -16777216 are replaced by " "
           sb1.append(bufferedImage.getRGB(x, y) == -16777216 ? " ": "$");

                   // sb.append(bufferedImage.getRGB(x, y) == -16777216 ? "$" : " ");
                }
                if (sb1.toString().trim().isEmpty()) {
                    continue;
                }
                System.out.println(sb1);
            }
        }

    }


