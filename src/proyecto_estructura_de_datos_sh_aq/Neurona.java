package proyecto_estructura_de_datos_sh_aq;

/**
 * Neurona para la red neuronal.
 * @author santiagoh
 */
public class Neurona {
    
    private int id;
    
    public Neurona(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Neurona{id=" + id + "}";
    }
}
