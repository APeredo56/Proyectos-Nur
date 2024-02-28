package paneles;

import elementos.*;
import jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Tablero extends JPanel implements Serializable {
    private ArrayList<ElementoTablero> listaElementos = new ArrayList<>();
    private ArrayList<Casa> listaCasas = new ArrayList<>();
    private ArrayList<Casilla> listaCasillas = new ArrayList<>();
    private ArrayList<Meta> listaMetas = new ArrayList<>();
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private int size;
    private int sizeCasas;
    private int sizeCasillas;
    private int casillaActual;

    public enum Posicion {derecha, izquierda, arriba, abajo}

    public Tablero(ArrayList<Jugador> listaJugadores) {
        size = Math.min(this.getHeight(), this.getWidth());
        this.listaJugadores = listaJugadores;
        crearTablero();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        size = Math.min(this.getHeight(), this.getWidth());
        sizeCasillas = size/15;
        sizeCasas = sizeCasillas*6;

        int casaActual = 1;

        for (Casa casa : listaCasas) {
            casa.setSize(sizeCasas);
            switch (casaActual) {
                case 2 -> casa.setPosX(sizeCasas + sizeCasillas * 3);
                case 3 -> {
                    casa.setPosX(sizeCasas + sizeCasillas * 3);
                    casa.setPosY(sizeCasas + sizeCasillas * 3);
                }
                case 4 -> casa.setPosY(sizeCasas + sizeCasillas * 3);
            }
            casaActual++;
        }
        //numero de casillas pintadas
        casillaActual = 0;
        int x = sizeCasas - sizeCasillas;
        int y = sizeCasas + sizeCasillas * 2;
        pintarCasillas(Posicion.izquierda, new Point(x, y), casillaActual);

        x = sizeCasas;
        y = sizeCasas - sizeCasillas;
        pintarCasillas(Posicion.arriba, new Point(x, y), casillaActual);

        x = sizeCasas + sizeCasillas * 3;
        y = sizeCasas;
        pintarCasillas(Posicion.derecha, new Point(x, y), casillaActual);

        x = sizeCasas + sizeCasillas * 2;
        y = sizeCasas + sizeCasillas * 3 ;
        pintarCasillas(Posicion.abajo, new Point(x, y), casillaActual);

        pintarMeta(Posicion.izquierda);
        pintarMeta(Posicion.arriba);
        pintarMeta(Posicion.derecha);
        pintarMeta(Posicion.abajo);

        for (ElementoTablero aux : listaElementos) {
            aux.dibujar(g);
        }
    }

    public void crearTablero() {
        Jugador rojo = null;
        Jugador verde = null;
        Jugador amarillo = null;
        Jugador azul = null;
        for (Jugador aux : listaJugadores){
            if (aux.getColor() == Color.red){
                rojo = aux;
            } else if (aux.getColor() == Color.green){
                verde = aux;
            } else if (aux.getColor() == Color.yellow){
                amarillo = aux;
            } else if (aux.getColor() == Color.blue){
                azul = aux;
            }
        }
        crearJugador(rojo, Color.red);
        crearJugador(verde, Color.green);
        crearJugador(amarillo, Color.yellow);
        crearJugador(azul, Color.blue);
        //Conectar la ultima casilla a la primera
        listaCasillas.get(listaCasillas.size()-1).setSiguiente(listaCasillas.get(0));
    }


    public void crearJugador(Jugador jugador, Color color) {
        Casa casa = new Casa(color);
        listaCasas.add(casa);
        listaElementos.add(casa);
        Casilla casilla;
        Meta meta = new Meta(color);
        listaMetas.add(meta);
        listaElementos.add(meta);
        int casillasMeta = 0;
        for (int i = 1; i < 19; i++) {
            casilla = switch (i) {
                case 4 -> new Casilla(Color.gray.brighter(), true, false);
                case 8, 9, 10, 11, 12 -> new Casilla(color, false, false);
                case 14 -> new Casilla(color, false, true);
                default -> new Casilla(Color.white, false, false);
            };
            if (i < 8 || i > 12) {
                //Agregar el siguiente a cada casilla
                try {
                    listaCasillas.get(listaCasillas.size() - 1 - casillasMeta).setSiguiente(casilla);
                    if (casillasMeta == 5){
                        casillasMeta = 0;
                    }
                } catch (Exception ignored) {}
            } else{
                if (jugador!= null) {
                    try {
                        jugador.getListaMeta().get(jugador.getListaMeta().size() - 1).setSiguiente(casilla);
                    } catch (Exception ignored) {
                    }

                    jugador.getListaMeta().add(casilla);
                }
                casillasMeta++;
            }
            listaCasillas.add(casilla);
            listaElementos.add(casilla);
        }
        if (jugador == null){
            return;
        }
        for (int i = 0; i < 4; i++) {
            Pieza nueva = new Pieza(color);
            jugador.getListaPiezas().add(nueva);
            casa.agregarPieza(nueva);
            listaElementos.add(nueva);
        }
    }

    private void pintarCasillas(Posicion sentido, Point posInicio, int casillaActual) {
        int comodin;
        switch (sentido){
            case arriba, izquierda -> comodin = -1;
            default -> comodin = 1;
        }
        int posX = posInicio.x;
        int posY = posInicio.y;

        int contadorCasillas = casillaActual;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                listaCasillas.get(contadorCasillas).setPosX(posX);
                listaCasillas.get(contadorCasillas).setPosY(posY);
                listaCasillas.get(contadorCasillas).setSize(sizeCasillas);
                switch (sentido) {
                    case izquierda, derecha -> posX += sizeCasillas * comodin;
                    case arriba, abajo -> posY += sizeCasillas * comodin;
                }
                contadorCasillas++;
                this.casillaActual++;
            }
            switch (sentido) {
                case izquierda -> {
                    posX = sizeCasas - sizeCasillas * 6;
                    posY -= sizeCasillas;
                }
                case arriba -> {
                    posX += sizeCasillas;
                    posY = sizeCasas - sizeCasillas * 6;
                }
                case derecha -> {
                    posX = sizeCasas*2 + sizeCasillas *2;
                    posY += sizeCasillas;
                }
                case abajo ->{
                    posX -= sizeCasillas;
                    posY = sizeCasas*2 + sizeCasillas*2;
                }
            }
            if (i < 1) {
                comodin = comodin * (-1);
            }
        }

    }

    public void pintarMeta(Posicion posicion){
        int centro = sizeCasas + sizeCasillas + sizeCasillas/2;
        Meta meta;
        switch (posicion){
            case izquierda ->{
                meta = listaMetas.get(0);
                meta.setPosX(new int[]{sizeCasas, centro, sizeCasas});
                meta.setPosY(new int[]{sizeCasas, centro, sizeCasas + sizeCasillas*3});
            }
            case arriba ->{
                meta = listaMetas.get(1);
                meta.setPosX(new int[]{sizeCasas + sizeCasillas*3, centro, sizeCasas});
                meta.setPosY(new int[]{sizeCasas, centro, sizeCasas});
            }
            case derecha ->{
                meta = listaMetas.get(2);
                meta.setPosX(new int[]{sizeCasas + sizeCasillas*3, centro, sizeCasas + sizeCasillas*3});
                meta.setPosY(new int[]{sizeCasas + sizeCasillas*3, centro, sizeCasas});
            }
            case abajo ->{
                meta = listaMetas.get(3);
                meta.setPosX(new int[]{sizeCasas, centro, sizeCasas + sizeCasillas*3});
                meta.setPosY(new int[]{sizeCasas + sizeCasillas * 3, centro, sizeCasas + sizeCasillas*3});
            }
            default -> meta = new Meta(null);
        }
        meta.setPosicion(posicion);

    }


}