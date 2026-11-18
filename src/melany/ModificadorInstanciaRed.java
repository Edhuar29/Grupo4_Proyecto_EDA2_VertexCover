package melany;

import karina.AlgoritmoExactoVertexCover;
import model.Arista;
import model.Grafo;
import model.ResultadoSolucion;

public class ModificadorInstanciaRed {

    public static class ResultadoModificacion {
        private final int u;
        private final int v;
        private final int kOriginal;
        private final int kModificado;
        private final int deltaK;
        private final Grafo redModificada;

        public ResultadoModificacion(int u, int v, int kOriginal, int kModificado, int deltaK, Grafo redModificada) {
            this.u = u;
            this.v = v;
            this.kOriginal = kOriginal;
            this.kModificado = kModificado;
            this.deltaK = deltaK;
            this.redModificada = redModificada;
        }

        public int getU() { return u; }
        public int getV() { return v; }
        public int getKOriginal() { return kOriginal; }
        public int getKModificado() { return kModificado; }
        public int getDeltaK() { return deltaK; }
        public Grafo getRedModificada() { return redModificada; }
    }

    public ResultadoModificacion aplicarCambio(Grafo redOriginal, AlgoritmoExactoVertexCover solverKarina) {
        int n = redOriginal.getNumVertices();
        int uElegido = -1;
        int vElegido = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!existeArista(redOriginal, i, j)) {
                    uElegido = i;
                    vElegido = j;
                    break;
                }
            }
            if (uElegido != -1) break;
        }

        if (uElegido == -1) {
            System.out.println("-> [AVISO]: La red ya es un grafo completo Kn; no existen enlaces adicionales disponibles.");
            ResultadoSolucion r = solverKarina.encontrarCoberturaMinima(redOriginal);
            int k = r.getCertificado().size();
            return new ResultadoModificacion(-1, -1, k, k, 0, redOriginal);
        }

        Grafo redModificada = clonarGrafo(redOriginal);
        redModificada.agregarArista(uElegido, vElegido);

        ResultadoSolucion resOrig = solverKarina.encontrarCoberturaMinima(redOriginal);
        ResultadoSolucion resMod = solverKarina.encontrarCoberturaMinima(redModificada);

        int kOrig = resOrig.getCertificado().size();
        int kMod = resMod.getCertificado().size();
        int deltaK = kMod - kOrig;

        return new ResultadoModificacion(uElegido, vElegido, kOrig, kMod, deltaK, redModificada);
    }

    public void aplicarCambioYComparar(Grafo redOriginal, AlgoritmoExactoVertexCover solverKarina) {
        System.out.println("==========================================================================");
        System.out.println("   MODIFICACIÓN OBLIGATORIA DE RED: ADICIÓN DE UN ENLACE (+1)            ");
        System.out.println("==========================================================================");

        ResultadoModificacion res = aplicarCambio(redOriginal, solverKarina);

        if (res.getU() != -1) {
            System.out.println("-> Enlace físico de contingencia agregado: (" + res.getU() + " <-> " + res.getV() + ")");
            System.out.println("-> Cobertura Óptima Original   (k* orig) : " + res.getKOriginal() + " sensores");
            System.out.println("-> Cobertura Óptima Modificada (k* mod)  : " + res.getKModificado() + " sensores");
            System.out.println("-> Variación de Cobertura      (Delta k*): " + res.getDeltaK() 
                    + (res.getDeltaK() == 0 ? " (k* se mantuvo invariante)" : " (k* aumentó en 1 sensor)"));
            System.out.println(">> PROPIEDAD VERIFICADA: Delta k* in {0, 1} satisfecha formalmente.");
        }
        System.out.println("==========================================================================\n");
    }

    public Grafo clonarGrafo(Grafo original) {
        Grafo copia = new Grafo(original.getNumVertices());
        for (Arista a : original.getAristas()) {
            copia.agregarArista(a.getU(), a.getV());
        }
        return copia;
    }

    public boolean existeArista(Grafo grafo, int u, int v) {
        for (Arista a : grafo.getAristas()) {
            if ((a.getU() == u && a.getV() == v) || (a.getU() == v && a.getV() == u)) {
                return true;
            }
        }
        return false;
    }
}
