package proyecto_estructura_de_datos_sh_aq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Cargador de archivos CSV.
 * @author santiagoh
 */
public class CargadorCSV {
    
    public static ListaEnlazada<String[]> leerArchivo(String rutaArchivo, boolean saltarEncabezado) {
        ListaEnlazada<String[]> lineas = new ListaEnlazada<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                if (saltarEncabezado && primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                
                String[] datos = linea.split(",");
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = datos[i].trim();
                }
                lineas.agregar(datos);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return lineas;
    }
}
