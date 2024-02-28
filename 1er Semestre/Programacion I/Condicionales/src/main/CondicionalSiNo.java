package main;

import java.util.Scanner;

public class CondicionalSiNo {
    public static void main(String[] args) {
        // En Java se utiliza el comando if (true) y el comando else (false)
        // > < >= <= == (igualdad) != (diferencia) && (y) || (o)
        int edad;
        String aprobado;
        String denegado;
        Scanner lector;
        // Bloque de Instrucciones
        System.out.println("--- Control de Ingreso ---");
        aprobado = "Bienvenido al Sistema!";
        denegado = "No puedes acceder a esta sección";
        lector = new Scanner(System.in);
        System.out.println("Para verificar el acceso, ingrese su edad:");
        edad = lector.nextInt();
        if(edad >= 18){
            System.out.println(aprobado);
        } else {
            System.out.println(denegado);
        }
    }
}
