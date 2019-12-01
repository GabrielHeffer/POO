package gui;
	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
 

public class Save extends JFrame {
     
    public Save() {
         
        setTitle("Menu Example");
        setSize(150, 150);
         
        // Cria uma barra de menu para o JFrame
        JMenuBar menuBar = new JMenuBar();
         
        // Adiciona a barra de menu ao  frame
        setJMenuBar(menuBar);
         
        // Define e adiciona dois menus drop down na barra de menus
        JMenu SaveMenu = new JMenu("Menu");
      
        menuBar.add(SaveMenu);
        // Cria e adiciona um item simples para o menu
        JMenuItem SaveAction = new JMenuItem("Save");
       
        SaveMenu.add(SaveAction);
        this.setVisible(true);

}
}