# INFORME TECNICO: PROYECTO INTEGRADOR EDA II
## PLANTILLA DE TRABAJO Y GUIA DE COMPLETACION — EQUIPO 4

**Escuela Politecnica Nacional — Facultad de Ingenieria de Sistemas**  
**Asignatura:** Estructura de Datos y Algoritmos II (ICCD442) — Periodo 2026-B  
**Proyecto:** Unidad 2 - Complejidad Computacional  
**Tema Asignado:** Vertex Cover (Ubicacion de Sensores sobre Enlaces de Red)  

### DATOS GENERALES DEL EQUIPO Y ENTREGA

| Integrante | Rol Asignado | Paquete / Archivo Java | Bloque en Defensa Oral |
|---|---|---|:---:|
| **Karina (Persona 1)** | Modelado formal, pseudocodigo y algoritmo exacto | `src/karina/AlgoritmoExactoVertexCover.java` | Bloque 2 |
| **Eduardo (Persona 2)** | Verificador independiente, menu en terminal y pruebas | `src/eduardo/VerificadorIndependiente.java` | Bloque 3 |
| **Melany (Persona 3)** | Modificacion obligatoria (+1 enlace), benchmarking y graficas | `src/melany/ModificadorInstanciaRed.java` | Bloque 4 |
| **Carolay (Persona 4)** | Correctitud, complejidad teorica, preguntas y consolidacion | `src/carolay/AnalisisComplejidad.java` | Bloque 1 |

* **Fecha de entrega:** Miercoles, 18 de noviembre de 2026 (23:59)  
* **Fecha de defensa:** Jueves, 19 de noviembre de 2026  
* **Repositorio de codigo en GitHub:** https://github.com/Edhuar29/Grupo4_Proyecto_EDA2_VertexCover  

## RESUMEN EJECUTIVO (ABSTRACT)

**Responsable:** Carolay (Persona 4)

> **[ESPACIO PARA REDACTAR POR CAROLAY]:**  
> Redactar aqui una sintesis formal (entre 150 y 250 palabras) que resuma:  
> 1. El contexto de telecomunicaciones y el problema de auditoria de enlaces con sensores.  
> 2. La formulacion matematica como Vertex Cover (NP-completo).  
> 3. El algoritmo exacto implementado en Java y el verificador independiente en tiempo lineal.  
> 4. Los resultados experimentales mas destacados del benchmarking y de la modificacion obligatoria (+1 enlace).

## 1. CONTEXTO DEL PROBLEMA Y PREGUNTA DE DECISION

**Responsable:** Carolay (Persona 4)

### 1.1. Contexto del Caso Alternativo Asignado (Redes y Sensores)
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Describir la infraestructura de red como un grafo G = (V, E): routers como nodos y enlaces de fibra/cobre como aristas.  
> * Explicar el principio del sensor: un sensor en el router u inspecciona todos los enlaces incidentes a u.  
> * Justificar por que es necesario minimizar los sensores k (costos de licencias, consumo electrico y mantenimiento).

### 1.2. Pregunta Formal de Decision
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Formular formalmente la pregunta de decision para una red G y un presupuesto entero k >= 0.  
> * Definir el dominio de las variables de entrada y el conjunto de salida esperado: {SI, NO}.

### 1.3. Ejemplo Pequeño Ilustrativo
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Dibujar o insertar un grafo de ejemplo de 5 nodos (ej. ciclo C_5 o una red de anillo).  
> * **Caso NO:** Explicar por que con k = 2 no alcanza a cubrir la red, señalando que enlace queda desprotegido.  
> * **Caso SI:** Mostrar la solucion con k = 3, indicando el conjunto testigo explicito S = {nodos seleccionados}.

### 1.4. Prediccion Inicial de Dificultad
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Explicar por que Vertex Cover pertenece a la clase NP-completo (citar el articulo de Richard Karp de 1972).  
> * Explicar por que una busqueda ciega por fuerza bruta C(n, k) se vuelve intratable al crecer n.  
> * Introducir la propiedad FPT (Fixed-Parameter Tractable) parametrizada sobre k.

## 2. MODELADO FORMAL Y ALGORITMO EXACTO

**Responsable:** Karina (Persona 1)

