import java.util.Scanner;

public class Metodos {
    private int [] numeros;
    private int maximo = 999999999;
    private int minimo = -999999999;
    private double promedio;
    Scanner lector = new Scanner(System.in);
    public int [] rellenarArreglo(int longitud){
        numeros = new int[longitud];
        for (int contador = 0; contador < numeros.length; contador ++){
            System.out.println("Ingrese el numero " + (contador + 1) + " de " + longitud);
            numeros[contador] = lector.nextInt();

        }
        return numeros;
    }
    public int encontrarMinimo (){
        for (int contador = 0; contador < numeros.length; contador ++){
            if (minimo > numeros[contador]){
                minimo = numeros[contador];
            }
        }
        return minimo;
    }



}
