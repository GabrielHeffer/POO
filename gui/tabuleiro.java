package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class tabuleiro extends JPanel implements ActionListener {
	
	public final int LARG_DEFAULT=400;
	public final int ALT_DEFAULT=300;
	
    public tabuleiro(){
    	Toolkit tk=Toolkit.getDefaultToolkit();
    	Dimension screenSize=tk.getScreenSize();
    	int sl=screenSize.width;
    	int sa=screenSize.height;
    	int x=sl/2-LARG_DEFAULT/2;
    	int y=sa/2-ALT_DEFAULT/2;
    	setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
  
    	
    }
    
    
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Rectangle2D rt;
		Graphics2D g2d=(Graphics2D) g;
		double leftX=30.0;
		double topY=30.0;
		double larg=30.0;
		double alt=30.0;
		for (int i=0;i<15;i++)
		{

			for (int j=1;j<15;j++)
			{
				rt=new Rectangle2D.Double(leftX,topY,larg,alt);
				g2d.draw(rt);
				topY+=30;
			}
			
			leftX+=30.0;
			topY=30;

		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
