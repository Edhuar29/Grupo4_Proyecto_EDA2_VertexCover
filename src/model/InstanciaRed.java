package model;

public class InstanciaRed {
    private final Grafo grafo;
    private final int k;

    public InstanciaRed(Grafo grafo, int k) {
        this.grafo = grafo;
        this.k = k;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public int getK() {
        return k;
    }
}
