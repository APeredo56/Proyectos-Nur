package jugador;

import elementos.Casilla;
import elementos.Pieza;
import lombok.Getter;
import lombok.Setter;
import paneles.Partida;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

@Getter
@Setter
public class Jugador implements Serializable {
    private String nombre;
    private ArrayList<Pieza> listaPiezas = new ArrayList<>();
    private boolean turno;
    private ArrayList<Casilla> listaMeta = new ArrayList<>();
    private Color color;
    private Boolean enJuego;
    private Partida partida;

    private transient Socket socket;

    public Jugador(Color color){
        this.color = color;
    }

    public Jugador(String nombre, Socket socket, Color color){
        this.nombre = nombre;
        this.socket = socket;
        this.color = color;
    }

    public Jugador(String nombre, Socket socket) {
        this.nombre = nombre;
        this.socket = socket;
    }
}
