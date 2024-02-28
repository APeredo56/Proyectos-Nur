package main;

import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
        int mes;
        String nombremes;
        Scanner lector;
        System.out.println(">>> Consulta del numero de un mes <<<");
        System.out.println("Ingresar el numero de mes para indicar su nombre respectivo:");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                mes = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros positivos");
                mes = 0;
            }
        } while (mes <= 0);
        switch (mes) {
            case 1:
                nombremes = "Enero";
                break;
            case 2:
                nombremes = "Febrero";
                break;
            case 3:
                nombremes = "Marzo";
                break;
            case 4:
                nombremes = "Abril";
                break;
            case 5:
                nombremes = "Mayo";
                break;
            case 6:
                nombremes = "Junio";
                break;
            case 7:
                nombremes = "Julio";
                break;
            case 8:
                nombremes = "Agosto";
                break;
            case 9:
                nombremes = "Septiembre";
                break;
            case 10:
                nombremes = "Octubre";
                break;
            case 11:
                nombremes = "Noviembre";
                break;
            case 12:
                nombremes = "Diciembre";
                break;
            default:
                nombremes = "Error debe ingresar un numero entre 1 Y 12";
        }
        if (mes <= 12){
            System.out.println("El mes " + mes + " es " + nombremes);
        } else {
            System.out.println(nombremes);
        }
        }
    }

