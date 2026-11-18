package karina;

import model.Arista;
import model.Grafo;
import model.ResultadoSolucion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Algoritmo exacto de Vertex Cover por ramificación binaria sobre aristas no cubiertas.
 * Entrada: grafo no dirigido G = (V, E) y presupuesto k. Salida: decisión y testigo S.
 */
public class AlgoritmoExactoVertexCover {

    private long nodosExplorados;
    private long nodosAcumulados;

    /**
     * Decide si existe S contenido en V con |S| <= k que cubra todas las aristas.
     *
     * @param grafo red con sus vértices y enlaces
     * @param k presupuesto máximo de sensores
     * @return decisión, testigo S ordenado ascendente y tiempo medido en nanosegundos
     */
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
        nodosAcumulados = nodosExplorados;
        return new ResultadoSolucion(exito, certificado, tFin - tInicio);
    }

    /**
     * Encuentra la cobertura mínima k* probando k = 0, 1, 2, ... hasta el primer SÍ.
     * Como k - 1 respondió NO, el testigo devuelto tiene exactamente k* nodos.
     *
     * @param grafo red de comunicaciones
     * @return resultado con el testigo mínimo y el tiempo total acumulado
     */
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
                nodosAcumulados = total;
                res.setTiempoNano(System.nanoTime() - tInicio);
                return res;
            }
        }
        nodosAcumulados = total;
        return new ResultadoSolucion(false, new ArrayList<>(), System.nanoTime() - tInicio);
    }

    /**
     * Ramificación exacta: elige una arista (u, v) sin cubrir y prueba u en S, luego v en S.
     *
     * @param grafo red de trabajo
     * @param kRestante presupuesto restante en la rama actual
     * @param solucionActual nodos elegidos hasta el momento
     * @param solucionFinal lista donde se copia el testigo cuando se encuentra
     * @return true si la rama encontró una cobertura válida
     */
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

    /**
     * Presenta la decisión y el testigo S en consola. Se invoca fuera del tramo medido.
     *
     * @param res resultado devuelto por resolverDecision
     * @param k presupuesto usado en la consulta
     */
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

    /**
     * Presenta la cobertura mínima k* y su testigo S* en consola. Se invoca fuera del tramo medido.
     *
     * @param res resultado devuelto por encontrarCoberturaMinima
     */
    public void presentarCoberturaMinima(ResultadoSolucion res) {
        if (res == null) {
            return;
        }
        System.out.println("Cobertura mínima k* = " + res.getCertificado().size());
        System.out.println("Testigo S* = " + res.getCertificado());
        System.out.println("Nodos explorados (suma de k = 0 hasta k*): " + nodosAcumulados + " | Tiempo: " + res.getTiempoNano() / 1_000_000.0 + " ms");
    }

    public long getNodosExplorados() {
        return nodosExplorados;
    }

    public long getNodosAcumulados() {
        return nodosAcumulados;
    }
}
