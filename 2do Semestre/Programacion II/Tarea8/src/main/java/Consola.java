public class Consola {
    public static void main(String[] args) {
        Pais bolivia = new Pais();
        bolivia.agregarCiudad("Santa Cruz", 10,false);
        bolivia.agregarCiudad("Beni", 5,false);
        bolivia.agregarCiudad("Sucre", 6,true);
        System.out.println(bolivia.getTotalHabitantes());
        System.out.println(bolivia.getNombreCapital());
    }
}
