public class Calculadora {

    public Calculadora() {

    }

    public int sumar(int a, int b) {
        int resultado = a + b;
        return resultado;
    }

    public float sumar(float a, float b) {
        float resultado = a + b;
        return resultado;
    }

    public float sumar(int a, int b, int c) {
        return a + b + c;
    }

    public int sumar(int... nros) {
        int total=0;
        for (int i = 0; i < nros.length; i++) {
            total += nros[i];
        }
        return total;
    }
    // sumar con 2, 3 y N digitos

    // restar, multiplicar y dividir --> 2 digitos


}
