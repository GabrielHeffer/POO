package controler;
import Observer.*;



public class Regras implements Observado {
    private Observador[] observadores = new Observador[100];
    private int observadoresIndex = 0;
    private String NomeJog1,NomeJog2;
    private int[] [] TabuleiroJog1 = new int[][]{new int[15],new int[15],new int[15],
                                                 new int[15],new int[15],new int[15],
                                                 new int[15],new int[15],new int[15],
                                                 new int[15],new int[15],new int[15],
                                                 new int[15],new int[15],new int[15]};
    private int[] [] TabuleiroJog2 = TabuleiroJog1.clone();

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
