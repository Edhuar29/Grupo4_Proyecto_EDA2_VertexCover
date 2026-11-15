package ec.edu.epn.eda2.model;

/**
 * Representa un enlace de red no dirigido entre dos dispositivos (u, v).
 */
public class Arista {
    private final int u;
    private final int v;

    public Arista(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    @Override
    public String toString() {
        return "(" + u + " <-> " + v + ")";
    }
}
