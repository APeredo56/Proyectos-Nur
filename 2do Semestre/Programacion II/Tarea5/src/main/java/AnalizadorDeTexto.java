import java.util.Scanner;

public class AnalizadorDeTexto {
    public static void main(String[] args) {
        String texto;
        Scanner lector = new Scanner(System.in);
        boolean salir = false;
        int accion;
        Herramientas analizar = new Herramientas();
        System.out.println(">>> Analizador de Texto <<<");
        System.out.println("Ingrese el texto a analizar:");
        texto = lector.nextLine();

        while (!salir) {
            System.out.println("Funciones disponibles:");
            System.out.println("1 -> Contar palabras");
            System.out.println("2 -> Contar vocales");
            System.out.println("3 -> Contar espacios");
            System.out.println("4 -> Contar palabras, vocales y espacios");
            System.out.println("5 -> Convertir el texto a solo mayusculas");
            System.out.println("6 -> Convertir el texto a solo minusculas");
            System.out.println("7 -> Capitalizar el texto");
            System.out.println("8 -> Ingresar otro texto");
            System.out.println("9 -> Salir");
            do {
                try {
                    System.out.println("Indique la accion que desea realizar:");
                    String auxiliar = lector.nextLine();
                    accion = Integer.parseInt(auxiliar);
                    if (accion > 9 || accion <= 0) {
                        System.out.println("Error debe ingresar un numero entero entre 1 y 9");
                        accion = -1;
                    }
                } catch (Exception error) {
                    System.out.println("Error debe ingresar un numero entero entre 1 y 9");
                    accion = -1;
                }
            } while (accion == -1);
            switch (accion) {
                case 1:
                    analizar.contarPalabras(texto);
                    System.out.println("El texto contiene " + analizar.getNumeroPalabras() + " palabras");
                    break;
                case 2:
                    analizar.contarVocales(texto);
                    System.out.println("El texto contiene " + analizar.getVocales() + " vocales");
                    break;
                case 3:
                    analizar.contarEspacios(texto);
                    System.out.println("El texto contiene " + analizar.getEspacios() + " espacios");
                    break;
                case 4:
                    analizar.contarPalabras(texto);
                    System.out.println("El texto contiene " + analizar.getNumeroPalabras() + " palabras");
                    analizar.contarVocales(texto);
                    System.out.println("El texto contiene " + analizar.getVocales() + " vocales");
                    analizar.contarEspacios(texto);
                    System.out.println("El texto contiene " + analizar.getEspacios() + " espacios");
                    break;
                case 5:
                    texto = analizar.convertirMayuscula(texto);
                    System.out.println(texto);
                    break;
                case 6:
                    texto = analizar.convertirMinuscula(texto);
                    System.out.println(texto);
                    break;
                case 7:
                    texto = analizar.capitalizarTexto(texto);
                    System.out.println(texto);
                    break;
                case 8:
                    System.out.println("Ingrese el nuevo texto:");
                    texto = lector.nextLine();
                    break;
                case 9:
                    System.out.println("Gracias por usar el Analizador de Texto");
                    salir = true;
                    break;
            }
        }
    }
}