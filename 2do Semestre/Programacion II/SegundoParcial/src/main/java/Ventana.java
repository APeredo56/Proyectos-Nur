import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Ventana extends JFrame implements MouseListener {
    private JLabel lbTitulo = new JLabel("Iniciar Sesión");
    private JLabel lbUsuario = new JLabel("Usuario:");
    private JLabel lbContraseña = new JLabel("Contraseña:");
    private JLabel lbRegistro = new JLabel("No tienes una cuenta? Registrate");
    private JLabel lbNombre = new JLabel("Nombre:");
    private JTextField txtUsuario = new JTextField();
    private JTextField txtContraseña = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JButton btIniciar = new JButton("Iniciar");
    private JButton btSalir = new JButton("Salir");

    private JPanel panelPrincipal = new JPanel();

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public Ventana() {
        setTitle("Segundo Parcial");
        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarPanelPrincipal(20, 20);
        registrar("1", "123", "456");
        btIniciar.addActionListener(e -> {
            if (lbTitulo.getText().equals("Iniciar sesión")) {
                String mensaje = autenticar(txtUsuario.getText(), txtContraseña.getText());
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                registrar(txtNombre.getText(), txtUsuario.getText(),txtContraseña.getText());
                panelPrincipal.remove(lbNombre);
                panelPrincipal.remove(txtNombre);
                lbTitulo.setText("Iniciar sesión");
                btIniciar.setText("Iniciar");
                repaint();
            }
        });
        btSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Has cerrado sesión");
        });
        lbRegistro.addMouseListener(this);
        setVisible(true);
    }

    public void cargarPanelPrincipal(int x, int y) {
        lbTitulo.setBounds(20, 20, 100, 30);
        y += 50;
        lbUsuario.setBounds(x, y, 80, 30);
        y += 50;
        lbContraseña.setBounds(x, y, 80, 30);
        y += 70;
        btSalir.setBounds(x, y, 80, 30);
        y += 50;
        lbRegistro.setBounds(x, y, 200, 30);
        y -= 170;
        x += 75;
        txtUsuario.setBounds(x, y, 80, 30);
        y += 50;
        txtContraseña.setBounds(x, y, 80, 30);
        y += 70;
        btIniciar.setBounds(x, y, 80, 30);
        lbNombre.setBounds(240,70,80,30);
        txtNombre.setBounds(335,70,80,30);

        panelPrincipal.setLayout(null);
        panelPrincipal.add(lbTitulo);
        panelPrincipal.add(lbContraseña);
        panelPrincipal.add(lbUsuario);
        panelPrincipal.add(btSalir);
        panelPrincipal.add(btIniciar);
        panelPrincipal.add(txtUsuario);
        panelPrincipal.add(txtContraseña);
        panelPrincipal.add(lbRegistro);
        add(panelPrincipal, BorderLayout.CENTER);
    }



    public String autenticar (String usuario, String contraseña){
        String autenticacion = "El usuario o contraseña es incorrecto";
        for (Usuario aux: usuarios){
            if (aux.getCuenta().equals(usuario) && aux.getContraseña().equals(contraseña)) {
                autenticacion = "Bienvenido " + aux.getNombre();
            }
        }
        return autenticacion;
    }

    public void registrar (String nombre, String usuario, String contraseña) {
        Usuario aux = new Usuario(nombre,usuario,contraseña);
        usuarios.add(aux);
    }

    public static void main(String[] args) {
        new Ventana();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        panelPrincipal.add(lbNombre);
        panelPrincipal.add(txtNombre);
        lbTitulo.setText("Registrar");
        btIniciar.setText("Registrar");
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
