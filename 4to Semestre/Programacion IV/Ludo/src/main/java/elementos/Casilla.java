package elementos;

import java.awt.*;
import java.util.ArrayList;

public class Casilla extends ElementoTablero {
    private Casilla siguiente;
    private ArrayList<Pieza> piezas = new ArrayList<>();
    private boolean segura;
    private boolean entrada;

    public Casilla(int posX, int posY, int size, Color color) {
        super(posX, posY, size, color);
    }

    public Casilla(Color color, boolean segura, boolean entrada) {
        super(color);
        this.segura = segura;
        this.entrada = entrada;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        if (entrada){
            g.setColor(Color.white);
        }
        g.fillRect(posX, posY, size, size);
        g.setColor(Color.black);
        g.drawRect(posX, posY, size, size);

        if (segura || entrada) {
            if (segura) {
                g.setColor(Color.white);
            } else {
                g.setColor(color);
            }
            int sector = size / 3;
            g.fillPolygon(new int[]{posX + size / 2, posX + sector * 2 - bordes, posX + size - bordes,
                    posX + sector * 2 - bordes, posX + size / 2, posX + sector + bordes, posX + bordes,
                    posX + sector + bordes}, new int[]{posY + bordes, posY + sector + bordes, posY + size / 2,
                    posY + sector * 2 - bordes, posY + size - bordes, posY + sector * 2 - bordes, posY + size / 2,
                    posY + sector + bordes}, 8);
        }
        dibujarPiezas(g);
    }

    private void dibujarPiezas(Graphics g){
        switch (piezas.size()){
            case 1 -> piezas.get(0).acomodar(posX, posY, size);
            case 2 -> {
                piezas.get(0).acomodar(posX, posY, size/2);
                piezas.get(1).acomodar(posX + size/2, posY + size/2, size/2);
            }
            case 3, 4 ,5 -> {
                piezas.get(0).acomodar(posX, posY, size/2);
                piezas.get(1).acomodar(posX + size/2, posY, size/2);
                piezas.get(2).acomodar(posX, posY +  size/2, size/2);
                if (piezas.size()<4) {
                    return;
                }
                piezas.get(3).acomodar(posX + size / 2, posY + size / 2, size / 2);
                if (piezas.size()< 5){
                    return;
                }
                piezas.get(4).acomodar(posX + size/4, posY + size/4, size/2);
            }
        }
    }

    public Casilla getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Casilla siguiente) {
        this.siguiente = siguiente;
    }

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public boolean isSegura() {
        return segura;
    }

    public boolean isEntrada() {
        return entrada;
    }
}
