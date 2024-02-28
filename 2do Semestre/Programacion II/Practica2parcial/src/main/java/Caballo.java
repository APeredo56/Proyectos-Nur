import javax.swing.*;
import java.awt.*;

public class Caballo extends JLabel {
    public Caballo (int x, int y, Color color, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        setBounds(x,y,size, size);
        setOpaque(true);
        setBackground(color);

    }
    private boolean paso;
    private int x;
    private int y;
    private int size;

    public void pasoIzquierdo(){
        if (paso){
            x += 10;
            paso = !paso;
            setBounds(x,y,size, size);
        }
    }
    public void pasoDerecho(){
        if (!paso){
            x += 10;
            paso = !paso;
            setBounds(x,y,size, size);
        }
    }

}
