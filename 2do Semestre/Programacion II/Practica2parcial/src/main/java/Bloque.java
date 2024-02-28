import javax.swing.*;
import java.awt.*;

public class Bloque extends JLabel {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Bloque (int x, int y, int ancho, int alto){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.setBounds(x,y,ancho,alto);
        this.setOpaque(true);
        this.setBackground(Color.DARK_GRAY);
    }
    public boolean colision (Caballo jugador) {
        Rectangle limite = this.getBounds();
        Rectangle limiteCaballo = jugador.getBounds();
        return limite.intersects(limiteCaballo);
    }

}
