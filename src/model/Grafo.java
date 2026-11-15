package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la topología de la red de comunicaciones.
 * - Vértices: routers/dispositivos (0 a numVertices - 1).
 * - Aristas: enlaces físicos de transmisión entre dispositivos.
 */
public class Grafo {
    private final int numVertices;
    private final List<Arista> aristas;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.aristas = new ArrayList<>();
    }

    public int getNumVertices() {
        return numVertices;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public int getNumAristas() {
        return aristas.size();
    }

    public void agregarArista(int u, int v) {
        aristas.add(new Arista(u, v));
    }
}
