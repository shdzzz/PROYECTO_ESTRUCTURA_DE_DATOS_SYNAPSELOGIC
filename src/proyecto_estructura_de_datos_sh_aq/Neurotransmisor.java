package proyecto_estructura_de_datos_sh_aq;

/**
 * Neurotransmisor para la red neuronal.
 * @author santiagoh
 */
public class Neurotransmisor {
    
    private int id;
    private String nombre;
    private String efecto;
    private double velocidad;
    private String descripcion;
    
    public Neurotransmisor(int id, String nombre, String efecto, double velocidad, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.efecto = efecto;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEfecto() {
        return efecto;
    }
    
    public double getVelocidad() {
        return velocidad;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }
    
    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Neurotransmisor{id=" + id + ", nombre=" + nombre + "}";
    }
}
