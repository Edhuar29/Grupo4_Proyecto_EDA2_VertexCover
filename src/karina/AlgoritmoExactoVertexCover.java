package karina;

import model.Arista;
import model.Grafo;
import model.ResultadoSolucion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoExactoVertexCover {

    private long nodosExplorados;

    public ResultadoSolucion resolverDecision(Grafo grafo, int k) {
        if (grafo == null) {
            throw new IllegalArgumentException("El grafo no puede ser nulo.");
        }
        nodosExplorados = 0;
        long tInicio = System.nanoTime();
        List<Integer> certificado = new ArrayList<>();
        boolean exito = false;
        if (k >= 0) {
            exito = funcionRecursivaBacktrack(grafo, k, new ArrayList<>(), certificado);
            Collections.sort(certificado);
        }
        long tFin = System.nanoTime();
        return new ResultadoSolucion(exito, certificado, tFin - tInicio);
    }

    public ResultadoSolucion encontrarCoberturaMinima(Grafo grafo) {
        if (grafo == null) {
            throw new IllegalArgumentException("El grafo no puede ser nulo.");
        }
        long tInicio = System.nanoTime();
        long total = 0;
        for (int k = 0; k <= grafo.getNumVertices(); k++) {
            ResultadoSolucion res = resolverDecision(grafo, k);
            total += nodosExplorados;
            if (res.isTieneSolucion()) {
                nodosExplorados = total;
                res.setTiempoNano(System.nanoTime() - tInicio);
                return res;
            }
        }
        nodosExplorados = total;
        return new ResultadoSolucion(false, new ArrayList<>(), System.nanoTime() - tInicio);
    }

    public boolean funcionRecursivaBacktrack(Grafo grafo, int kRestante, List<Integer> solucionActual, List<Integer> solucionFinal) {
        boolean[] enS = new boolean[grafo.getNumVertices()];
        for (int nodo : solucionActual) {
            enS[nodo] = true;
        }
        return ramificar(grafo.getAristas(), enS, kRestante, solucionActual, solucionFinal);
    }

    private boolean ramificar(List<Arista> aristas, boolean[] enS, int kRestante, List<Integer> actual, List<Integer> fin) {
        nodosExplorados++;
        Arista libre = primeraAristaDescubierta(aristas, enS);
        if (libre == null) {
            fin.addAll(actual);
            return true;
        }
        if (kRestante == 0) {
            return false;
        }
        int[] extremos = {libre.getU(), libre.getV()};
        for (int nodo : extremos) {
            enS[nodo] = true;
            actual.add(nodo);
            if (ramificar(aristas, enS, kRestante - 1, actual, fin)) {
                return true;
            }
            actual.remove(actual.size() - 1);
            enS[nodo] = false;
        }
        return false;
    }

    private Arista primeraAristaDescubierta(List<Arista> aristas, boolean[] enS) {
        for (Arista a : aristas) {
            if (!enS[a.getU()] && !enS[a.getV()]) {
                return a;
            }
        }
        return null;
    }

    public void presentarDecision(ResultadoSolucion res, int k) {
        if (res == null) {
            return;
        }
        System.out.println("Decisión con k = " + k + ": " + (res.isTieneSolucion() ? "SÍ" : "NO"));
        if (res.isTieneSolucion()) {
            System.out.println("Testigo S = " + res.getCertificado() + " (|S| = " + res.getCertificado().size() + ")");
        }
        System.out.println("Nodos explorados: " + nodosExplorados + " | Tiempo: " + res.getTiempoNano() / 1_000_000.0 + " ms");
    }

    public void presentarCoberturaMinima(ResultadoSolucion res) {
        if (res == null) {
            return;
        }
        System.out.println("Cobertura mínima k* = " + res.getCertificado().size());
        System.out.println("Testigo S* = " + res.getCertificado());
        System.out.println("Nodos explorados: " + nodosExplorados + " | Tiempo: " + res.getTiempoNano() / 1_000_000.0 + " ms");
    }

    public long getNodosExplorados() {
        return nodosExplorados;
    }
}
