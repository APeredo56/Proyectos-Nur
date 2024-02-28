import java.util.Scanner;

public class Consola {
    public static void main(String[] args) {
        Metodos calcular = new Metodos();
        String texto = "";
        String texto2 = "";
        boolean validador = false;
        Scanner lector = new Scanner(System.in);

        System.out.println("Ingrese los datos:");
        while (!validador){
            texto = lector.nextLine();
            calcular.obtenerLinea1(texto);
            if (texto.contains("-1")){
                break;
            }
            texto2 = lector.nextLine();
            calcular.obtenerLinea2(texto2);
            calcular.obtenerDuracion();
            calcular.guardarDuracion();
        }
        System.out.println(calcular.getTextoResultado());
    }
}
