package controler;

public class Couracado extends Peca {

    public Couracado(){
        valor = 4;
    }

    public Coordenadas[] Coordenadas_peca() {
        Coordenadas[] couracado;
        if (rotacao == 0){
            couracado = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna+1,linha),
                    new Coordenadas(coluna+2,linha), new Coordenadas(coluna+3,linha), new Coordenadas(coluna+4,linha)};
        }
        else if (rotacao == 1){
            couracado = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna,linha+1),
                    new Coordenadas(coluna,linha+2), new Coordenadas(coluna,linha+3),new Coordenadas(coluna,linha+4)};
        }
        else if (rotacao == 2){
            couracado = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna-1,linha),
                    new Coordenadas(coluna-2,linha),new Coordenadas(coluna-3,linha),new Coordenadas(coluna-4,linha)};
        }
        else{
            couracado = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna,linha-1),
                    new Coordenadas(coluna,linha-2),new Coordenadas(coluna,linha-3),new Coordenadas(coluna,linha-4)};
        }
        return couracado;
    }
    public void  abatido() {
        if(atingido >= 5)
            valor = -1;
    }

}
