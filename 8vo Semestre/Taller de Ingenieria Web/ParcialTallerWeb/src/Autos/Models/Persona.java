package Autos.Models;

public class Persona {
    String nombre;
    String apellido;
    boolean vip;

    public Persona(String nombre, String apellido, boolean vip) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.vip = vip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", vip=" + vip +
                '}';
    }
}
