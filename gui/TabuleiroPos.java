package gui;

import Observer.Observado;
import Observer.Observador;
import controler.Regras;
import controler.Coordenadas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class TabuleiroPos extends JPanel implements MouseListener, Observador {

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
        pecas = new Peca[]{new Peca(30,topY,"H",0,5),new Peca(150,topY,"H",1,5),new Peca(270,topY,"H",2,5),new Peca(390,topY,"H",3,5),new Peca(510,topY,"H",4,5),
                new Peca(30,topY+120,"S",0,1),new Peca(90,topY+120,"S",1,1),new Peca(150,topY+120,"S",2,1),new Peca(210,topY+120,"S",3,1),
                new Peca(30,topY+210,"D",0,2),new Peca(120,topY+210,"D",1,2),new Peca(210,topY+210,"D",2,2),
                new Peca(30,topY+300,"Cr",0,3),new Peca(180,topY+300,"Cr",1,3),
                new Peca(30,topY+390,"Co",0,4)};
    }


    public TabuleiroPos(){
        for(Peca peca:pecas)
            this.add(peca);
        setLayout(null);
        TabPronto.setBounds(sl/2-110,sa-200,220,40);
        addMouseListener(this);
        mudarFaseAtaque();
        Regras.getCtrl().add(this);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Object[] dados = (Object[]) Regras.getCtrl().get(this);
        TabPronto.setText(String.format("Tabuleiro de %s pronto",dados[3]));
        this.add(TabPronto);
        TabPronto.setVisible(true);
       
        DesenhaTabuleiro.Desenha( (int[][]) dados[ (int) dados[0] ],g,leftX,topY);

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

        pos[0] = (x-leftX)/30; //coluna
        pos[1] = (y-topY)/30; //linha

        if( ( pos[0]>=0 && pos[0]<15 ) && ( pos[1]>=0 && pos[1]<15 ) && select.peca_selecionada != null ){
            Coordenadas cord = new Coordenadas(pos[0],pos[1]);
            Regras.getCtrl().PosicionarPeca(select.peca_selecionada.getPeca(),
                    select.peca_selecionada.getId(),cord);
        }
        else
            repaint();


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

    @Override
    public void notify(Observado o) {
        repaint();
    }
}
