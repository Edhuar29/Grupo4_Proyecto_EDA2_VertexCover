# PLANTILLA OFICIAL DE DIAPOSITIVAS (CANVA) — EQUIPO 4
## Proyecto Integrador: Vertex Cover (Ubicación de Sensores sobre Enlaces de Red)
### Asignatura: Estructura de Datos y Algoritmos II (Periodo 2026-B)
### Duración Total: 15 minutos | 14 Diapositivas Equilibradas (3 por Estudiante)

---

### 🎨 Paleta de Colores y Tipografía Recomendada para Canva:
* **Fondo:** Blanco limpio (`#FFFFFF`) o Gris Técnico Muy Claro (`#F8F9FA`).
* **Color Primario (Títulos y Nodos):** Azul Politécnica / Marino Tecnológico (`#0D3B66`).
* **Color Secundario (Acentos y Sensores):** Ámbar / Naranja Energía (`#F4A261` o `#E76F51`).
* **Tipografía:** Montserrat o Inter para títulos (Negrita / Bold); Open Sans o Roboto para texto de cuerpo.

---

## 📋 ESTRUCTURA EQUILIBRADA DE DIAPOSITIVAS (14 EN TOTAL)

### PORTADA GRUPAL (Diapositiva 1)
* **Título:** Proyecto Integrador EDA II: Vertex Cover en Redes
* **Subtítulo:** Ubicación Óptima de Sensores sobre Enlaces de Comunicación
* **Institución:** Escuela Politécnica Nacional — Facultad de Ingeniería de Sistemas
* **Integrantes del Equipo 4:**
  1. Karina (Persona 1) — Modelado Formal y Algoritmo Exacto
  2. Eduardo (Persona 2) — Verificador Independiente, Interfaz y Pruebas
  3. Melany (Persona 3) — Modificación Obligatoria, Benchmarking y Gráficas
  4. Carolay (Persona 4) — Correctitud, Complejidad Teórica y Consolidación

---

### 👤 SECCIÓN 1: KARINA (3 DIAPOSITIVAS | 3 MINUTOS)

#### Diapositiva 2: Karina — Contexto Real y Necesidad en Redes
* **Encabezado:** Karina (Persona 1) | Planteamiento del Problema
* **Título:** Monitoreo y Auditoría de Tráfico en Redes de Telecomunicaciones
* **Puntos Clave:**
  * Modelado de la red: Nodos $V$ (routers/switches) y Enlaces $E$ (fibra/cobre).
  * Principio del sensor: Un sensor en el nodo $u$ inspecciona todos los enlaces incidentes a $u$.
  * Reto de optimización: Restricciones de presupuesto y consumo energético exigen minimizar sensores sin dejar ningún canal desprotegido.
* **Nota del Orador (1 min):** Explicar la motivación física y por qué no es viable poner un sensor en cada equipo.

#### Diapositiva 3: Karina — Modelo Matemático Formal
* **Encabezado:** Karina (Persona 1) | Formalización Matemática
* **Título:** Definición de Entradas, Salidas y Restricciones
* **Puntos Clave:**
  * **Entradas:** Grafo no dirigido $G = (V, E)$ y presupuesto $k \ge 0$.
  * **Salida de Decisión:** $\text{Decisión} \in \{\text{SÍ}, \text{NO}\}$ y conjunto testigo $S \subseteq V$.
  * **Restricción Fundamental:** $\forall (u, v) \in E: (u \in S \lor v \in S)$ con $|S| \le k$.
* **Nota del Orador (1 min):** Enfatizar la formulación lógica de la pregunta de decisión.

#### Diapositiva 4: Karina — Algoritmo Exacto y Obtención del Testigo
* **Encabezado:** Karina (Persona 1) | Algoritmo Exacto
* **Título:** Ramificación Basada en Aristas (Branch & Bound FPT)
* **Puntos Clave:**
  * **Regla estructural:** En cualquier arista no cubierta $(u, v)$, al menos $u \in S$ o $v \in S$.
  * **Árbol de búsqueda binario:** Exploración de Rama 1 ($u \in S, k-1$) y Rama 2 ($v \in S, k-1$).
  * **Generación del Testigo:** Extracción del certificado $S$ cuando todas las aristas quedan cubiertas.
* **Nota del Orador (1 min):** Explicar brevemente la traza manual sobre el grafo de 4 nodos y dar paso a Eduardo.

