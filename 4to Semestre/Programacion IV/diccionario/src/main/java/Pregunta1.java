import diccionario.DiccionarioSecuencia;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Pregunta1 {

    /**
     *
     * @param in Repesenta la entrada
     * @return Retorna una lista con las salidas
     */
    public ArrayList resolver(InputStream in){
        DiccionarioSecuencia diccionario;
        ArrayList output = new ArrayList<>();
        Scanner scanner = new Scanner(in);
        Scanner scanner2;
        int x = 0;
        int casos = Integer.parseInt(scanner.nextLine());
        String lineaActual = "";
        ArrayList listaNumeros;

        while (x < casos){
            diccionario = new DiccionarioSecuencia<>();
            lineaActual = scanner.nextLine();
            listaNumeros = new ArrayList<>();
            scanner2 = new Scanner(lineaActual);

            while (scanner2.hasNextInt()){
                int numero = scanner2.nextInt();
                listaNumeros.add(numero);
            }

            for (int numX:listaNumeros) {
                int numero;
                if (numX < 0){
                    numero = numX * (-1);
                } else {
                    numero = numX;
                }
                diccionario.insertar(numero, x);
            }
            addToOutput(diccionario.getCantidadElementos(), output);
            x++;
        }

        return output;
    }

    private void addToOutput(int value, ArrayList output){
        output.add(value);
        System.out.println(value);
    }

    public static void main(String[] args) {


        Pregunta1 p1 = new Pregunta1();
        ArrayList output = p1.resolver(System.in);



    }


}
