package Autos.Models;

public class Auto {
    String placa;
    double altura;
    double ancho;
    double largo;
    Persona conductor;

    public Auto(String placa, double altura, double ancho, double largo, Persona conductor) {
        this.placa = placa;
        this.altura = altura;
        this.ancho = ancho;
        this.largo = largo;
        this.conductor = conductor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public Persona getConductor() {
        return conductor;
    }

    public void setConductor(Persona conductor) {
        this.conductor = conductor;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "placa='" + placa + '\'' +
                ", altura=" + altura +
                ", ancho=" + ancho +
                ", largo=" + largo +
                ", conductor=" + conductor +
                '}';
    }
}
