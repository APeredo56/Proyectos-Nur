public class Item {

    protected int vida;
    protected int peso;
    protected float precio;
    protected String color;
    protected String nombre;
    protected String descripcion = "";

    public Item() {
    }

    public Item(String nombre, int vida) {
        this.vida = vida;
        this.nombre = nombre;
    }

    public String inspeccionar() {
        return nombre + " - " + descripcion;
    }

    public int usar() {
        return vida;
    }


}