### 2.1. Modelo Matematico Formal
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * **Entradas:** Grafo no dirigido G = (V, E) y presupuesto entero k >= 0.  
> * **Salidas:** Decision {SI, NO} y conjunto testigo S* con cardinalidad minima.  
> * **Restriccion de Cobertura:** Para toda arista (u, v) en E, se debe cumplir (u en S o v en S).  
> * **Restriccion de Presupuesto:** Cardinalidad |S| <= k.

### 2.2. Pseudocodigo del Algoritmo Exacto
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * Escribir aqui el pseudocodigo formal del algoritmo exacto de ramificacion binaria que programaste en `AlgoritmoExactoVertexCover.java`.  
> * Detallar la regla de particion ante una arista no cubierta (u, v), la rama 1 (u en S con k-1), la rama 2 (v en S con k-1) y las condiciones de caso base (exito y poda).

### 2.3. Prueba Manual de Escritorio
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * Insertar una tabla o diagrama con la traza paso a paso ejecutada a mano sobre un grafo de 4 nodos y 4 aristas con k = 2.  
> * Indicar como se evalua cada rama del arbol binario, donde ocurre el retroceso (backtracking) y como se obtiene el testigo final.

### 2.4. Implementacion en Java Estandar
> **[ESPACIO PARA COMPLETAR POR KARINA]:**  
> * Explicar la estructura de la clase `AlgoritmoExactoVertexCover.java` y sus metodos principales (`resolverDecision` y `encontrarCoberturaMinima`).  
> * Resaltar que el codigo es 100% Java estandar y no utiliza librerias externas que resuelvan el problema.

## 3. VERIFICADOR INDEPENDIENTE, ESTRUCTURAS Y PRUEBAS FUNCIONALES

**Responsable:** Eduardo (Persona 2)

### 3.1. Estructuras de Datos Utilizadas
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:**  
> * Justificar la eleccion de listas de adyacencia `List<Integer>[]` y coleccion de aristas inmutables `List<Arista>`.  
> * Explicar la estructura del certificado (`boolean[] estaEnS`) y por que permite consultar pertenencia en tiempo constante O(1).

### 3.2. Rutina Independiente de Verificacion Polinomial
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:**  
> * Explicar el desacoplamiento de `VerificadorIndependiente.java`: no ejecuta busquedas, solo valida el certificado recibido.  
> * Describir la validacion de presupuesto (|S| <= k) en O(1) y la validacion de cobertura de aristas en O(|E|).  
> * Concluir por que la rutina corre en tiempo lineal O(|V| + |E|), demostrando que la verificacion pertenece a la clase P.

### 3.3. Menu por Consola en Main.java y Validacion Rigurosa
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:**  
> * Describir la interaccion por terminal desarrollada en `Main.java` con `Scanner` y el menu de 10 opciones.  
> * Explicar el manejo de errores (nodos fuera de rango, formato numerico) y el mensaje al usuario cuando una instancia no tiene solucion.

### 3.4. Suite de Pruebas Funcionales y Casos Limite

**Responsable:** Eduardo (Persona 2)

A continuacion, documentar la ejecucion de los 5 casos de prueba obligatorios de `SuitePruebasFuncionales.java`. En cada caso se debe registrar la salida real de la terminal de VS Code:

