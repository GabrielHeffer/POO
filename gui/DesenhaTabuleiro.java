package gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DesenhaTabuleiro {

    static int i,j;

    public static void Desenha(int[][] Tabuleiro, Graphics g, int leftX, int topY,String momento) {
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
                if(momento.equals("Pos"))
                    DesenhaTabuleiro.CoresPosicionamento(g2d,Tabuleiro,rt);
                else if(momento.equals("Oponente")){
                    DesenhaTabuleiro.CoresAtaqueOponente(g2d,Tabuleiro,rt);
                }
                else if(momento.equals("Jogador")){
                    DesenhaTabuleiro.CoresAtaqueJogador(g2d,Tabuleiro,rt);
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
            g2d.setPaint(Color.white);
            g2d.fill(rt);
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
            else if (Tabuleiro[i][j]== 9){
                g2d.setPaint(Color.red);

                g2d.fill(rt);
                g2d.draw(rt);
            }

        }
        g2d.setColor(Color.black);
        g2d.draw(rt);
    }

    private static void CoresAtaqueOponente(Graphics2D g2d,int[][] Tabuleiro,Rectangle2D rt){
        if(Tabuleiro[i][j]== 6) {
            g2d.setPaint(new Color(45,114,143));
        //agua
            g2d.fill(rt);
            g2d.draw(rt);
        }
        //Acertou uma embarcação
        else if(Tabuleiro[i][j]== 7) {
            g2d.setPaint(new Color(207,255,176));

            g2d.fill(rt);
            g2d.draw(rt);
        }
        //Embarcação abatida
        else if(Tabuleiro[i][j]== 8) {
            g2d.setPaint(new Color(122,199,79));

            g2d.fill(rt);
            g2d.draw(rt);
        }
        else {
            g2d.setPaint(Color.white);
            g2d.fill(rt);
            g2d.draw(rt);
        }
        g2d.setPaint(Color.black);
        g2d.draw(rt);
    }

    private static void CoresAtaqueJogador(Graphics2D g2d,int[][] Tabuleiro,Rectangle2D rt){
        if(Tabuleiro[i][j]== 6) {
            g2d.setPaint(new Color(45,114,143));
            //agua
            g2d.fill(rt);
            g2d.draw(rt);
        }
        //Acertou uma embarcação
        else if(Tabuleiro[i][j]== 7) {
            g2d.setPaint(new Color(152,151,136));

            g2d.fill(rt);
            g2d.draw(rt);
        }
        //Embarcação abatida
        else if(Tabuleiro[i][j]== 8) {
            g2d.setPaint(new Color(191,6,3));

            g2d.fill(rt);
            g2d.draw(rt);
        }
        else if(Tabuleiro[i][j] >= 0){
            g2d.setPaint(Color.white);
            g2d.fill(rt);
            g2d.draw(rt);
        }
        g2d.setColor(Color.black);
        g2d.draw(rt);
    }
}