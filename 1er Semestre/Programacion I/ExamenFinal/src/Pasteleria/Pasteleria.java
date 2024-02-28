package Pasteleria;

import java.util.Scanner;

public class Pasteleria {
    public static void main(String[] args) {
        int numeroObtenido;
        Scanner lector = new Scanner(System.in);
        int pastelActual = 0;
        int accion;
        boolean salir = false;
        System.out.println(">>>>> Bienvenido <<<<<");
        System.out.println("Ingrese el numero de pasteles que desea ingresar");
        do {
            try {
                String auxiliar = lector.nextLine();
                numeroObtenido = Integer.parseInt(auxiliar);
            } catch (Exception error){
                System.out.println("Error debe ingresar un numero entero mayor a cero");
                numeroObtenido = 0;
            }
        } while (numeroObtenido <= 0);
        Pasteles obtener = new Pasteles(numeroObtenido);
        for (int columnas = 0; columnas < obtener.listaPasteles.length; columnas++) {
            for (int filas = 0; filas < obtener.listaPasteles[0].length; filas++) {
                if (obtener.listaPasteles[columnas][filas] == null){
                    obtener.listaPasteles[columnas][filas] = "Sin especificar";
                }
            }
        }
        while (!salir){
            obtener.menu();
            accion = obtener.pedirNumerosEnteros(lector);
            switch (accion){
                case 1:
                pastelActual += 1;
                obtener.guardarPastel(lector,pastelActual);
                break;
                case 2:
                obtener.guardarIngredientes(lector,pastelActual);
                break;
                case 3:
                    for (int filas = 0; filas < obtener.listaPasteles[0].length; filas++) {
                        obtener.listaPasteles[pastelActual][filas] = "Sin especificar";
                }
                case 4:
                    for (int columnas = 0; columnas < obtener.listaPasteles.length; columnas++) {
                        for (int filas = 0; filas < obtener.listaPasteles[0].length; filas++) {
                            System.out.print(obtener.listaPasteles[columnas][filas]);
                        }
                        System.out.println("");

                        }
                case 5:
                    salir = true;
                default:
                    System.out.println("Error solo puede ingresar numeros entre 1 y 5");
                    }

        }




    }

}
