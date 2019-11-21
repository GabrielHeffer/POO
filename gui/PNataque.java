package gui;

import Observer.Observado;
import Observer.Observador;
import controler.Regras;

import javax.swing.*;
import java.awt.*;

public class PNataque extends JPanel implements Observador {

    private int sl;
    private int sa;
    private JLabel label_jogador = new JLabel("Tabuleiro Jogador");
    private JLabel label_oponente = new JLabel("Tabuleiro Oponente");
    private JButton terminar_jogada = new JButton("Terminar jogada");


    public PNataque(){
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        Dimension okBt = terminar_jogada.getPreferredSize();
        sl=screenSize.width;
        sa=screenSize.height;
        terminar_jogada.setBounds(sl/2-110,sa-200,220,40);
        label_jogador.setBounds(30,20,200,50);
        label_oponente.setBounds(sl - 200,20,200,50);
        setLayout(null);
        add(label_jogador);
        add(label_oponente);
        add(terminar_jogada);
        Regras.getCtrl().add(this);
        repaint();
    }

    public void paintComponent(Graphics g) {
        int indice;
        Object[] dados = (Object[]) Regras.getCtrl().get(this);
        indice = (int)dados[0];
        DesenhaTabuleiro.Desenha( (int[][]) dados[ indice ],g,40,100,false);
        indice = (indice +1)%2;
        DesenhaTabuleiro.Desenha( (int[][]) dados[ indice ],g,sl - 490,100,false);
    }

    @Override
    public void notify(String mensagem, Observado o) {

    }
}
