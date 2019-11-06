package gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DesenhaTabuleiro {


    public static void Desenha(int[][] Tabuleiro, Graphics g, int leftX, int topY) {
        Rectangle2D rt;
        Graphics2D g2d = (Graphics2D) g;
        double larg = 30.0;
        double alt = 30.0;
        int X = leftX, Y = topY;


        for (int i = 0; i < Tabuleiro.length; i++) {
            String number = (i >= 9) ? Integer.toString(i + 1) : " " + Integer.toString(i + 1);
            g2d.drawString(Character.toString((char) (65 + i)), leftX - 30, topY + 30 * i + 20);
            g2d.drawString(number, leftX + 30 * i, topY - 20);
            for (int j = 0; j < Tabuleiro[i].length; j++) {


                rt = new Rectangle2D.Double(X, Y, larg, alt);
                if (Tabuleiro[i][j] == 0)
                    g2d.draw(rt);
                else {
                    System.out.println("ola");
                }
                //g2d.draw(rt);
                Y += 30;
            }

            X += 30.0;
            Y = topY;

        }

    }
}