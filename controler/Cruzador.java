package controler;

public class Cruzador extends Peca{

    public Cruzador(){
        valor = 3;
        atingido = 4;
    }

    public Coordenadas[] Coordenadas_peca() {
        Coordenadas[] cruzador;
        if (rotacao == 0){
            cruzador = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna+1,linha),
                    new Coordenadas(coluna+2,linha), new Coordenadas(coluna+3,linha)};
        }
        else if (rotacao == 1){
            cruzador = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna,linha+1),
                    new Coordenadas(coluna,linha+2), new Coordenadas(coluna,linha+3)};
        }
        else if (rotacao == 2){
            cruzador = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna-1,linha),
                    new Coordenadas(coluna-2,linha),new Coordenadas(coluna-3,linha)};
        }
        else{
            cruzador = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna,linha-1),
                    new Coordenadas(coluna,linha-2),new Coordenadas(coluna,linha-3)};
        }
        return cruzador;
    }

}
