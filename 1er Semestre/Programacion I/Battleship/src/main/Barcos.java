package main;

import java.util.Scanner;
public class Barcos {
    Tablero tablero = new Tablero();
    String letraActual;
    protected String posicionCoordenada;
    static String letraCoordenada;
    String posicionLetras = "ABCDEFGHIJ";
    static String coordenadaInicial;
    static String coordenadaFinal;
    static int posicionMatriz;
    Scanner lector = new Scanner(System.in);
    int contador = 1;
    boolean compatible = false;

    // buque = 4; 1
    // submarino = 3; 2
    // crucero = 2; 3
    // lancha = 1; 4

    String[] buque;
    String[] submarino;
    String[] crucero;


    public Barcos() {
        super();
        this.buque = new String[4];
        this.submarino = new String[3];
        this.crucero = new String[2];
        tablero.rellenarGuia1();
        tablero.rellenarGuia2();
    }

    public String equivalencia() {
        for (int indice = 0; indice < posicionLetras.length(); indice++) {
            letraActual = posicionLetras.substring(indice, indice + 1);
            if (letraActual.equals(tablero.getCoordenada().toUpperCase().substring(0, 1))) {
                this.posicionCoordenada = indice + "";
            }

        }
        int auxiliar = Integer.parseInt(tablero.getCoordenada().substring(1, 2));
        if (tablero.getCoordenada().length() == 3) {
            auxiliar = Integer.parseInt(tablero.getCoordenada().substring(1, 3));
        }
        auxiliar -= 1;
        this.posicionCoordenada = this.posicionCoordenada + (auxiliar + "");
        return this.posicionCoordenada;
    }

    public String getPosicionCoordenada() {
        return posicionCoordenada;
    }


    //Colocar los barcos
    public String[][] fijarCoordenadas(String[] barco, String nombre, String[][] jugador, int longitud,
                                       String[][] guia, int numJugador) {
        boolean validador1 = true;
        boolean validador2;
        int validadorInicial = 0;
        int validadorFinal = 0;
        while (!this.compatible) {
            tablero.lectorCoordenadas(lector, "colocar el " + nombre + " (Debe ocupar "+ longitud + " espacios)",
                    numJugador, longitud);
            equivalencia();
            coordenadaInicial = posicionCoordenada;
            tablero.lectorCoordenadas(lector, "colocar el " + nombre + " (Debe ocupar "+ longitud + " espacios)",
                    numJugador, longitud);
            equivalencia();
            coordenadaFinal = posicionCoordenada;
            //validar si las coordenadas estan en la misma fila o columna
            validador1 = coordenadaInicial.substring(0, 1).equals(coordenadaFinal.substring(0, 1));
            validador2 = coordenadaInicial.substring(1, 2).equals(coordenadaFinal.substring(1, 2));
            if (validador1 || validador2) {
                this.compatible = true;
            } else {
                System.out.println("Error las coordenadas indicadas deben estar en la misma fila o columna");
            }
            //validar si las coordenadas son distintas
            if (validador1 && validador2) {
                this.compatible = false;
                System.out.println("Error debe ingresar dos coordenadas distintas");
            }
            //validar que las coordenadas ocupan el espacio correcto
            if (this.compatible) {
                this.compatible = false;
                //si las columnas son iguales
                if (validador1) {
                    validadorInicial = Integer.parseInt(coordenadaInicial.substring(1, 2));
                    validadorFinal = Integer.parseInt(coordenadaFinal.substring(1, 2));
                    if (validadorInicial < validadorFinal) {
                        int auxiliarValores;
                        auxiliarValores = validadorFinal;
                        validadorFinal = validadorInicial;
                        validadorInicial = auxiliarValores;
                    }
                    if ((validadorInicial - validadorFinal) == (longitud - 1)) {
                        this.compatible = true;
                    } else {
                        System.out.println("Error las coordenadas deben estar entre " + longitud + " espacios");
                    }
                }
                //si las filas son iguales
                if (validador2) {
                    validadorInicial = Integer.parseInt(coordenadaInicial.substring(0, 1));
                    validadorFinal = Integer.parseInt(coordenadaFinal.substring(0, 1));
                    if (validadorInicial < validadorFinal) {
                        int auxiliarValores;
                        auxiliarValores = validadorFinal;
                        validadorFinal = validadorInicial;
                        validadorInicial = auxiliarValores;
                    }
                    if ((validadorInicial - validadorFinal) == (longitud - 1)) {
                        this.compatible = true;
                    } else {
                        System.out.println("Error las coordenadas deben estar entre " + longitud + " espacios");
                    }

                }
            }
            //rellenar un array con el rango de posiciones
            if (this.compatible) {
                for (int indice = 0; indice < barco.length; indice++) {
                    barco[indice] = coordenadaFinal;
                    if (validador1) {
                        coordenadaFinal = "";
                        validadorInicial--;
                        coordenadaFinal = coordenadaInicial.substring(0, 1) + ((validadorInicial) + "");
                    } else {
                        validadorInicial--;
                        coordenadaFinal = (validadorInicial + "") + coordenadaFinal.substring(1, 2);
                    }
                }
            }
            //validar que no haya ningun barco en las posiciones indicadas
            if (this.compatible) {
                for (int indice = 0; indice < barco.length; indice++) {
                    int posicionGuia1 = Integer.parseInt(barco[indice].substring(1, 2));
                    int posicionGuia2 = Integer.parseInt(barco[indice].substring(0, 1));
                    if (guia[posicionGuia1][posicionGuia2].equals("V")) {
                        this.compatible = false;
                    }
                }
                if (!this.compatible) {
                    System.out.println("Error ya hay un barco en esa posicion");
                } else {
                    //rellenar en la guia las posiciones del barco
                    for (int indice = 0; indice < barco.length; indice++) {
                        int posicionGuia1 = Integer.parseInt(barco[indice].substring(1, 2));
                        int posicionGuia2 = Integer.parseInt(barco[indice].substring(0, 1));
                        guia[posicionGuia1][posicionGuia2] = "V";
                        this.compatible = true;
                    }
                }

            }

        }
        this.compatible = false;
        return guia;
    }

