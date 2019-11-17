package controler;
import Observer.*;
import gui.PNjogo;

import java.util.ArrayList;
import java.util.List;


public class Regras implements Observado {
    List<Observador> lob=new ArrayList<Observador>();
    private String NomeJog1,NomeJog2;
    private String NomeJogVez;
    private int[] [] TabuleiroJog1 = new int[15][];
    private int[] [] TabuleiroJog2;
    private  int jogadorVez = 1;
    protected Hidroviao[] hidroavioes;
    protected Submarino[] submarinos;
    protected Destroyers[] destroyers;
    protected Cruzador[] cruzadores;
    protected Couracado[] couracado;
    private static  Regras ctrl_regras = null;

    private Regras(){ }
    public static Regras getCtrl(){
        if(ctrl_regras == null)
            ctrl_regras = new Regras();
        return ctrl_regras;
    }

    {
        for(int i = 0;i < 15;i++)
            TabuleiroJog1[i] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        TabuleiroJog2 = TabuleiroJog1.clone();
        hidroavioes = new Hidroviao[]{new Hidroviao(),new Hidroviao(),new Hidroviao(),new Hidroviao(),new Hidroviao()};
        submarinos = new Submarino[]{new Submarino(),new Submarino(),new Submarino(),new Submarino()};
        destroyers = new Destroyers[]{new Destroyers(),new Destroyers(),new Destroyers()};
        cruzadores = new Cruzador[]{new Cruzador(),new Cruzador()};
        couracado = new Couracado[]{new Couracado()};
    }

    public boolean SetNomesJogadores(String nomeJog1,String nomeJog2){
        if(nomeJog1.equals("") || nomeJog2.equals(""))
            return false;
        NomeJog1 = nomeJog1;
        NomeJog2 = nomeJog2;
        NomeJogVez = NomeJog1;
        return true;
    }
    public void add(Observador o) {
        lob.add(o);
    }

    @Override
    public void remove(Observador o) {
        lob.remove(o);
    }

    @Override
    public Object get(Observador o) {
        Object dados[]=new Object[5];

        dados[0]= jogadorVez;
        dados[1]=TabuleiroJog1;
        dados[2]=TabuleiroJog2;
        dados[3]=NomeJogVez;

        return dados;
    }

    private int SomaRegras(int[][] tab,Coordenadas[] cords){
        int soma =0;
        for(Coordenadas cord: cords) {
            try{
                soma += tab[cord.getLinha()][cord.getColuna()] ;
            }catch (Exception e){ return -1; }
            try {
                soma += tab[cord.getLinha() - 1][cord.getColuna() - 1] +
                        tab[cord.getLinha() - 1][cord.getColuna()] +
                        tab[cord.getLinha() - 1][cord.getColuna() + 1];
            } catch (Exception e) { soma += 0; }
            try {
                soma += tab[cord.getLinha()][cord.getColuna() - 1] +
                        tab[cord.getLinha()][cord.getColuna() + 1];
            } catch (Exception e) { soma += 0;}
            try {
                soma += tab[cord.getLinha() + 1][cord.getColuna() - 1] +
                        tab[cord.getLinha() + 1][cord.getColuna()] +
                        tab[cord.getLinha() + 1][cord.getColuna() + 1];
            } catch (Exception e) { soma += 0;}
        }
        return soma;
    }

    private boolean VerificarRegraPos(Coordenadas[] cords){
        int[][] tab;
        int soma = 0;
        if( jogadorVez == 1 )
            tab = TabuleiroJog1;
        else
            tab = TabuleiroJog2;
        soma = this.SomaRegras(tab,cords);
        if(soma == 0)
            return true;
        return false;

    }

    private void ColocarPeca(Coordenadas[] pos,Peca peca){
        for(Coordenadas cord: pos){
            if(jogadorVez == 1)
                TabuleiroJog1[cord.getLinha()][cord.getColuna()] = peca.valor;
            else if(jogadorVez == 2)
                TabuleiroJog2[cord.getLinha()][cord.getColuna()] = peca.valor;
        }
    }

    private Peca IdentificaPeca(String peca, int Idpeca){
        Peca selecionada = null;
        if(peca.equals("H"))
            return hidroavioes[Idpeca];
        else if(peca.equals("S"))
            return submarinos[Idpeca];
        else if(peca.equals("D"))
            return destroyers[Idpeca];
        else if(peca.equals("Cr"))
            return cruzadores[Idpeca];
        else if(peca.equals("Co"))
            return couracado[Idpeca];
        return null;
    }

    public void PosicionarPeca(String peca, int Idpeca,Coordenadas cord){
        Peca selecionada = null;
        Coordenadas[] pos;
        selecionada = this.IdentificaPeca(peca,Idpeca);
        selecionada.setLocation(cord.getColuna(),cord.getLinha());
        pos = selecionada.Coordenadas_peca();
        if(this.VerificarRegraPos(pos)) {
            this.ColocarPeca(pos, selecionada);
            this.notificar();
        }else{
            selecionada.setLocation(0,0);
        }
    }

    private void ApagarPeca(Coordenadas[] cords){
        for (Coordenadas cord : cords) {
            if (jogadorVez == 1)
                TabuleiroJog1[cord.getLinha()][cord.getColuna()] = 0;
            else if (jogadorVez == 2)
                TabuleiroJog2[cord.getLinha()][cord.getColuna()] = 0;
        }
    }

    public void RetirarPeca(String peca, int Idpeca){
        Peca selecionada = null;
        Coordenadas[] pos;
        selecionada = this.IdentificaPeca(peca,Idpeca);
        this.ApagarPeca(selecionada.Coordenadas_peca());
        selecionada.setLocation(0,0);
        this.notificar();
    }

    public void RotacionarPeca(String peca, int Idpeca){
        Peca selecionada = null;
        Coordenadas[] pos;
        selecionada = this.IdentificaPeca(peca,Idpeca);

        pos = selecionada.Coordenadas_peca();
        this.ApagarPeca(pos);
        selecionada.rotacionar();
        pos = selecionada.Coordenadas_peca();
        if( !this.VerificarRegraPos(pos) ){
            selecionada.rotacionar();
            selecionada.rotacionar();
            selecionada.rotacionar();
        }
        this.PosicionarPeca(peca,Idpeca,selecionada.getCoordenadaInicial());
    }
    
    public void MudaTabJogador2()
	{
		if (jogadorVez == 2)
		{
			for(Observador o: lob)
			{
				o.notify(this);
			}
		}

	}

    public void notificar() {
        for (Observador o : lob)
            o.notify(this);
    }
}
