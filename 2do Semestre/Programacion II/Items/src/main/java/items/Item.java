package items;

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
        return nombre + " - " + vida;
    }

    public int usar() {
        return vida;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
