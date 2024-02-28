import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Pregunta1 {

    public List<Integer> resolver(InputStream in) {
        List<Integer> salidas = new ArrayList<>();

        Scanner scanner = new Scanner(in);
        String linea = scanner.nextLine();

        Scanner analizador = new Scanner(linea);

        int tamano = analizador.nextInt();
        int numTareas = analizador.nextInt();
        linea = scanner.nextLine();
        analizador = new Scanner(linea);
        int contador = 0;
        int [] arreglo = new int[tamano];

        while (contador < tamano){
            int actual = analizador.nextInt();
            arreglo[contador] = actual;
            contador++;
        }

        contador = 0;
        while (contador < numTareas){
            linea = scanner.nextLine();
            analizador = new Scanner(linea);
            if (analizador.nextInt() == 1){
                int posicion = analizador.nextInt();
                int valor = analizador.nextInt();
                try {
                    arreglo[posicion] = valor;
                } catch (Exception error){
                    error.printStackTrace();
                }

            } else {
                int n1 = analizador.nextInt();
                int n2 = analizador.nextInt();
                int sumatoria = 0;
                try {
                    int prueba = arreglo[n1];
                    prueba = arreglo[n2];
                    while (n1 <= n2){
                        sumatoria += arreglo[n1];
                        n1 ++;
                    }
                    out(salidas, sumatoria);
                } catch (Exception error){

                    out(salidas, - 1);
                    error.printStackTrace();
                }


            }
            contador++;
        }

        //Completar código

        //si se desea imprimir una linea, hacer uso del método out
        //por ejemplo, si deseo imprimir el valor -1, realizar lo siguiente:
        //out(salidas, -1);

        return salidas;
    }

    public void out(List<Integer> salida, int valor){
        salida.add(valor);
        System.out.println(valor);
    }

    public static void main(String[] args) {
        Pregunta1 pregunta1 = new Pregunta1();
        pregunta1.resolver(System.in);
    }

}
