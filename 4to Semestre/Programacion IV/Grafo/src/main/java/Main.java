import diccionario.Diccionario;
import diccionario.DiccionarioTablaHash;

public class Main {


    public static void main(String[] args) {
        Diccionario<String, Integer> diccionario = new DiccionarioTablaHash<>();


        System.out.println(diccionario);

        diccionario.insertar("uno", 1);
        System.out.println(diccionario);
        diccionario.insertar("dos", 2);
        System.out.println(diccionario);
        diccionario.insertar("tres", 3);
        System.out.println(diccionario);
        diccionario.insertar("cuatro", 4);
        System.out.println(diccionario);
        diccionario.insertar("cinco", 5);
        System.out.println(diccionario);

        diccionario.insertar("seis", 6);
        System.out.println(diccionario);
        diccionario.insertar("siete", 7);
        System.out.println(diccionario);
        diccionario.insertar("ocho", 8);
        System.out.println(diccionario);
        diccionario.insertar("nueve", 9);
        System.out.println(diccionario);
        diccionario.insertar("diez", 10);
        System.out.println(diccionario);

        diccionario.insertar("once", 11);
        System.out.println(diccionario);
        diccionario.insertar("doce", 12);
        System.out.println(diccionario);
        diccionario.insertar("trece", 13);
        System.out.println(diccionario);
        diccionario.insertar("catorce", 14);
        System.out.println(diccionario);
        diccionario.insertar("quince", 15);
        System.out.println(diccionario);
        System.out.println("Cantidad de elementos: " + diccionario.getCantidadElementos());
        diccionario.insertar("cinco", 55555);
        System.out.println("Cantidad de elementos: " + diccionario.getCantidadElementos());
    }

}