    public String[][] colocarBarcos(String[][] jugador, String[][] guia, int numJugador) {
        fijarCoordenadas(buque, "buque", jugador, 4, guia, numJugador);
        traspasarBarcos(jugador, guia);
        tablero.imprimirTableros(jugador, numJugador, "colocar sus barcos");
        int contador = 1;
        while (contador <= 2) {
            fijarCoordenadas(submarino, "submarino " + (contador + ""), jugador, 3, guia, numJugador);
            traspasarBarcos(jugador, guia);
            tablero.imprimirTableros(jugador, numJugador, "colocar sus barcos");
            contador += 1;
        }
        contador = 1;
        while (contador <= 3) {
            fijarCoordenadas(crucero, "crucero " + (contador + ""), jugador, 2, guia, numJugador);
            traspasarBarcos(jugador, guia);
            tablero.imprimirTableros(jugador, numJugador, "colocar sus barcos");
            contador += 1;
        }
        return guia;

    }

    public String[][] traspasarBarcos(String[][] jugador, String[][] guia) {
        for (int columnas = 0; columnas < guia.length; columnas++) {
            for (int filas = 0; filas < guia[0].length; filas++) {
                if ((guia[columnas][filas]).equals("V")) {
                    jugador[columnas][filas] = "*";
                }

            }
        }
        return guia;
    }

    public void disparar(String[][] guia, String[][] jugador) {
        int turno = 1;
        int numJugador = 1;
        int contadorVictoria1 = 0;
        int contadorVictoria2 = 0;
        while (contadorVictoria1 < 4 && contadorVictoria2 < 4) {
            if (numJugador == 1){
                guia = tablero.guia2;
                jugador = tablero.jugador2;
            } else {
                guia = tablero.guia1;
                jugador = tablero.jugador1;
            }
            tablero.rellenarTableros(guia, jugador);
            if (numJugador == 1) {
                tablero.imprimirTableros(tablero.jugador2,1, "disparar");
            } else {
                tablero.imprimirTableros(tablero.jugador1, 2, "disparar");
            }
            tablero.lectorCoordenadas(lector, "disparar", numJugador, 0);
            equivalencia();
            int posicionGuia1 = Integer.parseInt(posicionCoordenada.substring(1, 2));
            int posicionGuia2 = Integer.parseInt(posicionCoordenada.substring(0, 1));
            if ((guia[posicionGuia1][posicionGuia2]).equals("V")) {
                guia[posicionGuia1][posicionGuia2] = "*";
                System.out.println("El jugador " + numJugador + " acerto el disparo");
                if (numJugador == 1) {
                    contadorVictoria1 ++;
                } else {
                    contadorVictoria2 ++;
                }
            } else {
                if ((guia[posicionGuia1][posicionGuia2]).equals("X") || (guia[posicionGuia1][posicionGuia2]).equals("*")) {
                    System.out.println("Error ya se disparo en esa coordenada");
                } else {
                    if ((guia[posicionGuia1][posicionGuia2]).equals("F")){
                        guia[posicionGuia1][posicionGuia2] = "X";
                        System.out.println("El jugador " + numJugador + " disparo al agua");
                        turno = 2;
                    }
                }
            }
            for (int columnas = 0; columnas < guia.length; columnas++) {
                for (int filas = 0; filas < guia[0].length; filas++) {
                    if ((guia[columnas][filas]).equals("X")) {
                        jugador[columnas][filas] = "X";
                    }
                }
            }
            for (int columnas = 0; columnas < guia.length; columnas++) {
                for (int filas = 0; filas < guia[0].length; filas++) {
                    if ((guia[columnas][filas]).equals("*")) {
                        jugador[columnas][filas] = "*";
                    }

                }
            }
            tablero.rellenarTableros(guia, jugador);
            if (numJugador == 1) {
                tablero.imprimirTableros(tablero.jugador2, 1, "mostrar");
            } else {
                tablero.imprimirTableros(tablero.jugador1, 2, "mostrar");
            }
            if (turno == 2) {
                if (numJugador == 1) {
                    numJugador = 2;
                    saltarLineas("cambiar");
                } else {
                    numJugador = 1;
                    saltarLineas("cambiar");
                }
                turno = 1;
            }
        }
        System.out.println(">>>>>>>>>> Fin de la partida <<<<<<<<<<");
        System.out.println("       El ganador es el jugador " + numJugador);
    }

    public String[][] obtenerGuia1() {
        return tablero.guia1;
    }
    public String[][] obtenerGuia2() {
        return tablero.guia2;
    }
    public String[][] obtenerJugador1() {
        return tablero.jugador1;
    }
    public String[][] obtenerJugador2() {
        return tablero.jugador2;
    }
    public void saltarLineas(String motivo){
        int auxiliar = 0;
        if (motivo == "colocar") {
            System.out.println("Barcos guardados correctamente, presione enter para continuar");
            lector.nextLine();
            while (auxiliar < 15){
                System.out.println(" ");
                auxiliar ++;
            }
        } else {
            System.out.println("Presione enter para continuar");
            lector.nextLine();
        }

    }
}

