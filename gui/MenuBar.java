package gui;


import controler.Regras;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;


public class MenuBar extends JMenuBar implements ActionListener{

	private JMenu SaveMenu = new JMenu("Menu");
	private JMenuItem SaveAction = new JMenuItem("Salvar Jogo");
	private JMenu LoadMenu = new JMenu("Menu");
	private JMenuItem LoadAction = new JMenuItem("Carregar Jogo");
	private String opcao;
	private JPanel panel;

	public MenuBar(String opcao, JPanel panel){
		int sl;
		this.panel = panel;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		sl=screenSize.width;
		this.opcao = opcao;
		super.setBounds(0,0,sl,30);
		super.setVisible(true);
		if(opcao == "Save"){
			SaveMenu.setSize(150,80);
			super.add(SaveMenu);
			SaveMenu.add(SaveAction);
			SaveAction.addActionListener(this);
		}
		else if(opcao == "Load"){
			LoadMenu.setSize(150,80);
			super.add(LoadMenu);
			LoadMenu.add(LoadAction);
			LoadAction.addActionListener(this);
		}
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		String fileName;
		if (opcao.equals("Save") ) {
			chooser.setCurrentDirectory(new File("/home/me/Documents"));
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				fileName = chooser.getSelectedFile() + ".txt";
				try {
					Regras.getCtrl().saveJogo(fileName);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		else if(opcao.equals("Load") ) {
			chooser.setCurrentDirectory(new File("/home/me/Documents"));
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				fileName = chooser.getSelectedFile() + "";
				try {
					Regras.getCtrl().LoadJogo(fileName);
					panel.setVisible(false);
					PNjogo.getPnjogo().PNAtaque();

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
