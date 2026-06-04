package proyecto_estructura_de_datos_sh_aq;

/**
 * Nodo para lista enlazada.
 * @param <T> Tipo de dato
 * @author santiagoh
 */
public class Nodo<T> {
    
    private T dato;
    private Nodo<T> siguiente;
    
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    public Nodo(T dato, Nodo<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    
    public T getDato() {
        return dato;
    }
    
    public Nodo<T> getSiguiente() {
        return siguiente;
    }
    
    public void setDato(T dato) {
        this.dato = dato;
    }
    
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
    
    @Override
    public String toString() {
        return "Nodo{dato=" + dato + "}";
    }
}
