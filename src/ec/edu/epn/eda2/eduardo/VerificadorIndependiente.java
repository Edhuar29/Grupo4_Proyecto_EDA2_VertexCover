package ec.edu.epn.eda2.eduardo;

import ec.edu.epn.eda2.model.Grafo;
import java.util.List;

/**
 * ============================================================================
 * CLASE DE: EDUARDO (Persona 2)
 * ROL ASIGNADO: Verificador independiente en tiempo polinomial O(|V| + |E|)
 * ============================================================================
 *
 * INSTRUCCIONES PARA EDUARDO:
 * Debes programar una rutina de verificación que tome un certificado propuesto
 * (la lista de nodos que supuestamente cubren la red) y compruebe en tiempo lineal
 * O(|V| + |E|) si la solución es correcta, sin hacer ninguna búsqueda ni recursión.
 */
public class VerificadorIndependiente {

    /**
     * Verifica si el conjunto de sensores propuesto cubre todos los enlaces
     * de la red sin exceder el presupuesto 'k'.
     *
     * @param grafo La red con sus aristas
     * @param k El presupuesto máximo permitido
     * @param certificado Lista de nodos seleccionados como sensores
     * @return true si el certificado es VÁLIDO; false si es INVÁLIDO.
     */
    public boolean verificarCertificado(Grafo grafo, int k, List<Integer> certificado) {
        /*
         * TODO EDUARDO: Programar la validación en tiempo lineal O(|V| + |E|).
         *
         * PASOS EN CÓDIGO:
         * 1. Validar que 'grafo' y 'certificado' no sean null.
         * 2. Comprobar cardinalidad:
         *    if (certificado.size() > k) {
         *        System.out.println("INVÁLIDO: El certificado excede el presupuesto k=" + k);
         *        return false;
         *    }
         * 3. Crear arreglo booleano para consultas en O(1):
         *    boolean[] tieneSensor = new boolean[grafo.getNumVertices()];
         *    for (int nodo : certificado) {
         *        if (nodo < 0 || nodo >= grafo.getNumVertices()) return false;
         *        tieneSensor[nodo] = true;
         *    }
         * 4. Recorrer cada Arista (u, v) en grafo.getAristas():
         *    for (ec.edu.epn.eda2.model.Arista a : grafo.getAristas()) {
         *        if (!tieneSensor[a.getU()] && !tieneSensor[a.getV()]) {
         *            System.out.println("INVÁLIDO: La arista " + a + " no tiene sensor en ningún extremo.");
         *            return false;
         *        }
         *    }
         * 5. Si todas las aristas pasaron la prueba:
         *    System.out.println("VÁLIDO: Todos los enlaces están debidamente supervisados.");
         *    return true;
         */

        System.out.println("-> [MÓDULO DE EDUARDO]: verificarCertificado() aún no programado por Eduardo.");
        return false;
    }
}
