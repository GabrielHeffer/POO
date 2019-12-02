package controler;
import Observer.*;
import gui.PNjogo;


import java.io.*;
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

    public void ApagarLixo(){
        int linha,coluna;
        int[][] Tab = (int[][])TabJogadores[jogadorVez];
        for(linha = 0;linha < Tab.length;linha++) {
            for (coluna = 0; coluna < Tab[linha].length; coluna++)
                if (Tab[linha][coluna] == 9)
                    Tab[linha][coluna] = 0;
        }
        this.notificar("");
    }

    private int somaMatriz(int[][] tab,int limite_sup,int limite_inf,int limite_esq,int limite_dir){
        int soma = 0;
        for(int i = limite_sup;i <= limite_inf; i++) {
            for (int j = limite_esq; j <= limite_dir; j++)
                soma += tab[i][j];
        }
        return soma;
    }
    private int SomaRegras(int[][] tab,Coordenadas[] cords) {
        int limite_sup, limite_inf, limite_esq, limite_dir,soma=0;
        for (Coordenadas cord : cords) {
            if(cord.getLinha() < 0 || cord.getLinha() > 14) {
                soma = 10;
            }
            else if(cord.getColuna() < 0 || cord.getColuna() > 14) {
                soma = 10;
            }
            else {
                limite_esq = cord.getColuna() - 1;
                limite_dir = cord.getColuna() + 1;
                limite_sup = cord.getLinha() - 1;
                limite_inf = cord.getLinha() + 1;
                if (limite_esq < 0)
                    limite_esq = 0;
                if (limite_dir > 14)
                    limite_dir = 14;
                if (limite_sup < 0)
                    limite_sup = 0;
                if (limite_inf > 14)
                    limite_inf = 14;
                soma += somaMatriz(tab, limite_sup, limite_inf, limite_esq, limite_dir);
            }
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
            if(cord.getColuna() >= 0 && cord.getColuna() < 15 && cord.getLinha() >= 0 && cord.getLinha() < 15 &&
                    tab[cord.getLinha()][cord.getColuna()] == 0)
                tab[cord.getLinha()][cord.getColuna()] = valor;
        }
    }

    public void PosicionarPeca(String peca, int Idpeca,Coordenadas cord){
        Peca selecionada = null;
        Coordenadas[] pos;
        this.ApagarLixo();
        selecionada = (Peca) PecasJogadores[jogadorVez].getPeca(peca,Idpeca);
        selecionada.setLocation(cord.getColuna(),cord.getLinha());
        pos = selecionada.Coordenadas_peca();
        if(this.VerificarRegraPos(pos)) {
            this.ColocarPeca(pos, selecionada,0);
            selecionada.posicionada = true;
            this.notificar("POS");
        }else{
            this.ColocarPeca(pos, null,9);
            this.notificar("NPOS");
        }
    }

    private void ApagarPeca(Coordenadas[] cords,int valor){
        int[][] tab = (int[][]) TabJogadores[jogadorVez];
        for (Coordenadas cord : cords) {
            if(tab[cord.getLinha()][cord.getColuna()] == valor)
                tab[cord.getLinha()][cord.getColuna()] = 0;
        }
    }

    public void RetirarPeca(String peca, int Idpeca){
        Peca selecionada = null;
        Coordenadas[] pos;
        selecionada = PecasJogadores[jogadorVez].getPeca(peca,Idpeca);;
        this.ApagarPeca(selecionada.Coordenadas_peca(),selecionada.getValor());
        selecionada.setLocation(0,0);
        selecionada.posicionada = false;
        this.notificar("");
    }

    public void RotacionarPeca(String peca, int Idpeca){
        Peca selecionada = null;
        Coordenadas[] pos;
        selecionada = PecasJogadores[jogadorVez].getPeca(peca,Idpeca);

        pos = selecionada.Coordenadas_peca();
        this.ApagarPeca(pos,selecionada.getValor());
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
                tab[cord.getLinha()][cord.getColuna()] = 6;
            else
                tab[cord.getLinha()][cord.getColuna()] = 7;
            return valor_peca;
        }
        return -1;
    }

    public void Ataque(Coordenadas cord){
        if(NomeGanhador == null) {
            int valor;
            if (AtaquesJogadores[jogadorVez] > 0) {
                String embarc_atingida = new String("");
                int oponente = (jogadorVez + 1) % 2;
                int[][] tab = (int[][]) TabJogadores[oponente];
                valor = this.realizaAtaque(cord, tab);
                if (valor == 0) {
                    AtaquesJogadores[jogadorVez]--;
                    this.notificar("agua");
                } else if (valor > 0) {
                    AtaquesJogadores[jogadorVez]--;
                    if (valor == 1) {
                        PecasJogadores[oponente].atingirPeca("S", cord);
                        embarc_atingida = "S";
                    } else if (valor == 2) {
                        PecasJogadores[oponente].atingirPeca("D", cord);
                        embarc_atingida = "D";
                    } else if (valor == 3) {
                        PecasJogadores[oponente].atingirPeca("Cr", cord);
                        embarc_atingida = "Cr";
                    } else if (valor == 4) {
                        PecasJogadores[oponente].atingirPeca("Co", cord);
                        embarc_atingida = "Co";
                    } else if (valor == 5) {
                        PecasJogadores[oponente].atingirPeca("H", cord);
                        embarc_atingida = "H";
                    }
                }
                this.notificar(embarc_atingida);
                if(this.verificaGanhador())
                    this.notificar("Vencedor");
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
            tab_oponente[cord.getLinha()][cord.getColuna()] = 8;
    }

    private boolean verificaGanhador(){
        if(PecasJogadores[ (jogadorVez + 1)%2 ].pecasAfundadas())
            NomeGanhador = NomeJogVez;
        if(NomeGanhador != null)
            return true;
        return false;
    }

    public void NovoJogo(){
        int[] [] TabuleiroJog1 = new int[15][];
        int[] [] TabuleiroJog2 = new int[15][];
        for(int i = 0;i < 15;i++)
            TabuleiroJog1[i] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i = 0;i < 15;i++)
            TabuleiroJog2[i] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        TabJogadores[0] = TabuleiroJog1;
        TabJogadores[1] = TabuleiroJog2;
        NomeGanhador = null;
        NomeJogVez = null;
        PecasJogadores[0] = new PecasJogador();
        PecasJogadores[1] = new PecasJogador();
        NomesJogadores = new String[2];
    }

    public void saveJogo(String nameFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile));
        String pecas;
        writer.write((String) NomesJogadores[0]);
        writer.newLine();

        int[][] tab =(int[][]) TabJogadores[0];


        for (int x = 0; x < 15; ++x)
            for (int y = 0; y < 15; ++y)
                writer.write(tab[x][y] + "");
        writer.newLine();

        writer.write((String) NomesJogadores[1]);
        int[][] tab2 =(int[][]) TabJogadores[1];

        writer.newLine();

        for (int x = 0; x < 15; ++x)
            for (int y = 0; y < 15; ++y)
                writer.write(tab2[x][y]+ "");
        writer.newLine();



        writer.write(AtaquesJogadores[0] + "");
        writer.newLine();
        writer.write(AtaquesJogadores[1] + "");
        writer.newLine();


        writer.write(NomeJogVez + "");
        writer.newLine();


        writer.write(jogadorVez + "");
        writer.newLine();

        pecas = PecasJogadores[0].pecas2str();
        writer.write(pecas + "");

        pecas = PecasJogadores[1].pecas2str();
        writer.write(pecas + "");

        writer.write(NomeGanhador + "");

        writer.flush();
        writer.close();

    }

    private void CarregarPecas(PecasJogador pc,BufferedReader leitor) throws IOException{
        int H_index = -1,S_index = -1,D_index = -1,Co_index = -1,Cr_index = -1;
        Peca p = null;
        for(int i=0;i<15;i++){
            String peca = leitor.readLine();
            if(peca.equals("H")) {
                H_index++;
                p = pc.getPeca(peca,H_index);
            }
            else if(peca.equals("D")) {
                D_index++;
                p =  pc.getPeca(peca,D_index);
            }
            else if(peca.equals("S")) {
                S_index++;
                p =  pc.getPeca(peca,S_index);
            }
            else if(peca.equals("Cr")) {
                Cr_index++;
                p =  pc.getPeca(peca,Cr_index);
            }
            else if(peca.equals("Co")) {
                Co_index++;
                p =  pc.getPeca(peca,Co_index);
            }
            int rotacao = Integer.parseInt(leitor.readLine());
            int linha = Integer.parseInt(leitor.readLine());
            int coluna = Integer.parseInt(leitor.readLine());
            Coordenadas cord = new Coordenadas(coluna, linha);
            int atingido = Integer.parseInt(leitor.readLine());
            pc.AtualizaPeca(p,rotacao,cord,atingido);
        }
    }

    public void LoadJogo(String nameFile) throws IOException{
        int[][] tab;
        String tabuleiro;
        int index = 0;

        FileReader arq = new FileReader(nameFile);
        BufferedReader leitor = new BufferedReader(arq);
        NomesJogadores[0] = leitor.readLine();
        tab = (int[][])TabJogadores[0];
        tabuleiro = leitor.readLine();
        for (int x = 0; x < 15; ++x) {
            for (int y = 0; y < 15; ++y) {
                int valor = tabuleiro.charAt(index) - '0';
                tab[x][y] = valor;
                index++;
            }
        }
        NomesJogadores[1] = leitor.readLine();
        tab = (int[][])TabJogadores[1];
        tabuleiro = leitor.readLine();
        index = 0;
        for (int x = 0; x < 15; ++x) {
            for (int y = 0; y < 15; ++y) {
                int valor = tabuleiro.charAt(index);
                tab[x][y] = valor - '0';
                index++;
            }
        }
        AtaquesJogadores[0] = Integer.parseInt(leitor.readLine());
        AtaquesJogadores[1] = Integer.parseInt(leitor.readLine());
        NomeJogVez = leitor.readLine();
        jogadorVez = Integer.parseInt(leitor.readLine());
        this.CarregarPecas(PecasJogadores[0],leitor);
        this.CarregarPecas(PecasJogadores[1],leitor);
        return;
    }

    public void notificar(String mensagem) {
        for (Observador o : lob)
            o.notify(mensagem,this);
    }
}
