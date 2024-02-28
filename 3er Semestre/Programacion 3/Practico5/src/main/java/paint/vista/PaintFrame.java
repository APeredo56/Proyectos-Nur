package paint.vista;

import paint.listas.Lista;
import paint.modelo.Imagen;
import paint.modelo.transformaciones.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PaintFrame extends JFrame {
    private Imagen modelo;
    private PaintPanel panel;

    private Lista<int[][]> listaCambios = new Lista<>();

    private int posicionCambios = 0;

    private JMenuBar barra = new JMenuBar();
    private JMenu menuArchivo = new JMenu("Archivo");
    private JMenu menuModificar = new JMenu("Modificar");
    private JButton btnDeshacer = new JButton("Deshacer");
    private JButton btnRehacer = new JButton("Rehacer");

    private JMenuItem cargar = new JMenuItem("Cargar Imagen");
    private JMenuItem gris = new JMenuItem("Gris");
    private JMenuItem espejoHorizontal = new JMenuItem("Espejo Horizontal");
    private JMenuItem espejoVertical = new JMenuItem("Espejo Vertical");
    private JMenuItem tonosRojos = new JMenuItem("Tonos Rojos");
    private JMenuItem tonosVerdes = new JMenuItem("Tonos Verdes");
    private JMenuItem tonosAzules = new JMenuItem("Tonos Azules");
    private JMenuItem pixelado = new JMenuItem("Pixelado");

    public PaintFrame() {
        init();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000,700);
        setLocationRelativeTo(null);

        modelo = new Imagen(400,400);
        panel = new PaintPanel(modelo);
        add(panel, BorderLayout.CENTER);

        barra.add(menuArchivo);
        barra.add(menuModificar);
        barra.add(btnDeshacer);
        barra.add(btnRehacer);

        menuArchivo.add(cargar);
        menuModificar.add(gris);
        menuModificar.add(espejoHorizontal);
        menuModificar.add(espejoVertical);
        menuModificar.add(tonosRojos);
        menuModificar.add(tonosVerdes);
        menuModificar.add(tonosAzules);
        menuModificar.add(pixelado);

        cargar.addActionListener(e -> accionCargar());
        gris.addActionListener(e -> accionGris());
        espejoHorizontal.addActionListener(e -> accionEspejoHorizontal());
        espejoVertical.addActionListener(e -> accionEspejoVertical());
        tonosRojos.addActionListener(e -> accionTonosRojos());
        tonosVerdes.addActionListener(e -> accionTonosVerdes());
        tonosAzules.addActionListener(e -> accionTonosAzules());
        pixelado.addActionListener(e -> accionPixelado());
        btnDeshacer.addActionListener(e -> accionDeshacer());
        btnRehacer.addActionListener(e -> accionRehacer());

        add(barra,BorderLayout.NORTH);

        setVisible(true);
    }

    private void accionGris() {
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }
        Transformacion tonosDeGris = new TransformarTonosDeGris(panel.getImagenRecortada());
        tonosDeGris.transformar();
        hacerCambioEnImagen();
    }

    private void accionCargar() {
        modelo.leerDeArchivo("src/monje.jpg");
        guardarCambio();
    }

    private void accionEspejoHorizontal (){
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }

        Transformacion cambiarEspejoHorizontal = new TransformarEspejoHorizontal(panel.getImagenRecortada());
        cambiarEspejoHorizontal.transformar();
        hacerCambioEnImagen();

    }

    private void accionEspejoVertical (){
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }

        Transformacion cambiarEspejoVertical = new TransformarEspejoVertical(panel.getImagenRecortada());
        cambiarEspejoVertical.transformar();
        hacerCambioEnImagen();

    }

    private void accionTonosRojos (){
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }

        Transformacion cambiarTonosRojos = new TransformarTonosRGB(panel.getImagenRecortada(),"red");
        cambiarTonosRojos.transformar();
        hacerCambioEnImagen();

    }

    private void accionTonosVerdes (){
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }

        Transformacion cambiarTonosVerdes = new TransformarTonosRGB(panel.getImagenRecortada(),"green");
        cambiarTonosVerdes.transformar();
        hacerCambioEnImagen();

    }
    private void accionTonosAzules (){
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }

        Transformacion cambiarTonosAzules = new TransformarTonosRGB(panel.getImagenRecortada(),"blue");
        cambiarTonosAzules.transformar();
        hacerCambioEnImagen();

    }

    private void accionPixelado() {
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }

        Transformacion hacerPixelado = new TransformarPixelado(panel.getImagenRecortada());
        hacerPixelado.transformar();
        hacerCambioEnImagen();
        repaint();

    }

    private void accionDeshacer() {
        if (posicionCambios > 0) {
            posicionCambios -= 1;
            actualizarPixeles(listaCambios.obtener(posicionCambios));
            //modelo.setPixeles(listaCambios.obtener(posicionCambios).getPixeles());
            modelo.transformada();
            panel.getLogger().debug("Posicion en la lista de cambios: " + posicionCambios + "/" + listaCambios.tamano());
        }
    }

    private void accionRehacer(){
        if(posicionCambios < listaCambios.tamano() -1) {
            posicionCambios += 1;
            actualizarPixeles(listaCambios.obtener(posicionCambios));

            //modelo.setPixeles(listaCambios.obtener(posicionCambios).getPixeles());
            modelo.transformada();
            panel.getLogger().debug("Posicion en la lista de cambios: " + posicionCambios+ "/" + listaCambios.tamano());
        }
    }

    private void hacerCambioEnImagen (){
        modelo.agregarCambio(panel.getImagenRecortada().getPixeles(), panel.getInicioPixeles(), panel.getAnchoRecorte(),
                panel.getAltoRecorte());
        panel.getBordes().setBounds(0,0,0,0);
        panel.setImagenRecortada(null);
        posicionCambios += 1;
        guardarCambio();

    }

    private void guardarCambio (){
        int [][] nuevosPixeles = new int[modelo.getAncho()][modelo.getAncho()];
        for (int j = 0; j < modelo.getAlto(); j++) {
            for (int i = 0; i < modelo.getAncho(); i++) {
                nuevosPixeles[i][j] = modelo.getPixeles()[i][j];
            }
        }
        //si no esta en la ultima posicion elimina los elementos de la lista desde la posicion actual
        if (posicionCambios < listaCambios.tamano() ){
            for (int i = 0; i <= listaCambios.tamano() - posicionCambios+1; i++) {
                listaCambios.eliminar(posicionCambios);
            }
            panel.getLogger().debug("Hubo un cambio en el orden para rehacer, nuevo tamaño de la lista: " +
                    listaCambios.tamano()+1);
        }
        listaCambios.adicionar(nuevosPixeles);
    }

    public void actualizarPixeles (int [][] pixelesNuevos){
        for (int j = 0; j < modelo.getAlto(); j++) {
            for (int i = 0; i < modelo.getAncho(); i++) {
                modelo.getPixeles()[i][j] = pixelesNuevos[i][j];
            }
        }
    }
}

