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

Las seis preguntas se formularon directamente en el cuaderno compartido por el docente (siete fuentes, documentos internos de la cátedra cargados por el docente). Las capturas de pantalla de cada respuesta, la síntesis, el contraste con Kleinberg y Tardos [4] (páginas impresas del libro) y la valoración completa están en el Anexo A del informe en formato Word. El contraste se hizo únicamente con [4]; lo que no aparece en esa fuente se indica como no contrastado.

| N° | Pregunta formulada en NotebookLM | Contraste con Kleinberg y Tardos [4] | Resultado |
|----|----------------------------------|--------------------------------------|-----------|
| 1 | ¿Por qué verificar un certificado de Vertex Cover es de tiempo polinomial, y qué relación tiene con que el problema esté en NP? | Sec. 8.3, pp. 463-465 (certificador eficiente, NP, P ⊆ NP) | Coincide. La cota O(\|V\|+\|E\|) es de la implementación propia, no del libro |
| 2 | ¿Cómo se demuestra que Vertex Cover es NP-completo? ¿Desde qué problema se reduce? | Enunciados (8.3)-(8.5), pp. 455-456; (8.8), pp. 460-462; p. 463 (cadena 3-SAT ≤p Independent Set ≤p Vertex Cover) | Coincide en la complementariedad. La reducción directa desde 3-SAT con gadgets de variables no se contrastó |
| 3 | ¿Por qué la ramificación sobre una arista no cubierta no pierde soluciones? ¿Cómo se justifica por inducción? | Enunciado (10.3), p. 556; (10.4), p. 557 | Coincide |
| 4 | ¿Qué significa que un problema sea FPT y por qué O(2^k · \|E\|) lo hace tratable respecto de k? | Pp. 554-555 y (10.4), p. 557 | Coincide en el fondo. El libro no define FPT en esas páginas; definición no contrastada |
| 5 | Si agrego una arista, ¿cuánto puede cambiar la cobertura mínima? ¿Puede subir más de una unidad? | No aparece en las secciones consultadas | No contrastado con el libro; razonamiento propio (Δk* ∈ {0, 1}) |
| 6 | ¿En qué se diferencia encontrar una solución de verificar un certificado? | P. 463; (8.10), p. 464; (8.11), p. 465 | Coincide. El libro no prueba que encontrar exija tiempo exponencial |

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
6. **MemoriaKB:** Estimación aproximada de la variación del heap de la JVM durante las repeticiones, en kilobytes; no es determinista (depende del recolector de basura) y solo indica un orden de magnitud.
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
