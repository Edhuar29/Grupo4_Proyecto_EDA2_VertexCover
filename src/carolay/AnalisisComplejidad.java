package carolay;

package carolay;

/**
 * Cotas teóricas de tiempo T(n) y espacio S(n) del algoritmo exacto de Vertex Cover
 * (k = presupuesto, |V| = vértices, |E| = aristas).
 */
public class AnalisisComplejidad {

    // Supuesto de referencia: ~1 ns por operación elemental (solo orden de magnitud).
    private static final double NS_POR_OPERACION = 1.0;

    /** T(n) = O(2^k * |E|). Devuelve Long.MAX_VALUE si desborda. */
    public long calcularOperacionesTeoricasMaximas(int k, int numAristas) {
        if (k < 0 || numAristas < 0) {
            throw new IllegalArgumentException("k y numAristas deben ser >= 0");
        }
        if (k >= 62) {
            return Long.MAX_VALUE;
        }
        try {
            return Math.multiplyExact(1L << k, (long) numAristas);
        } catch (ArithmeticException e) {
            return Long.MAX_VALUE;
        }
    }

    /** Cota holgada O(k * |E|): sería la memoria si cada marco copiara las aristas. */
    public long calcularMemoriaTeoricaPila(int k, int numAristas) {
        if (k < 0 || numAristas < 0) {
            throw new IllegalArgumentException("k y numAristas deben ser >= 0");
        }
        return (long) k * numAristas;
    }

    /** Razón de costo entre k2 y k1 (con |E| fijo equivale a 2^(k2 - k1)). */
    public double calcularFactorCrecimiento(int k1, int k2, int numAristas) {
        long op1 = calcularOperacionesTeoricasMaximas(k1, numAristas);
        long op2 = calcularOperacionesTeoricasMaximas(k2, numAristas);
        return op1 > 0 ? (double) op2 / op1 : 0.0;
    }

    /** Máximo de llamadas recursivas: 2^(k+1) - 1. */
    public long calcularNodosMaximosArbol(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k debe ser >= 0");
        }
        return k >= 62 ? Long.MAX_VALUE : (1L << (k + 1)) - 1;
    }

    /** Memoria auxiliar real: arreglo enS (|V|) + lista actual (k) + pila (k + 1). */
    public long calcularMemoriaAuxiliarReal(int k, int numVertices) {
        if (k < 0 || numVertices < 0) {
            throw new IllegalArgumentException("k y numVertices deben ser >= 0");
        }
        return (long) numVertices + k + (k + 1);
    }

    /** Compara el tiempo medido con la cota teórica. */
    public void imprimirComparativaTeoricaVsEmpirica(long tiempoRealNs, int k, int numAristas) {
        long opTeoricas = calcularOperacionesTeoricasMaximas(k, numAristas);
        double peorCasoNs = opTeoricas * NS_POR_OPERACION;

        System.out.println("=== Comparativa teórica vs empírica ===");
        System.out.println("Parámetros: k = " + k + ", |E| = " + numAristas);
        System.out.println("Cota teórica O(2^k * |E|): " + opTeoricas + " operaciones máximas");
        System.out.printf("Peor caso estimado (a %.1f ns/op): %.0f ns%n", NS_POR_OPERACION, peorCasoNs);
        System.out.println("Tiempo real medido: " + tiempoRealNs + " ns ("
                + String.format("%.4f", tiempoRealNs / 1_000_000.0) + " ms)");

        if (opTeoricas == 0) {
            System.out.println("Conclusión: no hay aristas; no hay árbol que explorar.");
        } else if (tiempoRealNs < peorCasoNs) {
            System.out.println("Conclusión: el tiempo real quedó por debajo del peor caso; "
                    + "la poda descartó ramas.");
        } else {
            System.out.println("Conclusión: el tiempo real superó la estimación. En instancias pequeñas "
                    + "domina la sobrecarga fija (reloj, JVM, recolector), así que esto NO prueba el peor "
                    + "caso; compare los nodos explorados con 2^(k+1) - 1.");
        }
        System.out.println("Nota: la cota es asintótica; la comparación es de orden de magnitud.");
    }

    /** Informe completo para la opción 10 del menú. */
    public void mostrarAnalisisTeorico(int k, int numVertices, int numAristas,
                                       long tiempoRealNs, long nodosExplorados) {
        long nodosMax = calcularNodosMaximosArbol(k);

        System.out.println("=== Análisis teórico (k = " + k + ", |V| = " + numVertices
                + ", |E| = " + numAristas + ") ===");
        System.out.println("T(n) = O(2^k * |E|): " + calcularOperacionesTeoricasMaximas(k, numAristas)
                + " operaciones máximas");
        System.out.println("Nodos máximos del árbol (2^(k+1) - 1): " + nodosMax);
        System.out.println("Nodos explorados realmente: " + nodosExplorados);
        if (nodosMax != Long.MAX_VALUE) {
            System.out.printf("Fracción del árbol explorada: %.1f %%%n", 100.0 * nodosExplorados / nodosMax);
        }
        System.out.println("S(n) real, O(|V| + k): " + calcularMemoriaAuxiliarReal(k, numVertices) + " celdas");
        System.out.println("Cota holgada O(k * |E|): " + calcularMemoriaTeoricaPila(k, numAristas));
        System.out.println("Búsqueda: exponencial en k. Verificación (Eduardo): lineal, O(|V| + |E|).");
        imprimirComparativaTeoricaVsEmpirica(tiempoRealNs, k, numAristas);
    }
}
