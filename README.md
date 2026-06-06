SynapseLogic - Analisis de Conectividad y Transmision Neuronal

Descripcion

SynapseLogic es una aplicacion desarrollada en Java que modela el cerebro humano como una red neuronal. Permite cargar archivos CSV con informacion de sinapsis y neurotransmisores, detectar zonas aisladas mediante el algoritmo BFS, calcular rutas de mayor activacion con Dijkstra y simular el deterioro cognitivo por fatiga. El proyecto implementa sus propias estructuras de datos como listas enlazadas y tablas hash.

Integrantes del Grupo
- Santiago Hernandez
Requisitos para ejecutar el programa

- JDK 25 o superior
- NetBeans IDE (recomendado)
- Librerias GraphStream (gs-core y gs-ui-swing) incluidas en el proyecto

Como utilizar el programa

1. Abrir el proyecto en NetBeans
2. Ejecutar la clase Principal como proyecto principal
3. En el menu Archivo, seleccionar Cargar Red Neuronal y elegir un archivo CSV con el formato adecuado
4. Luego cargar el diccionario de neurotransmisores desde el menu Archivo con Cargar Diccionario
5. Utilizar los botones disponibles para ejecutar los algoritmos:
   - Detectar Zonas Aisladas (BFS): pide un ID de neurona origen y muestra las neuronas inalcanzables
   - Calcular Ruta Mas Rapida (Dijkstra): pide origen y destino y muestra el camino optimo
   - Simular Fatiga (x1.2): multiplica todos los coeficientes de eficiencia por 1.2
   - Agregar Neurona: permite anadir una nueva neurona al grafo
   - Agregar Conexion: crea una sinapsis entre dos neuronas existentes
   - Mostrar Estadisticas: muestra el total de neuronas, sinapsis y neurotransmisores
6. El grafo se visualiza en el panel central y las zonas aisladas se resaltan en color rojo

Archivos CSV de ejemplo

El programa incluye dos archivos de ejemplo para probar la funcionalidad

Estructura del Proyecto

El proyecto esta organizado en las siguientes clases principales:

- Grafo: administra las neuronas y sinapsis con listas de adyacencia
- BFS: implementa la busqueda en anchura para detectar componentes aislados
- Dijkstra: calcula la ruta mas corta con la formula peso = distancia / (velocidad * k)
- TablaHash: estructura propia para almacenar neurotransmisores con complejidad O(1)
- ListaEnlazada: estructura generica para colecciones de datos
- PanelGrafo: maneja la visualizacion del grafo con GraphStream
- CargadorCSV: lee archivos CSV y procesa los datos
- Principal: interfaz grafica que integra todas las funcionalidades

Notas adicionales

- El programa no permite cargar un nuevo archivo sin antes confirmar la sobrescritura de los datos actuales
- La simulacion de fatiga afecta todas las sinapsis y recalcula automaticamente el grafo visual
- Para mover los nodos dentro del grafo visual se pueden arrastrar con el mouse
