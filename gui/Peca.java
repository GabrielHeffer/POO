package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import gui.select;

public class Peca extends JPanel implements MouseListener {
    String peca;
    int leftX;
    int topY;

    public Peca(int x, int y, String desPec){
        leftX = x;
        topY = y;
        peca = desPec;
        setLayout(null);
        setOpaque(false);
        if(desPec.equals("H"))
            setBounds(leftX,topY,90,60);
        else if(desPec.equals("S"))
            setBounds(leftX,topY,30,30);
        else if(desPec.equals("D"))
            setBounds(leftX,topY,60,30);
        else if(desPec.equals("Cr"))
            setBounds(leftX,topY,120,30);
        else
            setBounds(leftX,topY,150,30);
    }

    private void EscolheCor(Graphics2D g2d){
        if(select.peca_selecionada == this)
            g2d.setPaint(Color.gray);
        else if(peca.equals("H"))
            g2d.setPaint(Color.green);
        else if(peca.equals("S"))
            g2d.setPaint(Color.cyan);
        else if(peca.equals("D"))
            g2d.setPaint(Color.orange);
        else if(peca.equals("Cr"))
            g2d.setPaint(Color.blue);
        else if(peca.equals("Co"))
            g2d.setPaint(Color.pink);
    }

    private void DesenhaPeca(Graphics2D g2d,Rectangle2D[] rects){
        for(Rectangle2D rect: rects){
            if(rect != null) {
                g2d.fill(rect);
                g2d.draw(rect);
            }
        }
    }

    public void CriaPeca(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D[] rect = new Rectangle2D[5];
        double larg = 30.0;
        double alt = 30.0;

        if(peca.equals("H")){
            rect[0] = new Rectangle2D.Double(leftX, topY, larg, alt);
            rect[1] = new Rectangle2D.Double(leftX+30.0, topY+30.0, larg, alt);
            rect[2] = new Rectangle2D.Double(leftX+60.0, topY, larg, alt);
            this.EscolheCor(g2d);
        }
        else if(peca.equals("S")){
            rect[0] = new Rectangle2D.Double(leftX, topY, larg, alt);
            this.EscolheCor(g2d);
        }
        else if(peca.equals("D")){
            rect[0] = new Rectangle2D.Double(leftX, topY, larg, alt);
            rect[1] = new Rectangle2D.Double(leftX+30.0, topY, larg, alt);
            this.EscolheCor(g2d);
        }
        else if(peca.equals("Cr")){
            rect[0] = new Rectangle2D.Double(leftX, topY, larg, alt);
            rect[1] = new Rectangle2D.Double(leftX+30.0, topY, larg, alt);
            rect[2] = new Rectangle2D.Double(leftX+60.0, topY, larg, alt);
            rect[3] = new Rectangle2D.Double(leftX+90.0, topY, larg, alt);
            this.EscolheCor(g2d);
        }
        else if(peca.equals("Co")){
            rect[0] = new Rectangle2D.Double(leftX, topY, larg, alt);
            rect[1] = new Rectangle2D.Double(leftX+30.0, topY, larg, alt);
            rect[2] = new Rectangle2D.Double(leftX+60.0, topY, larg, alt);
            rect[3] = new Rectangle2D.Double(leftX+90.0, topY, larg, alt);
            rect[4] = new Rectangle2D.Double(leftX+120.0, topY, larg, alt);
            this.EscolheCor(g2d);
        }
        this.DesenhaPeca(g2d,rect);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        select.peca_selecionada = this;
        repaint();
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
}
