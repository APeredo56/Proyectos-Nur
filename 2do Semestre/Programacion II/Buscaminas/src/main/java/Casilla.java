import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Casilla extends JLabel {
    public static int tamano = 25;
    private boolean revelada = false;

    private String texto = " ";

    public Casilla(int x, int y){
        setBounds(x,y,tamano,tamano);
        setFont(new Font("Arial", 0,12));
        setBorder(new LineBorder(Color.black, 1));
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        setBackground(Color.gray);
    }
    public void revelarTexto(){
        setText(texto);
        if (!texto.equals("M")){
            revelada = true;
            setBackground(Color.white);
        }

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isRevelada() {
        return revelada;
    }
}
