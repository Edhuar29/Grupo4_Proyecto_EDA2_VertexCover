import carolay.AnalisisComplejidad;
import eduardo.SuitePruebasFuncionales;
import eduardo.VerificadorIndependiente;
import karina.AlgoritmoExactoVertexCover;
import melany.BateriaBenchmarking;
import melany.ModificadorInstanciaRed;
import model.Arista;
import model.Grafo;
import model.ResultadoSolucion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ============================================================================
 * CLASE PRINCIPAL: Main (Contiene el Menú por Consola)
 * ASIGNATURA: Estructura de Datos y Algoritmos II (Periodo 2026-B)
 * EQUIPO 4: Vertex Cover (Ubicación de Sensores sobre Enlaces de Red)
 *
 * ASIGNACIÓN DE ESTUDIANTES:
 * [1] KARINA  -> Algoritmo Exacto, Modelo Matemático y Testigo S
 * [2] EDUARDO -> Verificador Independiente, Suite de Pruebas y Menú Interactivo
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
            limpiarPantalla();
            System.out.println("==========================================================================");
            System.out.println("   PROYECTO INTEGRADOR EDA II: VERTEX COVER (EQUIPO 4)                   ");
            System.out.println("   Integrantes: Karina (P1), Eduardo (P2), Melany (P3), Carolay (P4)      ");
            System.out.println("==========================================================================");
            System.out.println("1. Ingresar red manualmente por consola (|V|, |E| y enlaces) (Eduardo)");
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

            if (!opcion.equals("0")) {
                limpiarPantalla();
            }

            switch (opcion) {
                case "1":
                    System.out.println("==========================================================================");
                    System.out.println("   OPCIÓN 1: INGRESO MANUAL DE RED POR CONSOLA (EDUARDO)                  ");
                    System.out.println("==========================================================================");
                    try {
                        System.out.print("  • Número de routers / nodos (|V| >= 1): ");
                        int n = Integer.parseInt(scanner.nextLine().trim());
                        if (n <= 0) {
                            System.out.println("\n[ERROR] El número de nodos debe ser mayor a 0.");
                            System.out.println("==========================================================================\n");
                            break;
                        }
                        redActual = new Grafo(n);
                        long maxEnlaces = (long) n * (n - 1) / 2;
                        int m = 0;

                        if (n == 1) {
                            System.out.println("  -> Red de 1 router: 0 enlaces físicos asignados automáticamente.");
                            m = 0;
                        } else {
                            while (true) {
                                System.out.print("  • Número de enlaces físicos (|E|, máx. " + maxEnlaces + "): ");
                                String inputM = scanner.nextLine().trim();
                                try {
                                    m = Integer.parseInt(inputM);
                                    if (m < 0) {
                                        System.out.println("    [!] El número de enlaces no puede ser negativo. Intente nuevamente.");
                                        continue;
                                    }
                                    if (m > maxEnlaces) {
                                        System.out.println("    [!] Máximo " + maxEnlaces + " enlaces permitidos para " + n + " routers. Intente nuevamente.");
                                        continue;
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("    [!] Ingrese un número entero válido.");
                                }
                            }
                        }

                        if (m > 0) {
                            System.out.println("\n  --- Ingrese los enlaces físicos (ejemplo: 0 1) ---");
                        }
                        for (int i = 0; i < m; i++) {
                            System.out.print("    Enlace " + (i + 1) + " (u v): ");
                            String lineaArista = scanner.nextLine().trim();
                            String[] partes = lineaArista.split("\\s+");
                            if (partes.length < 2) {
                                System.out.println("    [!] Formato incorrecto. Ingrese dos enteros separados por espacio.");
                                i--;
                                continue;
                            }
                            int u;
                            int v;
                            try {
                                u = Integer.parseInt(partes[0]);
                                v = Integer.parseInt(partes[1]);
                            } catch (NumberFormatException e) {
                                System.out.println("    [!] Ingrese números enteros válidos.");
                                i--;
                                continue;
                            }
                            if (u < 0 || u >= n || v < 0 || v >= n) {
                                System.out.println("    [!] Nodos fuera de rango [0, " + (n - 1) + "]. Reintente este enlace.");
                                i--;
                                continue;
                            }
                            if (u == v) {
                                System.out.println("    [!] No se permiten enlaces de un nodo consigo mismo. Reintente.");
                                i--;
                                continue;
                            }
                            boolean repetido = false;
                            for (Arista a : redActual.getAristas()) {
                                if ((a.getU() == u && a.getV() == v) || (a.getU() == v && a.getV() == u)) {
                                    repetido = true;
                                    break;
                                }
                            }
                            if (repetido) {
                                System.out.println("    [!] Enlace repetido o ya existente entre " + u + " y " + v + ". Reintente.");
                                i--;
                                continue;
                            }
                            redActual.agregarArista(u, v);
                        }

                        System.out.println();
                        while (true) {
                            System.out.print("  • Presupuesto de sensores (k >= 0): ");
                            String inputK = scanner.nextLine().trim();
                            try {
                                kActual = Integer.parseInt(inputK);
                                if (kActual < 0) {
                                    System.out.println("    [!] El presupuesto no puede ser negativo. Intente nuevamente.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("    [!] Ingrese un número entero válido.");
                            }
                        }
                        ultimoResultado = null;
                        System.out.println();
                        System.out.println("--------------------------------------------------------------------------");
                        System.out.println("[ÉXITO] Red cargada correctamente:");
                        System.out.println("        • Routers instalados (|V|): " + redActual.getNumVertices());
                        System.out.println("        • Enlaces conectados (|E|): " + redActual.getNumAristas());
                        System.out.println("        • Presupuesto asignado (k): " + kActual);
                        System.out.println("--------------------------------------------------------------------------\n");
                    } catch (NumberFormatException e) {
                        System.out.println("\n[ERROR] Entrada numérica no válida. Operación cancelada.");
                        System.out.println("==========================================================================\n");
                    }
                    break;

                case "2":
                    System.out.println("-> Opción 2 seleccionada: Generar red reproducible (Melany).");
                    redActual = benchmarkMelany.generarRedReproducible(6, 0.4, 12345L);
                    kActual = 3;
                    ultimoResultado = null;
                    System.out.println("-> Red aleatoria generada: |V|=" + redActual.getNumVertices() 
                            + ", |E|=" + redActual.getNumAristas() + ", k=" + kActual);
                    break;

                case "3":
                    redActual = new Grafo(4);
                    redActual.agregarArista(0, 1);
                    redActual.agregarArista(1, 2);
                    redActual.agregarArista(2, 3);
                    redActual.agregarArista(3, 0);
                    kActual = 2;
                    ultimoResultado = null;
                    System.out.println("-> Caso de prueba predeterminado cargado (|V|=4, |E|=4, k=2).");
                    break;

                case "4":
                    System.out.println("-> Opción 4 seleccionada: Ejecutar algoritmo exacto de decisión (Karina).");
                    if (redActual != null) {
                        ultimoResultado = solverKarina.resolverDecision(redActual, kActual);
                        solverKarina.presentarDecision(ultimoResultado, kActual);
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red (Opciones 1, 2 o 3).");
                    }
                    break;

                case "5":
                    System.out.println("-> Opción 5 seleccionada: Buscar cobertura mínima óptima k* (Karina).");
                    if (redActual != null) {
                        ultimoResultado = solverKarina.encontrarCoberturaMinima(redActual);
                        solverKarina.presentarCoberturaMinima(ultimoResultado);
                        kActual = ultimoResultado.getCertificado().size();
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red (Opciones 1, 2 o 3).");
                    }
                    break;

                case "6":
                    System.out.println("-> Opción 6 seleccionada: Ejecutar verificador independiente (Eduardo).");
                    if (redActual != null) {
                        if (ultimoResultado != null && ultimoResultado.isTieneSolucion()) {
                            System.out.println("Auditando el certificado obtenido por el algoritmo:");
                            System.out.println("Presupuesto evaluado: k = " + kActual);
                            System.out.println("Certificado a auditar: S = " + ultimoResultado.getCertificado());
                            verificadorEduardo.verificarCertificado(redActual, kActual, ultimoResultado.getCertificado(), true);
                        } else {
                            System.out.println("No hay un certificado previo válido generado por el algoritmo.");
                            System.out.print("¿Desea ingresar un certificado manualmente para probar el verificador? (s/n): ");
                            String resp = scanner.nextLine().trim();
                            if (resp.equalsIgnoreCase("s")) {
                                try {
                                    System.out.print("Ingrese presupuesto k: ");
                                    int kAuditar = Integer.parseInt(scanner.nextLine().trim());
                                    System.out.print("Ingrese los nodos del certificado separados por espacio (ej. 0 2): ");
                                    String lineaNodos = scanner.nextLine().trim();
                                    List<Integer> certManual = new ArrayList<>();
                                    if (!lineaNodos.isEmpty()) {
                                        for (String tok : lineaNodos.split("\\s+")) {
                                            certManual.add(Integer.parseInt(tok));
                                        }
                                    }
                                    verificadorEduardo.verificarCertificado(redActual, kAuditar, certManual, true);
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Formato de número inválido.");
                                }
                            }
                        }
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red (Opciones 1, 2 o 3).");
                    }
                    break;

                case "7":
                    System.out.println("-> Opción 7 seleccionada: Aplicar cambio obligatorio (+1 enlace) (Melany).");
                    if (redActual != null) {
                        modificadorMelany.aplicarCambioYComparar(redActual, solverKarina);
                    } else {
                        System.out.println("Primero debe ingresar o cargar una red (Opciones 1, 2 o 3).");
                    }
                    break;

                case "8":
                    System.out.println("-> Opción 8 seleccionada: Ejecutar batería de benchmarking (Melany).");
                    benchmarkMelany.ejecutarBateriaCompleta(solverKarina, 5);
                    break;

                case "9":
                    System.out.println("-> Opción 9 seleccionada: Ejecutar suite de pruebas funcionales (Eduardo).");
                    pruebasEduardo.ejecutarPruebas();
                    break;

                case "10":
                    System.out.println("-> Opción 10 seleccionada: Modelado de complejidad teórica (Carolay).");
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

            if (!salir) {
                System.out.println();
                System.out.print("Presione ENTER para volver al menú principal...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Limpia la pantalla de la consola para mantener una interfaz despejada y legible.
     * Compatible con VS Code, macOS, Linux y Windows Terminal mediante códigos ANSI estándar.
     */
    public static void limpiarPantalla() {
        try {
            System.out.print("\033[H\033[2J\033[3J");
            System.out.flush();
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (Exception ignored) {
            for (int i = 0; i < 30; i++) {
                System.out.println();
            }
        }
    }
}
