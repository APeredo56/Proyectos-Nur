package Vista;

import conexion.Conexion;
import dao.ProductoDao;
import dto.ProductoDTO;
import listas.Lista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Ventana extends JFrame {

    private JLabel lbNombre = new JLabel("Nombre:");
    private JLabel lbCodigo = new JLabel("Codigo:");
    private JLabel lbPrecio = new JLabel("Precio:");
    private JLabel lbCantidad = new JLabel("Cantidad:");
    private JLabel lbFVencimiento = new JLabel("Fecha Vencimiento:");

    private JTextField txtNombre = new JTextField();
    private JTextField txtCodigo = new JTextField();
    private JTextField txtPrecio = new JTextField();
    private JTextField txtCantidad = new JTextField();
    private JTextField txtFVencimiento = new JTextField();

    private JButton btnAgregar = new JButton("Agregar");
    private JButton btnEliminar = new JButton("Eliminar");
    private JButton btnModificar = new JButton("Modificar");

    private JPanel panelMenu = new JPanel();

    private JTable tbTabla = new JTable();
    private DefaultTableModel modelo = new DefaultTableModel();
    private JScrollPane scrollPane = new JScrollPane(tbTabla);

    private Conexion conexion = Conexion.getInstance();
    private ProductoDao productoDao = new ProductoDao();

    public Ventana (){
        setSize(600,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cargarComponentes();
        setVisible(true);
    }

    public void cargarComponentes(){
        panelMenu.setPreferredSize(new Dimension(600,200));
        panelMenu.setLayout(null);
        panelMenu.setOpaque(true);
        panelMenu.setBackground(Color.blue.brighter().brighter());
        int xLabel = 10;
        int xTexto = 150;
        int altura = 30;
        int anchoLabel = 130;
        int anchoTexto = 200;

        lbNombre.setBounds(xLabel,10,anchoLabel,altura);
        lbCodigo.setBounds(xLabel,50,anchoLabel,altura);
        lbPrecio.setBounds(xLabel,90,anchoLabel,altura);
        lbCantidad.setBounds(xLabel,130,anchoLabel,altura);
        lbFVencimiento.setBounds(xLabel,170,anchoLabel,altura);

        txtNombre.setBounds(xTexto, 10, anchoTexto, altura);
        txtCodigo.setBounds(xTexto, 50, anchoTexto, altura);
        txtPrecio.setBounds(xTexto, 90, anchoTexto, altura);
        txtCantidad.setBounds(xTexto, 130, anchoTexto, altura);
        txtFVencimiento.setBounds(xTexto, 170, anchoTexto, altura);

        btnAgregar.setBounds(400,10,100,30);
        btnEliminar.setBounds(400,90,100,30);
        btnModificar.setBounds(400,170,100,30);

        btnAgregar.addActionListener(e -> accionAgregarProducto());
        btnModificar.addActionListener(e -> accionModificarProducto());
        btnEliminar.addActionListener(e -> accionEliminarProducto());

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Codigo");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Fecha Vencimiento");

        tbTabla.setModel(modelo);

        Lista<ProductoDTO> productos = productoDao.buscar("");
        for (ProductoDTO aux: productos) {
            modelo.addRow(new Object[]{aux.id(),aux.nombre(), aux.codigo(), aux.precio(), aux.cantidad(),
            aux.fechaVencimiento()});
        }

        panelMenu.add(lbNombre);
        panelMenu.add(lbCodigo);
        panelMenu.add(lbPrecio);
        panelMenu.add(lbCantidad);
        panelMenu.add(lbFVencimiento);
        panelMenu.add(txtNombre);
        panelMenu.add(txtCodigo);
        panelMenu.add(txtPrecio);
        panelMenu.add(txtCantidad);
        panelMenu.add(txtFVencimiento);
        panelMenu.add(btnAgregar);
        panelMenu.add(btnEliminar);
        panelMenu.add(btnModificar);

        add(panelMenu, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void accionAgregarProducto() {
        modelo.setRowCount(0);
        ProductoDTO producto = new ProductoDTO(1, txtNombre.getText(), txtCodigo.getText(),
                Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtCantidad.getText()),
                txtFVencimiento.getText());
        productoDao.agregarProducto(producto);
        actualizarTabla();
        limpiarCampos();
    }

    public void accionModificarProducto() {
        int id = (Integer) modelo.getValueAt(tbTabla.getSelectedRow(), 0);
        ProductoDTO producto = new ProductoDTO(id, txtNombre.getText(), txtCodigo.getText(),
                Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtCantidad.getText()),
                txtFVencimiento.getText());
        productoDao.update(producto);
        modelo.setRowCount(0);
        actualizarTabla();
        limpiarCampos();
    }

    public void accionEliminarProducto(){
        int id = (Integer) modelo.getValueAt(tbTabla.getSelectedRow(), 0);
        productoDao.eliminarProducto(id);
        modelo.removeRow(tbTabla.getSelectedRow());
        limpiarCampos();
    }

    public void actualizarTabla(){
        Lista<ProductoDTO> productos = productoDao.buscar("");
        for (ProductoDTO aux: productos) {
            modelo.addRow(new Object[]{aux.id(),aux.nombre(), aux.codigo(), aux.precio(), aux.cantidad(),
                    aux.fechaVencimiento()});
        }
    }

    public void limpiarCampos(){
        txtNombre.setText("");
        txtCodigo.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtFVencimiento.setText("");
    }

}
