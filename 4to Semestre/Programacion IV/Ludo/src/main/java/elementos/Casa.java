package elementos;

import java.awt.*;
import java.util.ArrayList;

public class Casa extends ElementoTablero{
    private Pieza[] piezas = new Pieza[4];

    public Casa(int posX, int posY, int size, Color color) {
        super(posX, posY, size, color);
    }

    public Casa(Color color){
        super(color);

    }

    @Override
    public void dibujar(Graphics g) {
        int separador = size/6;
        g.setColor(color);
        g.fillRect(posX, posY, size, size);
        g.setColor(Color.white);
        g.fillRect(posX + separador, posY + separador, size -separador*2, size -separador*2);
        g.setColor(color);
        int x1 = (int) (posX + separador*1.5);
        int x2 = (int) (posX + separador*3.5);
        int y1 = (int) (posY + separador*1.5);
        int y2 = (int) (posY + separador*3.5);

        g.fillOval(x1, y1, separador,separador);
        g.fillOval(x2, y1, separador,separador);
        g.fillOval(x1, y2, separador,separador);
        g.fillOval(x2, y2, separador,separador);

        g.setColor(Color.black);
        g.drawRect(posX, posY, size, size);

        dibujarPiezas(g, x1, x2, y1, y2, separador);
    }

    private void dibujarPiezas(Graphics g, int x1, int x2, int y1, int y2, int sizePieza){
        int contador = 0;
        int separador = size/12;
        for (Pieza aux:piezas) {
            if (aux == null){
                contador++;
                continue;
            }
            switch (contador){
                case 0 -> aux.acomodar(x1 + separador - sizePieza/2, y1 + separador - sizePieza/2, sizePieza);
                case 1 -> aux.acomodar(x2 + separador - sizePieza/2, y1 + separador - sizePieza/2, sizePieza);
                case 2 -> aux.acomodar(x1 + separador - sizePieza/2, y2 + separador - sizePieza/2, sizePieza);
                case 3 -> aux.acomodar(x2 + separador - sizePieza/2, y2 + separador - sizePieza/2, sizePieza);
            }
            aux.dibujar(g);
            contador++;
        }
    }

    public Pieza getPieza(Pieza pieza) {
        for (Pieza aux: piezas) {
            if (aux == pieza){
                return aux;
            }
        }
        return null;
    }

    public void agregarPieza(Pieza pieza){
        int contador = 0;
        for (Pieza aux: piezas){
            if (aux == null){
                piezas[contador] = pieza;
                return;
            }
            contador++;
        }
    }

    public void eliminarPieza(Pieza pieza){
        int contador = 0;
        for (Pieza aux: piezas){
            if (aux == pieza){
                piezas[contador] = null;
                return;
            }
            contador++;
        }
    }
}
