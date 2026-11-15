# INFORME TÉCNICO: PROYECTO INTEGRADOR EDA II
## PLANTILLA DE TRABAJO Y GUÍA DE COMPLETACIÓN — EQUIPO 4

**Escuela Politécnica Nacional — Facultad de Ingeniería de Sistemas**  
**Asignatura:** Estructura de Datos y Algoritmos II (ICCD442) — Periodo 2026-B  
**Proyecto:** Unidad 2 - Complejidad Computacional  
**Tema Asignado:** Vertex Cover (Ubicación de Sensores sobre Enlaces de Red)  

---

### DATOS GENERALES DEL EQUIPO Y ENTREGA

| Integrante | Rol Asignado | Paquete / Archivo Java | Bloque en Defensa Oral |
|---|---|---|:---:|
| **Karina (Persona 1)** | Modelado formal, pseudocódigo y algoritmo exacto | `src/karina/AlgoritmoExactoVertexCover.java` | Bloque 2 |
| **Eduardo (Persona 2)** | Verificador independiente, menú en terminal y pruebas | `src/eduardo/VerificadorIndependiente.java` | Bloque 3 |
| **Melany (Persona 3)** | Modificación obligatoria (+1 enlace), benchmarking y gráficas | `src/melany/ModificadorInstanciaRed.java` | Bloque 4 |
| **Carolay (Persona 4)** | Correctitud, complejidad teórica, preguntas y consolidación | `src/carolay/AnalisisComplejidad.java` | Bloque 1 |

* **Fecha de entrega:** Miércoles, 18 de noviembre de 2026 (23:59)  
* **Fecha de defensa:** Jueves, 19 de noviembre de 2026  
* **Repositorio de código en GitHub:** https://github.com/Edhuar29/Grupo4_Proyecto_EDA2_VertexCover  

---

## RESUMEN EJECUTIVO (ABSTRACT)

**Responsable:** Carolay (Persona 4)

> **[ESPACIO PARA REDACTAR POR CAROLAY]:**  
> Redactar aquí una síntesis formal (entre 150 y 250 palabras) que resuma:  
> 1. El contexto de telecomunicaciones y el problema de auditoría de enlaces con sensores.  
> 2. La formulación matemática como Vertex Cover (NP-completo).  
> 3. El algoritmo exacto implementado en Java y el verificador independiente en tiempo lineal.  
> 4. Los resultados experimentales más destacados del benchmarking y de la modificación obligatoria (+1 enlace).

---

## 1. CONTEXTO DEL PROBLEMA Y PREGUNTA DE DECISIÓN

**Responsable:** Carolay (Persona 4)

### 1.1. Contexto del Caso Alternativo Asignado (Redes y Sensores)
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Describir la infraestructura de red como un grafo G = (V, E): routers como nodos y enlaces de fibra/cobre como aristas.  
> * Explicar el principio del sensor: un sensor en el router u inspecciona todos los enlaces incidentes a u.  
> * Justificar por qué es necesario minimizar los sensores k (costos de licencias, consumo eléctrico y mantenimiento).

### 1.2. Pregunta Formal de Decisión
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Formular formalmente la pregunta de decisión para una red G y un presupuesto entero k >= 0.  
> * Definir el dominio de las variables de entrada y el conjunto de salida esperado: {SÍ, NO}.

### 1.3. Ejemplo Pequeño Ilustrativo
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Dibujar o insertar un grafo de ejemplo de 5 nodos (ej. ciclo C_5 o una red de anillo).  
> * **Caso NO:** Explicar por qué con k = 2 no alcanza a cubrir la red, señalando qué enlace queda desprotegido.  
> * **Caso SÍ:** Mostrar la solución con k = 3, indicando el conjunto testigo explícito S = {nodos seleccionados}.

### 1.4. Predicción Inicial de Dificultad
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Explicar por qué Vertex Cover pertenece a la clase NP-completo (citar el artículo de Richard Karp de 1972).  
> * Explicar por qué una búsqueda ciega por fuerza bruta C(n, k) se vuelve intratable al crecer n.  
> * Introducir la propiedad FPT (Fixed-Parameter Tractable) parametrizada sobre k.

---

## 2. MODELADO FORMAL Y ALGORITMO EXACTO

**Responsable:** Karina (Persona 1)

