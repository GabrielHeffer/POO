package controler;

public class PecasJogador {
    private Hidroviao[] hidroavioes;
    private Submarino[] submarinos;
    private Destroyers[] destroyers;
    private Cruzador[] cruzadores;
    private Couracado[] couracado;

    public PecasJogador(){
        hidroavioes = new Hidroviao[]{new Hidroviao(),new Hidroviao(),new Hidroviao(),new Hidroviao(),new Hidroviao()};
        submarinos = new Submarino[]{new Submarino(),new Submarino(),new Submarino(),new Submarino()};
        destroyers = new Destroyers[]{new Destroyers(),new Destroyers(),new Destroyers()};
        cruzadores = new Cruzador[]{new Cruzador(),new Cruzador()};
        couracado = new Couracado[]{new Couracado()};
    }

    public Peca getPeca(String peca,int Idpeca){
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

    public boolean verificaPos(){
        for(Peca pec: hidroavioes)
            if(!pec.is_posicionada())
                return false;
        for(Peca pec: submarinos)
            if(!pec.is_posicionada())
                return false;
        for(Peca pec: destroyers)
            if(!pec.is_posicionada())
                return false;
        for(Peca pec: cruzadores)
            if(!pec.is_posicionada())
                return false;
        for(Peca pec: couracado)
            if(!pec.is_posicionada())
                return false;
        return true;
    }
}
