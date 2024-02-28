package paneles;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {
    private Image imagen;
    JButton button = new JButton();
    JButton button2 = new JButton();
    public PanelInicio(){
        //paint();
        imagen = Toolkit.getDefaultToolkit().createImage("src\\imagenes\\fondo inicio.png");
        init();
    }


    public void init(){
        this.setLayout(null);
        int separador = 16;

        add(Box.createVerticalStrut(separador));
        button.setBackground(Color.RED);
        button.setIcon(new ImageIcon("src\\imagenes\\btn unirse.png"));
        add(button);

        add(Box.createVerticalStrut(separador));


        button2.setMaximumSize(new Dimension(50,50));
        button2.setBackground(Color.YELLOW);
        button2.setIcon(new ImageIcon("src\\imagenes\\btn crear.png"));
        add(button2);
        add(Box.createVerticalStrut(separador));
    }

    public void paint(Graphics g) {
        //imagen = new ImageIcon(getClass().getResource("src\\imagenes\\dado.png")).getImage();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        button.setBounds(270,240,150,60);
        button2.setBounds(500,240,150,60);
        //super.paint(g);
    }
}
