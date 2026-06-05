package proyecto_estructura_de_datos_sh_aq;

/**
 * Tabla hash para almacenar pares clave-valor.
 * @param <K> Tipo de la clave
 * @param <V> Tipo del valor
 * @author santiagoh
 */
public class TablaHash<K, V> {
    
    private ListaEnlazada<Entrada<K, V>>[] tabla;
    private int capacidad;
    private int tamaño;
    private double factorCarga;
    
    private static class Entrada<K, V> {
        K clave;
        V valor;
        
        Entrada(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }
    
    public TablaHash() {
        this.capacidad = 16;
        this.tabla = new ListaEnlazada[capacidad];
        this.tamaño = 0;
        this.factorCarga = 0.75;
        
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaEnlazada<>();
        }
    }
    
    public TablaHash(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new ListaEnlazada[capacidad];
        this.tamaño = 0;
        this.factorCarga = 0.75;
        
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaEnlazada<>();
        }
    }
    
    private int hash(K clave) {
        return Math.abs(clave.hashCode() % capacidad);
    }
    
    private void rehash() {
        int nuevaCapacidad = capacidad * 2;
        ListaEnlazada<Entrada<K, V>>[] nuevaTabla = new ListaEnlazada[nuevaCapacidad];
        
        for (int i = 0; i < nuevaCapacidad; i++) {
            nuevaTabla[i] = new ListaEnlazada<>();
        }
        
        for (int i = 0; i < capacidad; i++) {
            for (int j = 0; j < tabla[i].getTamaño(); j++) {
                Entrada<K, V> entrada = tabla[i].obtener(j);
                int nuevoIndice = Math.abs(entrada.clave.hashCode() % nuevaCapacidad);
                nuevaTabla[nuevoIndice].agregar(entrada);
            }
        }
        
        tabla = nuevaTabla;
        capacidad = nuevaCapacidad;
    }
    
    public void insertar(K clave, V valor) {
        if ((double) tamaño / capacidad > factorCarga) {
            rehash();
        }
        
        int indice = hash(clave);
        ListaEnlazada<Entrada<K, V>> lista = tabla[indice];
        
        for (int i = 0; i < lista.getTamaño(); i++) {
            Entrada<K, V> entrada = lista.obtener(i);
            if (entrada.clave.equals(clave)) {
                entrada.valor = valor;
                return;
            }
        }
        
        lista.agregar(new Entrada<>(clave, valor));
        tamaño++;
    }
    
    public V obtener(K clave) {
        int indice = hash(clave);
        ListaEnlazada<Entrada<K, V>> lista = tabla[indice];
        
        for (int i = 0; i < lista.getTamaño(); i++) {
            Entrada<K, V> entrada = lista.obtener(i);
            if (entrada.clave.equals(clave)) {
                return entrada.valor;
            }
        }
        return null;
    }
    
    public boolean contiene(K clave) {
        return obtener(clave) != null;
    }
    
    public V eliminar(K clave) {
        int indice = hash(clave);
        ListaEnlazada<Entrada<K, V>> lista = tabla[indice];
        
        for (int i = 0; i < lista.getTamaño(); i++) {
            Entrada<K, V> entrada = lista.obtener(i);
            if (entrada.clave.equals(clave)) {
                lista.eliminar(i);
                tamaño--;
                return entrada.valor;
            }
        }
        return null;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public boolean estaVacia() {
        return tamaño == 0;
    }
    
    public Object[] getClaves() {
        ListaEnlazada<K> claves = new ListaEnlazada<>();
        for (int i = 0; i < capacidad; i++) {
            for (int j = 0; j < tabla[i].getTamaño(); j++) {
                claves.agregar(tabla[i].obtener(j).clave);
            }
        }
        return claves.aArreglo();
    }
}