---

### 👤 SECCIÓN 2: EDUARDO (3 DIAPOSITIVAS | 4 MINUTOS)

#### Diapositiva 5: Eduardo — Arquitectura en Java y Estructuras de Datos
* **Encabezado:** Eduardo (Persona 2) | Diseño de Software
* **Título:** Modularidad del Proyecto y Estructuras de Datos Eficientes
* **Puntos Clave:**
  * **Java estándar puro:** Cero dependencias externas que resuelvan el problema.
  * **Representación de red:** Listas de adyacencia y colección inmutable de aristas `(u <-> v)`.
  * **Estructura del certificado:** Arreglo booleano indexado para validación de pertenencia en $O(1)$.
* **Nota del Orador (1 min):** Resaltar la separación en paquetes (`model`, `karina`, `eduardo`, `melany`, `carolay`).

#### Diapositiva 6: Eduardo — Verificador Independiente en Tiempo Polinomial
* **Encabezado:** Eduardo (Persona 2) | Verificación Polinomial
* **Título:** Rutina Independiente de Certificación $O(|V| + |E|)$
* **Puntos Clave:**
  * **Desacoplamiento total:** El verificador no ejecuta búsquedas, solo valida el certificado $S$.
  * **Validación en 2 fases:**
    1. Cardinalidad: Comprueba $|S| \le k$ en $O(1)$.
    2. Cobertura: Recorre cada enlace $e \in E$ comprobando extremos en $O(|E|)$.
  * **Pertenencia a NP:** Demostración empírica de que verificar pertenece a la clase **P**.
* **Nota del Orador (1.5 min):** Explicar por qué el verificador es fundamental para auditar algoritmos en complejidad.

#### Diapositiva 7: Eduardo — Demostración Java en Vivo y Suite de Pruebas
* **Encabezado:** Eduardo (Persona 2) | Demostración y Pruebas
* **Título:** Menú por Consola (Terminal) y 5 Pruebas Funcionales (100% Éxito)
* **Puntos Clave:**
  * **Menú por consola:** Ingreso manual, generación reproducible y control de casos sin solución mediante Scanner.
  * **Casos límite probados:**
    * Grafo sin aristas ($k=0, S=\emptyset$) y Estrella $K_{1,4}$ ($k=1, S=\{0\}$).
    * Ciclo $C_5$ ($k=2 \to \text{NO}; k=3 \to \text{SÍ}$) y Completo $K_4$ ($k=3$).
  * **Control de fraude:** Detección inmediata de certificados incompletos con reporte de enlaces libres.
* **Nota del Orador (1.5 min):** Abrir terminal y mostrar la ejecución en vivo del menú de texto por consola y dar paso a Melany.

---

### 👤 SECCIÓN 3: MELANY (3 DIAPOSITIVAS | 3 MINUTOS)

#### Diapositiva 8: Melany — Modificación Obligatoria de la Instancia
* **Encabezado:** Melany (Persona 3) | Modificación Obligatoria
* **Título:** Adición de Enlace de Red y Variación de Cobertura Mínima
* **Puntos Clave:**
  * **Requisito Equipo 4:** Insertar un nuevo enlace físico entre dos nodos no conectados.
  * **Teorema de variación:** La nueva cobertura cumple $\Delta k^* = k^*_{mod} - k^*_{orig} \in \{0, 1\}$.
  * **Análisis estructural:**
    * $\Delta k^* = 0$: Si al menos un nodo ya tenía sensor, el nuevo enlace queda cubierto gratis.
    * $\Delta k^* = +1$: Si ambos nodos estaban desprotegidos, obliga a añadir un sensor.
* **Nota del Orador (1 min):** Explicar los resultados de la tabla comparativa donde se observó $\Delta k^*$.

#### Diapositiva 9: Melany — Protocolo Riguroso de Benchmarking
* **Encabezado:** Melany (Persona 3) | Experimentación Empírica
* **Título:** Medición Precisa con System.nanoTime() y Aislamiento de Ruido
* **Puntos Clave:**
  * **Aislamiento computacional:** Medición estricta sin entrada/salida (I/O) ni impresiones.
  * **Batería experimental:** 5 tamaños de red ($|V| \in \{4, 6, 8, 10, 12\}$).
  * **Métrica representativa:** 5 repeticiones independientes y cálculo de la **MEDIANA** para aislar el recolector de basura (GC) y la optimización JIT.
