package eduardo;

import karina.AlgoritmoExactoVertexCover;
import model.Grafo;
import model.ResultadoSolucion;

import java.util.ArrayList;
import java.util.List;

public class SuitePruebasFuncionales {

    public void ejecutarPruebas() {
        System.out.println("==========================================================================");
        System.out.println("   SUITE DE PRUEBAS FUNCIONALES Y CASOS LÍMITE (EDUARDO)                  ");
        System.out.println("==========================================================================");

        AlgoritmoExactoVertexCover solverKarina = new AlgoritmoExactoVertexCover();
        VerificadorIndependiente verificador = new VerificadorIndependiente();

        int pruebasPasadas = 0;
        int totalPruebas = 5;

        if (prueba1GrafoSinAristas(solverKarina, verificador)) pruebasPasadas++;
        if (prueba2GrafoEstrella(solverKarina, verificador)) pruebasPasadas++;
        if (prueba3GrafoCicloImpar(solverKarina, verificador)) pruebasPasadas++;
        if (prueba4GrafoCompleto(solverKarina, verificador)) pruebasPasadas++;
        if (prueba5CertificadoInvalido(verificador)) pruebasPasadas++;

        System.out.println("--------------------------------------------------------------------------");
        System.out.println(">> REPORTE FINAL: " + pruebasPasadas + "/" + totalPruebas + " pruebas aprobadas ("
                + (pruebasPasadas * 100 / totalPruebas) + "% de éxito).");
        if (pruebasPasadas == totalPruebas) {
            System.out.println(">> ESTADO: Todas las pruebas funcionales y de auditoría pasaron con éxito.");
        } else {
            System.out.println(">> ESTADO: Se detectaron discrepancias en alguna prueba.");
        }
        System.out.println("==========================================================================\n");
    }

    public boolean prueba1GrafoSinAristas(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        System.out.println("\n[PRUEBA 1]: Caso Límite - Grafo sin aristas (|V|=4, |E|=0, k=0)");
        Grafo g = new Grafo(4);
        ResultadoSolucion res = solver.resolverDecision(g, 0);

        boolean okAlgoritmo = res.isTieneSolucion() && res.getCertificado().isEmpty();
        boolean okVerificador = verif.verificarCertificado(g, 0, res.getCertificado(), false);

        if (okAlgoritmo && okVerificador) {
            System.out.println("  -> [PASÓ]: Decisión SÍ, Testigo S=[], Verificador confirmó validez.");
            return true;
        } else {
            System.out.println("  -> [FALLÓ]: Algoritmo o Verificador no respondieron lo esperado.");
            return false;
        }
    }

    public boolean prueba2GrafoEstrella(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        System.out.println("\n[PRUEBA 2]: Topología Estrella K_{1,4} (|V|=5, |E|=4, hub=0, k=1)");
        Grafo g = new Grafo(5);
        g.agregarArista(0, 1);
        g.agregarArista(0, 2);
        g.agregarArista(0, 3);
        g.agregarArista(0, 4);

        ResultadoSolucion res = solver.resolverDecision(g, 1);
        boolean okAlgoritmo = res.isTieneSolucion() && res.getCertificado().size() == 1 
                && res.getCertificado().contains(0);
        boolean okVerificador = verif.verificarCertificado(g, 1, res.getCertificado(), false);

        if (okAlgoritmo && okVerificador) {
            System.out.println("  -> [PASÓ]: Decisión SÍ, Testigo S=[0], Verificador confirmó cobertura total.");
            return true;
        } else {
            System.out.println("  -> [FALLÓ]: La topología estrella no fue resuelta o verificada adecuadamente.");
            return false;
        }
    }

    public boolean prueba3GrafoCicloImpar(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        System.out.println("\n[PRUEBA 3]: Ciclo Impar C_5 (|V|=5, |E|=5, k=2 da NO, k=3 da SÍ)");
        Grafo g = new Grafo(5);
        g.agregarArista(0, 1);
        g.agregarArista(1, 2);
        g.agregarArista(2, 3);
        g.agregarArista(3, 4);
        g.agregarArista(4, 0);

        ResultadoSolucion resK2 = solver.resolverDecision(g, 2);
        ResultadoSolucion resK3 = solver.resolverDecision(g, 3);

        boolean okK2 = !resK2.isTieneSolucion();
        boolean okK3 = resK3.isTieneSolucion() && resK3.getCertificado().size() <= 3;
        boolean okVerificador = verif.verificarCertificado(g, 3, resK3.getCertificado(), false);

        if (okK2 && okK3 && okVerificador) {
            System.out.println("  -> [PASÓ]: k=2 respondió NO; k=3 respondió SÍ con testigo " 
                    + resK3.getCertificado() + ", validado por verificador.");
            return true;
        } else {
            System.out.println("  -> [FALLÓ]: El ciclo impar C_5 no discriminó adecuadamente k=2 vs k=3.");
            return false;
        }
    }

    public boolean prueba4GrafoCompleto(AlgoritmoExactoVertexCover solver, VerificadorIndependiente verif) {
        System.out.println("\n[PRUEBA 4]: Caso Límite - Grafo Completo K_4 (|V|=4, |E|=6, k=2 da NO, k=3 da SÍ)");
        Grafo g = new Grafo(4);
        g.agregarArista(0, 1);
        g.agregarArista(0, 2);
        g.agregarArista(0, 3);
        g.agregarArista(1, 2);
        g.agregarArista(1, 3);
        g.agregarArista(2, 3);

        ResultadoSolucion resK2 = solver.resolverDecision(g, 2);
        ResultadoSolucion resK3 = solver.resolverDecision(g, 3);

        boolean okK2 = !resK2.isTieneSolucion();
        boolean okK3 = resK3.isTieneSolucion() && resK3.getCertificado().size() == 3;
        boolean okVerificador = verif.verificarCertificado(g, 3, resK3.getCertificado(), false);

        if (okK2 && okK3 && okVerificador) {
            System.out.println("  -> [PASÓ]: k=2 respondió NO; k=3 respondió SÍ con n-1=3 nodos (" 
                    + resK3.getCertificado() + ").");
            return true;
        } else {
            System.out.println("  -> [FALLÓ]: El grafo completo K_4 no requirió exactamente n-1 sensores.");
            return false;
        }
    }

    public boolean prueba5CertificadoInvalido(VerificadorIndependiente verif) {
        System.out.println("\n[PRUEBA 5]: Control Antifraude - Detección de Certificado Incompleto");
        Grafo g = new Grafo(3);
        g.agregarArista(0, 1);
        g.agregarArista(1, 2);

        List<Integer> certificadoIncompleto = new ArrayList<>();
        certificadoIncompleto.add(0);

        boolean rechazadoIncompleto = !verif.verificarCertificado(g, 1, certificadoIncompleto, false);

        List<Integer> certificadoExcedido = new ArrayList<>();
        certificadoExcedido.add(0);
        certificadoExcedido.add(1);
        certificadoExcedido.add(2);

        boolean rechazadoExcedido = !verif.verificarCertificado(g, 1, certificadoExcedido, false);

        if (rechazadoIncompleto && rechazadoExcedido) {
            System.out.println("  -> [PASÓ]: El verificador rechazó con éxito certificados incompletos y con sobrepresupuesto.");
            return true;
        } else {
            System.out.println("  -> [FALLÓ]: El verificador aceptó erróneamente un certificado fraudulento.");
            return false;
        }
    }
}
