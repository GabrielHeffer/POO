package controler;

public class Submarino extends Peca{

    public Submarino(){
        valor = 1;
    }


    public Coordenadas[] Coordenadas_peca() {
        Coordenadas[] submarino = new Coordenadas[]{new Coordenadas(coluna,linha)};
        return submarino;
    }


    public void abatido() {
        if(atingido > 0)
            valor = -1;
    }
    
    public int getValue () { return valor; }
}
