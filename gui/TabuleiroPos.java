package gui;

import controler.ctrl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class TabuleiroPos extends JPanel implements MouseListener {

    public final int LARG_DEFAULT=400;
    public final int ALT_DEFAULT=300;
    int leftX,topY;
    int JogadorVez = 1;
    private int sl;
    private int sa;
    JButton TabPronto  = new JButton();

    public TabuleiroPos(){
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        sl=screenSize.width;
        sa=screenSize.height;
        leftX=sl/2 + LARG_DEFAULT/2;
        topY= sa/2 - ALT_DEFAULT;
        setLayout(null);
        TabPronto.setBounds(sl/2-110,sa-200,220,40);
    }

    private void DesenhaTabuleiro(int[][] Tabuleiro,Graphics g){
        Rectangle2D rt;
        Graphics2D g2d=(Graphics2D) g;
        double larg=30.0;
        double alt=30.0;
        for (int i=0;i<Tabuleiro.length;i++)
        {

            for (int j=1;j<Tabuleiro[i].length;j++)
            {
                rt=new Rectangle2D.Double(leftX,topY,larg,alt);
                if(Tabuleiro[i][j] == 0)
                    g2d.draw(rt);
                else
                    g2d.draw(rt);
                topY+=30;
            }

            leftX+=30.0;
            topY= sa/2 - ALT_DEFAULT;

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] Tab = ctrl.getCtrl().getTabuleiro(JogadorVez);
        String nomeJogador = ctrl.getCtrl().getNomeJogador(JogadorVez);
        leftX=sl/2 + LARG_DEFAULT/2;
        DesenhaTabuleiro(Tab,g);
        TabPronto.setText(String.format("Tabuleiro %s prronto",nomeJogador));
        this.add(TabPronto);
        TabPronto.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
