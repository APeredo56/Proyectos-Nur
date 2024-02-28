public class Receta {
    private String nombre;
    private int madera;
    private int metal;


    public Receta (){
    }
    public Receta (String nombre, int madera, int metal){
        this.nombre = nombre;
        this.madera = madera;
        this.metal = metal;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMadera() {
        return madera;
    }

    public int getMetal() {
        return metal;
    }
}
