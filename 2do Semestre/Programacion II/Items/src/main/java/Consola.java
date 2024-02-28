import items.Arma;
import items.Item;
import items.Pocion;

public class Consola {

    public static void main(String[] args) {

        Personaje geralt=new Personaje("Geralt de Rivia",100);
        Personaje vesemir=new Personaje("Vesemir",200);

        Item pocion=new Pocion("Pocion pequeña", 50);
        Item veneno=new Item("Veneno Básico",-20);
        Item medicina=new Item("Botiquin",30);
        Item pistola=new Arma("Ballesta",700,2);

        geralt.guardar(pocion);
        geralt.guardar(veneno);
        geralt.guardar(medicina);
        geralt.guardar(pistola);
        geralt.crearPocion("Elixir Especial");


        System.out.println(geralt.verMochila());

        System.out.println(geralt.getVida());  // 100
        geralt.usar(pocion);
        System.out.println(geralt.getVida());
        geralt.usar("Pocion pequeña");
        System.out.println(geralt.getVida());
        geralt.usar(3);
        geralt.usar(3);
        geralt.usar(3);
        Arma aux = (Arma) geralt.getItem(3);
        aux.recargar();
        geralt.usar(3);
        geralt.usar(3);
        System.out.println(geralt.getVida());   // 85

        System.out.println(geralt.verMochila());

        System.out.println("Vesemir: "+vesemir.getVida());
        geralt.usar(3,vesemir);
        System.out.println("Vesemir: "+vesemir.getVida());






        //3. Crear pociones, poner un nombre standard, y la vida puede ser de 5 a 20 puntos (RANDOM)
        //4. Usar items sobre otros personajes



    }
}
