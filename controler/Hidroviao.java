package controler;

public class Hidroviao extends Peca {

    public Hidroviao(){
        valor = 5;
    }

    public Coordenadas[] Coordenadas_peca() {
        Coordenadas[] hidroaviao;
        if (rotacao == 0){
            hidroaviao = new Coordenadas[]{new Coordenadas(coluna,linha+1),new Coordenadas(coluna+1,linha),
                    new Coordenadas(coluna+2,linha+1)};
        }
        else if (rotacao == 1){
            hidroaviao = new Coordenadas[]{new Coordenadas(coluna-1,linha),new Coordenadas(coluna,linha+1),
                    new Coordenadas(coluna-1,linha+2)};
        }
        else if (rotacao == 2){
            hidroaviao = new Coordenadas[]{new Coordenadas(coluna,linha-1),new Coordenadas(coluna-1,linha),
                    new Coordenadas(coluna-2,linha-1)};
        }
        else{
            hidroaviao = new Coordenadas[]{new Coordenadas(coluna+1,linha),new Coordenadas(coluna,linha-1),
                    new Coordenadas(coluna+1,linha-2)};
        }
        return hidroaviao;
    }


    public void  abatido() {
        if(atingido >= 3)
            valor = -1;
    }
}
