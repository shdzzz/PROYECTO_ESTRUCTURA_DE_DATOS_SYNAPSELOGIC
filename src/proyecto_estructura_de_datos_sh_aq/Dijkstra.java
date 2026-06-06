package proyecto_estructura_de_datos_sh_aq;

/**
 * Algoritmo Dijkstra - Encuentra el camino más corto en un grafo ponderado.
 * @author santiagoh
 */
public class Dijkstra {
    
    /**
     * Clase para guardar el resultado del algoritmo Dijkstra.
     */
    public static class ResultadoDijkstra {
        public TablaHash<Integer, Double> distancias;
        public TablaHash<Integer, Integer> anteriores;
        
        public ResultadoDijkstra(TablaHash<Integer, Double> distancias, TablaHash<Integer, Integer> anteriores) {
            this.distancias = distancias;
            this.anteriores = anteriores;
        }
    }
    
    /**
     * Ejecuta el algoritmo Dijkstra desde un nodo origen.
     * 
     * @param grafo TablaHash donde clave es ID de neurona y valor es lista de sinapsis
     * @param diccionarioNeuro TablaHash con diccionario de neurotransmisores
     * @param idOrigen ID de la neurona origen
     * @return ResultadoDijkstra con distancias y nodos anteriores
     */
    public static ResultadoDijkstra buscar(TablaHash<Integer, ListaEnlazada<Sinapsis>> grafo, 
                                           TablaHash<String, Neurotransmisor> diccionarioNeuro, 
                                           int idOrigen) {
        System.out.println("DEBUG: Dijkstra iniciado. Diccionario tiene " + 
                          (diccionarioNeuro != null ? diccionarioNeuro.getTamaño() : 0) + " neurotransmisores");
        
        if (diccionarioNeuro != null) {
            Object[] claves = diccionarioNeuro.getClaves();
            System.out.println("DEBUG: IDs en diccionario: ");
            for (int i = 0; i < claves.length; i++) {
                System.out.println("  - " + claves[i]);
            }
        }
        
        TablaHash<Integer, Double> distancias = new TablaHash<>();
        TablaHash<Integer, Integer> anteriores = new TablaHash<>();
        TablaHash<Integer, Boolean> visitados = new TablaHash<>();
        
        Object[] claves = grafo.getClaves();
        for (int i = 0; i < claves.length; i++) {
            Integer id = (Integer) claves[i];
            distancias.insertar(id, Double.MAX_VALUE);
        }
        
        distancias.insertar(idOrigen, 0.0);
        
        while (hayNoVisitados(distancias, visitados)) {
            int nodoActual = obtenerNodoNoVisitadoConMenorDistancia(distancias, visitados);
            
            if (nodoActual == -1) {
                break;
            }
            
            visitados.insertar(nodoActual, true);
            
            ListaEnlazada<Sinapsis> sinapsis = grafo.obtener(nodoActual);
            if (sinapsis != null) {
                for (int i = 0; i < sinapsis.getTamaño(); i++) {
                    Sinapsis s = sinapsis.obtener(i);
                    int vecino = s.getDestino().getId();
                    
                    if (visitados.obtener(vecino) == null) {
                        double peso = calcularPeso(s, diccionarioNeuro);
                        double distanciaActual = distancias.obtener(nodoActual);
                        double nuevaDistancia = distanciaActual + peso;
                        double distanciaVecino = distancias.obtener(vecino);
                        
                        if (nuevaDistancia < distanciaVecino) {
                            distancias.insertar(vecino, nuevaDistancia);
                            anteriores.insertar(vecino, nodoActual);
                        }
                    }
                }
            }
        }
        
        return new ResultadoDijkstra(distancias, anteriores);
    }
    
    /**
     * Calcula el peso de una sinapsis usando la fórmula:
     * peso = distancia / (velocidad * coeficienteEficiencia)
     * 
     * @param sinapsis Sinapsis a evaluar
     * @param diccionarioNeuro Diccionario de neurotransmisores
     * @return Peso calculado
     */
    private static double calcularPeso(Sinapsis sinapsis, TablaHash<String, Neurotransmisor> diccionarioNeuro) {
        String idNeuro = sinapsis.getIdNeurotransmisor();
        Neurotransmisor neuro = diccionarioNeuro.obtener(idNeuro);
        
        if (neuro == null) {
            System.err.println("WARNING: Neurotransmisor no encontrado: " + idNeuro + ". Usando distancia directa.");
            return sinapsis.getDistancia();
        }
        
        double distancia = sinapsis.getDistancia();
        double velocidad = neuro.getVelocidad();
        double coeficiente = sinapsis.getCoeficienteEficiencia();
        
        if (velocidad == 0 || coeficiente == 0) {
            return Double.MAX_VALUE;
        }
        
        double peso = distancia / (velocidad * coeficiente);
        System.out.println("DEBUG: Sinapsis " + sinapsis.getOrigen().getId() + "->" + sinapsis.getDestino().getId() + 
                          ", Neuro: " + idNeuro + ", Velocidad: " + velocidad + 
                          ", Coeficiente: " + coeficiente + ", Distancia: " + distancia + 
                          ", Peso calculado: " + peso);
        
        return peso;
    }
    
    /**
     * Verifica si hay nodos no visitados.
     */
    private static boolean hayNoVisitados(TablaHash<Integer, Double> distancias, TablaHash<Integer, Boolean> visitados) {
        Object[] claves = distancias.getClaves();
        for (int i = 0; i < claves.length; i++) {
            Integer id = (Integer) claves[i];
            if (visitados.obtener(id) == null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtiene el nodo no visitado con menor distancia.
     */
    private static int obtenerNodoNoVisitadoConMenorDistancia(TablaHash<Integer, Double> distancias, TablaHash<Integer, Boolean> visitados) {
        int nodoMinimo = -1;
        double distanciaMinima = Double.MAX_VALUE;
        
        Object[] claves = distancias.getClaves();
        for (int i = 0; i < claves.length; i++) {
            Integer id = (Integer) claves[i];
            if (visitados.obtener(id) == null) {
                double distancia = distancias.obtener(id);
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    nodoMinimo = id;
                }
            }
        }
        
        return nodoMinimo;
    }
    
    /**
     * Reconstruye el camino desde el origen hasta un destino.
     * 
     * @param resultado Resultado de Dijkstra
     * @param idDestino ID de la neurona destino
     * @return ListaEnlazada con el camino (incluye origen y destino)
     */
    public static ListaEnlazada<Integer> reconstruirCamino(ResultadoDijkstra resultado, int idDestino) {
        ListaEnlazada<Integer> camino = new ListaEnlazada<>();
        Integer actual = idDestino;
        
        while (actual != null) {
            camino.agregarAlInicio(actual);
            actual = resultado.anteriores.obtener(actual);
        }
        
        return camino;
    }
}
