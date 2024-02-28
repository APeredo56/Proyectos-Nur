package paint.vista;

import paint.modelo.Imagen;
import paint.modelo.transformaciones.*;

import javax.swing.*;
import java.awt.*;

public class PaintFrame extends JFrame {
    private Imagen modelo;
    private PaintPanel panel;

    private JMenuBar barra = new JMenuBar();
    private JMenu menuArchivo = new JMenu("Archivo");
    private JMenu menuModificar = new JMenu("Modificar");

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
        add(barra,BorderLayout.NORTH);

        setVisible(true);
    }

    private void hacerCambioEnImagen (){
        modelo.agregarCambio(panel.getImagenRecortada().getPixeles(), panel.getInicioPixeles(), panel.getAnchoRecorte(),
                panel.getAltoRecorte());
        panel.getBordes().setBounds(0,0,0,0);
        panel.setImagenRecortada(null);
    }

    private void accionGris() {
        if (panel.getImagenRecortada() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una parte de la imagen para modificar");
            return;
        }
        Transformacion tonosDeGris = new TransformarTonosDeGris(panel.getImagenRecortada());
        tonosDeGris.transformar();
        hacerCambioEnImagen();
        panel.getBordes().setBounds(0,0,0,0);
    }

    private void accionCargar() {
        modelo.leerDeArchivo("src/monje.jpg");
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
    }
}
