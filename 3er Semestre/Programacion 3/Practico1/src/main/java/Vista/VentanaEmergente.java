package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaEmergente extends JFrame {
    private JButton btnOk = new JButton("Ok");
    private JLabel lbPosicion = new JLabel("Posición:");
    private JLabel lbNumero = new JLabel("Número:");
    private JTextField txtPosicion = new JTextField();
    private JTextField txtNumero = new JTextField();

    public VentanaEmergente () {
        cargarComponentes();
    }

    public void cargarComponentes(){
        int x = 30;
        int y = 0;

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(300,270);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setBackground(Color.lightGray);
        lbPosicion.setBounds(x,y,80,50);
        y += 50;
        txtPosicion.setBounds(x,y,100,30);
        y += 50;
        lbNumero.setBounds(x,y,80,50);
        y += 50;
        txtNumero.setBounds(x,y,100,30);
        y += 50;
        btnOk.setBounds(x,y,this.getWidth(),50);


        this.add(lbPosicion, BorderLayout.SOUTH);
        this.add(txtPosicion, BorderLayout.SOUTH);
        this.add(lbNumero, BorderLayout.SOUTH);
        this.add(txtNumero, BorderLayout.SOUTH);
        this.add(btnOk,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public JTextField getTxtNumero() {
        return txtNumero;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public JTextField getTxtPosicion() {
        return txtPosicion;
    }

    public void setTxtPosicion(JTextField txtPosicion) {
        this.txtPosicion = txtPosicion;
    }

    public void setTxtNumero(JTextField txtNumero) {
        this.txtNumero = txtNumero;
    }
}
