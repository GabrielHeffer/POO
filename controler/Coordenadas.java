package controler;

public class Coordenadas {
    private int linha;
    private int coluna;

    public Coordenadas(int x, int y){
        this.linha = x;
        this.coluna = y;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}
