package eduardo;

import model.Arista;
import model.Grafo;
import java.util.List;

/**
 * ============================================================================
 * CLASE DE: EDUARDO (Persona 2)
 * ROL ASIGNADO: Verificador independiente en tiempo polinomial O(|V| + |E|)
 * ============================================================================
 *
 * En teoría de complejidad computacional, el verificador actúa como un evaluador
 * imparcial (juez determinista de tiempo polinomial en la clase P).
 *
 * PROPIEDADES FUNDAMENTALES:
 * 1. Desacoplamiento total: No realiza ramificaciones, búsquedas ni recursión.
 * 2. Fase de Presupuesto: Valida que |S| <= k en tiempo O(1).
 * 3. Fase de Cobertura: Verifica que cada arista (u, v) tenga al menos un extremo en S en tiempo O(|E|).
 * 4. Complejidad asintótica: O(|V| + |E|), demostrando que certificar Vertex Cover está en P.
 */
public class VerificadorIndependiente {

    /**
     * Verifica si el conjunto de sensores propuesto (certificado S) cubre todos
     * los enlaces de la red sin exceder el presupuesto 'k', mostrando diagnósticos por consola.
     *
     * @param grafo La red con sus aristas (|V| nodos, |E| enlaces)
     * @param k El presupuesto máximo de sensores permitido
     * @param certificado Lista de nodos seleccionados como sensores (testigo S)
     * @return true si el certificado es VÁLIDO; false si es INVÁLIDO.
     */
    public boolean verificarCertificado(Grafo grafo, int k, List<Integer> certificado) {
        return verificarCertificado(grafo, k, certificado, true);
    }

    /**
     * Versión con opción de activar o silenciar mensajes de diagnóstico por consola.
     *
     * @param grafo La red evaluada
     * @param k Presupuesto máximo
     * @param certificado Conjunto de vértices propuesto como testigo S
     * @param verbose Si es true, imprime los detalles de la auditoría en consola
     * @return true si es válido, false en caso contrario
     */
    public boolean verificarCertificado(Grafo grafo, int k, List<Integer> certificado, boolean verbose) {
        // 1. Validación defensiva de entradas nulas
        if (grafo == null) {
            if (verbose) {
                System.out.println("[RECHAZADO] Error: El grafo proporcionado es nulo.");
            }
            return false;
        }

        if (certificado == null) {
            if (verbose) {
                System.out.println("[RECHAZADO] Error: El certificado es nulo.");
            }
            return false;
        }

        if (k < 0) {
            if (verbose) {
                System.out.println("[RECHAZADO] Presupuesto inválido: k = " + k + " no puede ser negativo.");
            }
            return false;
        }

        int n = grafo.getNumVertices();
        int tamCertificado = certificado.size();

        // 2. FASE 1: Validación de Cardinalidad y Presupuesto en O(1)
        if (tamCertificado > k) {
            if (verbose) {
                System.out.println("[RECHAZADO] Fallo de Presupuesto: |S| = " + tamCertificado 
                        + " excede el presupuesto permitido k = " + k + ".");
            }
            return false;
        }

        // 3. FASE 2: Validación de rango de vértices y preparación del arreglo booleano en O(|V|)
        boolean[] tieneSensor = new boolean[n];
        for (int nodo : certificado) {
            if (nodo < 0 || nodo >= n) {
                if (verbose) {
                    System.out.println("[RECHAZADO] Vértice inválido: El nodo " + nodo 
                            + " no pertenece al rango [0, " + (n - 1) + "].");
                }
                return false;
            }
            tieneSensor[nodo] = true;
        }

        // 4. FASE 3: Validación de Cobertura de Aristas en O(|E|)
        // Para cada arista (u, v), obligatoriamente u tiene sensor o v tiene sensor.
        for (Arista arista : grafo.getAristas()) {
            int u = arista.getU();
            int v = arista.getV();

            if (!tieneSensor[u] && !tieneSensor[v]) {
                if (verbose) {
                    System.out.println("[RECHAZADO] Cobertura Incompleta: El enlace " + arista 
                            + " carece de sensor en ambos extremos (punto ciego detectado).");
                }
                return false;
            }
        }

        // 5. Veredicto final positivo: cumple presupuesto y cubre todas las aristas
        if (verbose) {
            System.out.println("[APROBADO] Certificado VÁLIDO: Cobertura de " + tamCertificado 
                    + " sensor(es) supervisa los " + grafo.getNumAristas() 
                    + " enlaces de la red (Presupuesto k=" + k + ").");
        }
        return true;
    }
}
