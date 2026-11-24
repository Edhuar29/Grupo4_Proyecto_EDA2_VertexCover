# Plantilla Oficial — Proyecto Integrador EDA II (2026-B)
## Equipo 4: Vertex Cover (Ubicación de Sensores sobre Enlaces de Red)

###  Integrantes y Roles Asignados
* **Persona 1: Karina** (`src/karina/`)
  * Modelado matemático formal (entradas, salidas, restricciones, parámetro $k$).
  * Pseudocódigo del algoritmo exacto y prueba de escritorio manual.
  * Programación en Java estándar del algoritmo exacto desde cero (sin librerías externas).
  * Obtención y presentación del testigo o certificado de solución.
* **Persona 2: Eduardo** (`src/eduardo/`)
  * Rutina independiente que verifica el certificado en tiempo polinomial $O(|V| + |E|)$ (`VerificadorIndependiente.java`).
  * Batería de al menos 5 pruebas funcionales por consola (`SuitePruebasFuncionales.java`).
  * Menú por consola en [`Main.java`](src/Main.java) (10 opciones) con ingreso manual validado de la red.
  * Modularidad, README y empaquetado del ZIP ejecutable.
* **Persona 3: Melany** (`src/melany/`)
  * Funcionalidad para el cambio obligatorio: agregar una conexión y comparar coberturas mínimas en instancias pequeñas ($\Delta k^*$).
  * Protocolo experimental con `System.nanoTime()` aislando el cómputo y registro de memoria.
  * Batería experimental con al menos 5 tamaños de entrada y 3+ repeticiones para calcular la mediana.
  * Generación de tablas CSV y elaboración de las dos gráficas obligatorias.
* **Persona 4: Carolay** (`src/carolay/`)
  * Contexto real, pregunta formal de decisión, ejemplo pequeño y predicción inicial de dificultad.
  * Demostración formal de correctitud (inducción sobre $k$).
  * Análisis fundamentado de complejidad temporal $T(n)$ y espacial $S(n)$ (distinguiendo búsqueda vs. verificación).
  * Respuestas a las tres preguntas obligatorias de la guía.
  * Consolidación del informe técnico y presentación de diapositivas.

---

### Cómo compilar y ejecutar en VS Code o Terminal
Desde la raíz del proyecto (requiere JDK 17 o superior):

1. **Compilar**:
```bash
mkdir bin
javac -encoding UTF-8 -d bin -sourcepath src src/Main.java
```

2. **Ejecutar el Orquestador:**
```bash
java -Dfile.encoding=UTF-8 -cp bin Main
```
En Windows, si los acentos se ven mal en la consola, ejecute antes `chcp 65001`.
O simplemente presione `F5` en VS Code para ejecutar la configuración preconfigurada en `.vscode/launch.json`.

---

### Casos de prueba
Se ejecutan todos juntos con la **opción 9** del menú (`SuitePruebasFuncionales`). Resultado esperado: `5/5 pruebas aprobadas`.

| N° | Caso | Entrada | Resultado esperado |
| --- | --- | --- | --- |
| 1 | Grafo sin aristas (límite) | \|V\| = 4, \|E\| = 0, k = 0 | SÍ, S = [ ] |
| 2 | Estrella K(1,4) | centro 0 unido a 1, 2, 3 y 4; k = 1 | SÍ, S = [0] |
| 3 | Ciclo impar C5 | 0-1, 1-2, 2-3, 3-4, 4-0 | k = 2 da NO; k = 3 da SÍ con un testigo de 3 nodos (por ejemplo [0, 1, 3]) |
| 4 | Grafo completo K4 (límite) | 6 enlaces entre 4 routers | k = 2 da NO; k = 3 da SÍ con 3 nodos |
| 5 | Certificados inválidos | 0-1 y 1-2, k = 1; S = [0] y S = [0, 1, 2] | El verificador rechaza ambos: S = [0] deja sin sensor el enlace (1, 2) y S = [0, 1, 2] excede el presupuesto (\|S\| > k). La suite lo ejecuta en modo silencioso; el detalle se ve con la opción 6 |

Prueba manual rápida desde el menú: opción 3 (carga un anillo de 4 nodos con k = 2), opción 4 (resuelve la decisión y muestra el testigo), opción 5 (halla k\*), opción 6 (verifica el certificado) y opción 7 (agrega un enlace y compara k\* y nodos explorados).

Opción 2: genera una red aleatoria reproducible ingresando por consola |V|, probabilidad de enlace (0.0 a 1.0), semilla y presupuesto k (p. ej. |V| = 6, probabilidad 0.4, semilla 12345, k = 3). Opción 8: ejecuta la batería (5 tamaños, 5 repeticiones, mediana) y escribe `bateria_benchmarking.csv` en el directorio desde el que se ejecuta el programa (la raíz del proyecto).

### Validación de entradas (opción 1)
El menú rechaza nodos fuera de rango, formato incorrecto, k negativo, enlaces repetidos y enlaces de un router consigo mismo. El número de nodos debe ser ≥ 1 y el número de enlaces se limita al máximo de un grafo simple, \|V\|(\|V\| − 1)/2, calculado con los nodos ingresados (p. ej. 6 enlaces para 4 nodos); con un solo router se asignan 0 enlaces automáticamente.

### Archivos de resultados
* `bateria_benchmarking.csv`: tabla de las 5 pruebas (|V|, |E|, k\*, mediana en ms, memoria estimada, testigo). Los tiempos dependen de la máquina; los valores incluidos se midieron en Apple Silicon con OpenJDK 21.
* `grafica1_tiempo_vs_tamano.png` y `grafica2_comparacion_delta_k.png`: las dos gráficas obligatorias.
* `anexos/ANEXO_NOTEBOOKLM_Y_BENCHMARKING.md`: registro de NotebookLM y transcripción del CSV.
