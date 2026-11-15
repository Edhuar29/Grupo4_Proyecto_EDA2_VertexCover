#!/usr/bin/env python3
"""
Generador automático de la presentación de diapositivas para Canva / PowerPoint
Asignatura: Estructura de Datos y Algoritmos II (Periodo 2026-B) - EPN
Proyecto: Vertex Cover (Ubicación de Sensores sobre Enlaces de Red)
Equipo 4: Karina, Eduardo, Melany, Carolay (14 diapositivas equilibradas)
"""

import os
from pptx import Presentation
from pptx.util import Inches, Pt
from pptx.dml.color import RGBColor
from pptx.enum.text import PP_ALIGN
from pptx.enum.shapes import MSO_SHAPE

def create_deck():
    prs = Presentation()
    # Configurar formato panorámico 16:9
    prs.slide_width = Inches(13.333)
    prs.slide_height = Inches(7.5)

    blank_layout = prs.slide_layouts[6] # Blank slide layout

    # Paleta de colores institucional y moderna
    COLOR_NAVY = RGBColor(13, 59, 102)      # Azul Politécnica (#0D3B66)
    COLOR_ORANGE = RGBColor(231, 111, 81)   # Naranja Sensor / Acento (#E76F51)
    COLOR_AMBER = RGBColor(244, 162, 97)    # Ámbar suave (#F4A261)
    COLOR_BG_CARD = RGBColor(245, 247, 250) # Fondo tarjetas (#F5F7FA)
    COLOR_BORDER = RGBColor(218, 224, 233)  # Borde tarjeta
    COLOR_DARK = RGBColor(33, 37, 41)       # Texto oscuro principal (#212529)
    COLOR_MUTED = RGBColor(108, 117, 125)   # Texto secundario (#6C757D)
    COLOR_WHITE = RGBColor(255, 255, 255)   # Blanco puro

    # 14 Slides Data
    slides_data = [
        # SLIDE 1: Portada
        {
            "is_cover": True,
            "badge": "ESCUELA POLITÉCNICA NACIONAL • FACULTAD DE INGENIERÍA DE SISTEMAS",
            "title": "Optimización de Sensores en Redes mediante Cobertura de Vértices (Vertex Cover)",
            "subtitle": "Proyecto Integrador de Estructura de Datos y Algoritmos II (Periodo 2026-B)",
            "members": [
                ("Karina (Persona 1)", "Modelado Formal, Algoritmo Exacto y Testigo"),
                ("Eduardo (Persona 2)", "Estructuras de Datos, Verificador O(|V|+|E|) y Pruebas en Consola"),
                ("Melany (Persona 3)", "Modificación Obligatoria (+1 Enlace), Benchmarking y Gráficas"),
                ("Carolay (Persona 4)", "Demostración de Correctitud, Complejidad T(n)/S(n) y Consolidación")
            ],
            "notes": "Introducción de la defensa del Equipo 4 ante el tribunal. Explicar brevemente que el proyecto aborda el problema de Vertex Cover aplicado a redes de comunicaciones y presentar a los 4 expositores."
        },

        # SLIDE 2: Karina - Contexto
        {
            "badge": "PERSONA 1: KARINA • PLANTEAMIENTO DEL PROBLEMA",
            "title": "Monitoreo y Auditoría de Tráfico en Redes de Telecomunicaciones",
            "cards": [
                {
                    "title": "Topología y Enlaces Críticos",
                    "bullets": [
                        "La red se modela como un grafo no dirigido G = (V, E).",
                        "Los nodos V representan routers, switches o servidores centrales.",
                        "Las aristas E representan enlaces físicos de fibra óptica o radioenlaces."
                    ]
                },
                {
                    "title": "Principio del Sensor Físico",
                    "bullets": [
                        "Instalar un sensor en el router u inspecciona todo el tráfico incidente a u.",
                        "Un enlace (u, v) queda supervisado si al menos u o v tiene un sensor instalado.",
                        "No es necesario colocar sensores en ambos extremos de la misma línea."
                    ]
                },
                {
                    "title": "Desafío de Optimización",
                    "bullets": [
                        "Presupuesto y consumo de energía limitados: no se puede instrumentar cada nodo.",
                        "Objetivo: Determinar el número mínimo de sensores k que garantiza 100% de cobertura en los enlaces.",
                        "Problema de negocio: Garantizar auditoría de red sin enlaces 'ciegos'."
                    ]
                }
            ],
            "notes": "Karina (1 min): Explicar el caso de uso real de la industria de telecomunicaciones. Por qué colocar sensores cuesta licencias y energía, y por qué modelar como cobertura de vértices resuelve la auditoría total de enlaces."
        },

        # SLIDE 3: Karina - Modelo Matemático
        {
            "badge": "PERSONA 1: KARINA • MODELADO MATEMÁTICO FORMAL",
            "title": "Definición Formal de Entradas, Salidas y Restricciones",
            "cards": [
                {
                    "title": "Entradas del Problema",
                    "bullets": [
                        "Grafo no dirigido simple G = (V, E), donde |V| = n y |E| = m.",
                        "Presupuesto entero no negativo k ∈ ℤ, con k ≥ 0.",
                        "No existen lazos (u, u) ni aristas dirigidas."
                    ]
                },
                {
                    "title": "Pregunta de Decisión y Salidas",
                    "bullets": [
                        "¿Existe un subconjunto S ⊆ V tal que |S| ≤ k y toda arista e ∈ E tenga al menos un extremo en S?",
                        "Salida booleana: SÍ (True) o NO (False).",
                        "Salida de optimización: Testigo o certificado S* que minimiza |S*|."
                    ]
                },
                {
                    "title": "Restricción Inviolable de Cobertura",
                    "bullets": [
                        "Formalmente: ∀ (u, v) ∈ E, (u ∈ S ∨ v ∈ S).",
                        "Condición de cardinalidad: |S| ≤ k.",
                        "Cualquier solución que deje al menos una arista libre es inválida (inviolabilidad de seguridad)."
                    ]
                }
            ],
            "notes": "Karina (1 min): Enfatizar la formulación lógica rigurosa. Explicar la diferencia entre el problema de decisión (SÍ/NO) y el de optimización (encontrar el k mínimo)."
        },

        # SLIDE 4: Karina - Algoritmo Exacto
        {
            "badge": "PERSONA 1: KARINA • ALGORITMO EXACTO",
            "title": "Algoritmo de Ramificación Exacta FPT (Branch & Bound)",
            "cards": [
                {
                    "title": "Regla Estructural de Ramificación",
                    "bullets": [
                        "Sea (u, v) una arista arbitraria no cubierta en el grafo.",
                        "Por definición, para cubrirla se DEBE incluir obligatoriamente u ∈ S o v ∈ S.",
                        "Esto permite ramificar en exactamente DOS caminos exhaustivos."
                    ]
                },
                {
                    "title": "Árbol de Decisión Binario FPT",
                    "bullets": [
                        "Rama Izquierda: Asumir u ∈ S, reducir presupuesto a (k - 1) y filtrar aristas incidentes.",
                        "Rama Derecha: Asumir v ∈ S, reducir presupuesto a (k - 1) y filtrar aristas incidentes.",
                        "Profundidad máxima del árbol acotada estrictamente por k."
                    ]
                },
                {
                    "title": "Extracción del Testigo y Poda",
                    "bullets": [
                        "Caso Éxito: Si todas las aristas quedan cubiertas y k ≥ 0, se retorna SÍ junto con S.",
                        "Caso Poda: Si k = 0 y aún quedan aristas sin cubrir, la rama se descarta inmediatamente.",
                        "Búsqueda del mínimo: Iterar k = 0, 1, 2... hasta encontrar la primera solución."
                    ]
                }
            ],
            "notes": "Karina (1 min): Explicar por qué este algoritmo explora las 2 ramas y cómo garantiza encontrar la solución óptima exacta sin heurísticas incompletas. Dar paso a Eduardo."
        },

        # SLIDE 5: Eduardo - Arquitectura y Estructuras de Datos
        {
            "badge": "PERSONA 2: EDUARDO • INGENIERÍA DE SOFTWARE Y ESTRUCTURAS",
            "title": "Modularidad del Proyecto y Estructuras de Datos en Java",
            "cards": [
                {
                    "title": "Separación por Responsabilidades",
                    "bullets": [
                        "Paquete model/: Clases POJO inmutables Arista, Grafo, InstanciaRed y ResultadoSolucion.",
                        "Paquetes por integrante: ec.edu.epn.eda2.karina, eduardo, melany, carolay.",
                        "Cero librerías externas que resuelvan la lógica: código Java estándar puro."
                    ]
                },
                {
                    "title": "Representación de la Topología",
                    "bullets": [
                        "Grafo implementado mediante Lista de Adyacencia List<Integer>[] para consultas rápidas.",
                        "Colección de aristas inmutables List<Arista> normalizadas (u < v) para evitar duplicados.",
                        "Garantía de idempotencia en operaciones de recorrido."
                    ]
                },
                {
                    "title": "Estructura del Certificado / Testigo",
                    "bullets": [
                        "Representación compacta como Set<Integer> y arreglo booleano boolean[] estaEnS.",
                        "Permite verificar pertenencia en O(1) tiempo constante durante la auditoría.",
                        "Evita consumos innecesarios de memoria en la máquina virtual."
                    ]
                }
            ],
            "notes": "Eduardo (1 min): Explicar cómo se diseñó el código en Java, la separación en paquetes limpios y por qué las estructuras elegidas (listas de adyacencia y boolean[]) optimizan la velocidad."
        },

        # SLIDE 6: Eduardo - Verificador Polinomial
        {
            "badge": "PERSONA 2: EDUARDO • VERIFICACIÓN POLINOMIAL INDEPENDIENTE",
            "title": "Verificador Independiente en Tiempo Polinomial O(|V| + |E|)",
            "cards": [
                {
                    "title": "Principio de Independencia",
                    "bullets": [
                        "El verificador NO busca soluciones ni ejecuta algoritmos de grafos.",
                        "Recibe como entrada únicamente: la instancia (G, k) y el certificado testigo S.",
                        "Emite un veredicto binario con reporte detallado de infracciones si falla."
                    ]
                },
                {
                    "title": "Dos Fases de Validación O(1) + O(|E|)",
                    "bullets": [
                        "Fase 1 (Presupuesto): Comprueba que |S| ≤ k en O(1) operaciones.",
                        "Fase 2 (Cobertura): Construye boolean[] en O(|V|) e itera cada arista (u, v) ∈ E en O(|E|).",
                        "Costo total estrictamente lineal O(|V| + |E|), demostrando pertenencia a la clase NP."
                    ]
                },
                {
                    "title": "Detección Rigurosa de Fraude",
                    "bullets": [
                        "Rechaza certificados que excedan el presupuesto k.",
                        "Identifica la lista exacta de aristas huérfanas si el testigo está incompleto.",
                        "Valida que todos los vértices del testigo existan en el rango [0, n - 1]."
                    ]
                }
            ],
            "notes": "Eduardo (1.5 min): Destacar por qué la verificación independiente es el pilar de la teoría de complejidad NP. Explicar cómo el verificador comprueba la solución en tiempo ultra rápido sin importar cuán difícil haya sido encontrarla."
        },

        # SLIDE 7: Eduardo - Menú de Consola y Pruebas
        {
            "badge": "PERSONA 2: EDUARDO • DEMOSTRACIÓN Y PRUEBAS FUNCIONALES",
            "title": "Menú por Consola (100% Terminal) y Suite de Pruebas Unitarias",
            "cards": [
                {
                    "title": "Menú Interactivo en Main.java",
                    "bullets": [
                        "Operación 100% por consola mediante bucle interactivo con Scanner.",
                        "Opciones para ingreso manual de red, carga de instancias predefinidas y benchmarking.",
                        "Manejo robusto de errores y advertencias de certificados inválidos."
                    ]
                },
                {
                    "title": "Suite de 5 Casos de Prueba Límite",
                    "bullets": [
                        "Caso 1 (Grafo Vacío): |E| = 0, k = 0 → Solución válida S = ∅.",
                        "Caso 2 (Estrella K_1,4): Nodo central de grado 4 → Solución óptima k = 1.",
                        "Caso 3 (Ciclo Impar C_5): k = 2 produce NO; k = 3 produce SÍ.",
                        "Caso 4 (Grafo Completo K_4): Requiere n - 1 = 3 sensores.",
                        "Caso 5 (Control de Fraude): Certificado incompleto rechazado exitosamente."
                    ]
                },
                {
                    "title": "Demostración en Vivo",
                    "bullets": [
                        "Ejecución en vivo por terminal ante el tribunal.",
                        "Validación automática con 100% de pruebas pasando satisfactoriamente.",
                        "Visualización clara del certificado y del tiempo de respuesta."
                    ]
                }
            ],
            "notes": "Eduardo (1.5 min): Mostrar la ejecución del menú en la terminal, correr la suite de pruebas funcionales y evidenciar que el sistema no usa ventanas gráficas sino consola pura como solicitó el docente. Dar paso a Melany."
        },

        # SLIDE 8: Melany - Modificación Obligatoria
        {
            "badge": "PERSONA 3: MELANY • MODIFICACIÓN OBLIGATORIA DE INSTANCIA",
            "title": "Adición de un Enlace Físico (+1 Arista) y Variación de Cobertura",
            "cards": [
                {
                    "title": "Requisito Oficial del Equipo 4",
                    "bullets": [
                        "Asignación: Evaluar el impacto de añadir una nueva conexión física entre dos nodos no adyacentes.",
                        "Instancia base: Red G = (V, E) con cobertura mínima óptima k*.",
                        "Instancia modificada: G' = (V, E ∪ {e_nuevo}) con cobertura k*' ."
                    ]
                },
                {
                    "title": "Teorema de Variación Δk* ∈ {0, 1}",
                    "bullets": [
                        "Caso Δk* = 0 (Sin impacto): Si al menos uno de los extremos del nuevo enlace ya tenía sensor en la solución original, el enlace queda cubierto 'gratis'.",
                        "Caso Δk* = +1 (Aumento unitario): Si ambos extremos carecían de sensor, es obligatorio colocar un nuevo sensor en u o en v para cubrir el nuevo enlace."
                    ]
                },
                {
                    "title": "Impacto en el Árbol de Búsqueda",
                    "bullets": [
                        "Una arista adicional introduce una nueva restricción al problema.",
                        "Aumenta la poda en ramas infactibles, compensando el crecimiento del espacio de búsqueda.",
                        "Confirmación empírica en las 5 redes experimentales evaluadas."
                    ]
                }
            ],
            "notes": "Melany (1 min): Explicar formalmente por qué añadir un enlace nunca puede disminuir k* (monotonicidad) y a lo sumo lo aumenta en 1. Mostrar cómo se implementó la comparación antes/después."
        },

        # SLIDE 9: Melany - Metodología de Benchmarking
        {
            "badge": "PERSONA 3: MELANY • BENCHMARKING RIGUROSO",
            "title": "Protocolo Experimental de Medición con System.nanoTime()",
            "cards": [
                {
                    "title": "Aislamiento Computacional Estricto",
                    "bullets": [
                        "El cronómetro System.nanoTime() inicia y detiene exclusivamente alrededor de la llamada algorítmica.",
                        "Cero operaciones de entrada/salida (I/O), consola o instanciación dentro del bloque medido.",
                        "Garantía de medición del costo algorítmico puro."
                    ]
                },
                {
                    "title": "Mitigación de Ruido y JIT",
                    "bullets": [
                        "Fase de calentamiento de la JVM (Warm-up) para estabilizar la compilación HotSpot JIT.",
                        "Batería de 5 tamaños de red (|V| ∈ {4, 6, 8, 10, 12}).",
                        "5 repeticiones independientes por cada tamaño."
                    ]
                },
                {
                    "title": "Métrica Estadística: La Mediana",
                    "bullets": [
                        "Se calcula la MEDIANA de las repeticiones en lugar del promedio simple.",
                        "La mediana descarta valores atípicos (outliers) causados por pausas del Garbage Collector (GC).",
                        "Especificaciones de máquina documentadas (CPU Apple Silicon, JDK 17)."
                    ]
                }
            ],
            "notes": "Melany (1 min): Resaltar la rigurosidad científica del benchmarking: por qué usamos nanosegundos, por qué descartamos I/O y por qué la mediana protege la medición contra pausas del sistema operativo."
        },

        # SLIDE 10: Melany - Gráficas y Resultados
        {
            "badge": "PERSONA 3: MELANY • RESULTADOS Y GRÁFICAS",
            "title": "Análisis de Escalabilidad: Gráfica 1 y Gráfica 2",
            "cards": [
                {
                    "title": "Gráfica 1: Tiempo vs. Tamaño de Red",
                    "bullets": [
                        "Muestra la curva empírica de tiempo de ejecución (ms / ns) frente a |V|.",
                        "Se observa el crecimiento exponencial controlado propio de algoritmos FPT.",
                        "Para |V| ≤ 10 el tiempo se mantiene por debajo del milisegundo gracias a la poda."
                    ]
                },
                {
                    "title": "Gráfica 2: Comparativa Antes vs. Después",
                    "bullets": [
                        "Compara los valores de k* y tiempo antes y después de añadir el enlace adicional.",
                        "Evidencia visual de los dos escenarios teóricos: Δk* = 0 y Δk* = 1.",
                        "Demuestra cómo el nuevo enlace altera los tiempos sin romper la estabilidad del algoritmo."
                    ]
                },
                {
                    "title": "Exportación de Datos CSV",
                    "bullets": [
                        "Los resultados se exportan automáticamente a bateria_benchmarking.csv.",
                        "Estructura tabular lista para replicabilidad y verificación por el docente.",
                        "Permite regenerar las curvas gráficas en cualquier herramienta."
                    ]
                }
            ],
            "notes": "Melany (1 min): Describir las dos figuras clave del informe y del póster. Enfatizar que los datos experimentales respaldan la teoría FPT. Dar paso a Carolay."
        },

        # SLIDE 11: Carolay - Demostración de Correctitud
        {
            "badge": "PERSONA 4: CAROLAY • FUNDAMENTACIÓN MATEMÁTICA",
            "title": "Demostración Formal de Correctitud por Inducción Matemática",
            "cards": [
                {
                    "title": "Hipótesis Inductiva sobre el Presupuesto k",
                    "bullets": [
                        "P(k): El algoritmo retorna SÍ con un testigo válido si y solo si existe una cobertura de tamaño ≤ k en G.",
                        "Inducción sobre el parámetro entero no negativo k.",
                        "El algoritmo cubre todo el espacio de soluciones válidas."
                    ]
                },
                {
                    "title": "Caso Base (k = 0)",
                    "bullets": [
                        "Si |E| = 0 (sin enlaces): La cobertura vacía S = ∅ es válida → Retorna SÍ (correcto).",
                        "Si |E| > 0 (existen enlaces): Ningún subconjunto de tamaño 0 cubre aristas → Retorna NO (correcto).",
                        "El caso base se cumple con exactitud estricta."
                    ]
                },
                {
                    "title": "Paso Inductivo (k > 0)",
                    "bullets": [
                        "Para cualquier arista no cubierta (u, v), una cobertura válida OBLIGA a u ∈ S o v ∈ S.",
                        "El algoritmo explora ambas opciones recursivamente con k - 1.",
                        "Por hipótesis inductiva, si existe solución en alguna de las ramas, el algoritmo la encuentra sin omisiones."
                    ]
                }
            ],
            "notes": "Carolay (1 min): Explicar el rigor de la prueba matemática inductiva. Demostrar que el algoritmo nunca produce falsos positivos ni falsos negativos."
        },

        # SLIDE 12: Carolay - Complejidad Teórica
        {
            "badge": "PERSONA 4: CAROLAY • ANÁLISIS DE COMPLEJIDAD ASINTÓTICA",
            "title": "Cotas Teóricas: Tiempo Exponencial FPT y Memoria Polinomial",
            "cards": [
                {
                    "title": "Complejidad Temporal T(n) = O(2^k • |E|)",
                    "bullets": [
                        "Árbol de recursión binario: En cada llamada se ramifica a lo sumo en 2 ramas.",
                        "Profundidad máxima acotada por k; número total de nodos en el árbol: 2^(k+1) - 1 = O(2^k).",
                        "Costo por llamada: Filtrar aristas no cubiertas toma O(|E|).",
                        "Cota total FPT: O(2^k • |E|), drásticamente superior a fuerza bruta ciega O(C(n, k) • |E|)."
                    ]
                },
                {
                    "title": "Complejidad Espacial S(n) = O(k • |E|)",
                    "bullets": [
                        "La profundidad de la pila de llamadas del sistema está limitada a k niveles.",
                        "Cada nivel almacena un conjunto local de aristas filtradas de tamaño ≤ |E|.",
                        "Memoria estrictamente POLINOMIAL O(k • |E|) ≤ O(|V| • |E|), sin explosión de RAM."
                    ]
                },
                {
                    "title": "Teoría vs. Comportamiento Empírico",
                    "bullets": [
                        "En redes reales, los nodos con grado alto (hubs) podan ramas masivamente.",
                        "El factor de crecimiento práctico es significativamente menor que 2^k.",
                        "Permite resolver redes de mediano porte en fracciones de segundo."
                    ]
                }
            ],
            "notes": "Carolay (1 min): Enfatizar que la complejidad temporal depende del parámetro k (FPT) y no de n en el exponente. Resaltar que la memoria es estrictamente polinomial, lo cual evita que la JVM se quede sin memoria (OutOfMemory)."
        },

        # SLIDE 13: Carolay - Preguntas Teóricas y NotebookLM
        {
            "badge": "PERSONA 4: CAROLAY • PREGUNTAS GUÍA Y NOTEBOOKLM",
            "title": "Respuestas a Preguntas Teóricas del Docente y Reflexión Ética",
            "cards": [
                {
                    "title": "Respuestas a las Preguntas Guía",
                    "bullets": [
                        "Pregunta 1: Crecimiento empírico vs teórico; limitaciones de granularidad del reloj en instancias micro (|V| ≤ 6).",
                        "Pregunta 2: Asimetría computacional fundamental: Búsqueda exacta en clase NP-difícil vs Verificación lineal en clase P.",
                        "Pregunta 3: Impacto de la arista adicional en la selectividad del árbol y en el factor de ramificación."
                    ]
                },
                {
                    "title": "Integración con NotebookLM",
                    "bullets": [
                        "Se cargaron los documentos base (Cormen Leiserson, Karp 1972) en NotebookLM.",
                        "Se formularon 5 preguntas conceptuales avanzadas sobre parametrización FPT y cobertura.",
                        "Respuestas contrastadas y citadas rigurosamente en el informe final."
                    ]
                },
                {
                    "title": "Honestidad Académica y Fuentes",
                    "bullets": [
                        "Reflexión ética de 175 palabras sobre el uso responsable de IA como asistente de aprendizaje.",
                        "Cada integrante comprende, defiende y puede modificar cada línea de código.",
                        "Citas bibliográficas completas en formato IEEE/APA."
                    ]
                }
            ],
            "notes": "Carolay (1 min): Presentar las respuestas a las 3 preguntas teóricas solicitadas en la rúbrica y detallar el uso de NotebookLM y la declaración de honestidad académica."
        },

        # SLIDE 14: Cierre y Defensa
        {
            "is_cover": False,
            "badge": "EQUIPO 4 • CONCLUSIONES Y RONDA DE DEFENSA",
            "title": "Conclusiones Principales y Preparación para Preguntas Individuales",
            "cards": [
                {
                    "title": "Logros Técnicos del Proyecto",
                    "bullets": [
                        "Modelado matemático formal y algoritmo exacto FPT O(2^k • |E|) 100% operativo.",
                        "Verificador polinomial O(|V| + |E|) independiente con suite de pruebas funcionales en terminal.",
                        "Benchmarking riguroso con System.nanoTime(), mediana y gráficas de antes vs después.",
                        "Fundamentación teórica completa de correctitud (inducción) y cotas asintóticas."
                    ]
                },
                {
                    "title": "Cumplimiento Rúbrica 20/20",
                    "bullets": [
                        "Bloque 1 (Carolay): Marco teórico, complejidad y respuestas guía.",
                        "Bloque 2 (Karina): Algoritmo exacto y extracción del testigo.",
                        "Bloque 3 (Eduardo): Verificador y demostración en vivo por consola.",
                        "Bloque 4 (Melany): Modificación obligatoria (+1 enlace), benchmarking y gráficas."
                    ]
                },
                {
                    "title": "Espacio para Preguntas del Tribunal",
                    "bullets": [
                        "El equipo está listo para responder preguntas individuales del profesor.",
                        "Disponibilidad de la terminal interactiva para probar cualquier grafo nuevo en vivo.",
                        "¡Muchas gracias por su atención!"
                    ]
                }
            ],
            "notes": "Cierre grupal (2 min): Agradecer al tribunal, resumir el éxito del proyecto y dar inicio a la ronda de preguntas individuales donde cada estudiante defenderá su bloque de acuerdo a la rúbrica."
        }
    ]

    # Construcción de las 14 diapositivas
    for idx, data in enumerate(slides_data, start=1):
        slide = prs.slides.add_slide(blank_layout)

        # Notas del orador
        if "notes" in data and data["notes"]:
            notes_slide = slide.notes_slide
            text_frame = notes_slide.notes_text_frame
            text_frame.text = f"[DIAPOSITIVA {idx}/14] {data['notes']}"

        if data.get("is_cover"):
            # DISEÑO PORTADA
            # Fondo superior institucional
            header_rect = slide.shapes.add_shape(
                MSO_SHAPE.RECTANGLE,
                Inches(0), Inches(0), Inches(13.333), Inches(3.2)
            )
            header_rect.fill.solid()
            header_rect.fill.fore_color.rgb = COLOR_NAVY
            header_rect.line.fill.background()

            # Franja naranja de acento
            accent_line = slide.shapes.add_shape(
                MSO_SHAPE.RECTANGLE,
                Inches(0), Inches(3.2), Inches(13.333), Inches(0.12)
            )
            accent_line.fill.solid()
            accent_line.fill.fore_color.rgb = COLOR_ORANGE
            accent_line.line.fill.background()

            # Texto del encabezado de portada
            tx_box = slide.shapes.add_textbox(Inches(0.8), Inches(0.4), Inches(11.733), Inches(2.6))
            tf = tx_box.text_frame
            tf.word_wrap = True

            p0 = tf.paragraphs[0]
            p0.text = data["badge"]
            p0.font.size = Pt(13)
            p0.font.bold = True
            p0.font.color.rgb = COLOR_AMBER
            p0.space_after = Pt(10)

            p1 = tf.add_paragraph()
            p1.text = data["title"]
            p1.font.size = Pt(28)
            p1.font.bold = True
            p1.font.color.rgb = COLOR_WHITE
            p1.space_after = Pt(8)

            p2 = tf.add_paragraph()
            p2.text = data["subtitle"]
            p2.font.size = Pt(16)
            p2.font.color.rgb = RGBColor(220, 230, 242)

            # Tarjetas de integrantes (4 columnas)
            card_width = Inches(2.7)
            card_height = Inches(3.4)
            card_gap = Inches(0.28)
            left_margin = Inches(0.8)
            top_margin = Inches(3.6)

            for i, (member, role) in enumerate(data["members"]):
                c_left = left_margin + i * (card_width + card_gap)
                
                # Rectángulo tarjeta
                c_rect = slide.shapes.add_shape(
                    MSO_SHAPE.ROUNDED_RECTANGLE,
                    c_left, top_margin, card_width, card_height
                )
                c_rect.fill.solid()
                c_rect.fill.fore_color.rgb = COLOR_BG_CARD
                c_rect.line.color.rgb = COLOR_BORDER
                c_rect.line.width = Pt(1.5)

                # Decoración superior de la tarjeta
                c_top_bar = slide.shapes.add_shape(
                    MSO_SHAPE.RECTANGLE,
                    c_left, top_margin, card_width, Inches(0.08)
                )
                c_top_bar.fill.solid()
                c_top_bar.fill.fore_color.rgb = COLOR_NAVY if i % 2 == 0 else COLOR_ORANGE
                c_top_bar.line.fill.background()

                # Contenido tarjeta
                c_box = slide.shapes.add_textbox(c_left + Inches(0.15), top_margin + Inches(0.2), card_width - Inches(0.3), card_height - Inches(0.4))
                ctf = c_box.text_frame
                ctf.word_wrap = True

                cp0 = ctf.paragraphs[0]
                cp0.text = f"INTEGRANTE {i+1}"
                cp0.font.size = Pt(10)
                cp0.font.bold = True
                cp0.font.color.rgb = COLOR_ORANGE
                cp0.space_after = Pt(6)

                cp1 = ctf.add_paragraph()
                cp1.text = member
                cp1.font.size = Pt(15)
                cp1.font.bold = True
                cp1.font.color.rgb = COLOR_NAVY
                cp1.space_after = Pt(10)

                cp2 = ctf.add_paragraph()
                cp2.text = "Rol Asignado:"
                cp2.font.size = Pt(11)
                cp2.font.bold = True
                cp2.font.color.rgb = COLOR_DARK

                cp3 = ctf.add_paragraph()
                cp3.text = role
                cp3.font.size = Pt(11)
                cp3.font.color.rgb = COLOR_MUTED

        else:
            # DISEÑO DIAPOSITIVAS DE CONTENIDO (2 a 14)
            # Banner superior
            top_bar = slide.shapes.add_shape(
                MSO_SHAPE.RECTANGLE,
                Inches(0), Inches(0), Inches(13.333), Inches(0.12)
            )
            top_bar.fill.solid()
            top_bar.fill.fore_color.rgb = COLOR_ORANGE
            top_bar.line.fill.background()

            # Encabezado con badge y título
            header_box = slide.shapes.add_textbox(Inches(0.8), Inches(0.3), Inches(11.733), Inches(1.3))
            htf = header_box.text_frame
            htf.word_wrap = True

            hp0 = htf.paragraphs[0]
            hp0.text = f"DIAPOSITIVA {idx}/14 • {data['badge']}"
            hp0.font.size = Pt(11)
            hp0.font.bold = True
            hp0.font.color.rgb = COLOR_ORANGE
            hp0.space_after = Pt(4)

            hp1 = htf.add_paragraph()
            hp1.text = data["title"]
            hp1.font.size = Pt(22)
            hp1.font.bold = True
            hp1.font.color.rgb = COLOR_NAVY

            # 3 Tarjetas de contenido horizontal
            cards = data.get("cards", [])
            num_cards = len(cards)
            card_width = Inches(3.72)
            card_height = Inches(5.1)
            card_gap = Inches(0.28)
            left_margin = Inches(0.8)
            top_margin = Inches(1.8)

            for i, card in enumerate(cards):
                c_left = left_margin + i * (card_width + card_gap)

                # Rectángulo tarjeta
                c_rect = slide.shapes.add_shape(
                    MSO_SHAPE.ROUNDED_RECTANGLE,
                    c_left, top_margin, card_width, card_height
                )
                c_rect.fill.solid()
                c_rect.fill.fore_color.rgb = COLOR_BG_CARD
                c_rect.line.color.rgb = COLOR_BORDER
                c_rect.line.width = Pt(1.5)

                # Decoración superior de la tarjeta
                c_top_bar = slide.shapes.add_shape(
                    MSO_SHAPE.RECTANGLE,
                    c_left, top_margin, card_width, Inches(0.08)
                )
                c_top_bar.fill.solid()
                c_top_bar.fill.fore_color.rgb = COLOR_NAVY if i == 0 else (COLOR_ORANGE if i == 1 else COLOR_NAVY)
                c_top_bar.line.fill.background()

                # Contenido dentro de la tarjeta
                c_box = slide.shapes.add_textbox(c_left + Inches(0.2), top_margin + Inches(0.2), card_width - Inches(0.4), card_height - Inches(0.4))
                ctf = c_box.text_frame
                ctf.word_wrap = True

                # Título de la tarjeta
                ctp0 = ctf.paragraphs[0]
                ctp0.text = f"Pilar {i+1}: {card['title']}"
                ctp0.font.size = Pt(14)
                ctp0.font.bold = True
                ctp0.font.color.rgb = COLOR_NAVY
                ctp0.space_after = Pt(12)

                # Bullets
                for b_text in card["bullets"]:
                    bp = ctf.add_paragraph()
                    bp.text = f"•  {b_text}"
                    bp.font.size = Pt(11)
                    bp.font.color.rgb = COLOR_DARK
                    bp.space_after = Pt(8)

    output_path = "/Users/panchis/.gemini/antigravity/scratch/Proyecto_EDA2_VertexCover/docs/Plantilla_Presentacion_EDA2_Equipo4.pptx"
    prs.save(output_path)
    print(f"Presentación generada con éxito: {output_path}")

if __name__ == "__main__":
    create_deck()
