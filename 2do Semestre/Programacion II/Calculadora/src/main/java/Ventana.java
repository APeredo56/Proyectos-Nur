import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ventana extends JFrame {

    private JButton btn1 = new JButton("1");
    private JButton btn2 = new JButton("2");
    private JButton btn3 = new JButton("3");
    private JButton btn4 = new JButton("4");
    private JButton btn5 = new JButton("5");
    private JButton btn6 = new JButton("6");
    private JButton btn7 = new JButton("7");
    private JButton btn8 = new JButton("8");
    private JButton btn9 = new JButton("9");
    private JButton btn0 = new JButton("0");
    private JButton btnSuma = new JButton("+");
    private JButton btnResta = new JButton("-");
    private JButton btnMultiplicacion = new JButton("*");
    private JButton btnDivision = new JButton("/");
    private JButton btnComa = new JButton(".");
    private JButton btnIgual = new JButton("=");
    private JButton btnBorrar = new JButton("DEL");
    private JButton btnBorrarTodo = new JButton("AC");
    private JTextField txtOperaciones = new JTextField();
    private JTextField txtResultado = new JTextField();
    private JLabel lbTitulo = new JLabel("Calculadora");
    private JPanel panelBotones = new JPanel();

    private String textoOperaciones = "";
    private String auxiliarTextoOperaciones = "";
    private double resultado = 0;
    private double numeroAdicional;
    private boolean existePrimerNumero = false;
    private int indice;



    public Ventana () {
        setTitle("Calculadora");
        setSize(375, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarPanelBotones();
        JOptionPane.showMessageDialog(null, "La calculadora solo funciona con los botones");
        btn1.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "1";
            txtOperaciones.setText(textoOperaciones);
        });
        btn2.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "2";
            txtOperaciones.setText(textoOperaciones);
        });
        btn3.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "3";
            txtOperaciones.setText(textoOperaciones);
        });
        btn4.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "4";
            txtOperaciones.setText(textoOperaciones);
        });
        btn5.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "5";
            txtOperaciones.setText(textoOperaciones);
        });
        btn6.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "6";
            txtOperaciones.setText(textoOperaciones);
        });
        btn7.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "7";
            txtOperaciones.setText(textoOperaciones);
        });
        btn8.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "8";
            txtOperaciones.setText(textoOperaciones);
        });
        btn9.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "9";
            txtOperaciones.setText(textoOperaciones);
        });
        btn0.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "0";
            txtOperaciones.setText(textoOperaciones);
        });
        btnSuma.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "+";
            txtOperaciones.setText(textoOperaciones);
            setPrimerNumero();
            realizarOperacion();
            txtResultado.setText(resultado+"");
        });
        btnResta.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "-";
            txtOperaciones.setText(textoOperaciones);
            setPrimerNumero();
            realizarOperacion();
            txtResultado.setText(resultado+"");
        });
        btnMultiplicacion.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "*";
            txtOperaciones.setText(textoOperaciones);
            setPrimerNumero();
            realizarOperacion();
            txtResultado.setText(resultado+"");
        });
        btnDivision.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "/";
            txtOperaciones.setText(textoOperaciones);
            setPrimerNumero();
            realizarOperacion();
            txtResultado.setText(resultado+"");
        });
        btnComa.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + ".";
            txtOperaciones.setText(textoOperaciones);});
        btnBorrar.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText();
            textoOperaciones = textoOperaciones.substring(0, textoOperaciones.length() - 1);
            txtOperaciones.setText(textoOperaciones);});
        btnBorrarTodo.addActionListener(e -> {
            textoOperaciones = "";
            auxiliarTextoOperaciones = "";
            resultado = 0;
            existePrimerNumero = false;
            txtOperaciones.setText(textoOperaciones);
            txtResultado.setText("");
        });
        btnIgual.addActionListener(e -> {
            textoOperaciones = txtOperaciones.getText() + "=";
            txtOperaciones.setText(textoOperaciones);
            realizarOperacion();
            txtResultado.setText(resultado + "");
        });

        setVisible(true);
    }

    public void cargarPanelBotones (){
        int x = 20;
        int y = 10;
        panelBotones.setLayout(null);
        lbTitulo.setBounds(x,y,80,30);
        y += 30;
        txtOperaciones.setBounds(x,y,320,40);
        y += 60;
        txtResultado.setBounds(x,y,320,40);
        y += 60;
        btn1.setBounds(x,y,50,50);
        x += 60;
        btn2.setBounds(x,y,50,50);
        x += 60;
        btn3.setBounds(x,y,50,50);
        x += 60;
        btnSuma.setBounds(x,y,50,50);
        x += 60;
        btnBorrar.setBounds(x,y,80,50);
        x -= 240;
        y += 60;
        btn4.setBounds(x,y,50,50);
        x += 60;
        btn5.setBounds(x,y,50,50);
        x += 60;
        btn6.setBounds(x,y,50,50);
        x += 60;
        btnResta.setBounds(x,y,50,50);
        x += 60;
        btnBorrarTodo.setBounds(x,y,80,50);
        x -= 240;
        y += 60;
        btn7.setBounds(x,y,50,50);
        x += 60;
        btn8.setBounds(x,y,50,50);
        x += 60;
        btn9.setBounds(x,y,50,50);
        x += 60;
        btnMultiplicacion.setBounds(x,y,50,50);
        x -= 180;
        y += 60;
        btnComa.setBounds(x,y,50,50);
        x += 60;
        btn0.setBounds(x,y,50,50);
        x += 60;
        btnIgual.setBounds(x,y,50,50);
        x += 60;
        btnDivision.setBounds(x,y,50,50);

        panelBotones.setPreferredSize(new Dimension(300,550));
        panelBotones.add(lbTitulo);
        panelBotones.add(txtOperaciones);
        panelBotones.add(txtResultado);
        panelBotones.add(btn1);
        panelBotones.add(btn2);
        panelBotones.add(btn3);
        panelBotones.add(btn4);
        panelBotones.add(btn5);
        panelBotones.add(btn6);
        panelBotones.add(btn7);
        panelBotones.add(btn8);
        panelBotones.add(btn9);
        panelBotones.add(btn0);
        panelBotones.add(btnSuma);
        panelBotones.add(btnResta);
        panelBotones.add(btnMultiplicacion);
        panelBotones.add(btnDivision);
        panelBotones.add(btnComa);
        panelBotones.add(btnIgual);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnBorrarTodo);
        this.add(panelBotones);

    }


    public void setPrimerNumero (){
        if (!existePrimerNumero) {
            resultado = Double.parseDouble(textoOperaciones.substring(0, textoOperaciones.length() - 1));
            existePrimerNumero = true;
        }
    }
    public void realizarOperacion(){
        if (auxiliarTextoOperaciones.length() > 0){
            indice = textoOperaciones.length() - (textoOperaciones.length() - auxiliarTextoOperaciones.length());
            String operacion = textoOperaciones.charAt(indice - 1) + "";
            switch (operacion){
                case "+":
                    resultado += Double.parseDouble(textoOperaciones.substring(indice, textoOperaciones.length() - 1));
                    break;
                case "-":
                    resultado -= Double.parseDouble(textoOperaciones.substring(indice, textoOperaciones.length() - 1));
                    break;
                case "*":
                    resultado = resultado * Double.parseDouble(textoOperaciones.substring(indice, textoOperaciones.length() - 1));
                    break;
                case "/":
                    resultado = resultado / Double.parseDouble(textoOperaciones.substring(indice, textoOperaciones.length() - 1));
                    break;

            }
        }
        auxiliarTextoOperaciones = textoOperaciones;
    }
}
