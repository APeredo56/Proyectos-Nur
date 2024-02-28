/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro2.pkgfinal;

/**
 *
 * @author BlueFox
 */
public class Producto {
    private int codigo;
    private String nombre;
    private String descripcion;
    private int precio;

    public Producto() {
        new Producto();
    }

    public Producto(String nombre, String apellido) {
        this.nombre = apellido;
        this.descripcion = apellido;
    }

    public Producto(int codigo, String nombre, int precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }
}
