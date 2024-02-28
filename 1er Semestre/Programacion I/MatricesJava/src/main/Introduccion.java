package main;

public class Introduccion {
    public static void main(String[] args) {
        System.out.println("--- MATRICES ---");
        System.out.println("Son estructuras de datos básicas, permiten almacenar información de manera ordenada");
        System.out.println("en un conjunto bidimensional compuesto por filas y columnas.");
        System.out.println("DEFINICIÓN DE UNA MATRIZ EN JAVA");
        System.out.println("Forma Tradicional");
        System.out.println("Tipo de Dato [][] identificador = new Tipo de Dato [filas][columnas];");
        System.out.println("Forma Simplificada (Se recomienda cuando se conoce el contenido de la matriz)");
        System.out.println("Tipo de Dato [][] identificador = {{valor1, valor2, valor3}, {valor3, valor4, valor5},….{}};");
        System.out.println("INSERTAR REGISTROS");
        System.out.println("identificador[nro_fila][nro_columna] = valor;");
        System.out.println("Ejemplo: agenda[0][1] = \"Hola\";");
        System.out.println("MOSTRAR REGISTROS");
        System.out.println("identificador [nro_fila][nro_columna];");
        System.out.println("RECORRIDO DE UNA MATRIZ");
        System.out.println("Para recorrer una matriz se utilizan bucles anidados, el bucle externo recorre las filas y");
        System.out.println("el bucle interno recorre las columnas.");
        System.out.println("for(int filas = 0; filas < agenda.lenght; filas++){");
        System.out.println("for(int columnas = 0; columnas < agenda[0].lenght; columnas++) {} }");
    }
}
