package controler;

public class Coordenadas {
    private int linha;
    private int coluna;

    public Coordenadas(int x, int y){
        this.coluna = x;
        this.linha = y;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}
