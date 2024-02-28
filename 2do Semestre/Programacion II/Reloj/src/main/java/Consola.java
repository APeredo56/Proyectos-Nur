import java.util.Scanner;

public class Consola {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Reloj bolivia = new Reloj();
        Reloj españa = new Reloj();

        bolivia.configurarHora(18, 1, 7);
        bolivia.configurarAlarma(7,0,0);
        bolivia.activarAlarma();
        //españa.configurarHora(14, 49, 5);

        System.out.println(bolivia.verHora());
        //System.out.println(españa.verHora());

        try {
            for (int i = 0; i < 10; i++) {
                bolivia.avanzar();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bolivia.cambiarFormato();
        System.out.println(bolivia.verHora());
    }
}
