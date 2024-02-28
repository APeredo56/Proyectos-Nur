/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro2.pkgfinal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author BlueFox
 */
public class Ventana extends JFrame implements ActionListener {

    private JButton btnDiez = new JButton(new ImageIcon("10bol.jpg"));
    private JButton btnVeinte = new JButton(new ImageIcon("20bol.jpg"));
    private JButton btnCincuenta = new JButton(new ImageIcon("50bol.jpg"));
    private JButton btnCien = new JButton(new ImageIcon("100bol.jpg"));
    private JButton btnHamburguesa = new JButton("Hamburguesa");
    private JButton btnPollo = new JButton("Pollo");
    private JButton btnSoda = new JButton("Soda");
    private JTextArea taPedidos=new JTextArea();
    private JTextField txtTotal=new JTextField();
    private JTextField txtPagado=new JTextField();
    private JTextField txtCambio=new JTextField();
    private JPanel pnlOpciones = new JPanel();
    private JPanel panelPrincipal = new JPanel();
    private JMenuItem teorico = new JMenuItem("Preguntas Teoricas");
    private JMenuItem practico = new JMenuItem("Preguntas Practicas");
    private ArrayList<Producto> lista = new ArrayList<Producto>();

    private JButton btLimpiar = new JButton("Limpiar");

    private int totalPagado = 0;

    public Ventana() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        btnDiez.setMaximumSize(new Dimension(150, 85));
        btnVeinte.setMaximumSize(new Dimension(150, 85));
        btnCincuenta.setMaximumSize(new Dimension(150, 85));
        btnCien.setMaximumSize(new Dimension(150, 85));
        txtCambio.setEditable(false);
        txtPagado.setEditable(false);
        txtTotal.setEditable(false);
        taPedidos.setEditable(false);
        pnlOpciones.add(btnDiez);
        pnlOpciones.add(btnVeinte);
        pnlOpciones.add(btnCincuenta);
        pnlOpciones.add(btnCien);
        pnlOpciones.setLayout(new BoxLayout(pnlOpciones, BoxLayout.Y_AXIS));
        this.getContentPane().add(pnlOpciones, BorderLayout.WEST);

        btnHamburguesa.addActionListener(this);
        btnPollo.addActionListener(this);
        btnSoda.addActionListener(this);
        btnDiez.addActionListener(this);
        btnVeinte.addActionListener(this);
        btnCincuenta.addActionListener(this);
        btnCien.addActionListener(this);
        panelPrincipal.setLayout(null);
        JLabel campo=new JLabel("<html><br><br><strong>FINAL DE PROGRAMACION II</strong></html>");
        JLabel lbPedidos=new JLabel("Pedidos");
        JLabel lbTotal=new JLabel("Total");
        JLabel lbPagado=new JLabel("Pagado");
        JLabel lbCambio=new JLabel("Cambio");
        lbPedidos.setBounds(10, 90, 100, 25);
        lbTotal.setBounds(280, 90, 100, 25);
        lbPagado.setBounds(280, 120, 100, 25);
        lbCambio.setBounds(280, 150, 100, 25);
        taPedidos.setBounds(70, 90, 130, 125);
        txtTotal.setBounds(350, 90, 100, 25);
        txtPagado.setBounds(350, 120, 100, 25);
        txtCambio.setBounds(350, 150, 100, 25);
        campo.setBounds(150, 0, 200, 45);

        
        btnHamburguesa.setBounds(100, 250, 100, 25);
        btnPollo.setBounds(200, 250, 100, 25);
        btnSoda.setBounds(300, 250, 100, 25);
        btLimpiar.setBounds(380,250,100,25);

        panelPrincipal.add(campo);
        panelPrincipal.add(lbPedidos);
        panelPrincipal.add(lbTotal);
        panelPrincipal.add(lbPagado);
        panelPrincipal.add(lbCambio);
        panelPrincipal.add(taPedidos);
        panelPrincipal.add(txtTotal);
        panelPrincipal.add(txtPagado);
        panelPrincipal.add(txtCambio);
        panelPrincipal.add(btnHamburguesa);
        panelPrincipal.add(btnPollo);
        panelPrincipal.add(btnSoda);
        panelPrincipal.add(btLimpiar);
        btLimpiar.addActionListener(this);
        this.add(panelPrincipal);

        JMenuBar menu = new JMenuBar();
        JMenu ayuda = new JMenu("Ayuda");
        teorico.addActionListener(this);
        ayuda.add(teorico);
        practico.addActionListener(this);
        ayuda.add(practico);
        menu.add(ayuda);
        this.setJMenuBar(menu);

        this.setMenuBar(new MenuBar());

