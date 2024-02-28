package sockets;

public class MainServidor {

    public static void main(String[] args) {
        Servidor servidor = Servidor.getInstancia();

        servidor.start();
    }
}
