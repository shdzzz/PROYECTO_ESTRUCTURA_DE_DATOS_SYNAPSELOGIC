package proyecto_estructura_de_datos_sh_aq;

/**
 * Grafo dirigido para la red neuronal.
 * @author santiagoh
 */
public class Grafo {
    
    private TablaHash<Integer, Neurona> neuronas;
    private TablaHash<Integer, ListaEnlazada<Sinapsis>> adyacencia;
    private TablaHash<String, Neurotransmisor> diccionarioNeuro;
    
    public Grafo() {
        this.neuronas = new TablaHash<>();
        this.adyacencia = new TablaHash<>();
        this.diccionarioNeuro = new TablaHash<>();
    }
    
    public void agregarNeurona(Neurona neurona) {
        neuronas.insertar(neurona.getId(), neurona);
        adyacencia.insertar(neurona.getId(), new ListaEnlazada<>());
    }
    
    public void agregarSinapsis(Sinapsis sinapsis) {
        int idOrigen = sinapsis.getOrigen().getId();
        ListaEnlazada<Sinapsis> lista = adyacencia.obtener(idOrigen);
        
        if (lista != null) {
            lista.agregar(sinapsis);
        }
    }
    
    public void agregarNeurotransmisor(Neurotransmisor neuro) {
        diccionarioNeuro.insertar(neuro.getId(), neuro);
    }
    
    public void eliminarNeurona(int id) {
        // Eliminar la neurona de la tabla de neuronas
        neuronas.eliminar(id);
        
        // Eliminar todas las sinapsis donde esta neurona es origen
        adyacencia.eliminar(id);
        
        // Eliminar todas las sinapsis donde esta neurona es destino
        Object[] claves = adyacencia.getClaves();
        for (int i = 0; i < claves.length; i++) {
            Integer idOrigen = (Integer) claves[i];
            ListaEnlazada<Sinapsis> lista = adyacencia.obtener(idOrigen);
            if (lista != null) {
                for (int j = 0; j < lista.getTamaño(); j++) {
                    Sinapsis s = lista.obtener(j);
                    if (s.getDestino().getId() == id) {
                        lista.eliminar(j);
                        j--; // Ajustar índice después de eliminar
                    }
                }
            }
        }
    }
    
    public Neurona obtenerNeurona(int id) {
        return neuronas.obtener(id);
    }
    
    public ListaEnlazada<Sinapsis> obtenerVecinos(int idNeurona) {
        return adyacencia.obtener(idNeurona);
    }
    
    public Neurotransmisor obtenerNeurotransmisor(String nombre) {
        return diccionarioNeuro.obtener(nombre);
    }
    
    public TablaHash<Integer, Neurona> getNeuronas() {
        return neuronas;
    }
    
    public TablaHash<Integer, ListaEnlazada<Sinapsis>> getAdyacencia() {
        return adyacencia;
    }
    
    public TablaHash<String, Neurotransmisor> getDiccionarioNeuro() {
        return diccionarioNeuro;
    }
    
    public int getNumNeuronas() {
        return neuronas.getTamaño();
    }
    
    public int getNumSinapsis() {
        int total = 0;
        Object[] claves = adyacencia.getClaves();
        for (int i = 0; i < claves.length; i++) {
            Integer id = (Integer) claves[i];
            ListaEnlazada<Sinapsis> lista = adyacencia.obtener(id);
            if (lista != null) {
                total += lista.getTamaño();
            }
        }
        return total;
    }
}
