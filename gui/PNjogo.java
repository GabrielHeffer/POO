package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PNjogo extends JFrame implements ActionListener {

    public PNjogo(){
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        int sl=screenSize.width;
        int sa=screenSize.height;
        int x=sl/20-400/20;
        int y=sa/20-300/20;
        setBounds(x,y,4000,2000);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //IdJogadoresPanel();
        getTab();

    }
    private void IdJogadoresPanel (){
        getContentPane().add(new PNIndetJog());
    }
    
    private void getTab () {
    	getContentPane().add(new tabuleiro());
    	
    }
    

    public static void main(String args[]){
        PNjogo x = new PNjogo();
        x.setTitle("Jogadores");
        x.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
