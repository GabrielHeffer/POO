package controler;

public abstract class Peca {
    protected int valor;
    protected int rotacao = 0;
    protected int linha, coluna;
    protected int atingido;

    public abstract Coordenadas[] Coordenadas_peca();

    public abstract void abatido();

    public void setLocation(int x,int y){
        linha = y;
        coluna = x;
    }

    public void rotacionar(){
        rotacao = (rotacao+1)%4;
    }

    void Atingido(){
        atingido++;
        abatido();
    }

    public Coordenadas getCoordenadaInicial(){
        Coordenadas cord = new Coordenadas(coluna,linha);
        return cord;
    }
}