#### Caso de Prueba 1: Grafo Vacio (Caso Limite)
* **Topologia:** Red de 4 nodos aislados sin ningun enlace (V = 4, E = 0). Presupuesto: k = 0.
* **Resultado Teorico Esperado:** SI (true), con certificado vacio S = [ ].
* **Salida de Consola / Captura:**  
  *[Pegar aqui la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO

#### Caso de Prueba 2: Grafo Estrella K_(1,4)
* **Topologia:** Nodo central 0 conectado a 4 nodos perifericos (V = 5, E = 4). Presupuesto: k = 1.
* **Resultado Teorico Esperado:** SI (true), con sensor unico en el nodo central S = [0].
* **Salida de Consola / Captura:**  
  *[Pegar aqui la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO

#### Caso de Prueba 3: Grafo Ciclo Impar C_5
* **Topologia:** Anillo cerrado de 5 routers (V = 5, E = 5).
* **Resultado Teorico Esperado:** Con k = 2 responde NO (false). Con k = 3 responde SI (true) con S = [0, 2, 3].
* **Salida de Consola / Captura:**  
  *[Pegar aqui la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO

#### Caso de Prueba 4: Grafo Completo K_4 (Caso Limite)
* **Topologia:** 4 routers interconectados todos entre si con 6 enlaces (V = 4, E = 6).
* **Resultado Teorico Esperado:** Requiere n - 1 = 3 sensores. Con k = 2 responde NO (false) y con k = 3 responde SI (true).
* **Salida de Consola / Captura:**  
  *[Pegar aqui la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO

#### Caso de Prueba 5: Deteccion y Rechazo Antifraude
* **Topologia:** Red de 3 nodos (0-1 y 1-2). Presupuesto k = 1. Certificado enviado: S = [0].
* **Resultado Teorico Esperado:** El verificador debe rechazarlo (INVALIDO / false) e identificar que el enlace (1, 2) quedo desprotegido.
* **Salida de Consola / Captura:**  
  *[Pegar aqui la salida de la terminal de VS Code]*
* **Veredicto:** APROBADO

## 4. MODIFICACION OBLIGATORIA, BENCHMARKING Y GRAFICAS

**Responsable:** Melany (Persona 3)

### 4.1. Modificacion Obligatoria: Adicion de un Enlace de Red
> **[ESPACIO PARA COMPLETAR POR MELANY]:**  
> * Explicar la logica programada en `ModificadorInstanciaRed.java` para conectar dos nodos que no tenian arista.  
> * Explicar teoricamente por que la variacion de cobertura Delta k* = k*mod - k*orig solo puede ser 0 o 1.  
> * Presentar una tabla comparativa con 3 redes pequeñas evaluando k* antes y despues del enlace adicional.

### 4.2. Protocolo Riguroso de Benchmarking
> **[ESPACIO PARA COMPLETAR POR MELANY]:**  
> * Documentar las caracteristicas de tu equipo: Procesador, Memoria RAM, Sistema Operativo y version de Java (JDK).  
> * Explicar como aislaste la medicion con `System.nanoTime()` sin incluir lecturas de teclado ni impresiones por pantalla.  
> * Explicar la realizacion de 5 repeticiones por cada tamaño y por que se toma la **MEDIANA** para descartar el ruido del Garbage Collector.

### 4.3. Tabla Oficial de Resultados Experimentales

**Responsable:** Melany (Persona 3)

Llenar los valores medidos en la bateria de pruebas (`bateria_benchmarking.csv`) sobre los 5 tamaños de red evaluados:

| N° Prueba | Entrada / Tamaño de Red | Cobertura Optima k* | Tiempo Mediano (ms) | Memoria Estimada (KB) | Certificado Testigo S* |
|:---:|---|:---:|:---:|:---:|---|
| **1** | V = 4 nodos, E = 3 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **2** | V = 6 nodos, E = 5 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **3** | V = 8 nodos, E = 8 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **4** | V = 10 nodos, E = 12 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |
| **5** | V = 12 nodos, E = 16 enlaces | k* = [Llenar] | [Llenar ms] | [Llenar KB] | S* = [Nodos elegidos] |

### 4.4. Analisis de las Dos Graficas Obligatorias
> **[ESPACIO PARA COMPLETAR POR MELANY]:**  
> * **Grafica 1 (Tamaño de Entrada frente a Tiempo):** Pegar aqui la imagen de la curva de tiempo vs. numero de nodos |V| e interpretar su comportamiento.  
> * **Grafica 2 (Comparacion Antes vs. Despues del Enlace Adicional):** Pegar aqui el grafico de barras comparando k* y tiempos antes y despues del cambio obligatorio e interpretar el resultado.

## 5. DEMOSTRACION DE CORRECTITUD Y COMPLEJIDAD TEORICA

**Responsable:** Carolay (Persona 4)

### 5.1. Demostracion Formal de Correctitud por Induccion Matematica
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Enunciar formalmente la hipotesis inductiva P(k) sobre el parametro de presupuesto k.  
> * Desarrollar el **Caso Base (k = 0)**: demostrar que ocurre cuando |E| = 0 y cuando |E| > 0.  
> * Desarrollar el **Paso Inductivo (k > 0)**: justificar por que una arista no cubierta (u, v) divide exhaustivamente el espacio en las ramas u y v con presupuesto k-1 sin perder ninguna solucion valida.

### 5.2. Deduccion Fundamentada de la Complejidad Temporal T(n)
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Calcular el numero maximo de nodos en el arbol binario de busqueda: sum_(i=0 a k) 2^i = 2^(k+1) - 1 = O(2^k).  
> * Multiplicar por el costo de filtrado de aristas por nivel: O(|E|).  
> * Obtener la cota superior formal: T(n) = O(2^k * |E|).  
> * Diferenciar entre algoritmo e instancia: explicar como los nodos de alto grado generan podas que hacen que en la practica se exploren muchos menos nodos.

### 5.3. Deduccion Fundamentada de la Complejidad Espacial S(n)
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Analizar la profundidad de la pila de recursion (acotada por k <= |V|).  
> * Demostrar que el consumo de memoria es estrictamente polinomial: S(n) = O(k * |E|) <= O(|V| * |E|), garantizando que el programa no sufra de desbordamiento de memoria (`OutOfMemoryError`).

## 6. RESPUESTAS OBLIGATORIAS A LAS PREGUNTAS DE ANALISIS

**Responsable:** Carolay (Persona 4)

### Pregunta 1: ¿Como se relaciona el crecimiento observado con T(n) y cuales son las limitaciones de la medicion?
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Contrastar la curva teorica O(2^k * |E|) con los tiempos reales obtenidos por Melany en la tabla del benchmarking.  
> * Explicar las limitaciones de medir con `System.nanoTime()` en instancias muy pequeñas (optimizacion JIT, resolucion del reloj del SO).

### Pregunta 2: ¿Que diferencia existe entre encontrar una solucion y verificar el certificado correspondiente?
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Explicar la asimetria computacional entre la dificultad de busqueda (problema NP-dificil en tiempo exponencial) y la facilidad de verificacion (algoritmo en tiempo polinomial lineal O(|V| + |E|)).

### Pregunta 3: ¿Que cambio en el resultado o en el costo al modificar la instancia y por que?
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:**  
> * Explicar bajo que condiciones geometricas del grafo k* no cambia (Delta k* = 0) y cuando se incrementa en uno (Delta k* = 1).  
> * Analizar el efecto de la nueva arista sobre la tasa de poda en el arbol de busqueda.

## 7. USO CRITICO DE NOTEBOOKLM Y HONESTIDAD ACADEMICA

**Responsable:** Todo el Equipo (Consolidado por Carolay)

### 7.1. Registro de Preguntas y Contraste Bibliografico
> **[ESPACIO PARA COMPLETAR POR EL EQUIPO]:**  
> Registrar en la tabla al menos 5 preguntas conceptuales realizadas en el cuaderno compartido de NotebookLM, la sintesis de la respuesta y la fuente academica (Cormen, Karp, etc.) con la que se contrasto:

| N° | Pregunta Conceptual en NotebookLM | Sintesis de la Respuesta | Fuente Academica de Contraste |
|:---:|---|---|---|
| **1** | *[Pregunta 1]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **2** | *[Pregunta 2]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **3** | *[Pregunta 3]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **4** | *[Pregunta 4]* | *[Respuesta]* | *[Libro / Paper de referencia]* |
| **5** | *[Pregunta 5]* | *[Respuesta]* | *[Libro / Paper de referencia]* |

### 7.2. Reflexion Grupal sobre Honestidad Academica (150 a 200 palabras)
> **[ESPACIO PARA REDACTAR POR EL EQUIPO]:**  
> Redactar aqui una reflexion etica de autoria grupal (estrictamente entre 150 y 200 palabras) sobre el rol de la IA generativa como asistente conceptual frente a la autoria real y dominio del codigo implementado en Java.

## 8. CONCLUSIONES Y RECOMENDACIONES

**Responsable:** Todo el Equipo (Una conclusion por integrante)

> **[ESPACIO PARA COMPLETAR POR KARINA]:** Conclusion tecnica enfocada en el modelado formal y el algoritmo exacto.  
> **[ESPACIO PARA COMPLETAR POR EDUARDO]:** Conclusion tecnica enfocada en el verificador en tiempo lineal y las pruebas.  
> **[ESPACIO PARA COMPLETAR POR MELANY]:** Conclusion tecnica enfocada en el benchmarking y la variacion del nuevo enlace.  
> **[ESPACIO PARA COMPLETAR POR CAROLAY]:** Conclusion tecnica enfocada en la complejidad teorica y la correctitud.  
> **[ESPACIO PARA COMPLETAR POR EL EQUIPO]:** 2 recomendaciones para trabajos futuros o extensiones del problema.

## 9. REFERENCIAS BIBLIOGRAFICAS (FORMATO IEEE)

> **[ESPACIO PARA COMPLETAR POR EL EQUIPO]:**  
> Incluir las referencias bibliograficas en formato estandar IEEE (Cormen, Karp, documentacion de Java, etc.).
