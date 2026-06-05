package proyecto_estructura_de_datos_sh_aq;

/**
 * Sinapsis para la red neuronal.
 * @author santiagoh
 */
public class Sinapsis {
    
    private Neurona origen;
    private Neurona destino;
    private double distancia;
    private String idNeurotransmisor;
    private double coeficienteEficiencia;
    
    public Sinapsis(Neurona origen, Neurona destino, double distancia, String idNeurotransmisor, double coeficienteEficiencia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.idNeurotransmisor = idNeurotransmisor;
        this.coeficienteEficiencia = coeficienteEficiencia;
    }
    
    public Neurona getOrigen() {
        return origen;
    }
    
    public Neurona getDestino() {
        return destino;
    }
    
    public double getDistancia() {
        return distancia;
    }
    
    public String getIdNeurotransmisor() {
        return idNeurotransmisor;
    }
    
    public double getCoeficienteEficiencia() {
        return coeficienteEficiencia;
    }
    
    public void setOrigen(Neurona origen) {
        this.origen = origen;
    }
    
    public void setDestino(Neurona destino) {
        this.destino = destino;
    }
    
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    public void setIdNeurotransmisor(String idNeurotransmisor) {
        this.idNeurotransmisor = idNeurotransmisor;
    }
    
    public void setCoeficienteEficiencia(double coeficienteEficiencia) {
        this.coeficienteEficiencia = coeficienteEficiencia;
    }
    
    @Override
    public String toString() {
        return "Sinapsis{origen=" + (origen != null ? origen.getId() : "null") + 
               ", destino=" + (destino != null ? destino.getId() : "null") + 
               ", distancia=" + distancia + 
               ", idNeurotransmisor=" + idNeurotransmisor + "}";
    }
}
