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
		
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("InformacoesJogo.txt"));
		writer.write((String) dados[0]);
		writer.newLine();
		writer.write((String) dados[1]);
		writer.newLine();
		int[][] tab =(int[][]) dados[5];
		int[][] tab2 =(int[][]) dados[6];

		for (int x = 0; x < 15; ++x) 
	        for (int y = 0; y < 15; ++y)  
	         writer.write(tab[x][y] + "");
		writer.newLine();
		
		writer.write((String) dados[1]);
		writer.newLine();
		
		for (int x = 0; x < 15; ++x) 
	        for (int y = 0; y < 15; ++y) 
	         writer.write(tab2[x][y]+ "");
		writer.newLine();
		
		
		AtaquesJogadores[0]=(int) dados[3];
		AtaquesJogadores[1]=(int) dados[4];
		
		writer.write(AtaquesJogadores[0] + "");
		writer.newLine();
		writer.write(AtaquesJogadores[1] + "");
		writer.newLine();
	
		NomeJogVez = (String) dados[2];
		writer.write(NomeJogVez + "");
		writer.newLine();
		
		jogadorVez =  (int) dados[7];
		writer.write(jogadorVez + "");
		writer.newLine();
		
		PecasJogadores[0] = (PecasJogador) dados[8];
		
		writer.write(PecasJogadores[0].pecasAfundadas() + "");
		writer.newLine();
        PecasJogadores[1] = (PecasJogador) dados[9];
        writer.write(PecasJogadores[1] + "");
        
		writer.flush(); 
		writer.close();
	
	}

	@Override
	public void notify(String mensagem, Observado o) {
		// TODO Auto-generated method stub
		
	}
}