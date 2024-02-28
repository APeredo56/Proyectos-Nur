package Vista;

import Modelo.Barras;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;

public class PanelBarras extends JPanel implements PropertyChangeListener {
    private ArrayList<Barras> listaBarras = new ArrayList<>();
    private final static Logger logger = LogManager.getLogger(PanelBarras.class);
    private boolean ordenado;

    public PanelBarras (){
        this.setLayout(null);

    }


    public void agregarBarra(int x, int y, int alto, int posicion){
        Barras nuevaBarra = new Barras(x, y, alto);
        logger.info("Se creo una barra de tamaño: " + nuevaBarra.getAlto() + ", en la posicion X: " + nuevaBarra.getX());
        if (posicion > listaBarras.size()){
            nuevaBarra = colocarBarrasSeguidas(nuevaBarra);
            logger.debug("Se movio la barra de tamaño: " + nuevaBarra.getAlto() + ", a la posicion X: " +
                    nuevaBarra.getX());
        }
        if (listaBarras.isEmpty()){
            listaBarras.add(nuevaBarra);
            this.add(nuevaBarra);
        } else {
            if (!verificarPosicionRepetida(nuevaBarra.getX())) {
                listaBarras.add(nuevaBarra);
                this.add(nuevaBarra);
            } else {
                listaBarras.add(posicion, nuevaBarra);
                recorrerBarrasEnLista(posicion);
            }
        }

    }

    public boolean verificarPosicionRepetida (int xNuevo){
        boolean repetido = false;
        for (JLabel aux:listaBarras) {
            if (aux.getX() == xNuevo){
                repetido = true;
            }
        }
        return repetido;
    }

    public Barras colocarBarrasSeguidas (Barras barra){
        barra.setX((listaBarras.size() * barra.getEspacioOcupado()));
        return barra;
    }

    public void recorrerBarrasEnLista (int posicion){
        this.removeAll();
        int contador = 0;
        for (Barras aux: listaBarras) {
            if (contador > posicion) {
                aux.setX(aux.getX() + aux.getEspacioOcupado());
            }
            contador += 1;
            this.add(aux);
        }
    }

    public void ordenarBarras (){
        while (!verificarOrden()) {
            ordenado = false;
            Barras posicion1;
            Barras posicion2;
            int contador = 0;
            int posicionBarraDesordenada = 0;
            //encontrar posicion de la barra desordenada
            while (contador != listaBarras.size()) {
                posicion1 = listaBarras.get(contador);
                posicion2 = listaBarras.get(contador + 1);
                if (posicion1.getAlto() > posicion2.getAlto()) {
                    posicionBarraDesordenada = contador + 1;
                    logger.debug("La barra en posicion: " + posicionBarraDesordenada + ", de tamaño: " +
                            listaBarras.get(posicionBarraDesordenada).getAlto() + ", altero el orden de las barras");
                    break;
                }
                contador += 1;
            }
            //Poner la barra desordenada en el primer lugar de la lista
            Barras aux = listaBarras.get(posicionBarraDesordenada);
            listaBarras.remove(aux);
            listaBarras.add(0, aux);
            //mover la barra hasta que sea menor que la siguiente posicion
            contador = 0;
            while (true) {
                posicion1 = listaBarras.get(contador);
                posicion2 = listaBarras.get(contador + 1);
                if (posicion1.getAlto() < posicion2.getAlto()) {
                    break;
                }
                listaBarras.remove(posicion2);
                listaBarras.add(contador, posicion2);

                contador += 1;
            }
            contador = 0;
            //la lista se ordeno, entonces cambio la posicion de x segun el orden de la lista
            for (Barras aux2 : listaBarras) {
                aux2.setX(contador * aux2.getEspacioOcupado());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.add(aux2);
                contador += 1;
            }
        }
    }
    public boolean verificarOrden () {
        ordenado = true;
        int auxComparacion = -1;
        if (listaBarras.size() != 0) {
            for (JLabel aux : listaBarras) {
                if (auxComparacion >= 0 && aux.getHeight() < listaBarras.get(auxComparacion).getHeight()) {
                    ordenado = false;
                }
                auxComparacion += 1;
            }
        }
        return ordenado;
    }

    public ArrayList<Barras> getListaBarras() {
        return listaBarras;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("AGREGAR")){
            if (!verificarOrden()){
                logger.debug("La lista se desordeno, ordenando nuevamente");
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ordenarBarras();
                    }
                });

                t.start();
            }
        }
    }


}
