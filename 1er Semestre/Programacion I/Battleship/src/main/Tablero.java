package main;
import java.util.Scanner;
public class Tablero {
    String[][] jugador1;
    String[][] jugador2;
    String[][] guia1;
    String[][] guia2;
    public String coordenada;
    String linea = "-";
    boolean condicion = true;
    boolean condicion2 = true;
    int contador = 1;

    public Tablero() {
        this.jugador1 = new String[10][10];
        this.jugador2 = new String[10][10];
        this.guia1 = new String[10][10];
        this.guia2 = new String[10][10];
        for (int columnas = 0; columnas < this.guia1.length; columnas++) {
            for (int filas = 0; filas < this.guia1[0].length; filas++) {
                this.guia1[columnas][filas] = "F";

            }
        }
        for (int columnas = 0; columnas < this.guia2.length; columnas++) {
            for (int filas = 0; filas < this.guia2[0].length; filas++) {
                this.guia2[columnas][filas] = "F";

            }
        }
        for (int columnas = 0; columnas < this.jugador1.length; columnas++) {
            for (int filas = 0; filas < this.jugador1[0].length; filas++) {
                this.jugador1[columnas][filas] = " ";

            }
        }
        for (int columnas = 0; columnas < this.jugador2.length; columnas++) {
            for (int filas = 0; filas < this.jugador2[0].length; filas++) {
                this.jugador2[columnas][filas] = " ";

            }
        }
    }

    public void limpiarTablero1() {
        for (int columnas = 0; columnas < this.jugador1.length; columnas++) {
            for (int filas = 0; filas < this.jugador1[0].length; filas++) {
                jugador1[columnas][filas] = " ";
            }
        }
    }

    public void limpiarTablero2() {
        for (int columnas = 0; columnas < this.jugador2.length; columnas++) {
            for (int filas = 0; filas < this.jugador2[0].length; filas++) {
                jugador2[columnas][filas] = " ";
            }
        }
    }

    public void rellenarGuia1() {
        for (int columnas = 0; columnas < this.guia1.length; columnas++) {
            for (int filas = 0; filas < this.guia1[0].length; filas++) {
                guia1[columnas][filas] = "F";

            }
        }
    }

    public void rellenarGuia2() {
        for (int columnas = 0; columnas < this.guia2.length; columnas++) {
            for (int filas = 0; filas < this.guia2[0].length; filas++) {
                guia2[columnas][filas] = "F";

            }
        }
    }

    public void imprimirTableros(String[][] jugador, int numJugador, String motivo) {
        if (motivo.equals("disparar")) {
            System.out.println("Turno del jugador " + numJugador);
        } else {
            System.out.println("Tablero del jugador " + numJugador);
        }
        System.out.println("      A   B   C   D   E   F   G   H   I   J");
        for (int columnas = 0; columnas < jugador.length; columnas++) {
            System.out.print("    " + linea.repeat(41));
            System.out.println("");
            if (columnas < 9) {
                System.out.print(" ");
            }
            System.out.print((columnas + 1) + "  |");
            for (int filas = 0; filas < jugador[0].length; filas++) {
                System.out.print(" " + jugador[columnas][filas] + " |");
            }
            System.out.println("");
        }
        System.out.print("    " + linea.repeat(41));
        System.out.println("");
    }

    public void verGuia(String [][] guia) {
        for (int columnas = 0; columnas < guia.length; columnas++) {
            for (int filas = 0; filas < guia[0].length; filas++) {
                System.out.print(guia[columnas][filas]);
            }
            System.out.println("");
        }
    }

    public void lectorCoordenadas(Scanner lector, String motivo, int numJugador, int longitud) {
        if(motivo.contains("colocar")) {
            System.out.println("Ingrese las coordenadas para " + motivo + " (Ejemplo: A1-A" + longitud + ")");
        }else{
            System.out.println("Ingrese las coordenadas para " + motivo + " (Ejemplo: A1)");
        }
        do {
            condicion = true;
            condicion2 = true;
            if (motivo.contains("colocar")) {
                if (contador % 2 != 0) {
                    System.out.println("Ingrese la coordenada inicial:");
                }
                if (contador % 2 == 0) {
                    System.out.println("Ingrese la coordenada final:");
                }
            }
            this.coordenada = lector.nextLine();
            if (this.coordenada.length() == 2) {
                String verificador = this.coordenada.toLowerCase().substring(0, 1);
                switch (verificador) {
                    case "a":
                    case "b":
                    case "c":
                    case "d":
                    case "e":
                    case "f":
                    case "g":
                    case "h":
                    case "i":
                    case "j":
                        condicion = false;
                        break;
                    default:
                        System.out.println("Error se introdujo una coordenada incorrecta");
                }
                if (condicion == false) {
                    verificador = this.coordenada.substring(1, 2);
                    switch (verificador) {
                        case "1":
                        case "2":
                        case "3":
                        case "4":
                        case "5":
                        case "6":
                        case "7":
                        case "8":
                        case "9":
                        case "10":
                            condicion2 = false;
                            break;
                        default:
                            System.out.println("Error se introdujo una coordenada incorrecta");
                    }
                }
            } else {
                if (this.coordenada.length() == 3 && this.coordenada.contains("10")) {
                    String verificador = this.coordenada.toLowerCase().substring(0, 1);
                    switch (verificador) {
                        case "a":
                        case "b":
                        case "c":
                        case "d":
                        case "e":
                        case "f":
                        case "g":
                        case "h":
                        case "i":
                        case "j":
                            condicion = false;
                            condicion2 = false;
                            break;
                        default:
                            System.out.println("Error se introdujo una coordenada incorrecta");
                    }

                } else {
                    System.out.println("Error se ingreso una coordenada mas larga o mas pequeña de lo requerida");
                }

            }

        } while (condicion || condicion2);
        contador ++;
    }

    public String getCoordenada() {
        return coordenada;
    }
    public void rellenarTableros(String [][] guia, String [][] jugador) {
        for (int columnas = 0; columnas < this.jugador1.length; columnas++) {
            for (int filas = 0; filas < this.jugador1[0].length; filas++) {
                if ((guia[columnas][filas]).equals("*")) {
                    jugador[columnas][filas] = ("*");
                } else {
                    jugador[columnas][filas] = (" ");
                }
                if ((guia[columnas][filas]).equals("X")) {
                    jugador[columnas][filas] = ("X");
                }
            }
        }
    }

}
