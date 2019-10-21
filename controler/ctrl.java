package controler;

import gui.PNIndetJog;
import gui.PNjogo;

public class ctrl {
    static private ctrl controle_regras = null;
    PNjogo gui;
    Regras regrasJogo;
    private ctrl(){
        regrasJogo = new Regras();
        gui = new PNjogo();
    }
    public static ctrl getCtrl(){
        if(controle_regras == null)
            controle_regras = new ctrl();
        return controle_regras;
    }

    public boolean NomesJogadores (String jogador1, String jogador2){
        if(regrasJogo.SetNomesJogadores(jogador1,jogador2)) {
            gui.MostraTabuleiro();
            return true;
        }
        return false;
    }

    public String getNomeJogador(int jog){
        return regrasJogo.getNome(jog);
    }

    public int[][] getTabuleiro(int jog){
        return regrasJogo.getTab(jog);
    }

    public static void main(String args[]){
        getCtrl();
    }
}
