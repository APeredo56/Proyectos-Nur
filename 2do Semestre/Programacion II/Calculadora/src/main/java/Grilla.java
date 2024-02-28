import javax.swing.*;
import java.awt.*;

public class Grilla extends JFrame {

    public Grilla(){
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10,10));
        for (int i = 1; i <= 100; i++) {
            String numero=""+i;
            JButton boton=new JButton(numero);
            boton.addActionListener(e->{
                JOptionPane.showMessageDialog(null,"Soy el boton nro "+numero);
            });
            add(boton);
        }
        setVisible(true);

    }

    public static void main(String[] args) {
        new Grilla();
    }
}
