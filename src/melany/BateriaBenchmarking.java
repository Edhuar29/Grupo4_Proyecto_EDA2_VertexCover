package melany;

import karina.AlgoritmoExactoVertexCover;
import model.Grafo;
import model.ResultadoSolucion;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BateriaBenchmarking {

    public static class ResultadoBenchmark {
        private final int numeroPrueba;
        private final int numVertices;
        private final int numAristas;
        private final int kOptimo;
        private final double tiempoMedianoMs;
        private final double memoriaEstimadaKB;
        private final List<Integer> certificado;

        public ResultadoBenchmark(int numeroPrueba, int numVertices, int numAristas, int kOptimo,
                                  double tiempoMedianoMs, double memoriaEstimadaKB, List<Integer> certificado) {
            this.numeroPrueba = numeroPrueba;
            this.numVertices = numVertices;
            this.numAristas = numAristas;
            this.kOptimo = kOptimo;
            this.tiempoMedianoMs = tiempoMedianoMs;
            this.memoriaEstimadaKB = memoriaEstimadaKB;
            this.certificado = certificado;
        }

        public int getNumeroPrueba() { return numeroPrueba; }
        public int getNumVertices() { return numVertices; }
        public int getNumAristas() { return numAristas; }
        public int getKOptimo() { return kOptimo; }
        public double getTiempoMedianoMs() { return tiempoMedianoMs; }
        public double getMemoriaEstimadaKB() { return memoriaEstimadaKB; }
        public List<Integer> getCertificado() { return certificado; }
    }

    private final List<ResultadoBenchmark> ultimosResultados = new ArrayList<>();

    public Grafo generarRedReproducible(int numVertices, double probabilidadArista, long semilla) {
        Grafo g = new Grafo(numVertices);
        Random rng = new Random(semilla);

        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (rng.nextDouble() < probabilidadArista) {
                    g.agregarArista(i, j);
                }
            }
        }

        if (g.getAristas().isEmpty() && numVertices > 1) {
            g.agregarArista(0, 1);
        }
        return g;
    }

    public List<ResultadoBenchmark> ejecutarBateriaCompleta(AlgoritmoExactoVertexCover solverKarina, int repeticiones) {
        ultimosResultados.clear();

        System.out.println("==========================================================================");
        System.out.println("   BATERÍA EXPERIMENTAL DE BENCHMARKING (System.nanoTime)                 ");
        System.out.println("==========================================================================");
        System.out.println("-> Iniciando fase de precalentamiento JVM (JIT Warmup)...");

        for (int w = 0; w < 50; w++) {
            Grafo warm = generarRedReproducible(6, 0.4, 9999L + w);
            solverKarina.encontrarCoberturaMinima(warm);
        }
        System.out.println("-> Precalentamiento finalizado. Ejecutando 5 tamaños con " + repeticiones + " repeticiones...");

        int[] tamanos = {4, 6, 8, 10, 12};
        double[] densidades = {0.50, 0.40, 0.35, 0.30, 0.30};
        long semillaBase = 42000L;

        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-16s | %-10s | %-18s | %-20s | %-18s%n",
                "N°", "Tamaño (|V|, |E|)", "k* Óptimo", "Tiempo Mediano (ms)", "Memoria Est. (KB)", "Certificado S*");
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        for (int idx = 0; idx < tamanos.length; idx++) {
            int n = tamanos[idx];
            double p = densidades[idx];
            Grafo red = generarRedReproducible(n, p, semillaBase + n);

            List<Long> tiemposNano = new ArrayList<>();
            ResultadoSolucion solucionFinal = null;

            Runtime runtime = Runtime.getRuntime();
            System.gc();
            long memAntes = runtime.totalMemory() - runtime.freeMemory();

            for (int r = 0; r < repeticiones; r++) {
                long tInicio = System.nanoTime();
                solucionFinal = solverKarina.encontrarCoberturaMinima(red);
                long tFin = System.nanoTime();
                tiemposNano.add(tFin - tInicio);
            }

            long memDespues = runtime.totalMemory() - runtime.freeMemory();
            double memKB = Math.max(0.5, (memDespues - memAntes) / 1024.0);

            Collections.sort(tiemposNano);
            long medianaNano = tiemposNano.get(tiemposNano.size() / 2);
            double medianaMs = medianaNano / 1_000_000.0;

            int kOptimo = solucionFinal != null ? solucionFinal.getCertificado().size() : 0;
            List<Integer> certificado = solucionFinal != null ? solucionFinal.getCertificado() : new ArrayList<>();

            ResultadoBenchmark rb = new ResultadoBenchmark(idx + 1, n, red.getNumAristas(),
                    kOptimo, medianaMs, memKB, certificado);
            ultimosResultados.add(rb);

            System.out.printf("%-4d | V=%-2d, E=%-2d        | k*=%-6d | %-18.4f | %-20.2f | %-18s%n",
                    rb.getNumeroPrueba(), rb.getNumVertices(), rb.getNumAristas(),
                    rb.getKOptimo(), rb.getTiempoMedianoMs(), rb.getMemoriaEstimadaKB(), rb.getCertificado().toString());
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------");
        exportarCSV("bateria_benchmarking.csv");
        System.out.println("==========================================================================\n");

        return new ArrayList<>(ultimosResultados);
    }

    public void exportarCSV(String rutaArchivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            pw.println("Prueba,Vertices,Aristas,kOptimo,TiempoMedianoMs,MemoriaKB,Certificado");
            for (ResultadoBenchmark rb : ultimosResultados) {
                pw.printf("%d,%d,%d,%d,%.4f,%.2f,\"%s\"%n",
                        rb.getNumeroPrueba(), rb.getNumVertices(), rb.getNumAristas(),
                        rb.getKOptimo(), rb.getTiempoMedianoMs(), rb.getMemoriaEstimadaKB(),
                        rb.getCertificado().toString());
            }
            System.out.println(">> [EXPORTACIÓN CSV]: Archivo generado exitosamente en '" + rutaArchivo + "'.");
        } catch (Exception e) {
            System.err.println("Error al exportar CSV: " + e.getMessage());
        }
    }

    public List<ResultadoBenchmark> getUltimosResultados() {
        return new ArrayList<>(ultimosResultados);
    }
}
