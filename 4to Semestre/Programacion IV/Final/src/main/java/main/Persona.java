package main;
public class Persona {

    private String nombre;
    private int edad;
    private int estatura;
    private double peso;

    public Persona(String nombre, int edad, int estatura, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.estatura = estatura;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return nombre + "(" + edad + " años, "  + estatura + "cm, " + peso + "kg)";
    }

    public int compareTo(Persona o) {
        return edad - o.getEdad();
    }
}
