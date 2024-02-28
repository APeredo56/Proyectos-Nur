package Pasteleria;
import java.util.Scanner;
public class Pasteles {
    String [][] listaPasteles;
    String [][] listaIngredientes;
    String [] nombre;
    String [] precio;
    String [] cantidadInventario;
    String [] ingrediente;
    String [] cantidadIngredientes;
    String [] unidadDeMedida;
    String [] precioUnitario;
    int numeroEntero;
    int numeroDouble;

    public Pasteles(int numeroPasteles){
        this.listaPasteles = new String[numeroPasteles][3];
        this.listaPasteles [0][0] = "Nombre";
        this.listaPasteles [0][1] = "Precio";
        this.listaPasteles [0][2] = "Cantidad en Inventario";
        this.listaIngredientes = new String [numeroPasteles][4];
        this.listaIngredientes [0][0] = "Ingrediente";
        this.listaIngredientes [0][1] = "Cantidad";
        this.listaIngredientes [0][2] = "Unidad de medida";
        this.listaIngredientes [0][3] = "Precio Unitario";
        this.nombre = new String[numeroPasteles];
        this.precio = new String[numeroPasteles];
        this.cantidadInventario = new String[numeroPasteles];
        this.ingrediente = new String[numeroPasteles];
        this.cantidadIngredientes = new String[numeroPasteles];
        this.unidadDeMedida = new String[numeroPasteles];
        this.precioUnitario = new String[numeroPasteles];
    }
    public int pedirNumerosEnteros(Scanner lector){
        do {
            try {
                String auxiliar = lector.nextLine();
                this.numeroEntero = Integer.parseInt(auxiliar);
            } catch (Exception error){
                System.out.println("Error debe ingresar un numero entero mayor a cero");
                this.numeroEntero = 0;
            }
        } while ( this.numeroEntero <= 0);
    return  this.numeroEntero;
    }
    public int pedirNumerosDouble(Scanner lector){
        do {
            try {
                String auxiliar = lector.nextLine();
                this.numeroDouble = Integer.parseInt(auxiliar);
            } catch (Exception error){
                System.out.println("Error debe ingresar un numero entero mayor a cero");
                this.numeroDouble = 0;
            }
        } while ( this.numeroDouble <= 0);
        return  this.numeroDouble;
    }
    public String[][] guardarPastel(Scanner lector, int pastelActual){
        System.out.println("Ingrese el nombre del pastel:");
        this.listaPasteles[pastelActual][0] = lector.nextLine();
        System.out.println("Ingrese el precio del pastel:");
        pedirNumerosDouble(lector);
        this.listaPasteles [pastelActual][1] = this.numeroDouble + "";
        System.out.println("Ingrese la cantidad que hay en inventario de este pastel:");
        pedirNumerosEnteros(lector);
        this.listaPasteles [pastelActual][2] = this.numeroEntero + "";
        return this.listaPasteles;
    }
    public String[][] guardarIngredientes (Scanner lector, int pastelActual) {
        System.out.println("Ingrese la cantidad de ingredientes que tiene el pastel:");
        int numIngredientes = pedirNumerosEnteros(lector);
        int contador = 0;
        int ingredienteActual = 0;
        while (contador <= numIngredientes) {
            ingredienteActual += 1;
            System.out.println("Ingrese el nombre del ingrediente:");
            this.listaIngredientes[ingredienteActual][0] = lector.nextLine();
            System.out.println("Ingrese la cantidad de ingredientes usados:");
            pedirNumerosEnteros(lector);
            this.listaIngredientes[ingredienteActual][1] = this.numeroEntero + "";
            System.out.println("Ingrese la unidad de medida:");
            this.listaIngredientes[ingredienteActual][2] = lector.nextLine();
            System.out.println("Ingrese el precio por unidad del ingrediente:");
            pedirNumerosDouble(lector);
            this.listaIngredientes[ingredienteActual][3] = this.numeroDouble + "";
            contador += 1;
        }
        return listaIngredientes;
    }
    public void menu(){
        System.out.println("Lista de acciones:");
        System.out.println("1 Guardar un pastel");
        System.out.println("2 Guardar ingredientes de un pastel");
        System.out.println("3 Eliminar el ultimo pastel ingresado");
        System.out.println("4 Mostrar los pasteles ingresados");
        System.out.println("5 Salir del programa");
        System.out.println("Ingrese la opcion que desea realizar:");
    }
}
