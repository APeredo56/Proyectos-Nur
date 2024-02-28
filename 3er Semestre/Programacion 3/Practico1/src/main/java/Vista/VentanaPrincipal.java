package Vista;




import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeSupport;

public class VentanaPrincipal extends JFrame {

    private JButton btnAgregar = new JButton("Nuevo Numero");
    private PropertyChangeSupport cambios;

    private PanelBarras panel = new PanelBarras();
    private VentanaEmergente ventanaSecundaria;

    public VentanaPrincipal(){
        cargarComponentes();
        this.cambios = new PropertyChangeSupport(this);
    }
    public void addObserver (){
        cambios.addPropertyChangeListener(panel);
    }

    public PanelBarras getPanel() {
        return panel;
    }

    public void cargarComponentes (){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1300,265);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());
        this.setResizable(false);

        btnAgregar.setBounds(0,0,this.getWidth(), 30);
        btnAgregar.addActionListener(e -> {
            ventanaSecundaria = new VentanaEmergente();

            ventanaSecundaria.getBtnOk().addActionListener(f -> {
                int alto = -1;
                int posicion = -1;
                try {
                    alto = Integer.parseInt(ventanaSecundaria.getTxtNumero().getText());
                    posicion = Integer.parseInt(ventanaSecundaria.getTxtPosicion().getText());
                } catch (Exception error){
                    alto = -1;
                    posicion = -1;
                }
                if (alto > 1 && alto <= 200) {
                    panel.agregarBarra(posicion, 200 - alto, alto, posicion);
                    ventanaSecundaria.dispose();
                    cambios.firePropertyChange("AGREGAR", null, panel.getListaBarras());
                    this.repaint();
                } else {
                    JOptionPane.showMessageDialog(null,"Error, solo puede ingresar numeros " +
                            "del 1 al 200");
                }
            });
        });


        this.add(panel, BorderLayout.CENTER);
        this.add(btnAgregar, BorderLayout.NORTH);

        this.setVisible(true);

    }
}
