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
    private int jogadorVez = 1;
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

    private boolean VerificarRegraPos(Coordenadas cord,int[][] Tab){
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

    public void PosicionarPeca(String peca, int Idpeca,Coordenadas cord){
        Peca selecionada = null;
        Coordenadas[] pos;
        if(peca.equals("H"))
            selecionada = hidroavioes[Idpeca];
        else if(peca.equals("S"))
            selecionada = submarinos[Idpeca];
        else if(peca.equals("D"))
            selecionada = destroyers[Idpeca];
        else if(peca.equals("Cr"))
            selecionada = cruzadores[Idpeca];
        else if(peca.equals("Co"))
            selecionada = couracado[Idpeca];
        if(selecionada == null)
            return;
        selecionada.setLocation(cord.getColuna(),cord.getLinha());
        pos = selecionada.Coordenadas_peca();
        this.ColocarPeca(pos,selecionada);
        for(Observador o: lob)
            o.notify(this);
    }
}
