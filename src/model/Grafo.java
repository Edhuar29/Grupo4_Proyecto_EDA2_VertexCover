package model;

import java.util.ArrayList;
import java.util.List;

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
