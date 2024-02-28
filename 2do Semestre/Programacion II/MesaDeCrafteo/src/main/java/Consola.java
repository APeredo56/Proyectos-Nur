public class Consola {
    public static void main(String[] args) {
        Maquina mesa = new Maquina(3,3);
        mesa.crearReceta("Espada",1,2);
        mesa.crearReceta("Armadura", 0,8);
        mesa.crearReceta("Escudo",0, 4);
        mesa.crearReceta("Palo", 2,0);
        System.out.println(mesa.producir("Espada"));
        System.out.println(mesa.producir("Katana"));
        System.out.println(mesa.producir("Armadura"));
        mesa.addMetal(20);
        System.out.println(mesa.producir("Armadura"));
        mesa.addMadera(20);
        System.out.println(mesa.producir("Palo"));
        System.out.println(mesa.producir("Palo"));
        System.out.println(mesa.producir("Palo"));
        System.out.println(mesa.producir("Palos"));
        System.out.println(mesa.producir("Palo"));
    }
}
