package main;

public class CadenaCaracteres {
    public static void main(String[] args) {
        String mensaje = "Este es un mensaje de Prueba";
        System.out.println("----- Cadena de Caracteres -----");
        System.out.println("Tenemos una serie de atributos y métodos que podemos utilizar para trabajar con cadenas.");
        System.out.println("LONGITUD");
        System.out.println("Para ver la longitud de una cadena accedemos al método length de la misma.");
        System.out.println("Longitud de mensaje: " + mensaje.length());
        System.out.println("CONVERSIONES");
        System.out.println("Convertir todos los caracteres a Mayúsculas:");
        System.out.println(mensaje.toUpperCase());
        System.out.println("Convertir todos los caracteres a Minúsculas:");
        System.out.println(mensaje.toLowerCase());
        System.out.println("SUBCADENAS");
        System.out.println("Vamos a obtener los caracters entre las posiciones: 5 y 7");
        System.out.println(mensaje.substring(5, 7));
        System.out.println("Cuando sólo se le pasa un parámetro:");
        System.out.println(mensaje.substring(11));
        System.out.println("CONTIENE CARACTERES AL INICIO:");
        System.out.println(mensaje.toLowerCase().startsWith("est"));
        System.out.println("CONTIENE CARACTERES AL FINAL:");
        System.out.println(mensaje.toUpperCase().endsWith("MUNDO"));
        System.out.println("BUSCAR EN LA CADENA:");
        System.out.println(mensaje.contains("mensaje"));
        System.out.println("CONOCER LA POSICIÓN DE UN CARACTER O PALABRA DENTRO DEL MENSAJE:");
        System.out.println(mensaje.indexOf("mensaje"));
        System.out.println("REEMPLAZO DE CARACTERES:");
        System.out.println(mensaje.replace('e', 'i'));
        System.out.println(mensaje.replace("mensaje", "examen"));
        System.out.println("REPETICION");
        System.out.println(mensaje.repeat(5));
    }
}
