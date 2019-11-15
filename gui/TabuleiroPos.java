package gui;

import controler.ctrl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import gui.select;
import gui.Peca;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class TabuleiroPos extends JPanel implements MouseListener {

    public final int LARG_DEFAULT=400;
    public final int ALT_DEFAULT=300;
    int leftX,topY;
    int JogadorVez = 1;
    private int sl;
    private int sa;
    private Peca[] pecas;
    
    int[] pos = new int [2]; //pos na matriz tabuleiro
    
    
    JButton TabPronto  = new JButton();

    {
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        sl=screenSize.width;
        sa=screenSize.height;
        leftX=sl/2 + LARG_DEFAULT/2;
        topY= sa/2 - ALT_DEFAULT;
        pecas = new Peca[]{new Peca(30,topY,"H"),new Peca(150,topY,"H"),new Peca(270,topY,"H"),new Peca(390,topY,"H"),new Peca(510,topY,"H"),
                new Peca(30,topY+120,"S"),new Peca(90,topY+120,"S"),new Peca(150,topY+120,"S"),new Peca(210,topY+120,"S"),
                new Peca(30,topY+210,"D"),new Peca(120,topY+210,"D"),new Peca(210,topY+210,"D"),
                new Peca(30,topY+300,"Cr"),new Peca(180,topY+300,"Cr"),
                new Peca(30,topY+390,"Co")};
    }


    public TabuleiroPos(){
        for(Peca peca:pecas)
            this.add(peca);
        setLayout(null);
        TabPronto.setBounds(sl/2-110,sa-200,220,40);
        addMouseListener(this);
        mudarFaseAtaque();
    }
    


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] tabuleiro = ctrl.getCtrl().getTabuleiro(JogadorVez);
        String nomeJogador = ctrl.getCtrl().getNomeJogador(JogadorVez);
        TabPronto.setText(String.format("Tabuleiro de %s pronto",nomeJogador));
        this.add(TabPronto);
        TabPronto.setVisible(true);
        DesenhaTabuleiro.Desenha(tabuleiro,g,leftX,topY);
        for(Peca peca:pecas){
            peca.addMouseListener(peca);
            peca.addMouseListener(this);
            peca.CriaPeca(g);
        }

    }
    
    public void mudarFaseAtaque() {
    	TabPronto.addActionListener(new ActionListener(){
    	    public void actionPerformed(ActionEvent event){

    	    	System.out.println("B1");
    	    	
    	    	//COLOCAR COD PARA MUDANCA DE FASE
    	    }
    	});
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX() ;
        int y = e.getY() ;
        

        leftX=sl/2 + LARG_DEFAULT/2;
        topY= sa/2 - ALT_DEFAULT;

        pos[0] = (x-leftX)/30+1; //coluna
        pos[1] = (y-topY)/30+1; //linha

        System.out.println( pos[0] ) ;
        System.out.println( pos[1] ) ;
        
        repaint() ;
        
        return;

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
