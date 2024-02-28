import java.util.Scanner;

public class ElementoFaltante {
    public static void main(String[] args) {
        Herramientas iniciar = new Herramientas();
        Scanner lector;
        lector = new Scanner(System.in);
        String conjunto;
        System.out.println(">>> Elemento Faltante <<<");
        System.out.println("Ingresar el conjunto:");
        conjunto = lector.nextLine();
        iniciar.definirLimite(conjunto);
        iniciar.generarTextoComparador();
        iniciar.encontrarElemento(conjunto);
        System.out.println(iniciar.getElemento());
    }
}