        this.setSize(new Dimension(650, 400));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDiez) {
            totalPagado += 10;
            txtPagado.setText(totalPagado + "");
            /*this.remove(panelPrincipal);
            panelPrincipal = new Registro(lista);
            this.add(panelPrincipal, BorderLayout.CENTER);*/
            calcularCambio();
        }
        if (e.getSource() == btnVeinte){
            totalPagado += 20;
            txtPagado.setText(totalPagado + "");
            calcularCambio();
        }
        if (e.getSource() == btnCincuenta){
            totalPagado += 50;
            txtPagado.setText(totalPagado + "");
            calcularCambio();
        }
        if (e.getSource() == btnCien){
            totalPagado += 100;
            txtPagado.setText(totalPagado + "");
            calcularCambio();
        }
        if (e.getSource() == btnHamburguesa){
            lista.add(new Producto(0, "Hamburguesa", 20));
            txtTotal.setText(calcularTotal() + "");
            anotarPedidos();
            calcularCambio();
        }
        if (e.getSource() == btnPollo){
            lista.add(new Producto(0, "Pollo", 15));
            txtTotal.setText(calcularTotal() + "");
            anotarPedidos();
            calcularCambio();
        }
        if (e.getSource() == btnSoda){
            lista.add(new Producto(0, "Soda", 12));
            txtTotal.setText(calcularTotal() + "");
            anotarPedidos();
            calcularCambio();
        }

        if (e.getSource() == teorico) {
            this.remove(panelPrincipal);
            JPanel panel_teorico = new JPanel();
            panel_teorico.add(new JLabel("<html><br><strong>PREGUNTAS TEORICAS</strong><br><br>"
                    + "1. Diferencia de clase y objeto<br>"
                    + "2. Diferencia entre metodo y constructor<br>"
                    + "3. ¿Qué es un Diagrama de Clases?<br>"
                    + "4. ¿Qué es herencia, abstracción y polimorfismo?<br>"
                    + "5. ¿Es mejor utilizar objetos al programar? ¿Por qué?</html>"));
            this.add(panel_teorico, BorderLayout.CENTER);
            JButton volver=new JButton("Volver");
            this.add(volver,BorderLayout.NORTH);
            volver.addActionListener(ev -> {
                remove(panel_teorico);
                remove(volver);
                add(panelPrincipal,BorderLayout.CENTER);
                validate();
                repaint();
            });
            this.validate();
        }
        if (e.getSource() == btLimpiar){
            limpiarDatos();
            this.validate();
        }
        if (e.getSource() == practico) {
            this.remove(panelPrincipal);
            JPanel panel_practico = new JPanel();
            panel_practico.add(new JLabel("<html><br><strong>PREGUNTAS PRACTICAS</strong><br><br>"
                    + "1. Implementar la funcionalidad de los botones de montos de la izquierda<br> para los pagos del cliente, cada click debe acumular en el campo Pagado<br>"
                    + "2. Implementar la funcionalidad de los productos a elegir, <br>cada producto esta almacenado en la variable lista<br>"
                    + "3. Realizar el calculo automatico para saber cuanto cambio debe darse al cliente<br>"
                    + "4. Mostrar en el JTextArea pedidos los productos que han sido solicitados<br> y adicionar un boton para limpiar los datos ingresados<br>"
                    + "5. Dibujar el diagrama de clases de este examen</html>"));
            this.add(panel_practico, BorderLayout.CENTER);
            JButton volver=new JButton("Volver");
            this.add(volver,BorderLayout.NORTH);
            volver.addActionListener(ev -> {
                remove(panel_practico);
                remove(volver);
                add(panelPrincipal,BorderLayout.CENTER);
                validate();
                repaint();
            });
            this.validate();
        }
    }

    public int calcularTotal (){
        int total = 0;
        for (Producto aux: lista) {
            total += aux.getPrecio();
        }
        return total;
    }
    public void anotarPedidos (){
        int hamburguesas = 0;
        int sodas = 0;
        int pollos = 0;
        String pedidos = "";
        for (Producto aux: lista) {
            switch (aux.getNombre()){
                case "Hamburguesa":
                    hamburguesas += 1;
                    pedidos += "Hamburguesa" + "\n";
                    break;
                case "Pollo":
                    pollos += 1;
                    pedidos += "Pollo" + "\n";
                    break;
                case "Soda":
                    sodas += 1;
                    pedidos += "Soda" + "\n";
                    break;
            }
        }
        taPedidos.setText(pedidos);

    }
    public void calcularCambio (){
        int totalPagar = Integer.parseInt(txtTotal.getText());
        txtCambio.setText((totalPagado - totalPagar)  + "");
        this.validate();
    }
    public void limpiarDatos (){
        totalPagado = 0;
        txtTotal.setText("");
        txtCambio.setText("");
        txtPagado.setText("0");
        taPedidos.setText("");
        lista.clear();
    }
}
