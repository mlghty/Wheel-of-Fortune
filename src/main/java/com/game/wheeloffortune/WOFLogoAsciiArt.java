package com.game.wheeloffortune;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WOFLogoAsciiArt {

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

            Graphics WOF = bufferedImage.getGraphics();
            //setting font style and size
            WOF.setFont(new Font("Arial", Font.BOLD, 15));

            Graphics2D WOFGraphic = (Graphics2D) WOF;
            WOFGraphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            WOF.drawString( "has won!", 5, 13);

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
    }


