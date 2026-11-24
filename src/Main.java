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

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AlgoritmoExactoVertexCover solverKarina = new AlgoritmoExactoVertexCover();
        VerificadorIndependiente verificadorEduardo = new VerificadorIndependiente();
        SuitePruebasFuncionales pruebasEduardo = new SuitePruebasFuncionales();
        ModificadorInstanciaRed modificadorMelany = new ModificadorInstanciaRed();
        BateriaBenchmarking benchmarkMelany = new BateriaBenchmarking();
        AnalisisComplejidad analisisCarolay = new AnalisisComplejidad();

        Grafo redActual = null;
        int kActual = 0;
        ResultadoSolucion ultimoResultado = null;

        boolean salir = false;

        while (!salir) {
            limpiarPantalla();
            String estadoMemoria;
            if (redActual == null) {
                estadoMemoria = "Sin red cargada (elija Opción 1, 2 o 3 para iniciar)";
            } else {
                String infoSolucion = (ultimoResultado != null && ultimoResultado.isTieneSolucion())
                        ? " | S = " + ultimoResultado.getCertificado()
                        : " | S = pendiente (use Opción 4 o 5)";
                estadoMemoria = "Red de " + redActual.getNumVertices() + " routers y "
                        + redActual.getNumAristas() + " enlaces | k = " + kActual + infoSolucion;
            }

            System.out.println("==========================================================================");
            System.out.println("   PROYECTO INTEGRADOR EDA II: VERTEX COVER (EQUIPO 4)                   ");
            System.out.println("   Integrantes: Karina, Eduardo, Melany, Carolay                          ");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("   ESTADO EN MEMORIA: " + estadoMemoria);
            System.out.println("==========================================================================");
            System.out.println(" [1] CARGAR O CREAR RED EN MEMORIA");
            System.out.println("     1. Ingresar red manualmente por consola (|V|, |E| y enlaces)");
            System.out.println("     2. Generar red aleatoria reproducible");
            System.out.println("     3. Cargar caso de prueba predeterminado en consola (|V|=4)");
            System.out.println();
            System.out.println(" [2] OPERAR SOBRE LA RED CARGADA");
            System.out.println("     4. Resolver problema de decisión con Algoritmo Exacto");
            System.out.println("     5. Encontrar Cobertura Mínima Óptima k*");
            System.out.println("     6. Ejecutar Verificador Independiente sobre el certificado");
            System.out.println("     7. Aplicar Modificación Obligatoria: +1 enlace");
            System.out.println();
            System.out.println(" [3] BENCHMARKING, PRUEBAS Y COMPLEJIDAD");
            System.out.println("     8. Ejecutar Batería de Benchmarking con medianas y CSV");
            System.out.println("     9. Ejecutar Suite de 5 Pruebas Funcionales automáticas");
            System.out.println("    10. Mostrar Análisis Teórico de Complejidad y Respuestas");
            System.out.println();
            System.out.println("     0. Salir");
            System.out.println("--------------------------------------------------------------------------");
            System.out.print("Seleccione una opción (0 al 10, ejemplo: 1): ");

            String opcion = scanner.nextLine().trim();

            if (!opcion.equals("0")) {
                limpiarPantalla();
            }

            switch (opcion) {
                case "1":
                    System.out.println("==========================================================================");
                    System.out.println("   OPCIÓN 1: INGRESO MANUAL DE RED POR CONSOLA                            ");
                    System.out.println("==========================================================================");

                    int n;
                    while (true) {
                        System.out.print("  • Número de routers / nodos |V| (mínimo 1, ejemplo: 4): ");
                        String inputN = scanner.nextLine().trim();
                        if (inputN.isEmpty()) {
                            System.out.println("    El campo no puede estar vacío. Ingrese un entero (ejemplo: 4).");
                            continue;
                        }
                        try {
                            n = Integer.parseInt(inputN);
                            if (n <= 0) {
                                System.out.println("    El número de routers debe ser mayor o igual a 1 (ejemplo: 4).");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("    \"" + inputN + "\" no es un entero válido. Ingrese solo números (ejemplo: 4).");
                        }
                    }

                    redActual = new Grafo(n);
                    long maxEnlaces = (long) n * (n - 1) / 2;
                    int m = 0;

                    if (n == 1) {
                        System.out.println("  Red de 1 solo router (nodo 0). Se asignan 0 enlaces automáticamente.");
                        m = 0;
                    } else {
                        long ejemploM = Math.min(4, maxEnlaces);
                        while (true) {
                            System.out.print("  • Número de enlaces físicos |E| (rango 0 a " + maxEnlaces + ", ejemplo: " + ejemploM + "): ");
                            String inputM = scanner.nextLine().trim();
                            if (inputM.isEmpty()) {
                                System.out.println("    El campo no puede estar vacío. Ingrese un entero entre 0 y " + maxEnlaces + " (ejemplo: " + ejemploM + ").");
                                continue;
                            }
                            try {
                                m = Integer.parseInt(inputM);
                                if (m < 0) {
                                    System.out.println("    El número de enlaces no puede ser negativo (ejemplo: " + ejemploM + ").");
                                    continue;
                                }
                                if (m > maxEnlaces) {
                                    System.out.println("    Para " + n + " routers el máximo posible es " + maxEnlaces + " enlaces. Ingrese un valor <= " + maxEnlaces + ".");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("    \"" + inputM + "\" no es un entero válido. Ingrese un número entre 0 y " + maxEnlaces + ".");
                            }
                        }
                    }

                    if (m > 0) {
                        System.out.println("\n  --- Ingrese los enlaces físicos: dos routers separados por espacio ---");
                        System.out.println("  --- Rango válido de routers: de 0 a " + (n - 1) + " (ejemplo: 0 1) ---");
                    }
                    for (int i = 0; i < m; i++) {
                        int ejU = i % n;
                        int ejV = (i + 1) % n;
                        System.out.print("    Enlace " + (i + 1) + " de " + m + " (u v, ejemplo: " + ejU + " " + ejV + "): ");
                        String lineaArista = scanner.nextLine().trim();
                        if (lineaArista.isEmpty()) {
                            System.out.println("    Entrada vacía. Debe escribir dos routers separados por espacio (ejemplo: 0 1).");
                            i--;
                            continue;
                        }
                        String[] partes = lineaArista.split("\\s+");
                        if (partes.length != 2) {
                            System.out.println("    Formato incorrecto. Debe ingresar exactamente 2 números separados por espacio (ejemplo: 0 1).");
                            i--;
                            continue;
                        }
                        int u;
                        int v;
                        try {
                            u = Integer.parseInt(partes[0]);
                            v = Integer.parseInt(partes[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("    \"" + lineaArista + "\" contiene caracteres no numéricos. Use solo enteros (ejemplo: 0 1).");
                            i--;
                            continue;
                        }
                        if (u < 0 || u >= n || v < 0 || v >= n) {
                            System.out.println("    Router fuera de rango. Los routers deben estar entre 0 y " + (n - 1) + " (ejemplo: 0 1).");
                            i--;
                            continue;
                        }
                        if (u == v) {
                            System.out.println("    No se permiten enlaces de un router consigo mismo (" + u + " " + v + "). Use dos routers distintos (ejemplo: 0 1).");
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
                            System.out.println("    El enlace entre " + u + " y " + v + " ya existe. Ingrese un par de routers diferente.");
                            i--;
                            continue;
                        }
                        redActual.agregarArista(u, v);
                    }

                    System.out.println();
                    int ejemploK = Math.min(2, n);
                    while (true) {
                        System.out.print("  • Presupuesto máximo de sensores k (entero >= 0, ejemplo: " + ejemploK + "): ");
                        String inputK = scanner.nextLine().trim();
                        if (inputK.isEmpty()) {
                            System.out.println("    El campo no puede estar vacío. Ingrese un entero >= 0 (ejemplo: " + ejemploK + ").");
                            continue;
                        }
                        try {
                            kActual = Integer.parseInt(inputK);
                            if (kActual < 0) {
                                System.out.println("    El presupuesto k no puede ser negativo. Ingrese 0 o más (ejemplo: " + ejemploK + ").");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("    \"" + inputK + "\" no es un número entero válido (ejemplo: " + ejemploK + ").");
                        }
                    }
                    ultimoResultado = null;
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println("Éxito: Red cargada correctamente:");
                    System.out.println("        • Routers instalados (|V|): " + redActual.getNumVertices() + " (nodos 0 al " + (n - 1) + ")");
                    System.out.println("        • Enlaces conectados (|E|): " + redActual.getNumAristas());
                    System.out.println("        • Presupuesto asignado (k): " + kActual);
                    System.out.println("--------------------------------------------------------------------------\n");
                    break;

                case "2":
                    System.out.println("==========================================================================");
                    System.out.println("   OPCIÓN 2: GENERAR RED ALEATORIA REPRODUCIBLE                           ");
                    System.out.println("==========================================================================");

                    int nGen;
                    while (true) {
                        System.out.print("  • Número de routers / nodos |V| (mínimo 1, ejemplo: 6): ");
                        String inN = scanner.nextLine().trim();
                        if (inN.isEmpty()) {
                            System.out.println("    El campo no puede estar vacío. Ingrese un entero >= 1 (ejemplo: 6).");
                            continue;
                        }
                        try {
                            nGen = Integer.parseInt(inN);
                            if (nGen < 1) {
                                System.out.println("    El número de routers debe ser al menos 1 (ejemplo: 6).");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("    \"" + inN + "\" no es un entero válido. Ingrese un número entero (ejemplo: 6).");
                        }
                    }

                    double probGen;
                    while (true) {
                        System.out.print("  • Probabilidad de enlace entre routers (decimal de 0.0 a 1.0, ejemplo: 0.4): ");
                        String inP = scanner.nextLine().trim().replace(',', '.');
                        if (inP.isEmpty()) {
                            System.out.println("    El campo no puede estar vacío. Ingrese un decimal entre 0.0 y 1.0 (ejemplo: 0.4).");
                            continue;
                        }
                        try {
                            probGen = Double.parseDouble(inP);
                            if (Double.isNaN(probGen) || probGen < 0.0 || probGen > 1.0) {
                                System.out.println("    La probabilidad debe estar entre 0.0 y 1.0 (ejemplo: 0.4).");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("    \"" + inP + "\" no es un decimal válido. Ingrese un valor como 0.4 o 0.5.");
                        }
                    }

                    long semillaGen;
                    while (true) {
                        System.out.print("  • Semilla aleatoria para reproducibilidad (entero, ejemplo: 12345): ");
                        String inS = scanner.nextLine().trim();
                        if (inS.isEmpty()) {
                            System.out.println("    El campo no puede estar vacío. Ingrese un número entero (ejemplo: 12345).");
                            continue;
                        }
                        try {
                            semillaGen = Long.parseLong(inS);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("    \"" + inS + "\" no es un entero válido. Ingrese un número entero (ejemplo: 12345).");
                        }
                    }

                    while (true) {
                        System.out.print("  • Presupuesto máximo de sensores k (entero >= 0, ejemplo: 3): ");
                        String inK = scanner.nextLine().trim();
                        if (inK.isEmpty()) {
                            System.out.println("    El campo no puede estar vacío. Ingrese un entero >= 0 (ejemplo: 3).");
                            continue;
                        }
                        try {
                            kActual = Integer.parseInt(inK);
                            if (kActual < 0) {
                                System.out.println("    El presupuesto k no puede ser negativo (ejemplo: 3).");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("    \"" + inK + "\" no es un entero válido. Ingrese un número entero (ejemplo: 3).");
                        }
                    }

                    redActual = benchmarkMelany.generarRedReproducible(nGen, probGen, semillaGen);
                    ultimoResultado = null;

                    StringBuilder listaEnlaces = new StringBuilder();
                    List<Arista> aristasGen = redActual.getAristas();
                    for (int i = 0; i < aristasGen.size(); i++) {
                        Arista a = aristasGen.get(i);
                        listaEnlaces.append("(").append(a.getU()).append(" <-> ").append(a.getV()).append(")");
                        if (i < aristasGen.size() - 1) {
                            listaEnlaces.append(", ");
                        }
                    }

                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println("Éxito: Red aleatoria reproducible generada en memoria:");
                    System.out.println("        • Routers instalados (|V|)  : " + redActual.getNumVertices() + " (nodos 0 al " + (nGen - 1) + ")");
                    System.out.println("        • Probabilidad / Semilla    : p = " + probGen + " | semilla = " + semillaGen);
                    System.out.println("        • Enlaces generados (|E|)   : " + redActual.getNumAristas()
                            + (aristasGen.isEmpty() ? " (sin enlaces)" : " -> [" + listaEnlaces + "]"));
                    System.out.println("        • Presupuesto asignado (k)  : " + kActual);
                    System.out.println("--------------------------------------------------------------------------\n");
                    break;

                case "3":
                    redActual = new Grafo(4);
                    redActual.agregarArista(0, 1);
                    redActual.agregarArista(1, 2);
                    redActual.agregarArista(2, 3);
                    redActual.agregarArista(3, 0);
                    kActual = 2;
                    ultimoResultado = null;
                    System.out.println("Caso de prueba predeterminado cargado (|V|=4, |E|=4, enlaces: 0-1, 1-2, 2-3, 3-0, k=2).");
                    break;

                case "4":
                    System.out.println("==========================================================================");
                    System.out.println("   OPCIÓN 4: RESOLVER PROBLEMA DE DECISIÓN CON ALGORITMO EXACTO           ");
                    System.out.println("==========================================================================");
                    if (redActual != null) {
                        System.out.println("  Red actual en memoria : |V| = " + redActual.getNumVertices()
                                + " routers, |E| = " + redActual.getNumAristas() + " enlaces.\n");
                        while (true) {
                            System.out.print("  • Presupuesto de sensores k a consultar (entero >= 0, ejemplo: " + kActual + ", o ENTER): ");
                            String inKDec = scanner.nextLine().trim();
                            if (inKDec.isEmpty()) {
                                break;
                            }
                            try {
                                int nuevoK = Integer.parseInt(inKDec);
                                if (nuevoK < 0) {
                                    System.out.println("    El presupuesto k no puede ser negativo. Ingrese un entero >= 0 (ejemplo: 2).");
                                    continue;
                                }
                                kActual = nuevoK;
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("    \"" + inKDec + "\" no es un entero válido. Ingrese un número entero (ejemplo: 2) o presione ENTER.");
                            }
                        }
                        System.out.println();
                        ultimoResultado = solverKarina.resolverDecision(redActual, kActual);
                        solverKarina.presentarDecision(ultimoResultado, kActual);
                    } else {
                        System.out.println("No hay ninguna red cargada en memoria.");
                        System.out.println("Primero ingrese o genere una red usando las Opciones 1, 2 o 3.");
                    }
                    break;

                case "5":
                    System.out.println("Opción 5 seleccionada: Buscar cobertura mínima óptima k*.");
                    if (redActual != null) {
                        ultimoResultado = solverKarina.encontrarCoberturaMinima(redActual);
                        solverKarina.presentarCoberturaMinima(ultimoResultado);
                        kActual = ultimoResultado.getCertificado().size();
                    } else {
                        System.out.println("No hay ninguna red cargada en memoria.");
                        System.out.println("Primero ingrese o genere una red usando las Opciones 1, 2 o 3.");
                    }
                    break;

                case "6":
                    System.out.println("==========================================================================");
                    System.out.println("   OPCIÓN 6: EJECUTAR VERIFICADOR INDEPENDIENTE SOBRE EL CERTIFICADO      ");
                    System.out.println("==========================================================================");
                    if (redActual != null) {
                        int maxRouter = redActual.getNumVertices() - 1;
                        String ejCert = redActual.getNumVertices() >= 3 ? "0 2" : "0";
                        System.out.println("  Red actual en memoria : |V| = " + redActual.getNumVertices()
                                + " routers (rango válido: 0 al " + maxRouter + "), |E| = " + redActual.getNumAristas() + " enlaces.");

                        if (ultimoResultado != null && ultimoResultado.isTieneSolucion()) {
                            System.out.println("  Certificado actual del algoritmo: S = " + ultimoResultado.getCertificado()
                                    + " (con presupuesto k = " + kActual + ")\n");

                            int kAuditar = kActual;
                            while (true) {
                                System.out.print("  • Presupuesto k a auditar (ejemplo: " + kActual + ", o ENTER para usar k = " + kActual + "): ");
                                String inKAud = scanner.nextLine().trim();
                                if (inKAud.isEmpty()) {
                                    break;
                                }
                                try {
                                    kAuditar = Integer.parseInt(inKAud);
                                    if (kAuditar < 0) {
                                        System.out.println("    El presupuesto k no puede ser negativo. Ingrese un entero >= 0.");
                                        continue;
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("    \"" + inKAud + "\" no es un entero válido. Ingrese un número entero (ejemplo: " + kActual + ") o presione ENTER.");
                                }
                            }

                            List<Integer> certAuditar = ultimoResultado.getCertificado();
                            while (true) {
                                System.out.print("  • Nodos del certificado S separados por espacio (ejemplo: " + ejCert
                                        + ", o ENTER para auditar " + ultimoResultado.getCertificado() + "): ");
                                String lineaNodos = scanner.nextLine().trim();
                                if (lineaNodos.isEmpty()) {
                                    break;
                                }
                                List<Integer> certManual = new ArrayList<>();
                                boolean valido = true;
                                for (String tok : lineaNodos.split("\\s+")) {
                                    try {
                                        int nodo = Integer.parseInt(tok);
                                        if (nodo < 0 || nodo >= redActual.getNumVertices()) {
                                            System.out.println("    El router " + nodo + " no existe. Los routers válidos van de 0 a " + maxRouter + " (ejemplo: " + ejCert + ").");
                                            valido = false;
                                            break;
                                        }
                                        if (certManual.contains(nodo)) {
                                            System.out.println("    El router " + nodo + " está repetido. Ingrese cada router una sola vez (ejemplo: " + ejCert + ").");
                                            valido = false;
                                            break;
                                        }
                                        certManual.add(nodo);
                                    } catch (NumberFormatException e) {
                                        System.out.println("    \"" + tok + "\" no es un número entero. Escriba routers separados por espacio (ejemplo: " + ejCert + ").");
                                        valido = false;
                                        break;
                                    }
                                }
                                if (valido) {
                                    certAuditar = certManual;
                                    break;
                                }
                            }

                            System.out.println();
                            verificadorEduardo.verificarCertificado(redActual, kAuditar, certAuditar, true);
                        } else {
                            System.out.println();
                            int kAuditar;
                            while (true) {
                                System.out.print("  • Ingrese presupuesto k a auditar (entero >= 0, ejemplo: " + kActual + "): ");
                                String inKAud = scanner.nextLine().trim();
                                if (inKAud.isEmpty()) {
                                    System.out.println("    El campo no puede estar vacío. Ingrese un entero >= 0 (ejemplo: " + kActual + ").");
                                    continue;
                                }
                                try {
                                    kAuditar = Integer.parseInt(inKAud);
                                    if (kAuditar < 0) {
                                        System.out.println("    El presupuesto k no puede ser negativo. Ingrese un entero >= 0.");
                                        continue;
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("    \"" + inKAud + "\" no es un entero válido. Ingrese un número entero (ejemplo: " + kActual + ").");
                                }
                            }

                            List<Integer> certManual = new ArrayList<>();
                            while (true) {
                                System.out.print("  • Nodos del certificado S separados por espacio (rango 0 a " + maxRouter + ", ejemplo: " + ejCert + "): ");
                                String lineaNodos = scanner.nextLine().trim();
                                if (lineaNodos.isEmpty()) {
                                    break;
                                }
                                certManual.clear();
                                boolean valido = true;
                                for (String tok : lineaNodos.split("\\s+")) {
                                    try {
                                        int nodo = Integer.parseInt(tok);
                                        if (nodo < 0 || nodo >= redActual.getNumVertices()) {
                                            System.out.println("    El router " + nodo + " no existe. Los routers válidos van de 0 a " + maxRouter + " (ejemplo: " + ejCert + ").");
                                            valido = false;
                                            break;
                                        }
                                        if (certManual.contains(nodo)) {
                                            System.out.println("    El router " + nodo + " está repetido. Ingrese routers distintos (ejemplo: " + ejCert + ").");
                                            valido = false;
                                            break;
                                        }
                                        certManual.add(nodo);
                                    } catch (NumberFormatException e) {
                                        System.out.println("    \"" + tok + "\" no es un número entero. Escriba routers separados por espacio (ejemplo: " + ejCert + ").");
                                        valido = false;
                                        break;
                                    }
                                }
                                if (valido) {
                                    break;
                                }
                            }

                            System.out.println();
                            verificadorEduardo.verificarCertificado(redActual, kAuditar, certManual, true);
                        }
                    } else {
                        System.out.println("No hay ninguna red cargada en memoria.");
                        System.out.println("Primero ingrese o genere una red usando las Opciones 1, 2 o 3.");
                    }
                    break;

                case "7":
                    System.out.println("Opción 7 seleccionada: Aplicar cambio obligatorio (+1 enlace).");
                    if (redActual != null) {
                        modificadorMelany.aplicarCambioYComparar(redActual, solverKarina);
                    } else {
                        System.out.println("No hay ninguna red cargada en memoria.");
                        System.out.println("Primero ingrese o genere una red usando las Opciones 1, 2 o 3.");
                    }
                    break;

                case "8":
                    System.out.println("Opción 8 seleccionada: Ejecutar batería de benchmarking.");
                    benchmarkMelany.ejecutarBateriaCompleta(solverKarina, 5);
                    break;

                case "9":
                    System.out.println("Opción 9 seleccionada: Ejecutar suite de pruebas funcionales.");
                    pruebasEduardo.ejecutarPruebas();
                    break;

                case "10":
                    System.out.println("Opción 10 seleccionada: Modelado de complejidad teórica.");
                    if (redActual != null) {
                        analisisCarolay.mostrarAnalisisTeorico(
                                kActual,
                                redActual.getNumVertices(),
                                redActual.getNumAristas(),
                                ultimoResultado != null ? ultimoResultado.getTiempoNano() : 0,
                                solverKarina.getNodosExplorados()
                        );
                    } else {
                        System.out.println("No hay ninguna red cargada en memoria.");
                        System.out.println("Primero ingrese o genere una red usando las Opciones 1, 2 o 3.");
                    }
                    break;

                case "0":
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("La opción \"" + opcion + "\" no es válida. Ingrese un número entero del 0 al 10 (ejemplo: 1).");
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
