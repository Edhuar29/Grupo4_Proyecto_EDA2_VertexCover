package ec.edu.epn.eda2.melany;

import ec.edu.epn.eda2.karina.AlgoritmoExactoVertexCover;
import ec.edu.epn.eda2.model.Arista;
import ec.edu.epn.eda2.model.Grafo;

/**
 * ============================================================================
 * CLASE DE: MELANY (Persona 3)
 * ROL ASIGNADO: Modificación obligatoria del grafo (+1 enlace de red)
 * ============================================================================
 *
 * INSTRUCCIONES PARA MELANY:
 * Debes programar el método que agrega una nueva conexión física a una red y
 * evalúa matemáticamente cómo varía la cobertura mínima (Delta k*).
 */
public class ModificadorInstanciaRed {

    /**
     * Aplica la modificación obligatoria:
     * 1. Encuentra dos nodos u, v no conectados.
     * 2. Clona la red original y le agrega la arista (u, v).
     * 3. Compara k* antes y después usando el método de Karina.
     *
     * @param redOriginal La red antes de la modificación
     * @param solverKarina Instancia del algoritmo exacto de Karina para calcular k*
     */
    public void aplicarCambioYComparar(Grafo redOriginal, AlgoritmoExactoVertexCover solverKarina) {
        /*
         * TODO MELANY: Programar la modificación y la comparación de coberturas.
         *
         * PASOS EN CÓDIGO:
         * 1. Buscar un par de nodos (u, v) que no tengan arista directa:
         *    int uElegido = -1, vElegido = -1;
         *    for (int i = 0; i < redOriginal.getNumVertices(); i++) {
         *        for (int j = i + 1; j < redOriginal.getNumVertices(); j++) {
         *            if (!existeArista(redOriginal, i, j)) {
         *                uElegido = i; vElegido = j; break;
         *            }
         *        }
         *    }
         * 2. Clonar la red y agregar la nueva arista:
         *    Grafo redModificada = clonarGrafo(redOriginal);
         *    redModificada.agregarArista(uElegido, vElegido);
         * 3. Calcular coberturas mínimas óptimas:
         *    ec.edu.epn.eda2.model.ResultadoSolucion resOrig = solverKarina.encontrarCoberturaMinima(redOriginal);
         *    ec.edu.epn.eda2.model.ResultadoSolucion resMod = solverKarina.encontrarCoberturaMinima(redModificada);
         * 4. Calcular la variación:
         *    int kOrig = resOrig.getCertificado().size();
         *    int kMod = resMod.getCertificado().size();
         *    int deltaK = kMod - kOrig;
         * 5. Imprimir la comparativa por consola:
         *    System.out.println("Enlace agregado: (" + uElegido + " <-> " + vElegido + ")");
         *    System.out.println("k* Original: " + kOrig + " | k* Modificado: " + kMod + " | Delta: " + deltaK);
         */

        System.out.println("-> [MÓDULO DE MELANY]: aplicarCambioYComparar() aún no programado por Melany.");
    }

    /**
     * Método auxiliar para clonar un grafo.
     */
    public Grafo clonarGrafo(Grafo original) {
        Grafo copia = new Grafo(original.getNumVertices());
        for (Arista a : original.getAristas()) {
            copia.agregarArista(a.getU(), a.getV());
        }
        return copia;
    }

    /**
     * Método auxiliar para comprobar si existe una arista (u, v) en el grafo.
     */
    public boolean existeArista(Grafo grafo, int u, int v) {
        for (Arista a : grafo.getAristas()) {
            if ((a.getU() == u && a.getV() == v) || (a.getU() == v && a.getV() == u)) {
                return true;
            }
        }
        return false;
    }
}
