import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problema1 {
    public static void main(String[] args) {
        Problema1 p=new Problema1();
        p.generarProblema(50);
        Scanner sc = new Scanner(System.in);
        long numeros = sc.nextLong();
        long total = (numeros * (numeros + 1)) / 2;
        long acumulado = 0;
        for (int i = 0; i < numeros - 1; i++) {
            acumulado += sc.nextLong();
        }
        System.out.println(total - acumulado);


    }


    private void generarProblema(int tamaño) {
        Integer[] arreglo = new Integer[tamaño];
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = i + 1;
        }
        List<Integer> intList = Arrays.asList(arreglo);
        Collections.shuffle(intList);
        intList.toArray(arreglo);

        int nro = (int) (Math.random() * tamaño);
        System.out.println(nro);
        System.out.print(tamaño+" ");
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] != nro) {
                System.out.print("" + arreglo[i] + " ");
            }
        }
    }
}
