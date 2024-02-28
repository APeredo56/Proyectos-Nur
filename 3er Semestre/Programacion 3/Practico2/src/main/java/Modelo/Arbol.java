package Modelo;

import java.awt.*;

public class Arbol {
    private int grosor;
    private Point puntoInicial;
    private int complejidadMain;

    public Arbol (Point puntoInicial, int grosor, int complejidad){
        this.grosor = grosor;
        this.puntoInicial = puntoInicial;
        this.complejidadMain = complejidad;
    }

    public void dibujar(Graphics g){
        g.setColor(new Color(122,72,41));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(grosor));

        dibujarArbol(g,puntoInicial, new Point(puntoInicial.x, puntoInicial.y - 30), new Point(puntoInicial.x + 15,
                puntoInicial.y - 45), new Point(puntoInicial.x - 15, puntoInicial.y - 45), complejidadMain);
    }

    public void dibujarArbol(Graphics g, Point puntoPartida, Point puntoAltura, Point puntoDerecha, Point puntoIzquierda,
                        int complejidad){
        int xPartida = puntoPartida.getLocation().x;
        int yPartida = puntoPartida.getLocation().y;
        int xAltura = puntoAltura.getLocation().x;
        int yAltura = puntoAltura.getLocation().y;
        int xDerecha = puntoDerecha.getLocation().x;
        int yDerecha = puntoDerecha.getLocation().y;
        int xIzquierda = puntoIzquierda.getLocation().x;
        int yIzquierda = puntoIzquierda.getLocation().y;
        //Dibujar el tronco
        g.drawLine(xPartida, yPartida, xAltura, yAltura);
        //Dibujar la rama izquierda
        g.setColor(Color.green.darker().darker());
        g.drawLine(xAltura, yAltura, xIzquierda, yIzquierda);
        //Dibujar la rama derecha
        g.drawLine(xAltura,yAltura, xDerecha, yDerecha);
        complejidad -= 1;
        if (complejidad == 0){
            return;
        }
        //Despejando formula de punto medio, sacar el tronco del nuevo arbol = Pm * 2 - P1
        Point newPuntoAlturaDerecha = new Point(2 * puntoDerecha.x - puntoAltura.x, 2 * puntoDerecha.y -
                puntoAltura.y);
        Point newPuntoAlturaIzquierda = new Point(2 * puntoIzquierda.x - puntoAltura.x, 2 * puntoIzquierda.y -
                puntoAltura.y);
        //Calculando el triangulo que se forma desde el tronco de la nueva rama hasta el nuevo punto
        // d = raiz cuadrada[(x2 - x1)^2 + (y2 - y1)^2]
        double distancia = Math.pow(puntoAltura.getX() - puntoDerecha.getX(), 2) + Math.pow(puntoAltura.getY() -
                puntoDerecha.getY(), 2);
        int catetoAdyacente = (int) Math.sqrt(distancia);
        //Cos A = Cateto adyacente / hipotenusa
        int hipotenusa = (int) (catetoAdyacente / Math.cos(Math.toRadians(15)));
        // Sen A = Cateto opuesto / hipotenusa
        int catetoOpuesto = (int)(Math.sin(Math.toRadians(15)) * hipotenusa);
        //Rama izquierda
        Point newPuntoDerecha = new Point(newPuntoAlturaDerecha.x + catetoOpuesto, newPuntoAlturaIzquierda.y -
                catetoAdyacente);
        Point newPuntoIzquierda = new Point(newPuntoAlturaDerecha.x - catetoOpuesto, newPuntoAlturaIzquierda.y -
                catetoAdyacente);
        dibujarArbol(g, puntoDerecha, newPuntoAlturaDerecha, newPuntoDerecha, newPuntoIzquierda, complejidad);
        //Rama Derecha
        newPuntoDerecha = new Point(newPuntoAlturaIzquierda.x + catetoOpuesto, newPuntoAlturaIzquierda.y -
                catetoAdyacente);
        newPuntoIzquierda = new Point(newPuntoAlturaIzquierda.x - catetoOpuesto, newPuntoAlturaIzquierda.y -
                catetoAdyacente);
        dibujarArbol(g, puntoIzquierda,newPuntoAlturaIzquierda,newPuntoDerecha, newPuntoIzquierda, complejidad);
    }






}

