# ESCUELA POLITÉCNICA NACIONAL
## FACULTAD DE INGENIERÍA DE SISTEMAS
### Asignatura: Estructura de Datos y Algoritmos II (ICCD442) — Periodo 2026-B
### Proyecto Integrador — Unidad 2: Complejidad Computacional
### **TEMA: VERTEX COVER (UBICACIÓN DE SENSORES SOBRE ENLACES DE RED)**

---

### DATOS DEL EQUIPO (EQUIPO 4)
* **Persona 1: Karina** — Rol: Modelado formal, pseudocódigo y algoritmo exacto
* **Persona 2: Eduardo** — Rol: Verificador independiente, interfaz, validación y pruebas
* **Persona 3: Melany** — Rol: Modificación obligatoria, benchmarking y gráficas
* **Persona 4: Carolay** — Rol: Correctitud, complejidad teórica y consolidación

**Fecha de entrega:** Miércoles, 18 de noviembre de 2026 (23:59)  
**Fecha de defensa:** Jueves, 19 de noviembre de 2026  

---

## RESUMEN EJECUTIVO (ABSTRACT)
> **[TODO CAROLAY]:** Redactar un resumen formal de 150 a 250 palabras que sintetice:
> 1. El problema de Vertex Cover aplicado a la supervisión de redes de telecomunicaciones.
> 2. El objetivo de hallar el número mínimo de sensores k* para auditar todos los enlaces.
> 3. La técnica algorítmica exacta implementada en Java y el verificador en tiempo polinomial.
> 4. Los hallazgos principales del benchmarking empírico y el impacto del cambio obligatorio (+1 enlace).

---

## 1. CONTEXTO DEL PROBLEMA Y PREGUNTA DE DECISIÓN
**Responsable de Redacción:** Carolay (Persona 4)

### 1.1. Contexto del Caso Alternativo Asignado (Redes y Sensores)
> **[TODO CAROLAY]:** 
> * Describir la infraestructura de red modelada como un grafo no dirigido G = (V, E), donde los nodos son routers/switches y las aristas son líneas de transmisión de fibra óptica o cobre.
> * Explicar por qué es necesario colocar sensores de tráfico (sondas de paquetes) en los nodos y por qué un sensor en un nodo supervisa todas sus conexiones incidentes.
> * Justificar la importancia de minimizar los sensores desplegados por costos económicos y restricciones energéticas.

### 1.2. Pregunta Formal de Decisión
> **[TODO CAROLAY]:** 
> * Formular textualmente y con notación matemática formal la pregunta de decisión para un grafo G y un presupuesto entero k.
> * Especificar claramente el dominio de las variables de entrada y la salida esperada en el conjunto {SÍ, NO}.

### 1.3. Ejemplo Pequeño Ilustrativo
> **[TODO CAROLAY]:** 
> * Presentar un grafo pequeño de ejemplo (por ejemplo, un ciclo de 5 nodos C_5 o una red de 5 routers).
> * Mostrar un caso donde la respuesta sea NO (con k insuficiente) explicando qué aristas quedan desprotegidas.
> * Mostrar un caso donde la respuesta sea SÍ (con k adecuado), indicando explícitamente el conjunto testigo S de nodos elegidos.

### 1.4. Predicción Inicial de Dificultad
> **[TODO CAROLAY]:** 
> * Argumentar por qué Vertex Cover pertenece a la clase NP-completo (referenciar el artículo de Richard Karp de 1972).
> * Explicar la inviabilidad de la fuerza bruta combinatoria C(n, k) a medida que n crece.
> * Mencionar brevemente la propiedad FPT (Fixed-Parameter Tractability) respecto a k.

---

## 2. MODELADO FORMAL Y ALGORITMO EXACTO
**Responsable de Redacción:** Karina (Persona 1)

### 2.1. Modelo Matemático Formal
> **[TODO KARINA]:**
> * **Entradas:** Grafo no dirigido G = (V, E) y entero k >= 0.
> * **Salidas:** Decisión en {SÍ, NO} y conjunto testigo S de nodos cuando la respuesta es SÍ.
> * **Restricciones:** S subconjunto de V, cardinalidad |S| <= k, y para toda arista (u, v) en E se cumple (u en S o v en S).

### 2.2. Pseudocódigo del Algoritmo Exacto
> **[TODO KARINA]:**
> * Incluir el pseudocódigo formal y estructurado de su algoritmo exacto implementado en Java.
> * Debe basarse en la regla fundamental: para cualquier arista no cubierta (u, v), obligatoriamente u o v debe estar en la cobertura.
> * Detallar los casos base (todas las aristas cubiertas -> true; k=0 y aristas pendientes -> false) y las dos ramas recursivas.

