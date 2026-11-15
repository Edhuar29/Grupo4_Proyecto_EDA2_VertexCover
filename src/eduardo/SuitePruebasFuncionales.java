package eduardo;

import karina.AlgoritmoExactoVertexCover;

/**
 * ============================================================================
 * CLASE DE: EDUARDO (Persona 2)
 * ROL ASIGNADO: Suite de 5 pruebas funcionales automáticas y casos límite
 * ============================================================================
 *
 * INSTRUCCIONES PARA EDUARDO:
 * Debes programar y ejecutar los 5 casos de prueba obligatorios requeridos por la guía,
 * comprobando que el algoritmo de Karina y tu Verificador den la respuesta esperada.
 */
public class SuitePruebasFuncionales {

    /**
     * Ejecuta todas las pruebas funcionales en serie e imprime el reporte.
     */
    public void ejecutarPruebas() {
        System.out.println("==========================================================================");
        System.out.println("   SUITE DE PRUEBAS FUNCIONALES Y CASOS LÍMITE (EDUARDO)                  ");
        System.out.println("==========================================================================");

        AlgoritmoExactoVertexCover solverKarina = new AlgoritmoExactoVertexCover();
        VerificadorIndependiente verificador = new VerificadorIndependiente();

        prueba1GrafoSinAristas(solverKarina, verificador);
        prueba2GrafoEstrella(solverKarina, verificador);
        prueba3GrafoCicloImpar(solverKarina, verificador);
        prueba4GrafoCompleto(solverKarina, verificador);
        prueba5CertificadoInvalido(verificador);
    }

    /**
     * Caso Límite 1: Grafo sin aristas (nodos aislados).
     * Configuración: |V| = 4, |E| = 0, k = 0.
     * Resultado esperado: SÍ, certificado vacío S = [].
     */
    public void prueba1GrafoSinAristas(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        /*
         * TODO EDUARDO:
         * model.Grafo g = new model.Grafo(4); // sin aristas
         * model.ResultadoSolucion res = solver.resolverDecision(g, 0);
         * Validar si res.isTieneSolucion() == true y res.getCertificado().isEmpty().
         */
        System.out.println("-> Prueba 1 (Grafo sin aristas): pendiente de codificar por Eduardo.");
    }

    /**
     * Caso 2: Grafo estrella K_{1,4}.
     * Configuración: 5 nodos, centro en 0 conectado a 1, 2, 3, 4. Presupuesto k = 1.
     * Resultado esperado: SÍ, certificado S = [0].
     */
    public void prueba2GrafoEstrella(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        /*
         * TODO EDUARDO:
         * model.Grafo g = new model.Grafo(5);
         * g.agregarArista(0, 1); g.agregarArista(0, 2); g.agregarArista(0, 3); g.agregarArista(0, 4);
         * model.ResultadoSolucion res = solver.resolverDecision(g, 1);
         * Validar si res.isTieneSolucion() == true y contiene el nodo central 0.
         */
        System.out.println("-> Prueba 2 (Grafo estrella): pendiente de codificar por Eduardo.");
    }

    /**
     * Caso 3: Grafo ciclo impar C_5.
     * Configuración: 5 nodos en ciclo (0-1-2-3-4-0).
     * Resultado esperado: k = 2 debe dar NO; k = 3 debe dar SÍ.
     */
    public void prueba3GrafoCicloImpar(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        /*
         * TODO EDUARDO:
         * model.Grafo g = new model.Grafo(5);
         * g.agregarArista(0, 1); g.agregarArista(1, 2); g.agregarArista(2, 3);
         * g.agregarArista(3, 4); g.agregarArista(4, 0);
         * Probar con k = 2 (debe dar false).
         * Probar con k = 3 (debe dar true).
         */
        System.out.println("-> Prueba 3 (Ciclo impar C5): pendiente de codificar por Eduardo.");
    }

    /**
     * Caso Límite 4: Grafo completo K_4.
     * Configuración: 4 nodos todos conectados con todos (6 aristas).
     * Resultado esperado: Requiere n - 1 = 3 sensores. Con k = 2 da NO; con k = 3 da SÍ.
     */
    public void prueba4GrafoCompleto(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        /*
         * TODO EDUARDO:
         * model.Grafo g = new model.Grafo(4);
         * Conectar todos los pares (0,1), (0,2), (0,3), (1,2), (1,3), (2,3).
         * Con k = 2 debe dar false; con k = 3 debe dar true.
         */
        System.out.println("-> Prueba 4 (Grafo completo K4): pendiente de codificar por Eduardo.");
    }

    /**
     * Caso 5: Detección y rechazo de certificado inválido / fraudulento.
     * Resultado esperado: El verificador debe retornar false e identificar aristas libres.
     */
    public void prueba5CertificadoInvalido(VerificadorIndependiente verif) {
        /*
         * TODO EDUARDO:
         * model.Grafo g = new model.Grafo(3);
         * g.agregarArista(0, 1); g.agregarArista(1, 2);
         * java.util.List<Integer> certificadoMalo = new java.util.ArrayList<>();
         * certificadoMalo.add(0); // Falta el nodo 1 o 2 para cubrir la arista (1, 2)
         * boolean valido = verif.verificarCertificado(g, 1, certificadoMalo);
         * Comprobar que 'valido' sea false.
         */
        System.out.println("-> Prueba 5 (Certificado inválido): pendiente de codificar por Eduardo.");
    }
}
