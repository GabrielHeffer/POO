package controler;

public abstract class Peca {
    protected int valor;
    protected int rotacao = 0;
    protected int linha, coluna;
    protected int atingido;
    protected boolean posicionada = false;

    public abstract Coordenadas[] Coordenadas_peca();

    public void Atingir(){
        if(atingido>0){
            atingido--;
            if(atingido == 0)
                this.valor = -10;
        }
    }

    public void setLocation(int x,int y){
        linha = y;
        coluna = x;
    }

    public void rotacionar(){
        rotacao = (rotacao+1)%4;
    }

    public Coordenadas getCoordenadaInicial(){
        Coordenadas cord = new Coordenadas(coluna,linha);
        return cord;
    }

    public int getValor(){
        return valor;
    }

    public boolean is_posicionada(){
        return posicionada;
    }
}