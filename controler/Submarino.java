package controler;

public class Submarino extends Peca{

    public Submarino(){
        valor = 1;
        atingido = 1;
    }


    public Coordenadas[] Coordenadas_peca() {
        Coordenadas[] submarino = new Coordenadas[]{new Coordenadas(coluna,linha)};
        return submarino;
    }

}
