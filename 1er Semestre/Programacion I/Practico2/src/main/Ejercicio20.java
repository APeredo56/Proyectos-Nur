package main;
import java.util.Scanner;
public class Ejercicio20 {
    public static void main(String[] args) {
        int dia;
        int mes;
        int year;
        String nombremes;
        Scanner lector;
        System.out.println(">>> Formalizador de fechas <<<");
        System.out.println("Ingrese el dia de la fecha (en numeros):");
        lector = new Scanner(System.in);
        nombremes = "";
        do {
            try {
                String auxiliar = lector.nextLine();
                dia = Integer.parseInt(auxiliar);
                if (dia > 31){
                    System.out.println("Error, el dia debe ser un numero entre 1 y 31");
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros positivos entre 1 y 31");
                dia = 0;
            }
        } while (dia <= 0 || dia > 31);
        System.out.println("Ingrese el mes de la fecha (en numeros):");
        do {
            try {
                String auxiliar = lector.nextLine();
                mes = Integer.parseInt(auxiliar);
                if (mes > 12){
                    System.out.println("Error, el mes debe ser un numero entre 1 y 12");
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros positivos entre 1 y 12");
                mes = 0;
            }
        } while (mes <= 0 || mes > 12);
        System.out.println("Ingrese el año de la fecha (en numeros):");
        do {
            try {
                String auxiliar = lector.nextLine();
                year = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros positivos");
                year = 0;
            }
        } while (year <= 0);
        System.out.println("Fecha ingresada: " + dia + "/0" + mes + "/" + year);
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
        }
        System.out.println("Fecha en formato formal: " + dia + " de " + nombremes + " de " + year);
    }
}
