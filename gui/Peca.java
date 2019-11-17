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
    private String peca;
    private int Id;
    private int leftX;
    private int topY;
    private int value;
    private boolean posicionada;
    private int width,height,rotacao = 0;

    public Peca(int x, int y, String desPec, int id, int valor){
        leftX = x;
        topY = y;
        peca = desPec;
        Id = id;
        value = valor;
        setLayout(null);
        setOpaque(false);
        if(desPec.equals("H")){
            width = 90;
            height = 60;
        }
        else if(desPec.equals("S")){
            width = 30;
            height = 30;
        }
        else if(desPec.equals("D")){
            width = 60;
            height = 30;
        }
        else if(desPec.equals("Cr")){
            width = 120;
            height = 30;
        }
        else{
            width = 150;
            height = 30;
        }
            setBounds(leftX,topY,width,height);
    }
    
    public int getValue() { return value; }

    public String getPeca(){ return peca; }

    public int getId(){ return Id; }

    public void Posicionar(int leftx,int topy) {
        this.leftX = leftx;
        this.topY = topy;
        setLocation(leftX,topY);
    }

    public void Rotacionar(){
        rotacao = (rotacao+1)%4;
        if(rotacao == 1)
            setSize(height,width);
        else if(rotacao == 2)
            setBounds(leftX - width,topY - height,width,height);
        else if(rotacao == 3)
            setBounds(leftX - height,topY - width,height,width);
        else
            setBounds(leftX,topY,width,height);
    }

    private void EscolheCor(Graphics2D g2d){
        if(this.equals(select.peca_selecionada))
            g2d.setColor(Color.gray);
        else if(this.posicionada)
            g2d.setColor(Color.white);
        else if(peca.equals("H"))
            g2d.setColor(new Color(130, 169, 230));
        else if(peca.equals("S"))
            g2d.setColor(new Color(169, 220, 160));
        else if(peca.equals("D"))
            g2d.setColor(new Color(255, 230, 101));
        else if(peca.equals("Cr"))
            g2d.setColor(new Color(232, 128, 115));
        else if(peca.equals("Co"))
            g2d.setColor(new Color(186, 126, 235));
    }


    private void DesenhaPeca(Graphics2D g2d,Rectangle2D[] rects){
        for(Rectangle2D rect: rects){
            if(rect != null) {
                g2d.fill(rect);
                g2d.draw(rect);
            }
        }
        g2d.setColor(null);
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

    public void setPosicionada(){
        posicionada = true;
    }

    public void setNotPosicionada(){
        posicionada = false;
    }

    public boolean equals(Peca p){
        if(p != null && p.peca.equals(this.peca) && p.Id == this.Id)
            return true;
        return false;
    }

    public boolean is_posicionada(){
        return posicionada;
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
