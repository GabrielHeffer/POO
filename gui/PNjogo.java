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
        int x=sl/2-400/2;
        int y=sa/2-300/2;
        setBounds(x,y,400,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        IdJogadoresPanel();

    }
    private void IdJogadoresPanel (){
        getContentPane().add(new PNIndetJog());
    }

    public static void main(String args[]){
        PNjogo x = new PNjogo();
        x.setTitle("Jogadores");
        x.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
