package sockets;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Getter
public class Servidor {

    private static ServerSocket servidor;
    private static boolean activo = true;
    private static SalaEspera lobbyActual;
    private static ArrayList<ClientHandler> clientesConectados = new ArrayList<>();

    private static Servidor instancia;

    private Servidor() {

    }

    public static Servidor getInstancia() {
        if (instancia == null) {
            instancia = new Servidor();
        }
        return instancia;
    }

    public void start() {
        new Thread(new ServerHandler()).start();
    }

    private static class ServerHandler implements Runnable {

        @Override
        public void run() {
            try {
                servidor = new ServerSocket(11102);
                lobbyActual = new SalaEspera();
                while (activo) {
                    ClientHandler hiloCliente = new ClientHandler(servidor.accept());
                    clientesConectados.add(hiloCliente);
                    new Thread(hiloCliente).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientHandler implements Runnable {

        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String nombre;
        private String color;
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                if(lobbyActual.isLleno()){
                    lobbyActual = new SalaEspera();
                    log.info("Creando nueva sala de espera");
                }
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                while (activo) {
                    String accion = in.readLine();
                    if (accion.startsWith("Nombre: ")){
                        nombre = accion.substring(8);
                    } else if (accion.startsWith("Color: ")){
                        color = accion.substring(7);
                    } else if (accion.startsWith("Agregar")){
                        if (lobbyActual.addJugador(nombre, socket, color) && lobbyActual.isLleno()){
                            //agregar metodo para iniciar la partida
                            log.info("Iniciando la partida");
                        }
                    } else if (accion.startsWith("Listo")){
                        lobbyActual.jugadorListo();
                        if (lobbyActual.getJugadoresListos() > 1 &&
                                lobbyActual.getJugadoresListos() == lobbyActual.getJugadores().size()) {
                            lobbyActual.iniciarPartida();
                            log.info("Iniciando la partida");
                        }
                    } else if (accion.startsWith("Esperar")){
                        lobbyActual.iniciarPartida();
                        lobbyActual.esperarJugador();
                    } else {
                        log.info(accion);
                    }
                }
            } catch (Exception e) {
                    e.printStackTrace();
            }
        }

    }

}