### 2.3. Prueba Manual de Escritorio
> **[TODO KARINA]:**
> * Presentar una tabla o diagrama de traza paso a paso ejecutando a mano el algoritmo sobre un grafo de al menos 4 nodos y 4 enlaces con k = 2.
> * Demostrar cómo se exploran las ramas y cómo se obtiene el testigo final.

### 2.4. Implementación en Java Estándar
> **[TODO KARINA]:**
> * Explicar el diseño de la clase AlgoritmoExactoVertexCover (métodos resolverDecision y encontrarCoberturaMinima).
> * Resaltar que no se utilizaron librerías externas que resuelvan el problema.

---

## 3. VERIFICADOR INDEPENDIENTE, ESTRUCTURAS Y PRUEBAS FUNCIONALES
**Responsable de Redacción:** Eduardo (Persona 2)

### 3.1. Estructuras de Datos Utilizadas
> **[TODO EDUARDO]:**
> * Justificar la elección de listas de adyacencia y arreglos/vectores booleanos indexados para la representación del grafo y del certificado.
> * Explicar el impacto de estas estructuras en la eficiencia de consulta temporal O(1).

### 3.2. Rutina Independiente de Verificación Polinomial
> **[TODO EDUARDO]:**
> * Describir la lógica de VerificadorIndependiente.java.
> * Explicar cómo valida que |S| <= k en O(1) y cómo recorre todas las aristas en O(|E|).
> * Demostrar que el verificador opera de manera completamente desacoplada del algoritmo de búsqueda.
> * Concluir formalmente por qué la complejidad de verificación es O(|V| + |E|), demostrando la pertenencia a la clase NP.

### 3.3. Menú por Consola en Main.java y Validación Rigurosa
> **[TODO EDUARDO / EQUIPO]:**
> * Describir las opciones del menú por consola ubicado en Main.java (ingreso manual, generación aleatoria, casos predeterminados).
> * Detallar los mecanismos de validación contra errores numéricos (NumberFormatException, nodos fuera de rango, bucles).
> * Explicar el control y mensaje amigable ante instancias sin solución (cuando k es insuficiente).

### 3.4. Suite de Pruebas Funcionales y Casos Límite
> **[TODO EDUARDO]:**
> * Presentar la tabla con las 5 pruebas funcionales ejecutadas:
>   1. Caso límite: Grafo sin aristas (|V|=4, |E|=0, k=0).
>   2. Grafo estrella (centro cubre todo con k=1).
>   3. Grafo ciclo impar C_5 (k=2 responde NO, k=3 responde SÍ).
>   4. Caso límite: Grafo completo K_4 (requiere n-1 = 3 sensores).
>   5. Control de certificado inválido / defectuoso.
> * Reportar el porcentaje de éxito (100%) y capturas de consola.

---

## 4. MODIFICACIÓN OBLIGATORIA, BENCHMARKING Y GRÁFICAS
**Responsable de Redacción:** Melany (Persona 3)

### 4.1. Modificación Obligatoria: Adición de un Enlace de Red
> **[TODO MELANY]:**
> * Explicar la funcionalidad desarrollada en ModificadorInstanciaRed.java: agregar un enlace entre dos nodos no conectados y comparar k* antes y después.
> * Presentar la tabla comparativa de instancias pequeñas mostrando el enlace agregado, k* original, k* modificado y Delta k*.
> * Explicar teóricamente por qué Delta k* solo puede tomar valores en {0, 1}.

### 4.2. Protocolo Riguroso de Benchmarking
> **[TODO MELANY]:**
> * Detallar las especificaciones de la máquina donde se ejecutó la batería (Procesador, Arquitectura, Memoria RAM, Versión de Java y SO).
> * Explicar cómo se aisló la medición con System.nanoTime() para no incluir I/O ni impresiones.
> * Describir el uso de 5 repeticiones por cada configuración y el cálculo de la MEDIANA para aislar outliers del recolector de basura (GC) y optimizador JIT.

### 4.3. Tabla de Resultados Experimentales
> **[TODO MELANY]:**
> * Insertar la tabla de resultados obtenida de los 5 tamaños evaluados (|V| = 4, 6, 8, 10, 12), incluyendo:
>   - Tamaño (|V| y |E|)
>   - Cobertura mínima k*
>   - Tiempo mediano (en ns y ms)
>   - Memoria estimada (en KB)
>   - Certificado óptimo S*

### 4.4. Análisis de las Dos Gráficas Obligatorias
> **[TODO MELANY]:**
> * **Gráfica 1 (Tamaño de Entrada vs. Tiempo):** Insertar la imagen y explicar la tendencia de crecimiento frente al número de vértices.
> * **Gráfica 2 (Comparación Antes vs. Después del Cambio Obligatorio):** Insertar la imagen de barras comparando coberturas mínimas y tiempos de cómputo.

---

