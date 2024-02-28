package main;
import java.util.Scanner;
public class Juego  {
    public static void main(String[] args) {
        Tablero imprimir = new Tablero();
        Barcos colocar = new Barcos();
        Scanner lector = new Scanner(System.in);
        imprimir.limpiarTablero1();
        imprimir.limpiarTablero2();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("                Batalla naval");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Presione enter para comenzar");
        lector.nextLine();
        System.out.println("Turno del Jugador 1 de colocar sus barcos");
        imprimir.imprimirTableros(colocar.obtenerJugador1(), 1, "colocar sus barcos");
        colocar.colocarBarcos(colocar.obtenerJugador1(), colocar.obtenerGuia1(), 1);
        colocar.saltarLineas("colocar");
        System.out.println("Turno del jugador 2 de colocar sus barcos");
        imprimir.imprimirTableros(colocar.obtenerJugador2(), 2, "colocar sus barcos");
        colocar.colocarBarcos(colocar.obtenerJugador2(), colocar.obtenerGuia2(), 2);
        colocar.saltarLineas("colocar");
        System.out.println("Comienza el juego");
        colocar.disparar(colocar.obtenerGuia2(), colocar.obtenerJugador2());
    }

}

