package eduardo;

import model.Arista;
import model.Grafo;
import java.util.List;

public class VerificadorIndependiente {

    public boolean verificarCertificado(Grafo grafo, int k, List<Integer> certificado) {
        return verificarCertificado(grafo, k, certificado, true);
    }

    public boolean verificarCertificado(Grafo grafo, int k, List<Integer> certificado, boolean verbose) {
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

        if (tamCertificado > k) {
            if (verbose) {
                System.out.println("[RECHAZADO] Fallo de Presupuesto: |S| = " + tamCertificado 
                        + " excede el presupuesto permitido k = " + k + ".");
            }
            return false;
        }

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

        if (verbose) {
            System.out.println("[APROBADO] Certificado VÁLIDO: Cobertura de " + tamCertificado 
                    + " sensor(es) supervisa los " + grafo.getNumAristas() 
                    + " enlaces de la red (Presupuesto k=" + k + ").");
        }
        return true;
    }
}
