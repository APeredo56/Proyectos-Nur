package sockets;

import jugador.Jugador;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

@Getter
@Setter
@Slf4j
public class Cliente {

    //Relacionado con el socket.
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName = "";
    private boolean enSala;
    private Jugador jugador;

    public Cliente() {
        try {
            clientSocket = new Socket("localhost", 11102);
            log.info("Conexion al servidor exitosa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(String name, String color) {
        try {
            clientName = name;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            new Thread(new Listener()).start();
            out.println("Nombre: " + clientName);
            out.println("Color: " + color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Listener implements Runnable {

        @Override
        public void run() {
            try {
                String read;
                /*while (true) {
                    read = in.readLine();
                    if (read.equals("Valido")){
                        enSala = true;
                    }
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
