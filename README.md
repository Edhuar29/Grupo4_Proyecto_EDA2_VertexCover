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
  * Modularidad, README y empaquetado del ZIP ejecutable.

* **Menú por Consola:** Integrado directamente en [`Main.java`](src/Main.java) para orquestar y llamar a todos los módulos.
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
Desde la raíz del proyecto:

1. **Compilar:**
```bash
mkdir -p bin
javac -d bin -sourcepath src src/*.java src/*/*.java
```

2. **Ejecutar el Orquestador:**
```bash
java -cp bin Main
```
O simplemente presione `F5` en VS Code para ejecutar la configuración preconfigurada en `.vscode/launch.json`.
