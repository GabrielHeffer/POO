package gui;
import controler.Regras;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class PNjogo extends JFrame implements ActionListener {
    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    private static PNjogo ctrl_PNjogo;
    private Save s = new Save();
    
    
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
        MenuBar ();
        

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
        repaint();
        getContentPane().add(new PNataque());
    }

    public void terminarJogo(){
        repaint();
    }
    
    public void MenuBar() {
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu SaveMenu = new JMenu("Menu");
      
        menuBar.add(SaveMenu);
        
        JMenuItem SaveAction = new JMenuItem("Save");
       
        SaveMenu.add(SaveAction);
        this.setVisible(true);
        SaveAction.addActionListener(this);

}

    public static void main(String[] args){
        Regras.getCtrl();
        PNjogo.getPnjogo();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		try {
			s.saveJogo();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}



}
