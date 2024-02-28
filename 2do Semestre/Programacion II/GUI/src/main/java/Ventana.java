import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ventana extends JFrame {

    private ArrayList<Persona> datos = new ArrayList<>();
    String numeroActual = "";
    int contactoActual;
    private int contador = 0;
    private JLabel lbNombre = new JLabel("Nombre:");
    private JLabel lbTelefono = new JLabel("Telefono:");
    private JLabel lbEmail = new JLabel("E-Mail:");
    private JLabel lbContador = new JLabel(contactoActual + "/" + datos.size());
    private JTextField txtNombre = new JTextField();
    private JTextField txtTelefono = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JButton btnGuardar = new JButton("Guardar");
    private JButton btnLista = new JButton("Lista");
    private JButton btnAdelante = new JButton("->");
    private JButton btnAtras = new JButton("<-");
    private JButton btnNumContacto = new JButton (numeroActual);

    public Ventana() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 280);
        this.setLocationRelativeTo(null);
        cargarElementos();
        this.setVisible(true);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Persona nuevo = new Persona();
                nuevo.setNombre(txtNombre.getText());
                nuevo.setEmail(txtEmail.getText());
                nuevo.setTelefono(Integer.parseInt(txtTelefono.getText()));
                JOptionPane.showMessageDialog(rootPane, "Contacto Registrado");
                datos.add(nuevo);
                limpiarCampos();
                lbContador.setText(contactoActual + "/" + datos.size());
            }
        });
        btnLista.addActionListener(e -> {
            String resultado = "";
            for (Persona aux : datos) {
                resultado += aux.getNombre() + "\n";
            }
            JOptionPane.showMessageDialog(rootPane, resultado);
        });
        btnAdelante.addActionListener(e -> {
            txtNombre.setText(datos.get(contador).getNombre());
            txtTelefono.setText(datos.get(contador).getTelefono() + "");
            txtEmail.setText(datos.get(contador).getEmail());
            contador += 1;
            if (contador >= datos.size()){
                contador = 0;
            }
            contactoActual = contador;
            lbContador.setText(contactoActual + "/" + datos.size());
        });
        btnAtras.addActionListener(e -> {
            contador -= 1;
            if (contador < 0){
                contador = datos.size() - 1;
            }
            txtNombre.setText(datos.get(contador).getNombre());
            txtTelefono.setText(datos.get(contador).getTelefono() + "");
            txtEmail.setText(datos.get(contador).getEmail());
            contactoActual = contador;
            lbContador.setText(contactoActual + "/" + datos.size());
        });

    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
    }

    private void cargarElementos() {
        int x = 40;
        int y = 30;
        this.setLayout(null);
        lbNombre.setBounds(x, y, 150, 24);
        txtNombre.setBounds(x + 110, y, 150, 24);
        y += 40;
        lbTelefono.setBounds(x, y, 150, 24);
        txtTelefono.setBounds(x + 110, y, 150, 24);
        y += 40;
        lbEmail.setBounds(x, y, 150, 24);
        txtEmail.setBounds(x + 110, y, 150, 24);
        y += 40;
        btnGuardar.setBounds(150, y, 100, 30);
        y += 40;
        btnLista.setBounds(150, y, 100, 30);
        y -= 40;
        btnAdelante.setBounds(270, y, 50,50);
        btnAtras.setBounds(40, y, 50,50);
        lbContador.setBounds(300, -20,70,70);
        add(lbNombre);
        add(lbTelefono);
        add(lbEmail);
        add(txtEmail);
        add(txtTelefono);
        add(txtNombre);
        add(btnGuardar);
        add(btnLista);
        add(btnAdelante);
        add(btnAtras);
        add(lbContador);
    }

}
