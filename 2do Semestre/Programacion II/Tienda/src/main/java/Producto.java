public class Producto {

    private String nombre;
    private float precio;
    private String categoria;

    //Constructor
    //MA - nombreClase
    public Producto(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(String nombre, float precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    //MA - ret  - nombre (parametros)

    public String verInfo() {
        return nombre + " - Bs. " + precio;
    }

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
