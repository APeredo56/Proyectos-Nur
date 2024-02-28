package main;

import abb.ArbolBinarioBusqueda;

import java.util.Comparator;

public class MainComparacion {

    public static void main(String[] args) {

        Persona persona1 = new Persona("Juan", 20, 180, 80);
        Persona persona2 = new Persona("Pedro", 25, 170, 70);
        Persona persona3 = new Persona("Maria", 30, 160, 60);
        Persona persona4 = new Persona("Jose", 35, 150, 50);

        comparar(persona3, persona2);

    }

    public static void comparar(Persona p1, Persona p2){
        Comparator<Persona> comparadorPersona = new ComparadorPersonaEstatura();
        ArbolBinarioBusqueda<Persona> arbol = new ArbolBinarioBusqueda<>( comparadorPersona);

        int comparacion = arbol.comparar(p1, p2);

        if (comparacion > 0){
            System.out.println("La persona 1 [ " + p1.toString() +  " ] es mayor");
        }else if (comparacion < 0){
            System.out.println("La persona 2 [ " + p2.toString() +  " ] es mayor");
        }else{
            System.out.println("Las personas son iguales");
        }

    }
}
