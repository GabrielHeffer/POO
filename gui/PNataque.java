package gui;

import Observer.Observado;
import Observer.Observador;
import controler.Regras;

import javax.swing.*;
import java.awt.*;

public class PNataque extends JPanel implements Observador {

    private int sl;
    private int sa;

    public PNataque(){
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        sl=screenSize.width;
        sa=screenSize.height;
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        int indice;
        Object[] dados = (Object[]) Regras.getCtrl().get(this);
        indice = (int)dados[0];
        DesenhaTabuleiro.Desenha( (int[][]) dados[ indice ],g,20,20,false);
        indice = (indice +1)%2;
        DesenhaTabuleiro.Desenha( (int[][]) dados[ indice ],g,sl/2,sa + 20,false);
    }

    @Override
    public void notify(String mensagem, Observado o) {

    }
}
