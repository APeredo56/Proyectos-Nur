import javax.swing.*;

public class Ventana extends JFrame {
    private JLabel lbNombre = new JLabel("Nombre");
    private JLabel lbNumero = new JLabel("Texto");
    private JLabel lbCorreo = new JLabel("Correo");

    public Ventana (){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        cargarElementos();
        this.setVisible(true);
    }

    private void cargarElementos (){
        int x = 50;
        int y = 50;
        this.setLayout(null);
        lbNombre.setBounds(x,y,150, 25);

        add(lbNombre);
        add(lbNumero);
    }
}
