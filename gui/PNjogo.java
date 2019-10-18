package gui;
import javax.swing.*;
import java.awt.*;

public class PNjogo extends JFrame {

    JPanel IDjog;
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
    private void IdJogadoresPanel(){
        IDjog = new JPanel();
        JLabel jog1Label = new JLabel("Jogador 1 : ");
        JLabel jog2Label = new JLabel("Jogador 2 : ");
        JTextField TextFieldjog1 = new JTextField(15);
        JTextField TextFieldjog2 = new JTextField(15);
        JButton ok = new JButton("OK");
        Dimension textjog = jog1Label.getPreferredSize();
        Dimension okBt = ok.getPreferredSize();
        IDjog.setLayout(null);
        jog1Label.setBounds(40,50,textjog.width,textjog.height);
        jog2Label.setBounds(40,120,textjog.width,textjog.height);
        TextFieldjog1.setBounds(120,40,200,textjog.height+20);
        TextFieldjog2.setBounds(120,110,200,textjog.height+20);
        ok.setBounds(200 - okBt.width/2 - 10,200,okBt.width,okBt.height);
        IDjog.add(jog1Label);
        IDjog.add(TextFieldjog1);
        IDjog.add(jog2Label);
        IDjog.add(TextFieldjog2);
        IDjog.add(ok);
        getContentPane().add(IDjog);
    }

    public static void main(String args[]){
        PNjogo x = new PNjogo();
        x.setTitle("Jogadores");
        x.setVisible(true);
    }
}
