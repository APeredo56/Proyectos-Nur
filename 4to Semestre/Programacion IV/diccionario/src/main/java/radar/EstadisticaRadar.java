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
        return veces == 0 && velocidadPromedio == 0 ?
                "El automovil no ha sido registrado" :
                "velocidadPromedio=" + velocidadPromedio +
                        ", veces=" + veces;
    }
}
