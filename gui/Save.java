package gui;
	
import controler.PecasJogador;
import controler.Regras;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;

import Observer.Observado;
import Observer.Observador;


public class Save extends JFrame implements Observador {
	
	private String NomeJogador1;
	private String NomeJogador2;
    private String NomeJogVez;
    private int[] AtaquesJogadores = new int[2];
    private Object[] TabJogadores = new Object[2];
    private  int jogadorVez = 0;
    private PecasJogador[] PecasJogadores = new PecasJogador[2];
    private String NomeGanhador = null;
	
	
	public void saveJogo() throws IOException {
		Object[] dados = (Object[]) Regras.getCtrl().getTodosDados(this);
		BufferedWriter outputWriter = null;
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("testeAB"));
		writer.write((String) dados[0]);
		writer.newLine();
		writer.write((String) dados[1]);
		writer.newLine();
		TabJogadores =(Object[]) dados[5];

		writer.write(Arrays.toString(TabJogadores));

		writer.flush(); 
		writer.close();
	
	}

	@Override
	public void notify(String mensagem, Observado o) {
		// TODO Auto-generated method stub
		
	}
}