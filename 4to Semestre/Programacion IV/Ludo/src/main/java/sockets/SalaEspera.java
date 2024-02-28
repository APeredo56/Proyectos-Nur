package sockets;

import jugador.Jugador;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import paneles.Partida;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

@Getter
@Slf4j
public class SalaEspera {
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private boolean lleno;
    private int jugadoresListos = 0;

    public boolean addJugador(String nickname, Socket socket, String colorSeleccionado) throws IOException {
        Color color = null;
        switch (colorSeleccionado){
            case "Verde" -> color = Color.green;
            case "Azul" -> color = Color.blue;
            case "Amarillo" -> color = Color.yellow;
            case "Rojo" -> color = Color.red;
        }
        for (Jugador aux : jugadores) {
            if (aux.getNombre().equals(nickname)) {
                new PrintWriter(socket.getOutputStream(), true).println("Nombre en uso");
                return false;
            } else if (aux.getColor() == color) {
                new PrintWriter(socket.getOutputStream(), true).println("Color en uso");
                return false;
            }
        }
        Jugador nuevo = new Jugador(nickname, socket, color);
        jugadores.add(nuevo);
        log.info("Se unio a la partida el jugador " + nickname);
        if (jugadores.size() == 4){
            lleno = true;
        }
        new PrintWriter(socket.getOutputStream(), true).println("Valido");
        log.info("enviando jugador");
        //new ObjectOutputStream(socket.getOutputStream()).writeObject(nuevo);
        return true;
    }

    public boolean isLleno() {
        return lleno;
    }

    public void iniciarPartida(){
        Partida partida = new Partida(jugadores);
        //pasar la partida a cada jugador
        for (Jugador aux:jugadores
             ) {
            try {
                aux.setPartida(partida);
                new PrintWriter(aux.getSocket().getOutputStream(),true).println("Iniciar Partida");
                ObjectOutputStream o = new ObjectOutputStream(new DataOutputStream(aux.getSocket().getOutputStream()));
                o.writeObject(aux);
                Thread.sleep(300);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        lleno = true;
    }

    public void jugadorListo(){
        jugadoresListos++;
    }

    public void esperarJugador(){
        jugadoresListos--;
    }

}