## 5. DEMOSTRACIÓN DE CORRECTITUD Y COMPLEJIDAD TEÓRICA
**Responsable de Redacción:** Carolay (Persona 4)

### 5.1. Demostración Formal de Correctitud
> **[TODO CAROLAY]:**
> * Redactar la demostración inductiva sobre el parámetro k:
>   - Caso Base (k = 0): Correctitud cuando E = vacio y cuando E != vacio.
>   - Paso Inductivo (k > 0): Justificar por qué la partición en dos ramas generada por una arista (u, v) no cubierta es exhaustiva y no omite ninguna solución válida.

### 5.2. Análisis Fundamentado de Complejidad Temporal T(n)
> **[TODO CAROLAY]:**
> * Desarrollar la deducción matemática del peor caso:
>   - Número máximo de nodos en el árbol binario de búsqueda: sum_{i=0}^k 2^i = O(2^k).
>   - Costo de filtrado de aristas en cada nivel: O(|E|).
>   - Cota superior total: T(n) = O(2^k * |E|).
> * Evitar limitarse a etiquetas Big O: contrastar la diferencia entre algoritmo e instancia (árbol podado vs árbol completo).

### 5.3. Análisis Fundamentado de Complejidad Espacial S(n)
> **[TODO CAROLAY]:**
> * Analizar la memoria ocupada por la pila de llamadas recursivas: profundidad máxima acotada por k <= |V|.
> * Memoria por marco de activación: O(|E|) para la lista de aristas residuales.
> * Demostrar que el consumo espacial es estrictamente polinomial: S(n) = O(k * |E|).

---

## 6. RESPUESTAS OBLIGATORIAS A LAS PREGUNTAS DE ANÁLISIS
**Responsable de Redacción:** Carolay (Persona 4)

### Pregunta 1: ¿Cómo se relaciona el crecimiento observado con T(n) y cuáles son las limitaciones de la medición?
> **[TODO CAROLAY]:**
> * Contrastar la curva teórica O(2^k * |E|) frente a los tiempos de milisegundos observados en los experimentos de Melany.
> * Explicar las limitaciones físicas: sobrecarga del JIT en entradas pequeñas y horizonte intratable para k grande.

### Pregunta 2: ¿Qué diferencia existe entre encontrar una solución y verificar el certificado correspondiente?
> **[TODO CAROLAY]:**
> * Argumentar la asimetría fundamental entre la clase P (verificación lineal O(|V| + |E|)) y los problemas NP-difíciles (búsqueda en espacio combinatorial exponencial).

### Pregunta 3: ¿Qué cambió en el resultado o en el costo al modificar la instancia y por qué?
> **[TODO CAROLAY]:**
> * Explicar las condiciones geométricas del grafo para que Delta k* sea 0 (cuando un nodo ya cubierto absorbe el enlace) o +1 (cuando une dos nodos desprotegidos).
> * Analizar el efecto restrictivo de aristas adicionales en la poda del árbol.

---

## 7. USO CRÍTICO DE NOTEBOOKLM Y HONESTIDAD ACADÉMICA
**Responsable de Redacción:** Todo el Equipo (Consolidado por Carolay)

### 7.1. Registro de Preguntas y Contraste Bibliográfico
> **[TODO EQUIPO]:**
> * Registrar al menos 5 preguntas conceptuales formuladas en el cuaderno de NotebookLM compartido por el docente.
> * Colocar para cada una la respuesta obtenida y el contraste con fuentes académicas (Cormen, Karp, Garey & Johnson).

### 7.2. Reflexión Grupal (150 a 200 palabras)
> **[TODO EQUIPO]:**
> * Redactar una reflexión crítica de autoría grupal (estrictamente entre 150 y 200 palabras) sobre:
>   - El rol de la IA generativa como apoyo conceptual frente a la autoría y razonamiento del código propio.
>   - El aprendizaje obtenido sobre complejidad computacional y verificación polinomial.

---

## 8. CONCLUSIONES Y RECOMENDACIONES
**Responsables:** Los 4 Integrantes

> **[TODO EQUIPO]:**
> * Redactar 4 conclusiones técnicas fundamentadas (una enfocada en modelado por Karina, una en verificación y pruebas por Eduardo, una en benchmarking por Melany, y una en complejidad teórica por Carolay).
> * Redactar 2 recomendaciones de diseño e investigación futura.

---

## 9. REFERENCIAS BIBLIOGRÁFICAS
> **[TODO EQUIPO]:**
> * Incluir las referencias en formato IEEE / APA:
>   1. Cormen, T. H. et al. (2022). Introduction to Algorithms. MIT Press.
>   2. Karp, R. M. (1972). Reducibility among Combinatorial Problems.
>   3. Cygan, M. et al. (2015). Parameterized Algorithms. Springer.
>   4. Documentación oficial de Java SE (Oracle) sobre System.nanoTime().
