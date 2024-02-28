import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Semaforo extends JPanel {
    private ArrayList<Graphics> circulos = new ArrayList<>();

    int temporizador = -1;

    public Semaforo (int x, int y, int ancho, int alto){
        this.setBounds(x,y,ancho,alto);
        //this.setBackground(Color.red);
    }
    @Override
    protected void paintComponent(Graphics g) {
        int x = (this.getWidth() - 166)/2;
        int y = 10;
        super.paintComponent(g);
        if (circulos.size()== 0) {
            for (int i = 0; i < 4; i++) {
                g.setColor(Color.darkGray);
                g.fillOval(x, y, 30, 30);
                circulos.add(g);
                x += 40;
            }
        }
        x = (this.getWidth() - 166)/2;
        int contador = 0;
        for (Graphics aux:circulos) {
            g.setColor(Color.darkGray);
            if (contador <= temporizador){
                g.setColor(Color.red);
            }
            if (temporizador == 3 && contador == 3){
                g.setColor(Color.green);
            }
            g.fillOval(x,y,30,30);
            contador ++;
            x += 40;
        }
        contador = 0;
    }
    public void aumentarTemporizador (){
        temporizador += 1;
        if (temporizador == 4){
            temporizador = -1;
        }
    }

    public int getTemporizador() {
        return temporizador;
    }

    public void setTemporizador(int temporizador) {
        this.temporizador = temporizador;
    }

    public ArrayList<Graphics> getCirculos() {
        return circulos;
    }
}
