package proyecto_estructura_de_datos_sh_aq;

/**
 * Algoritmo DFS (Depth-First Search) - Búsqueda en profundidad.
 * Explora el grafo tan profundo como posible antes de retroceder.
 * @author santiagoh
 */
public class DFS {
    
    /**
     * Realiza DFS desde un nodo inicial en un grafo representado como TablaHash.
     * 
     * @param grafo TablaHash donde la clave es el ID de neurona y el valor es la lista de vecinos
     * @param idOrigen ID de la neurona desde donde comienza la búsqueda
     * @return TablaHash con los nodos visitados (true = visitado)
     */
    public static TablaHash<Integer, Boolean> buscar(TablaHash<Integer, ListaEnlazada<Integer>> grafo, int idOrigen) {
        TablaHash<Integer, Boolean> visitados = new TablaHash<>();
        buscarAuxiliar(grafo, idOrigen, visitados);
        return visitados;
    }
    
    /**
     * Método auxiliar recursivo para DFS.
     * 
     * @param grafo TablaHash del grafo
     * @param nodo Nodo actual
     * @param visitados TablaHash de nodos visitados
     */
    private static void buscarAuxiliar(TablaHash<Integer, ListaEnlazada<Integer>> grafo, int nodo, TablaHash<Integer, Boolean> visitados) {
        visitados.insertar(nodo, true);
        
        ListaEnlazada<Integer> vecinos = grafo.obtener(nodo);
        if (vecinos != null) {
            for (int i = 0; i < vecinos.getTamaño(); i++) {
                int vecino = vecinos.obtener(i);
                if (visitados.obtener(vecino) == null) {
                    buscarAuxiliar(grafo, vecino, visitados);
                }
            }
        }
    }
    
    /**
     * Obtiene las zonas aisladas (neuronas no alcanzables desde el origen).
     * 
     * @param grafo TablaHash del grafo
     * @param idOrigen ID de la neurona origen
     * @return ListaEnlazada con los IDs de neuronas no alcanzables
     */
    public static ListaEnlazada<Integer> obtenerZonasAisladas(TablaHash<Integer, ListaEnlazada<Integer>> grafo, int idOrigen) {
        TablaHash<Integer, Boolean> visitados = buscar(grafo, idOrigen);
        ListaEnlazada<Integer> zonasAisladas = new ListaEnlazada<>();
        
        Object[] claves = grafo.getClaves();
        for (int i = 0; i < claves.length; i++) {
            Integer id = (Integer) claves[i];
            if (visitados.obtener(id) == null) {
                zonasAisladas.agregar(id);
            }
        }
        
        return zonasAisladas;
    }
}
