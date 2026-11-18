import carolay.AnalisisComplejidad;
import eduardo.SuitePruebasFuncionales;
import eduardo.VerificadorIndependiente;
import karina.AlgoritmoExactoVertexCover;
import melany.BateriaBenchmarking;
import melany.ModificadorInstanciaRed;
import model.Grafo;
import model.ResultadoSolucion;

import java.util.Scanner;

/**
 * ============================================================================
 * CLASE PRINCIPAL: Main (Contiene el Menú por Consola)
 * ASIGNATURA: Estructura de Datos y Algoritmos II (Periodo 2026-B)
 * EQUIPO 4: Vertex Cover (Ubicación de Sensores sobre Enlaces de Red)
 *
 * ASIGNACIÓN DE ESTUDIANTES:
 * [1] KARINA  -> Algoritmo Exacto, Modelo Matemático y Testigo S
 * [2] EDUARDO -> Verificador Independiente y Suite de Pruebas Funcionales
 * [3] MELANY  -> Modificación Obligatoria, Benchmarking y Gráficas
 * [4] CAROLAY -> Correctitud, Complejidad Teórica T(n), S(n) y Consolidación
 * ============================================================================
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciación de los módulos de cada estudiante
        AlgoritmoExactoVertexCover solverKarina = new AlgoritmoExactoVertexCover();
        VerificadorIndependiente verificadorEduardo = new VerificadorIndependiente();
        SuitePruebasFuncionales pruebasEduardo = new SuitePruebasFuncionales();
        ModificadorInstanciaRed modificadorMelany = new ModificadorInstanciaRed();
        BateriaBenchmarking benchmarkMelany = new BateriaBenchmarking();
        AnalisisComplejidad analisisCarolay = new AnalisisComplejidad();

        // Grafo de trabajo en memoria para las pruebas por consola
        Grafo redActual = null;
        int kActual = 0;
        ResultadoSolucion ultimoResultado = null;

        boolean salir = false;

        while (!salir) {
            System.out.println("\n==========================================================================");
            System.out.println("   PROYECTO INTEGRADOR EDA II: VERTEX COVER (EQUIPO 4)                   ");
            System.out.println("   Integrantes: Karina (P1), Eduardo (P2), Melany (P3), Carolay (P4)      ");
            System.out.println("==========================================================================");
            System.out.println("1. Ingresar red manualmente por consola (|V|, |E| y enlaces)");
            System.out.println("2. Generar red aleatoria reproducible (Melany)");
            System.out.println("3. Cargar caso de prueba predeterminado en consola (|V|=4)");
            System.out.println("4. Resolver problema de decisión con Algoritmo Exacto (Karina)");
            System.out.println("5. Encontrar Cobertura Mínima Óptima k* (Karina)");
            System.out.println("6. Ejecutar Verificador Independiente sobre el certificado (Eduardo)");
            System.out.println("7. Aplicar Modificación Obligatoria: +1 enlace (Melany)");
            System.out.println("8. Ejecutar Batería de Benchmarking con medianas y CSV (Melany)");
            System.out.println("9. Ejecutar Suite de 5 Pruebas Funcionales automáticas (Eduardo)");
            System.out.println("10. Mostrar Análisis Teórico de Complejidad y Respuestas (Carolay)");
            System.out.println("0. Salir");
            System.out.println("--------------------------------------------------------------------------");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine().trim();
            System.out.println();

            switch (opcion) {
                case "1":
                    /*
                     * TODO: Aquí se leen los datos por consola:
                     * 1. Pedir número de nodos (V) y aristas (E).
                     * 2. Crear redActual = new Grafo(V).
                     * 3. Pedir cada enlace (u, v) y agregarlo con redActual.agregarArista(u, v).
                     * 4. Pedir el presupuesto kActual.
                     */
                    System.out.println("-> Opción 1 seleccionada: Ingreso manual de red por consola.");
                    break;

                case "2":
                    /*
                     * TODO: Llamar al método de Melany:
                     * redActual = benchmarkMelany.generarRedReproducible(nodos, probabilidad, semilla);
                     */
                    System.out.println("-> Opción 2 seleccionada: Generar red reproducible.");
                    redActual = benchmarkMelany.generarRedReproducible(6, 0.4, 12345L);
                    break;

                case "3":
                    // Caso de prueba rápido para pruebas en consola
                    redActual = new Grafo(4);
                    redActual.agregarArista(0, 1);
                    redActual.agregarArista(1, 2);
                    redActual.agregarArista(2, 3);
                    redActual.agregarArista(3, 0);
                    kActual = 2;
                    System.out.println("-> Caso de prueba cargado (|V|=4, |E|=4, k=2).");
                    break;

                case "4":
                    /*
                     * TODO: Llamar al algoritmo de Karina para decidir con kActual:
                     * ultimoResultado = solverKarina.resolverDecision(redActual, kActual);
                     */
                    System.out.println("-> Opción 4 seleccionada: Ejecutar algoritmo exacto de Karina.");
                    if (redActual != null) {
                        ultimoResultado = solverKarina.resolverDecision(redActual, kActual);
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red (Opciones 1, 2 o 3).");
                    }
                    break;

                case "5":
                    /*
                     * TODO: Llamar a Karina para encontrar k*:
                     * ultimoResultado = solverKarina.encontrarCoberturaMinima(redActual);
                     */
                    System.out.println("-> Opción 5 seleccionada: Buscar cobertura mínima óptima (Karina).");
                    if (redActual != null) {
                        ultimoResultado = solverKarina.encontrarCoberturaMinima(redActual);
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red.");
                    }
                    break;

                case "6":
                    /*
                     * TODO: Llamar al verificador de Eduardo para auditar el certificado:
                     * verificadorEduardo.verificarCertificado(redActual, kActual, certificado);
                     */
                    System.out.println("-> Opción 6 seleccionada: Ejecutar verificador independiente de Eduardo.");
                    if (redActual != null) {
                        verificadorEduardo.verificarCertificado(redActual, kActual,
                                ultimoResultado != null ? ultimoResultado.getCertificado() : null);
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red.");
                    }
                    break;

                case "7":
                    /*
                     * TODO: Llamar al modificador de Melany:
                     * modificadorMelany.aplicarCambioYComparar(redActual, solverKarina);
                     */
                    System.out.println("-> Opción 7 seleccionada: Aplicar cambio obligatorio (+1 enlace) (Melany).");
                    if (redActual != null) {
                        modificadorMelany.aplicarCambioYComparar(redActual, solverKarina);
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red.");
                    }
                    break;

                case "8":
                    /*
                     * TODO: Llamar a la batería experimental de Melany:
                     * benchmarkMelany.ejecutarBateriaCompleta(solverKarina, 5);
                     */
                    System.out.println("-> Opción 8 seleccionada: Ejecutar batería de benchmarking (Melany).");
                    benchmarkMelany.ejecutarBateriaCompleta(solverKarina, 3);
                    break;

                case "9":
                    /*
                     * TODO: Llamar a la suite de pruebas de Eduardo:
                     * pruebasEduardo.ejecutarPruebas();
                     */
                    System.out.println("-> Opción 9 seleccionada: Ejecutar suite de pruebas de Eduardo.");
                    pruebasEduardo.ejecutarPruebas();
                    break;

                case "10":
                    /*
                     * TODO: Ejecutar el cálculo matemático de cotas teóricas de Carolay:
                     */
                    int kAnalisis = kActual > 0 ? kActual : 2;
                    int aristas = redActual != null ? redActual.getNumAristas() : 4;
                    int vertices = redActual != null ? redActual.getNumVertices() : 4;
                    analisisCarolay.mostrarAnalisisTeorico(kAnalisis, vertices, aristas,
                        ultimoResultado != null ? ultimoResultado.getTiempoNano() : 0,
                        solverKarina.getNodosExplorados());
                break;

                case "0":
                    salir = true;
                    System.out.println("Saliendo del programa. ¡Éxitos en la defensa del proyecto!");
                    break;

                default:
                    System.out.println("Opción no válida. Ingrese un número del 0 al 10.");
                    break;
            }
        }

        scanner.close();
    }
}
