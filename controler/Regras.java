package controler;
import Observer.*;
import gui.PNjogo;


import java.util.ArrayList;
import java.util.List;


public class Regras implements Observado {
    List<Observador> lob=new ArrayList<Observador>();
    private String[] NomesJogadores;
    private String NomeJogVez;
    private int[] AtaquesJogadores = new int[2];
    private Object[] TabJogadores = new Object[2];
    private  int jogadorVez = 0;
    private PecasJogador[] PecasJogadores = new PecasJogador[2];
    private String NomeGanhador = null;
    private static  Regras ctrl_regras = null;

    private Regras(){ }
    public static Regras getCtrl(){
        if(ctrl_regras == null)
            ctrl_regras = new Regras();
        return ctrl_regras;
    }

    {
        int[] [] TabuleiroJog1 = new int[15][];
        int[] [] TabuleiroJog2 = new int[15][];
        for(int i = 0;i < 15;i++)
            TabuleiroJog1[i] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i = 0;i < 15;i++)
            TabuleiroJog2[i] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        TabJogadores[0] = TabuleiroJog1;
        TabJogadores[1] = TabuleiroJog2;
        PecasJogadores[0] = new PecasJogador();
        PecasJogadores[1] = new PecasJogador();
        NomesJogadores = new String[2];
    }
    
    public Object getTodosDados(Observador o) {
        Object dados[]=new Object[11];

        dados[0] = NomesJogadores[0];
        dados[1] = NomesJogadores[1];
        dados[2] = NomeJogVez;
        dados[3] = AtaquesJogadores[0];
        dados[4] = AtaquesJogadores[1];
       
        dados[5] = TabJogadores[0];
        dados[6] = TabJogadores[1];
        dados[7] = jogadorVez;
        
        dados[8] = PecasJogadores[0];
        dados[9] = PecasJogadores[1];
        dados[10] = NomeGanhador;
        

        return dados;
    }

    public boolean SetNomesJogadores(String nomeJog1,String nomeJog2){
        if(nomeJog1.equals("") || nomeJog2.equals(""))
            return false;
        NomesJogadores[0] = nomeJog1;
        NomesJogadores[1] = nomeJog2;
        NomeJogVez = NomesJogadores[jogadorVez];
        return true;
    }
    public void add(Observador o) {
        lob.add(o);
    }

    public void remove(Observador o) {
        lob.remove(o);
    }

    @Override
    public Object get(Observador o) {
        Object dados[]=new Object[6];

        dados[0] = jogadorVez;
        dados[1] = TabJogadores[0];
        dados[2] = TabJogadores[1];
        dados[3] = NomeJogVez;
        dados[4] = AtaquesJogadores[jogadorVez];

        return dados;
    }
    


    private void ApagarLixo(int[][] Tab){
        int linha,coluna;
        for(linha = 0;linha < Tab.length;linha++)
            for(coluna = 0;coluna < Tab[linha].length;coluna++)
                if(Tab[linha][coluna] == -10)
                    Tab[linha][coluna] = 0;
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
        soma = this.SomaRegras((int[][])TabJogadores[jogadorVez],cords);
        if(soma == 0)
            return true;
        return false;

    }

    private void ColocarPeca(Coordenadas[] pos,Peca peca,int valor){
        int[][] tab = (int[][]) TabJogadores[jogadorVez];
        if(peca != null)
            valor = peca.getValor();
        for(Coordenadas cord: pos){
            if(tab[cord.getLinha()][cord.getColuna()] == 0)
                tab[cord.getLinha()][cord.getColuna()] = valor;
        }
    }

    public void PosicionarPeca(String peca, int Idpeca,Coordenadas cord){
        Peca selecionada = null;
        Coordenadas[] pos;
        this.ApagarLixo( (int[][]) TabJogadores[jogadorVez] );
        selecionada = (Peca) PecasJogadores[jogadorVez].getPeca(peca,Idpeca);
        selecionada.setLocation(cord.getColuna(),cord.getLinha());
        pos = selecionada.Coordenadas_peca();
        if(this.VerificarRegraPos(pos)) {
            this.ColocarPeca(pos, selecionada,0);
            selecionada.posicionada = true;
            this.notificar("POS");
        }else{
            this.ColocarPeca(pos, null,-10);
            this.notificar("NPOS");
        }
    }

    private void ApagarPeca(Coordenadas[] cords){
        int[][] tab = (int[][]) TabJogadores[jogadorVez];
        for (Coordenadas cord : cords) {
            tab[cord.getLinha()][cord.getColuna()] = 0;
        }
    }

    public void RetirarPeca(String peca, int Idpeca){
        Peca selecionada = null;
        Coordenadas[] pos;
        selecionada = PecasJogadores[jogadorVez].getPeca(peca,Idpeca);;
        this.ApagarPeca(selecionada.Coordenadas_peca());
        selecionada.setLocation(0,0);
        selecionada.posicionada = false;
        this.notificar("");
    }

    public void RotacionarPeca(String peca, int Idpeca){
        Peca selecionada = null;
        Coordenadas[] pos;
        selecionada = PecasJogadores[jogadorVez].getPeca(peca,Idpeca);

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
    
    public void MudaTabJogadorPos()
	{
	    if(PecasJogadores[jogadorVez].verificaPos()) {
            jogadorVez =(jogadorVez +1)%2;
            NomeJogVez = NomesJogadores[jogadorVez];
            this.notificar("");
        }
	}

	public boolean faseAtaque(){
        if(PecasJogadores[0].verificaPos() && PecasJogadores[1].verificaPos()){
            AtaquesJogadores = new int[2];
            AtaquesJogadores[0] = 3;
            AtaquesJogadores[1] = 3;
            jogadorVez = 0;
            NomeJogVez = NomesJogadores[0];
            return true;
        }
        return false;
    }

    private int realizaAtaque(Coordenadas cord,int [][] tab){
        int valor_peca = tab[cord.getLinha()][cord.getColuna()];
        if(tab[cord.getLinha()][cord.getColuna()] >= 0){
            if( valor_peca == 0)
                tab[cord.getLinha()][cord.getColuna()] = 10;
            else
                tab[cord.getLinha()][cord.getColuna()] = -1;
            return valor_peca;
        }
        return -1;
    }

    public void Ataque(Coordenadas cord){
        if(NomeGanhador == null) {
            int valor;
            if (AtaquesJogadores[jogadorVez] > 0) {
                int oponente = (jogadorVez + 1) % 2;
                int[][] tab = (int[][]) TabJogadores[oponente];
                valor = this.realizaAtaque(cord, tab);
                if (valor == 0) {
                    AtaquesJogadores[jogadorVez]--;
                    this.verificaGanhador();
                    this.notificar("agua");
                } else if (valor > 0) {
                    AtaquesJogadores[jogadorVez]--;
                    if (valor == 1) {
                        PecasJogadores[oponente].atingirPeca("S", cord);
                        this.notificar("S");
                    } else if (valor == 2) {
                        PecasJogadores[oponente].atingirPeca("D", cord);
                        this.notificar("D");
                    } else if (valor == 3) {
                        PecasJogadores[oponente].atingirPeca("Cr", cord);
                        this.notificar("Cr");
                    } else if (valor == 4) {
                        PecasJogadores[oponente].atingirPeca("Co", cord);
                        this.notificar("Co");
                    } else if (valor == 5) {
                        PecasJogadores[oponente].atingirPeca("H", cord);
                        this.notificar("H");
                    }
                }
            }
        }
    }

    public void mudarJogadorAtaque(){
        if(AtaquesJogadores[jogadorVez] == 0 && NomeGanhador == null){
            AtaquesJogadores[jogadorVez] = 3;
            jogadorVez = (jogadorVez+1)%2;
            NomeJogVez = NomesJogadores[jogadorVez];
            this.notificar("");
        }
    }

    public void afundarEmbarcacao(Peca p){
        int[][] tab_oponente = (int[][])TabJogadores[ (jogadorVez+1)%2 ];
        for(Coordenadas cord: p.Coordenadas_peca())
            tab_oponente[cord.getLinha()][cord.getColuna()] = -10;
    }

    public void verificaGanhador(){
        if(PecasJogadores[jogadorVez].pecasAfundadas())
            NomeGanhador = NomeJogVez;
        if(NomeGanhador != null)
            this.notificar("Vencedor");
    }

    public void notificar(String mensagem) {
        for (Observador o : lob)
            o.notify(mensagem,this);
    }
}
