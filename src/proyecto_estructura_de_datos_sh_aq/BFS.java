package proyecto_estructura_de_datos_sh_aq;

/**
 * Algoritmo BFS (Breadth-First Search) - Búsqueda en anchura.
 * Explora el grafo nivel por nivel desde un nodo inicial.
 * @author santiagoh
 */
public class BFS {
    
    /**
     * Realiza BFS desde un nodo inicial en un grafo representado como TablaHash.
     * 
     * @param grafo TablaHash donde la clave es el ID de neurona y el valor es la lista de vecinos
     * @param idOrigen ID de la neurona desde donde comienza la búsqueda
     * @return TablaHash con los nodos visitados (true = visitado)
     */
    public static TablaHash<Integer, Boolean> buscar(TablaHash<Integer, ListaEnlazada<Integer>> grafo, int idOrigen) {
        TablaHash<Integer, Boolean> visitados = new TablaHash<>();
        ListaEnlazada<Integer> cola = new ListaEnlazada<>();
        
        cola.agregar(idOrigen);
        visitados.insertar(idOrigen, true);
        
        while (!cola.estaVacia()) {
            int nodoActual = cola.eliminarPrimero();
            
            ListaEnlazada<Integer> vecinos = grafo.obtener(nodoActual);
            if (vecinos != null) {
                for (int i = 0; i < vecinos.getTamaño(); i++) {
                    int vecino = vecinos.obtener(i);
                    if (visitados.obtener(vecino) == null) {
                        visitados.insertar(vecino, true);
                        cola.agregar(vecino);
                    }
                }
            }
        }
        
        return visitados;
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
