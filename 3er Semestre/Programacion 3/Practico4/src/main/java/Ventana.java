import listas.Lista;
import modelo.Persona;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ventana extends JFrame {

    private JLabel lbTexto = new JLabel("Ingrese el texto: ");
    private JTextArea txtTexto = new JTextArea();
    private JButton btnCargar = new JButton("Cargar Datos");
    private JButton btnEliminar = new JButton("Eliminar");
    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private JTable tbTabla;
    private JScrollPane scrollPane;
    private JScrollPane scrollPaneTexto;

    private Lista<Persona> listaPersonas = new Lista<>();
    private Lista<String> grupos;
    private int personasEnLista = 0;

    private String expreg = "^([A-Za-z|Ñ|ñ])([A-Za-z|(áéíóúÑñ)]+)\\s+([A-Za-z|Ñ|ñ])([A-Za-z|(áéíóúÑñ)]+)\\s+([A-Za-z|Ñ|ñ])([A-Za-z|(áéíóúÑñ)]+)\\s+([0-9]{2})$";

    private static final Logger logger = LogManager.getLogger(Ventana.class);

    public Ventana (){
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        cargarComponentes();
        setVisible(true);
    }

    public void cargarComponentes(){
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Edad");

        tbTabla = new JTable(modeloTabla);
        scrollPane = new JScrollPane(tbTabla);
        scrollPaneTexto = new JScrollPane(txtTexto);

        TableColumn columna = tbTabla.getColumn("Edad");
        columna.setMinWidth(40);
        columna.setMaxWidth(40);

        lbTexto.setBounds(20,20,100,30);
        scrollPaneTexto.setBounds(130,20,250,200);
        btnCargar.setBounds(450,20,110,30);
        btnEliminar.setBounds(450,60,110,30);
        scrollPane.setBounds(20,250,500,300);

        txtTexto.setBorder(new LineBorder(Color.black));
        txtTexto.setLineWrap(true);
        txtTexto.setWrapStyleWord(true);

        btnCargar.addActionListener(e -> accionCargar());
        btnEliminar.addActionListener(e -> accionEliminar());

        this.add(lbTexto);
        this.add(scrollPane);
        this.add(scrollPaneTexto);
        this.add(btnCargar);
        this.add(btnEliminar);
    }

    public void accionCargar (){
        String datos = txtTexto.getText() + "\n";
        grupos = new Lista<>();
        guardarLineas(datos);

        Pattern p = Pattern.compile(expreg);
        for (int i = 0; i < grupos.tamano(); i++) {
            Matcher m = p.matcher(grupos.obtener(i));
            if (m.find()){
                Persona persona = new Persona(m.group(1).toUpperCase() + m.group(2).toLowerCase(),
                         m.group(3).toUpperCase() + m.group(4).toLowerCase(),
                        m.group(5).toUpperCase() + m.group(6).toLowerCase(),
                        Integer.parseInt(m.group(7)));
                listaPersonas.adicionar(persona);
            } else{
                logger.debug("No se reconocio como persona el texto: " + grupos.obtener(i));
            }
        }
        cargarTabla();
        grupos = null;
    }

    public void accionEliminar(){
        listaPersonas.eliminar(tbTabla.getSelectedRow());
        modeloTabla.removeRow(tbTabla.getSelectedRow());
    }

    public void guardarLineas (String texto){
        Scanner scanner = new Scanner(texto);
        boolean siguienteLinea = true;
        try {
            while (siguienteLinea) {
                grupos.adicionar(scanner.nextLine());
            }
        }catch (Exception e){
            siguienteLinea = false;
        }

    }

    public void cargarTabla (){
        for (int i = personasEnLista; i < listaPersonas.tamano(); i++) {
            Persona pActual = listaPersonas.obtener(i);
            modeloTabla.addRow(new Object[]{pActual.getNombre(), pActual.getPrimerApellido() + " " +
                    pActual.getSegundoApellido(), pActual.getEdad()});
        }
        personasEnLista = listaPersonas.tamano();
    }

    public static void main(String[] args){
        Ventana v = new Ventana();
    }

}
