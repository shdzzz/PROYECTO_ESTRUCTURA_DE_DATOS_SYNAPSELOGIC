package proyecto_estructura_de_datos_sh_aq;

/**
 * Lista enlazada generica.
 * @param <T> Tipo de dato
 * @author santiagoh
 */
public class ListaEnlazada<T> {
    
    private Nodo<T> cabeza;
    private int tamaño;
    
    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    public boolean estaVacia() {
        return cabeza == null;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }
    
    public void agregarAlInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
        tamaño++;
    }
    
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }
    
    public int buscar(T dato) {
        Nodo<T> actual = cabeza;
        int indice = 0;
        
        while (actual != null) {
            if (dato == null) {
                if (actual.getDato() == null) {
                    return indice;
                }
            } else {
                if (actual.getDato() != null && actual.getDato().equals(dato)) {
                    return indice;
                }
            }
            actual = actual.getSiguiente();
            indice++;
        }
        return -1;
    }
    
    public T eliminar(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        
        T datoEliminado;
        
        if (indice == 0) {
            datoEliminado = cabeza.getDato();
            cabeza = cabeza.getSiguiente();
        } else {
            Nodo<T> anterior = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                anterior = anterior.getSiguiente();
            }
            Nodo<T> nodoAEliminar = anterior.getSiguiente();
            datoEliminado = nodoAEliminar.getDato();
            anterior.setSiguiente(nodoAEliminar.getSiguiente());
        }
        tamaño--;
        return datoEliminado;
    }
    
    public Object[] aArreglo() {
        Object[] arreglo = new Object[tamaño];
        Nodo<T> actual = cabeza;
        
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return arreglo;
    }
    
    @Override
    public String toString() {
        if (estaVacia()) {
            return "ListaEnlazada[]";
        }
        
        StringBuilder sb = new StringBuilder("ListaEnlazada[");
        Nodo<T> actual = cabeza;
        
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(" -> ");
            }
            actual = actual.getSiguiente();
        }
        
        sb.append("]");
        return sb.toString();
    }
}