### 2.1. Modelo Matemático Formal
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * **Entradas:** Grafo no dirigido G = (V, E) y presupuesto entero k >= 0.  
> * **Salidas:** Decisión {SÍ, NO} y conjunto testigo S* con cardinalidad mínima.  
> * **Restricción de Cobertura:** Para toda arista (u, v) en E, se debe cumplir (u en S o v en S).  
> * **Restricción de Presupuesto:** Cardinalidad |S| <= k.

### 2.2. Pseudocódigo del Algoritmo Exacto
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * Escribir aquí el pseudocódigo formal del algoritmo exacto de ramificación binaria que programaste en `AlgoritmoExactoVertexCover.java`.  
> * Detallar la regla de partición ante una arista no cubierta (u, v), la rama 1 (u en S con k-1), la rama 2 (v en S con k-1) y las condiciones de caso base (éxito y poda).

### 2.3. Prueba Manual de Escritorio
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * Insertar una tabla o diagrama con la traza paso a paso ejecutada a mano sobre un grafo de 4 nodos y 4 aristas con k = 2.  
> * Indicar cómo se evalúa cada rama del árbol binario, dónde ocurre el retroceso (backtracking) y cómo se obtiene el testigo final.

### 2.4. Implementación en Java Estándar
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * Explicar la estructura de la clase `AlgoritmoExactoVertexCover.java` y sus métodos principales (`resolverDecision` y `encontrarCoberturaMinima`).  
> * Resaltar que el código es 100% Java estándar y no utiliza librerías externas que resuelvan el problema.

---

## 3. VERIFICADOR INDEPENDIENTE, ESTRUCTURAS Y PRUEBAS FUNCIONALES

**Responsable:** Eduardo (Persona 2)

### 3.1. Estructuras de Datos Utilizadas
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:**  
> * Justificar la elección de listas de adyacencia `List<Integer>[]` y colección de aristas inmutables `List<Arista>`.  
> * Explicar la estructura del certificado (`boolean[] estaEnS`) y por qué permite consultar pertenencia en tiempo constante O(1).

### 3.2. Rutina Independiente de Verificación Polinomial
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:**  
> * Explicar el desacoplamiento de `VerificadorIndependiente.java`: no ejecuta búsquedas, solo valida el certificado recibido.  
> * Describir la validación de presupuesto (|S| <= k) en O(1) y la validación de cobertura de aristas en O(|E|).  
> * Concluir por qué la rutina corre en tiempo lineal O(|V| + |E|), demostrando que la verificación pertenece a la clase P.

### 3.3. Menú por Consola en Main.java y Validación Rigurosa
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:**  
> * Describir la interacción por terminal desarrollada en `Main.java` con `Scanner` y el menú de 10 opciones.  
> * Explicar el manejo de errores (nodos fuera de rango, formato numérico) y el mensaje al usuario cuando una instancia no tiene solución.

### 3.4. Suite de Pruebas Funcionales y Casos Límite

**Responsable:** Eduardo (Persona 2)

A continuación, documentar la ejecución de los 5 casos de prueba obligatorios de `SuitePruebasFuncionales.java`. En cada caso se debe registrar la salida real de la terminal de VS Code:

