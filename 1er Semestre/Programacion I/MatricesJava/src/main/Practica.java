package main;

public class Practica {
    public static void main(String[] args) {
        // Declaración: Tipo de Dato [][] identificador;
        String[][] agenda;
        // Definición: identificador = new Tipo de Dato [filas][columnas];
        agenda = new String[10][5];
        //   0   |    1    |   2   |   3    |        4
        // Nombre|Apellidos|Celular|Teléfono|Correo Electrónico
        // Ahora guardaré estos valores en una fila de mi matriz
        agenda[0][0] = "Juan";
        agenda[0][1] = "Perez Perez";
        agenda[0][2] = "72652521";
        agenda[0][3] = "";
        agenda[0][4] = "jperez@mentira.test";
        agenda[4][2] = "7000001";
        // Acceder a un registro de la matriz
        // identificador[nro_fila][nro_columna];
        System.out.println(agenda[0][4]);
        // Recorrido de la matriz
        System.out.println("Filas: " + agenda.length);
        System.out.println("Columnas: " + agenda[0].length);
        for (int filas = 0; filas < agenda.length; filas++) {
            for (int columnas = 0; columnas < agenda[0].length; columnas++) {
                System.out.print(agenda[filas][columnas] + "  ");
            }
            System.out.println();
        }
        // Versión Simplificada
        double[][] notas = {{25, 52, 70}, {80, 95, 100}, {25, 25, 10}};
        for (int filas = 0; filas < notas.length; filas++) {
            for (int columnas = 0; columnas < notas[0].length; columnas++) {
                System.out.print(notas[filas][columnas] + "  ");
            }
            System.out.println();
        }
    }
}
