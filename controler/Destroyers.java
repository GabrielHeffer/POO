package controler;

public class Destroyers extends Peca {

    public Destroyers(){
        valor = 2;
    }

    public Coordenadas[] Coordenadas_peca() {
        Coordenadas[] destroyer;
        if (rotacao == 0){
            destroyer = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna+1,linha)};
        }
        else if (rotacao == 1){
            destroyer = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna,linha+1)};
        }
        else if (rotacao == 2){
            destroyer = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna-1,linha)};
        }
        else{
            destroyer = new Coordenadas[]{new Coordenadas(coluna,linha),new Coordenadas(coluna,linha-1)};
        }
        return destroyer;
    }
    public void  abatido() {
        if(atingido >= 2)
            valor = -1;
    }

}
