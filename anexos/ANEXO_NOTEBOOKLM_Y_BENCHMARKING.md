# ANEXO 4: REGISTRO DE NOTEBOOKLM Y ARCHIVOS TABULARES DE RESULTADOS

**Asignatura:** Estructura de Datos y Algoritmos II (2026-B)  
**Institución:** Escuela Politécnica Nacional — Facultad de Ingeniería de Sistemas  
**Equipo N°:** 4  
**Problema:** Cobertura de Vértices (Vertex Cover) — Caso Alternativo: Ubicación de Sensores en Enlaces de Red  
**Integrantes:**
* Karina Cárdenas
* Eduardo Anasi
* Melany Taco
* Carolay Pillajo

---

## 1. Anexo A: Evidencias y Registro del Cuaderno Oficial de NotebookLM

### 1.1. Enlace Oficial Compartido por el Docente
* **URL del Cuaderno Oficial:**  
  [https://notebook.google.com/notebook/c5725fd4-d5e0-4054-a7f4-5578da7d8ef2](https://notebook.google.com/notebook/c5725fd4-d5e0-4054-a7f4-5578da7d8ef2)

### 1.2. Metodología de Consulta y Contraste Crítico
En cumplimiento con las directrices de la rúbrica de evaluación, el entorno de NotebookLM se utilizó exclusivamente como asistente de investigación conceptual y contraste bibliográfico. La concepción arquitectónica del software en Java, la deducción analítica de la complejidad asintótica y el desarrollo de las pruebas funcionales fueron ejecutadas íntegramente por los estudiantes.

A continuación, se documentan las cinco preguntas conceptuales formuladas en dicho entorno y su contraste crítico con las fuentes formales de la literatura algorítmica:

| N° | Pregunta Formulada en NotebookLM | Respuesta Sintetizada por NotebookLM | Contraste con Fuente Académica Formal |
|:--:|:---|:---|:---|
| **1** | ¿Por qué la verificación de un certificado de Vertex Cover se realiza en tiempo polinomial lineal $O(\|V\| + \|E\|)$? | Para verificar un certificado $S$ basta con indexar los nodos en un arreglo booleano de pertenencia $O(1)$ y luego iterar sobre la lista de aristas $E$, comprobando que al menos un extremo esté marcado. Al realizar $\|V\|$ operaciones de inicialización y $\|E\|$ verificaciones, el costo total es lineal $O(\|V\| + \|E\|)$. | **T. H. Cormen et al.** (*Introduction to Algorithms*, 4th ed., MIT Press, 2022, cap. 34, pp. 1048-1060) y **J. Kleinberg y É. Tardos** (*Algorithm Design*, Addison-Wesley, 2006, cap. 8). Confirma que la verificación pertenece a la clase $P$, demostrando la pertenencia formal a $NP$. |
| **2** | ¿Cuál fue la construcción matemática original de Richard Karp para demostrar que Vertex Cover es NP-completo? | Karp (1972) demostró la NP-completitud de Vertex Cover (*Node Cover*) mediante una reducción polinomial desde 3-SAT (pasando por CLIQUE). Cada cláusula y literal se transforma en un subgrafo (*gadget*) donde existe una cobertura de tamaño $k$ si y solo si la fórmula es satisfacible. | **R. M. Karp** (*Reducibility among combinatorial problems*, Springer, 1972, pp. 85-103) y catalogado canónicamente en **M. R. Garey y D. S. Johnson** (*Computers and Intractability*, W. H. Freeman, 1979, [GT1]). |
| **3** | ¿Por qué el algoritmo exacto de ramificación binaria sobre aristas no cubiertas garantiza la solución óptima sin omitir casos? | En cualquier arista no supervisada $\{u, v\}$, toda cobertura válida debe contener necesariamente a $u$, a $v$, o a ambos. Al explorar recursivamente ambas ramas decrementando el presupuesto $k$ en 1, el árbol cubre exhaustivamente todas las alternativas viables sin omitir certificados válidos. | **J. Kleinberg y É. Tardos** (*Algorithm Design*, 2006, cap. 10, pp. 553-562) y **M. Cygan et al.** (*Parameterized Algorithms*, Springer, 2015, cap. 3). La exhaustividad se fundamenta inductivamente sobre el parámetro de presupuesto $k$. |
| **4** | ¿Por qué la complejidad $O(2^k \cdot \|E\|)$ clasifica a Vertex Cover dentro de la clase FPT (*Fixed-Parameter Tractable*)? | A diferencia de la fuerza bruta que evalúa combinaciones binomiales $\binom{\|V\|}{k}$ donde el exponente crece con $\|V\|$, la ramificación acota la altura del árbol a $k$. La explosión combinatoria $2^k$ queda aislada exclusivamente al parámetro $k$, multiplicada por un polinomio en el tamaño de la red $\|E\|$. | **M. Cygan et al.** (*Parameterized Algorithms*, Springer, 2015, cap. 2, pp. 25-38). Se ratifica la definición formal de FPT: $T(n, k) \le f(k) \cdot \|G\|^{O(1)}$. |
| **5** | ¿Qué impacto teórico y práctico tiene la adición de un único enlace sobre la cardinalidad de la cobertura mínima ($\Delta k^*$)? | Al incorporar un nuevo enlace $e = \{u, v\}$, la cobertura mínima $k^*$ se mantiene idéntica ($\Delta k^* = 0$) si al menos uno de los extremos ya formaba parte de $S^*$, o se incrementa en exactamente una unidad ($\Delta k^* = 1$) si ambos extremos carecían de sensor inicial. | **M. R. Garey y D. S. Johnson** (1979) y **T. H. Cormen et al.** (2022). Demuestra que la variación está estrictamente confinada al conjunto discreto $\Delta k^* \in \{0, 1\}$. |

### 1.3. Reflexión Grupal sobre Honestidad Académica
Como equipo de desarrollo, asumimos un compromiso ético y riguroso con la autoría intelectual del proyecto. Reconocemos a las herramientas de inteligencia artificial generativa, específicamente NotebookLM, exclusivamente como un entorno de consulta conceptual y acelerador de investigación bibliográfica. Su función se limitó a estructurar discusiones teóricas sobre la clase NP, contrastar definiciones matemáticas y esclarecer los fundamentos de la tratabilidad parametrizada frente a la literatura clásica. Sin embargo, la concepción lógica, la arquitectura del software en Java, la gestión de estructuras de datos discretas y la validación matemática fueron íntegramente desarrolladas y dominadas por los integrantes del grupo. Cada línea del algoritmo exacto, de la rutina verificadora independiente y del generador experimental fue programada, depurada y comprobada manualmente en consola, asegurando un entendimiento profundo del árbol de recursión y del control de excepciones. La verdadera competencia profesional no radica en la generación automatizada de fragmentos de código, sino en la capacidad analítica para demostrar su correctitud, medir formalmente su complejidad computacional y defender técnicamente cada decisión de diseño tomada.

---

## 2. Anexo B: Archivo Tabular de Resultados de Benchmarking (`bateria_benchmarking.csv`)

### 2.1. Metadatos del Archivo Tabular
* **Nombre de archivo:** `bateria_benchmarking.csv`
* **Ubicación en el repositorio:** Raíz del proyecto (`/bateria_benchmarking.csv`)
* **Módulo generador:** `src/melany/BateriaBenchmarking.java`
* **Condiciones experimentales:**
  * Plataforma: Apple Silicon M-Series, OpenJDK 64-Bit Server VM, macOS.
  * Cronometraje: `System.nanoTime()` con aislamiento de entrada/salida.
  * Precalentamiento: 50 ejecuciones previas de JIT warmup sobre grafos aleatorios.
  * Repeticiones: 5 ejecuciones independientes por tamaño, reportando la **mediana** estadística.
  * Reproducibilidad: Semilla pseudoaleatoria determinista base $42000$.

### 2.2. Diccionario de Campos
1. **Prueba:** Identificador numérico correlativo de la configuración (1 a 5).
2. **Vertices:** Cardinalidad de nodos $\|V\|$ que componen los routers de la infraestructura.
3. **Aristas:** Cardinalidad de enlaces físicos $\|E\|$ de la red no dirigida.
4. **kOptimo:** Número mínimo de sensores $k^*$ para garantizar supervisión total.
5. **TiempoMedianoMs:** Mediana estadística del tiempo de cómputo en milisegundos.
6. **MemoriaKB:** Estimación de memoria auxiliar heap en la JVM en kilobytes.
7. **Certificado:** Lista ordenada de nodos $S^*$ que certifican la cobertura óptima.

### 2.3. Tabla de Resultados Consolidados
A continuación, se transcribe el contenido exacto almacenado en `bateria_benchmarking.csv`:

| Prueba | Vértices ($\|V\|$) | Aristas ($\|E\|$) | $k^*$ Óptimo | Tiempo Mediano (ms) | Memoria Est. (KB) | Certificado Testigo $S^*$ |
|:---:|:---:|:---:|:---:|:---:|:---:|:---|
| **1** | 4 | 5 | 2 | 0.0047 ms | 512.00 KB | `[2, 3]` |
| **2** | 6 | 7 | 4 | 0.0103 ms | 54.48 KB | `[0, 1, 2, 4]` |
| **3** | 8 | 8 | 4 | 0.0090 ms | 32.70 KB | `[0, 2, 4, 5]` |
| **4** | 10 | 13 | 3 | 0.0095 ms | 32.70 KB | `[2, 7, 8]` |
| **5** | 12 | 20 | 6 | 0.0238 ms | 65.41 KB | `[0, 1, 2, 3, 4, 8]` |

---
*Archivo generado para la entrega oficial del Reto Integrador — Unidad 2-CC.*
