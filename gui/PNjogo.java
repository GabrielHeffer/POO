package gui;
import controler.Regras;

import javax.swing.*;
import java.awt.*;

public class PNjogo extends JFrame  {
    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    private static PNjogo ctrl_PNjogo;
    public static PNjogo getPnjogo(){
        if(ctrl_PNjogo == null)
            ctrl_PNjogo = new PNjogo();
        return ctrl_PNjogo;
    }
    private PNjogo(){
        int sl=screenSize.width;
        int sa=screenSize.height;
        int x=sl/2-400/2;
        int y=sa/2-300/2;
        setBounds(x,y,400,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        IdJogadoresPanel();
        setVisible(true);

    }
    private void IdJogadoresPanel (){
        setTitle("Jogadores");
        getContentPane().add(new PNIndetJog());
    }

    public void MostraTabuleiro () {
        int sl=screenSize.width;
        int sa=screenSize.height;
        setTitle("Posicionamento as Peças");
        setBounds(0,0,sl,sa);
        getContentPane().add(new TabuleiroPos());
    }

    public void PNAtaque(){
        getContentPane().add(new PNataque());
    }

    public static void main(String[] args){
        Regras.getCtrl();
        PNjogo.getPnjogo();
    }


}
