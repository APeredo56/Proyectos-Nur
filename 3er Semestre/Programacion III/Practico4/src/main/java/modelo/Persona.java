package modelo;

public class Persona {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private int edad;


    public Persona(String nombre, String primerApellido, String segundoApellido, int edad) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido(){
        return segundoApellido;
    }

    public int getEdad() {
        return edad;
    }
}
