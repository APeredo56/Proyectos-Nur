package radar;

public class Automovil {

    private String matricula;
    private float velocidad;

    public Automovil(String matricula, float velocidad) {
        this.matricula = matricula;
        this.velocidad = velocidad;
    }

    public Automovil(String matricula) {
        this(matricula, 0);
    }

    public String getMatricula() {
        return matricula;
    }

    public float getVelocidad() {
        return velocidad;
    }
}
