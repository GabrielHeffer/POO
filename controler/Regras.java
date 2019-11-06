package controler;
import Observer.*;



public class Regras implements Observado {
    private Observador[] observadores = new Observador[100];
    private int observadoresIndex = 0;
    private String NomeJog1,NomeJog2;
    private int[] [] TabuleiroJog1 = new int[15][];
    private int[] [] TabuleiroJog2;
    private Hidroviao[] hidroavioes;
    private Submarino[] submarinos;
    private Destroyers[] destroyers;
    private Cruzador[] cruzadores;
    private Couracado[] couracado;

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

    public Regras(){ }

    public boolean SetNomesJogadores(String nomeJog1,String nomeJog2){
        if(nomeJog1.equals("") || nomeJog2.equals(""))
            return false;
        NomeJog1 = nomeJog1;
        NomeJog2 = nomeJog2;
        return true;
    }
    public void add(Observador o) {
        observadores[observadoresIndex] = o;
        observadoresIndex++;
    }

    public int[][] getTab(int jog){
        if(jog == 1)
            return TabuleiroJog1;
        else if(jog == 2)
            return TabuleiroJog2;
        return null;
    }

    public String getNome(int jog) {
        if (jog == 1)
            return NomeJog1;
        else if (jog == 2)
            return NomeJog2;
        return null;
    }
}
