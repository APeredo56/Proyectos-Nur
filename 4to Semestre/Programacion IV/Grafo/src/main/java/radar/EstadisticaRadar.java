package radar;

public class EstadisticaRadar {

    private float velocidadPromedio;
    private int veces;

    public EstadisticaRadar(float velocidadPromedio, int veces) {
        this.velocidadPromedio = velocidadPromedio;
        this.veces = veces;
    }

    public float getVelocidadPromedio() {
        return velocidadPromedio;
    }

    public int getVeces() {
        return veces;
    }

    @Override
    public String toString() {
        return "EstadisticaRadar{" +
                "velocidadPromedio=" + velocidadPromedio +
                ", veces=" + veces +
                '}';
    }
}
