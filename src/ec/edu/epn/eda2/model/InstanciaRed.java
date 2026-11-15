package ec.edu.epn.eda2.model;

/**
 * Representa la instancia de entrada del problema de decision:
 * El grafo G y el presupuesto maximo de sensores k.
 */
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
