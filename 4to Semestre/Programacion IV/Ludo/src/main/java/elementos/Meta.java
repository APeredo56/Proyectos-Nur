package elementos;

import paneles.Tablero;

import java.awt.*;

public class Meta extends ElementoTablero{

    private int[] puntosX = new int[3];
    private int[] puntosY = new int[3];
    private Pieza[] piezas = new Pieza[4];
    private int numPiezas = 0;
    private Tablero.Posicion posicion;


    public Meta(Color color) {
        super(color);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillPolygon(puntosX, puntosY, 3);
        g.setColor(Color.black);
        g.drawPolygon(puntosX, puntosY, 3);
        dibujarPiezas(g);
    }

    public void dibujarPiezas(Graphics g){
        Pieza actual;
        Point centro = new Point(puntosX[1], puntosY[1]);
        size = puntosX[0] - centro.x;
        if (size < 0){
            size = size * (-1);
        }
        int sizePiezas = size / 2;
        for (int i = 0; i <= 3; i++) {
            actual = piezas[i];
            if (actual == null){
                return;
            }
            switch (posicion){
                case izquierda, derecha ->{
                    //Las 3 piezas mas cercanas al borde tienen el mismo x
                    if (posicion == Tablero.Posicion.izquierda) {
                        actual.setPosX(centro.x - size);
                    } else {
                        actual.setPosX(centro.x + sizePiezas);
                    }
                    switch (i){
                        case 0 -> {
                            if (posicion == Tablero.Posicion.izquierda) {
                                actual.acomodar(centro.x - size + sizePiezas, centro.y - sizePiezas/2, sizePiezas);
                            } else {
                                actual.acomodar(centro.x, centro.y -sizePiezas/2, sizePiezas);
                            }
                        }
                        case 1  -> actual.setPosY(centro.y - sizePiezas*3/2);
                        case 2 -> actual.setPosY(centro.y - sizePiezas/2);
                        case 3 -> actual.setPosY(centro.y + sizePiezas/2);
                    }
                }
                case arriba, abajo -> {
                    //Las 3 piezas mas cercanas al borde tienen el mismo y
                    if (posicion == Tablero.Posicion.arriba) {
                        actual.setPosY(centro.y - size);
                    } else {
                        actual.setPosY(centro.y + sizePiezas);
                    }
                    switch (i){
                        case 0 -> {
                            if (posicion == Tablero.Posicion.arriba) {
                                actual.acomodar(centro.x - sizePiezas / 2, centro.y - sizePiezas, sizePiezas);
                            } else {
                                actual.acomodar(centro.x - sizePiezas / 2, centro.y, sizePiezas);
                            }
                        }
                        case 1 -> actual.setPosX(centro.x - sizePiezas*3/2);
                        case 2 -> actual.setPosX(centro.x - sizePiezas/2);
                        case 3 -> actual.setPosX(centro.x + sizePiezas/2);
                    }
                }
            }
            actual.setSize(sizePiezas);
            actual.dibujar(g);
        }
    }

    public void agregarPieza(Pieza pieza) {
        piezas[numPiezas] = pieza;
        numPiezas++;
    }

    public int getNumPiezas() {
        return numPiezas;
    }

    public void setPosX(int[] posX) {
        this.puntosX = posX;
    }

    public void setPosY(int[] posY) {
        this.puntosY = posY;
    }

    public void setPosicion(Tablero.Posicion posicion) {
        this.posicion = posicion;
    }
}
