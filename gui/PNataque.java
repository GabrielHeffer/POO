package gui;

import Observer.Observado;
import Observer.Observador;
import controler.Coordenadas;
import controler.Regras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class PNataque extends JPanel implements Observador, MouseListener, ActionListener {

    private int sl;
    private int sa;
    private String label_ataque = new String("");
    private JButton terminar_jogada = new JButton();
    private int jodadas_restantes = 3;
    private MenuBar menu_save = new MenuBar("Save",this);


    public PNataque(){
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        Dimension okBt = terminar_jogada.getPreferredSize();
        sl=screenSize.width;
        sa=screenSize.height;
        terminar_jogada.setBounds(sl/2-110,sa-200,220,40);
        terminar_jogada.addActionListener(this);
        add(terminar_jogada);
        add(menu_save);
        setLayout(null);
        addMouseListener(this);
        Regras.getCtrl().add(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int indice;
        Graphics2D g2d = (Graphics2D) g;
        Object[] dados = (Object[]) Regras.getCtrl().get(this);
        jodadas_restantes = (int)dados[4];
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        terminar_jogada.setText(String.format("Terminar jogada %s",(String)dados[3] ) );
        g2d.drawString(String.format("Tabuleiro Jogador %s",(String)dados[3]),30,50);
        g2d.drawString(String.format("Tabuleiro Oponente",(String)dados[3]),sl - 200,50);
        g2d.drawString(String.format("Jogadas restantes: %d",jodadas_restantes),sl/2-150/2,sa-100);
        g2d.drawString(label_ataque,sl/2 - 70,sa/2+150);
        g2d.setFont(null);
        indice = (int)dados[0];
        DesenhaTabuleiro.Desenha( (int[][]) dados[ indice+1 ],g,40,100,"Jogador");
        indice = (indice +1)%2;
        DesenhaTabuleiro.Desenha( (int[][]) dados[ indice+1 ],g,sl - 490,100,"Oponente");
    }

    public void notify(String mensagem, Observado o) {
        if( mensagem.equals("agua") )
            label_ataque = "Tiro na água!";
        else if( mensagem.equals("S") )
            label_ataque = "Acertou um Submarino";
        else if( mensagem.equals("H") )
            label_ataque = "Acertou um Hidroavião";
        else if( mensagem.equals("D") )
            label_ataque = "Acertou um Destroyer";
        else if( mensagem.equals("Cr") )
            label_ataque = "Acertou um Cruzador";
        else if( mensagem.equals("Co") )
            label_ataque = "Acertou um Couraçado";
        else if( mensagem.equals("Vencedor") ){
            Object[] dados = (Object[]) Regras.getCtrl().get(this);
            JOptionPane.showMessageDialog(null, String.format("Vencedor é o jogador %s",(String)dados[3]));
            setVisible(false);
            Regras.getCtrl().NovoJogo();
            PNjogo.getPnjogo().IdJogadoresPanel();
        }
        Object[] dados = (Object[]) Regras.getCtrl().get(this);
        jodadas_restantes = (int)dados[4];
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int[] pos = new int[2];
        int leftX = sl - 490;
        int topY = 100;
        int x = e.getX();
        int y = e.getY();

        pos[0] = (x - leftX) / 30; //coluna
        pos[1] = (y - topY) / 30; //linha

        if (pos[0] >= 0 && pos[0] < 15 && pos[1] >= 0 && pos[1] < 15 && jodadas_restantes>0){
            Coordenadas cord = new Coordenadas(pos[0], pos[1]);
            Regras.getCtrl().Ataque(cord);
        }


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
    public void actionPerformed(ActionEvent e) {
        if(jodadas_restantes == 0) {
            Regras.getCtrl().mudarJogadorAtaque();
            label_ataque = "";
        }
        repaint();
    }
}
