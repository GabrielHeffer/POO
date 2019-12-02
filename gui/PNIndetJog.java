package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import controler.Regras;


public class PNIndetJog extends JPanel implements ActionListener {
    private JLabel jog1Label = new JLabel("Jogador 1 : ");
    private JLabel jog2Label = new JLabel("Jogador 2 : ");
    private JTextField TextFieldjog1 = new JTextField(30);
    private JTextField TextFieldjog2 = new JTextField(30);
    private JButton ok = new JButton("Novo Jogo");
    private MenuBar menu_load;

    public PNIndetJog(){
        Dimension textjog = jog1Label.getPreferredSize();
        Dimension okBt = ok.getPreferredSize();
        menu_load = new MenuBar("Load",this);
        this.setLayout(null);
        add(menu_load);
        jog1Label.setBounds(40,50,textjog.width,textjog.height);
        jog2Label.setBounds(40,120,textjog.width,textjog.height);
        TextFieldjog1.setBounds(120,40,200,textjog.height+20);
        TextFieldjog2.setBounds(120,110,200,textjog.height+20);
        ok.setBounds(200 - okBt.width/2 - 10,200,okBt.width,okBt.height);
        ok.addActionListener(this);
        this.add(jog1Label);
        this.add(TextFieldjog1);
        this.add(jog2Label);
        this.add(TextFieldjog2);
        this.add(ok);

    }

    public void actionPerformed(ActionEvent e) {
        String nome1,nome2;
        nome1 = TextFieldjog1.getText();
        nome2 = TextFieldjog2.getText();
        if(Regras.getCtrl().SetNomesJogadores(nome1,nome2)) {
            this.setVisible(false);
            PNjogo.getPnjogo().MostraTabuleiro();
        }
        return;
    }
}