#### Caso de Prueba 1: Grafo Vacío (Caso Límite)
* **Topología:** Red de 4 nodos aislados sin ningún enlace (V = 4, E = 0). Presupuesto: k = 0.
* **Resultado Teórico Esperado:** SÍ (true), con certificado vacío S = [ ].
* **Salida de Consola / Captura:**  
  *[Pegar aquí la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO ✅

#### Caso de Prueba 2: Grafo Estrella K_(1,4)
* **Topología:** Nodo central 0 conectado a 4 nodos periféricos (V = 5, E = 4). Presupuesto: k = 1.
* **Resultado Teórico Esperado:** SÍ (true), con sensor único en el nodo central S = [0].
* **Salida de Consola / Captura:**  
  *[Pegar aquí la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO ✅

#### Caso de Prueba 3: Grafo Ciclo Impar C_5
* **Topología:** Anillo cerrado de 5 routers (V = 5, E = 5).
* **Resultado Teórico Esperado:** Con k = 2 responde NO (false). Con k = 3 responde SÍ (true) con S = [0, 2, 3].
* **Salida de Consola / Captura:**  
  *[Pegar aquí la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO ✅

#### Caso de Prueba 4: Grafo Completo K_4 (Caso Límite)
* **Topología:** 4 routers interconectados todos entre sí con 6 enlaces (V = 4, E = 6).
* **Resultado Teórico Esperado:** Requiere n - 1 = 3 sensores. Con k = 2 responde NO (false) y con k = 3 responde SÍ (true).
* **Salida de Consola / Captura:**  
  *[Pegar aquí la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO ✅

#### Caso de Prueba 5: Detección y Rechazo Antifraude
* **Topología:** Red de 3 nodos (0-1 y 1-2). Presupuesto k = 1. Certificado enviado: S = [0].
* **Resultado Teórico Esperado:** El verificador debe rechazarlo (INVÁLIDO / false) e identificar que el enlace (1, 2) quedó desprotegido.
* **Salida de Consola / Captura:**  
  *[Pegar aquí la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO ✅

---

## 4. MODIFICACIÓN OBLIGATORIA, BENCHMARKING Y GRÁFICAS

**Responsable:** Melany (Persona 3)

### 4.1. Modificación Obligatoria: Adición de un Enlace de Red
> **[ESPACIO PARA COMPLETAR POR MELANY]:**  
> * Explicar la lógica programada en `ModificadorInstanciaRed.java` para conectar dos nodos que no tenían arista.  
> * Explicar teóricamente por qué la variación de cobertura Delta k* = k*mod - k*orig solo puede ser 0 o 1.  
> * Presentar una tabla comparativa con 3 redes pequeñas evaluando k* antes y después del enlace adicional.

### 4.2. Protocolo Riguroso de Benchmarking
> **[ESPACIO PARA COMPLETAR POR MELANY]:**  
> * Documentar las características de tu equipo: Procesador, Memoria RAM, Sistema Operativo y versión de Java (JDK).  
> * Explicar cómo aislaste la medición con `System.nanoTime()` sin incluir lecturas de teclado ni impresiones por pantalla.  
> * Explicar la realización de 5 repeticiones por cada tamaño y por qué se toma la **MEDIANA** para descartar el ruido del Garbage Collector.

### 4.3. Tabla Oficial de Resultados Experimentales

**Responsable:** Melany (Persona 3)

Llenar los valores medidos en la batería de pruebas (`bateria_benchmarking.csv`) sobre los 5 tamaños de red evaluados:

| N° Prueba | Entrada / Tamaño de Red | Cobertura Óptima k* | Tiempo Mediano (ms) | Memoria Estimada (KB) | Certificado Testigo S* |
|:---:|---|:---:|:---:|:---:|---|
| **1** | V = 4 nodos, E = 3 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **2** | V = 6 nodos, E = 5 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **3** | V = 8 nodos, E = 8 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **4** | V = 10 nodos, E = 12 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **5** | V = 12 nodos, E = 16 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |

### 4.4. Análisis de las Dos Gráficas Obligatorias
> **[ESPACIO PARA COMPLETAR POR MELANY]:**  
> * **Gráfica 1 (Tamaño de Entrada frente a Tiempo):** Pegar aquí la imagen de la curva de tiempo vs. número de nodos |V| e interpretar su comportamiento.  
> * **Gráfica 2 (Comparación Antes vs. Después del Enlace Adicional):** Pegar aquí el gráfico de barras comparando k* y tiempos antes y después del cambio obligatorio e interpretar el resultado.

---

## 5. DEMOSTRACIÓN DE CORRECTITUD Y COMPLEJIDAD TEÓRICA

**Responsable:** Carolay (Persona 4)

### 5.1. Demostración Formal de Correctitud por Inducción Matemática
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Enunciar formalmente la hipótesis inductiva P(k) sobre el parámetro de presupuesto k.  
> * Desarrollar el **Caso Base (k = 0)**: demostrar qué ocurre cuando |E| = 0 y cuando |E| > 0.  
> * Desarrollar el **Paso Inductivo (k > 0)**: justificar por qué una arista no cubierta (u, v) divide exhaustivamente el espacio en las ramas u y v con presupuesto k-1 sin perder ninguna solución válida.

### 5.2. Deducción Fundamentada de la Complejidad Temporal T(n)
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Calcular el número máximo de nodos en el árbol binario de búsqueda: sum_(i=0 a k) 2^i = 2^(k+1) - 1 = O(2^k).  
> * Multiplicar por el costo de filtrado de aristas por nivel: O(|E|).  
> * Obtener la cota superior formal: T(n) = O(2^k * |E|).  
> * Diferenciar entre algoritmo e instancia: explicar cómo los nodos de alto grado generan podas que hacen que en la práctica se exploren muchos menos nodos.

### 5.3. Deducción Fundamentada de la Complejidad Espacial S(n)
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Analizar la profundidad de la pila de recursión (acotada por k <= |V|).  
> * Demostrar que el consumo de memoria es estrictamente polinomial: S(n) = O(k * |E|) <= O(|V| * |E|), garantizando que el programa no sufra de desbordamiento de memoria (`OutOfMemoryError`).

---

## 6. RESPUESTAS OBLIGATORIAS A LAS PREGUNTAS DE ANÁLISIS

**Responsable:** Carolay (Persona 4)

### Pregunta 1: ¿Cómo se relaciona el crecimiento observado con T(n) y cuáles son las limitaciones de la medición?
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Contrastar la curva teórica O(2^k * |E|) con los tiempos reales obtenidos por Melany en la tabla del benchmarking.  
> * Explicar las limitaciones de medir con `System.nanoTime()` en instancias muy pequeñas (optimización JIT, resolución del reloj del SO).

### Pregunta 2: ¿Qué diferencia existe entre encontrar una solución y verificar el certificado correspondiente?
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Explicar la asimetría computacional entre la dificultad de búsqueda (problema NP-difícil en tiempo exponencial) y la facilidad de verificación (algoritmo en tiempo polinomial lineal O(|V| + |E|)).

### Pregunta 3: ¿Qué cambió en el resultado o en el costo al modificar la instancia y por qué?
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Explicar bajo qué condiciones geométricas del grafo k* no cambia (Delta k* = 0) y cuándo se incrementa en uno (Delta k* = 1).  
> * Analizar el efecto de la nueva arista sobre la tasa de poda en el árbol de búsqueda.

---

## 7. USO CRÍTICO DE NOTEBOOKLM Y HONESTIDAD ACADÉMICA

**Responsable:** Todo el Equipo (Consolidado por Carolay)

### 7.1. Registro de Preguntas y Contraste Bibliográfico
> **[ESPACIO PARA COMPLETAR POR EL EQUIPO]:**  
> Registrar en la tabla al menos 5 preguntas conceptuales realizadas en el cuaderno compartido de NotebookLM, la síntesis de la respuesta y la fuente académica (Cormen, Karp, etc.) con la que se contrastó:

| # | Pregunta Conceptual en NotebookLM | Síntesis de la Respuesta | Fuente Académica de Contraste |
|:---:|---|---|---|
| **1** | *[Pregunta 1]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **2** | *[Pregunta 2]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **3** | *[Pregunta 3]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **4** | *[Pregunta 4]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **5** | *[Pregunta 5]* | *[Respuesta]* | *[Libro / Paper de referencia]* |

### 7.2. Reflexión Grupal sobre Honestidad Académica (150 a 200 palabras)
> **[ESPACIO PARA REDACTAR POR EL EQUIPO]:**  
> Redactar aquí una reflexión ética de autoría grupal (estrictamente entre 150 y 200 palabras) sobre el rol de la IA generativa como asistente conceptual frente a la autoría real y dominio del código implementado en Java.

---

## 8. CONCLUSIONES Y RECOMENDACIONES

**Responsable:** Todo el Equipo (Una conclusión por integrante)

> **[ESPACIO PARA COMPLETAR POR KARINA]:** Conclusión técnica enfocada en el modelado formal y el algoritmo exacto.  
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:** Conclusión técnica enfocada en el verificador en tiempo lineal y las pruebas.  
> **[ESPACIO PARA COMPLETAR POR MELANY]:** Conclusión técnica enfocada en el benchmarking y la variación del nuevo enlace.  
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:** Conclusión técnica enfocada en la complejidad teórica y la correctitud.  
> **[ESPACIO PARA COMPLETAR POR EL EQUIPO]:** 2 recomendaciones para trabajos futuros o extensiones del problema.

---

## 9. REFERENCIAS BIBLIOGRÁFICAS (FORMATO IEEE)

> **[ESPACIO PARA COMPLETAR POR EL EQUIPO]:**  
> Incluir las referencias bibliográficas en formato estándar IEEE (Cormen, Karp, documentación de Java, etc.).
