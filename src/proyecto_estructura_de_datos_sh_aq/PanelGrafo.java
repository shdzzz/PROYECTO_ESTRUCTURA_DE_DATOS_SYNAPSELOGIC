package proyecto_estructura_de_datos_sh_aq;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;
import javax.swing.*;
import java.awt.*;

public class PanelGrafo extends JPanel {
    
    private Graph graphStream;
    private SwingViewer viewer;
    private Grafo grafoActual;
    
    public PanelGrafo() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        System.setProperty("org.graphstream.ui", "swing");
        
        graphStream = new SingleGraph("Red Neuronal");
        
        // CSS CORREGIDO - SIN ERRORES DE SINTAXIS
        String estilo = 
            "graph {" +
            "   fill-color: white;" +
            "}" +
            "node {" +
            "   fill-color: #4CAF50;" +
            "   size: 20px;" +
            "   text-size: 12px;" +
            "   text-style: bold;" +
            "   text-alignment: center;" +
            "   text-color: black;" +
            "}" +
            "node.aislado {" +
            "   fill-color: #F44336;" +
            "}" +
            "edge {" +
            "   fill-color: #2196F3;" +
            "   arrow-size: 10px, 10px;" +
            "   text-size: 10px;" +
            "   text-alignment: along;" +
            "   text-color: #0D47A1;" +
            "   text-background-mode: plain;" +
            "   text-background-color: white;" +
            "}";
        
        graphStream.setAttribute("ui.stylesheet", estilo);
        
        viewer = new SwingViewer(graphStream, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        viewPanel.setBackground(Color.WHITE);
        
        add(viewPanel, BorderLayout.CENTER);
    }
    
    public void actualizarGrafo(Grafo grafo) {
        this.grafoActual = grafo;
        
        graphStream.clear();
        
        // Agregar nodos
        TablaHash<Integer, Neurona> neuronas = grafo.getNeuronas();
        Object[] claves = neuronas.getClaves();
        
        for (Object clave : claves) {
            Integer id = (Integer) clave;
            Neurona neurona = neuronas.obtener(id);
            if (neurona != null) {
                String nodeId = String.valueOf(neurona.getId());
                org.graphstream.graph.Node node = graphStream.addNode(nodeId);
                node.setAttribute("ui.label", String.valueOf(neurona.getId()));
                node.setAttribute("ui.class", "normal");
            }
        }
        
        // Agregar aristas
        TablaHash<Integer, ListaEnlazada<Sinapsis>> adyacencia = grafo.getAdyacencia();
        Object[] clavesAdy = adyacencia.getClaves();
        
        for (Object clave : clavesAdy) {
            Integer idOrigen = (Integer) clave;
            ListaEnlazada<Sinapsis> listaSinapsis = adyacencia.obtener(idOrigen);
            
            if (listaSinapsis != null) {
                for (int i = 0; i < listaSinapsis.getTamaño(); i++) {
                    Sinapsis sinapsis = listaSinapsis.obtener(i);
                    if (sinapsis != null && sinapsis.getDestino() != null) {
                        String idOrigenStr = String.valueOf(sinapsis.getOrigen().getId());
                        String idDestinoStr = String.valueOf(sinapsis.getDestino().getId());
                        String edgeId = idOrigenStr + "->" + idDestinoStr;
                        
                        try {
                            org.graphstream.graph.Edge edge = graphStream.addEdge(edgeId, idOrigenStr, idDestinoStr, true);
                            edge.setAttribute("ui.label", sinapsis.getIdNeurotransmisor());
                        } catch (Exception e) {
                            // Arista ya existe
                        }
                    }
                }
            }
        }
        
        revalidate();
        repaint();
    }
    
    public void resaltarZonasAisladas(ListaEnlazada<Integer> idsAislados) {
        if (graphStream == null) return;
        
        for (org.graphstream.graph.Node node : graphStream) {
            node.setAttribute("ui.class", "normal");
        }
        
        if (idsAislados != null) {
            for (int i = 0; i < idsAislados.getTamaño(); i++) {
                Integer id = idsAislados.obtener(i);
                org.graphstream.graph.Node node = graphStream.getNode(String.valueOf(id));
                if (node != null) {
                    node.setAttribute("ui.class", "aislado");
                }
            }
        }
        
        revalidate();
        repaint();
    }
    
    public void limpiarGrafo() {
        if (graphStream != null) {
            graphStream.clear();
        }
        revalidate();
        repaint();
    }
}