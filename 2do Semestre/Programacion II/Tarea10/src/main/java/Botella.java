public class Botella {
    int litros = 0;
    int capacidad;
    int espacioDisponible;
    public Botella(int capacidad) {
        this.capacidad = capacidad;
    }

    public int verLitros() {
        return litros;
    }

    public void vaciar() {
        this.litros = 0;
    }

    public void vaciar(Botella otra) {
        otra.espacioDisponible = otra.capacidad - otra.litros;
        if (this.litros < otra.espacioDisponible){
            otra.litros = otra.litros + this.litros;
            this.litros = 0;
        } else {
            otra.llenar();
            this.litros = this.litros - otra.espacioDisponible;

        }

    }

    public void llenar() {
        this.litros = this.capacidad;
    }

    public void verBotella() {
        System.out.println("Botella de " + capacidad + " litros: " + verLitros());
    }


}
