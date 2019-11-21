package gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DesenhaTabuleiro {

    static int i,j;

    public static void Desenha(int[][] Tabuleiro, Graphics g, int leftX, int topY,boolean  posicionamento) {
        Rectangle2D rt;
        Graphics2D g2d = (Graphics2D) g;
        double larg = 30.0;
        double alt = 30.0;
        int X = leftX, Y = topY;


        for ( i = 0; i < Tabuleiro.length; i++) {
            String number = (i >= 9) ? Integer.toString(i + 1) : " " + Integer.toString(i + 1);
            g2d.drawString(Character.toString((char) (65 + i)), leftX - 30, topY + 30 * i + 20);
            g2d.drawString(number, leftX + 30 * i, topY - 20);
            for ( j = 0; j < Tabuleiro[i].length; j++) {


                rt = new Rectangle2D.Double(X, Y, larg, alt);
                if(posicionamento)
                    DesenhaTabuleiro.CoresPosicionamento(g2d,Tabuleiro,rt);
                else{
                    DesenhaTabuleiro.CoresAtaque(g2d,Tabuleiro,rt);
                }

                //g2d.draw(rt);
                X += 30;
            }

            X = leftX;
            Y += 30.0;

        }

    }

    private static void CoresPosicionamento(Graphics2D g2d,int[][] Tabuleiro,Rectangle2D rt){
        if (Tabuleiro[i][j] == 0) {
            g2d.setColor(Color.black);
            g2d.draw(rt);
        }

        else {
            if(Tabuleiro[i][j]== 1) {
                g2d.setPaint(new Color(169, 220, 160));

                g2d.fill(rt);
                g2d.draw(rt);
            }

            else if(Tabuleiro[i][j]== 2) {
                g2d.setPaint(new Color(255, 230, 101));

                g2d.fill(rt);
                g2d.draw(rt);
            }

            else if(Tabuleiro[i][j]== 3) {
                g2d.setPaint(new Color(232, 128, 115));

                g2d.fill(rt);
                g2d.draw(rt);
            }

            else if(Tabuleiro[i][j]== 4) {

                g2d.setPaint(new Color(186, 126, 235));

                g2d.fill(rt);
                g2d.draw(rt);
            }

            else if (Tabuleiro[i][j]== 5){
                g2d.setPaint(new Color(130, 169, 230));

                g2d.fill(rt);
                g2d.draw(rt);
            }
            else if (Tabuleiro[i][j]== -10){
                g2d.setPaint(Color.red);

                g2d.fill(rt);
                g2d.draw(rt);
            }

        }
    }

    private static void CoresAtaque(Graphics2D g2d,int[][] Tabuleiro,Rectangle2D rt){
        if(Tabuleiro[i][j]== 10) {
            g2d.setPaint(Color.blue);

            g2d.fill(rt);
            g2d.draw(rt);
        }

        else if(Tabuleiro[i][j]== 15) {
            g2d.setPaint(Color.lightGray);

            g2d.fill(rt);
            g2d.draw(rt);
        }

        else if(Tabuleiro[i][j]== -1) {
            g2d.setPaint(Color.green);

            g2d.fill(rt);
            g2d.draw(rt);
        }
        else{
            g2d.setColor(Color.black);
            g2d.draw(rt);
        }
    }
}