* **Nota del Orador (1 min):** Explicar las especificaciones de hardware y Java documentadas.

#### Diapositiva 10: Melany — Análisis de las Dos Gráficas Obligatorias
* **Encabezado:** Melany (Persona 3) | Gráficas y Resultados
* **Título:** Gráfica 1 (Tiempo vs. Tamaño) y Gráfica 2 (Impacto del Cambio)
* **Puntos Clave:**
  * **Gráfica 1 (Escalabilidad):** Tiempos medidos en escala de nanosegundos y milisegundos.
  * **Gráfica 2 (Comparativa):** Impacto en $k^*$ y tiempos antes vs. después del enlace adicional.
  * **Exportación tabular:** Resultados consolidados en `bateria_benchmarking.csv`.
* **Nota del Orador (1 min):** Interpretar las figuras y dar paso a Carolay para la teoría.

---

### 👤 SECCIÓN 4: CAROLAY (3 DIAPOSITIVAS | 3 MINUTOS)

#### Diapositiva 11: Carolay — Demostración Formal de Correctitud
* **Encabezado:** Carolay (Persona 4) | Fundamentación Matemática
* **Título:** Demostración Inductiva sobre el Presupuesto $k$
* **Puntos Clave:**
  * **Caso Base ($k=0$):** Si $E=\emptyset$, solución $S=\emptyset$ (correcto); si $E \ne \emptyset$, no hay solución (correcto).
  * **Paso Inductivo ($k>0$):** Para toda arista $(u, v) \in E$, al menos uno debe pertenecer a la cobertura.
  * **Exhaustividad:** Ambas ramas se exploran recursivamente con $k-1$; ninguna solución válida queda fuera del árbol.
* **Nota del Orador (1 min):** Resaltar la solidez formal del algoritmo sin heurísticas aproximadas.

#### Diapositiva 12: Carolay — Análisis de Complejidad Teórica $T(n)$ y $S(n)$
* **Encabezado:** Carolay (Persona 4) | Complejidad Computacional
* **Título:** Cotas Asintóticas de Tiempo Exponencial y Memoria Polinomial
* **Puntos Clave:**
  * **Complejidad Temporal $T(n) = O(2^k \cdot |E|)$:** Árbol binario de profundidad $k$ con filtrado de aristas en cada nivel.
  * **Complejidad Espacial $S(n) = O(k \cdot |E|) \le O(|V| \cdot |E|)$:** Pila de llamadas acotada por $k$, memoria estrictamente polinomial.
  * **Algoritmo vs. Instancia:** En la práctica, nodos con alto grado podan el árbol mucho más rápido que la cota teórica.
* **Nota del Orador (1 min):** Enfatizar la diferencia entre tiempo exponencial y memoria polinomial.

#### Diapositiva 13: Carolay — Preguntas Obligatorias y NotebookLM
* **Encabezado:** Carolay (Persona 4) | Análisis Crítico y Honestidad
* **Título:** Respuestas a Preguntas Guía y Reflexión de Honestidad Académica
* **Puntos Clave:**
  * **Pregunta 1:** Crecimiento empírico vs. teórico y limitaciones del reloj en instancias micro.
  * **Pregunta 2:** Asimetría fundamental: Búsqueda NP-difícil vs. Verificación determinista en P.
  * **Pregunta 3:** Impacto del nuevo enlace en restricciones y poda.
  * **NotebookLM:** 5 preguntas conceptuales contrastadas con literatura académica (Cormen, Karp) y reflexión de 175 palabras.
* **Nota del Orador (1 min):** Conectar los conceptos y dar paso al cierre de defensa.

---

### CIERRE GRUPAL Y PREGUNTAS (Diapositiva 14 | 2 MINUTOS)
* **Encabezado:** Equipo 4 | Conclusiones y Defensa
* **Título:** Conclusiones Principales y Ronda de Preguntas
* **Puntos Clave:**
  * Dominio integral del código y de los fundamentos matemáticos por los 4 integrantes.
  * Plataforma Java probada, verificada y documentada según la rúbrica oficial de 20 puntos.
  * Espacio abierto para preguntas individuales del profesor y prueba de casos nuevos en vivo.
* **Nota del Orador:** Agradecer al tribunal y responder individualmente las preguntas técnicas del docente.
