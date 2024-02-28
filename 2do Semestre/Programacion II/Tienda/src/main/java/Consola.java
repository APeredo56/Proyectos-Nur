import java.util.Scanner;

public class Consola {


    public static void main(String[] args) {

        Tienda tiendita = new Tienda();

        tiendita.addProducto("Pepsi 2l", 7.5f);
        tiendita.addProducto("Lays", 16);

        System.out.println(tiendita.verLista());
        System.out.println("TOTAL:      Bs. " + tiendita.getTotalPrecioProductos());


    }
}
