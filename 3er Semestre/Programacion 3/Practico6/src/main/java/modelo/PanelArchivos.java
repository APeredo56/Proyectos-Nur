package modelo;

import arbol.Arbol;
import elemento.Archivo;
import elemento.Carpeta;
import elemento.Elemento;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelArchivos extends JPanel {
    private Arbol<Elemento> arbolArchivos = new Arbol<>();
    private String carpetaActual = "";
    private JTable tabla;
    private DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JScrollPane scrollPane;
    private Configuracion configuracion;

    private JLabel lbCarpetaActual;
    private static final Logger logger = LogManager.getLogger(PanelArchivos.class);

    public PanelArchivos(JLabel lbCarpetaActual) {
        this.lbCarpetaActual = lbCarpetaActual;
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        configuracion = Configuracion.getInstance();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Tamaño");
        modeloTabla.addColumn("Nombre Fisico");

        tabla = new JTable(modeloTabla);
        scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(0, 250, 500, 300);
        add(scrollPane);

        Carpeta raiz = new Carpeta(configuracion, configuracion.getDirectorioBase(), "raiz");
        arbolArchivos.insertar(raiz, "", null);
        //al dar click a una celda de la tabla, si contiene una carpeta, cambia la carpeta actual y la muestra en la tabla
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable tablaAux = (JTable) e.getSource();
                    int fila = tablaAux.getSelectedRow();
                    int columna = tablaAux.getSelectedColumn();
                    if (tablaAux.getValueAt(fila, columna + 1).equals("DIR")) {
                        carpetaActual = (String) tablaAux.getValueAt(fila, columna);
                        abrirCarpeta();
                        lbCarpetaActual.setText(lbCarpetaActual.getText() + "\\" + carpetaActual);
                    }
                }
            }
        });
    }

    public void subirArchivo(String ruta) {
        Archivo nuevo = new Archivo(ruta, configuracion);
        arbolArchivos.insertar(nuevo, nuevo.getNombreReal(), carpetaActual);
        agregarArchivoEnTabla(nuevo);
        logger.debug("Se subio el archivo " + nuevo.getNombreReal() + " a la carpeta: " + carpetaActual);
    }

    public void crearCarpeta(String nombre) {
        Carpeta nueva = new Carpeta(configuracion, configuracion.getDirectorioBase() + carpetaActual, nombre);
        arbolArchivos.insertar(nueva, nueva.getNombreReal(), carpetaActual);
        agregarArchivoEnTabla(nueva);
        logger.debug("Se creo la carpeta " + nueva.getNombreReal() + " dentro de la carpeta: " + carpetaActual);
    }

    public void abrirCarpeta() {
        //vacia todas las filas de la tabla
        while (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }
        //busca la carpeta actual en el arbol
        Carpeta aux = (Carpeta) arbolArchivos.buscar(carpetaActual).getContenido();
        //agrega a la tabla todos los archivos que estan en la lista que contiene la carpeta
        for (Elemento aux2 : aux.getArchivosEnCarpeta()) {
            if (aux2.getTipo().equals("DIR")) {
                modeloTabla.addRow(new Object[]{aux2.getNombreReal(), aux2.getTipo()});
            } else {
                Archivo aux3 = (Archivo) aux2;
                modeloTabla.addRow(new Object[]{aux3.getNombreCambiado(), aux3.getTipo(), aux3.getPeso(),
                        aux3.getNombreReal()});
            }
        }
        logger.debug("Se accedio a la carpeta: " + carpetaActual);
    }

    public void retrocederCarpeta(){
        //Si la carpeta actual no es la raiz del arbol modifica el label con la ruta de la carpeta actual a una anterior
        // y luego cambia la carpeta actual por la anterior en el label
        if (!carpetaActual.equals("")) {
            lbCarpetaActual.setText(lbCarpetaActual.getText().substring(0, lbCarpetaActual.getText().indexOf(carpetaActual) - 1));
            carpetaActual = lbCarpetaActual.getText().substring(lbCarpetaActual.getText().lastIndexOf("\\") + 1);
            if (!lbCarpetaActual.getText().contains("\\")) {
                carpetaActual = "";
            }
            abrirCarpeta();
        }
    }

    public void agregarArchivoEnTabla(Elemento elemento) {
        //agrega un nuevo elemento a la tabla despues de crearlo y lo guarda en la lista de archivos de la carpeta actual
        Carpeta auxCarp = (Carpeta) arbolArchivos.buscar(carpetaActual).getContenido();
        auxCarp.getArchivosEnCarpeta().adicionar(elemento);
        if (elemento.getTipo().equals("DIR")) {
            modeloTabla.addRow(new Object[]{elemento.getNombreReal(), elemento.getTipo()});
        } else {
            Archivo aux = (Archivo) elemento;
            modeloTabla.addRow(new Object[]{aux.getNombreCambiado(), aux.getTipo(), aux.getPeso(),
                    aux.getNombreReal()});
        }
    }
}
