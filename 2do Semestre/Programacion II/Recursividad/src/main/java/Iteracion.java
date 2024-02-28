public class Iteracion implements Generador{
    @Override
    public String factorial(int valores) {
        int resultado = 1;
        for (int i = 1; i <= valores ; i++) {
            resultado = resultado * i;
        }
        return resultado+"";
    }

    @Override
    public String fibonacci(int valores) {
        int valor1 = 0;
        int valor2 = 1;
        String serie = "0";
        for (int i = 1; i < valores; i++) {
            if (i==1){
                valor1 ++;
            } else {
                valor1 += valor2;
            }
            serie += valor2;
        }
        return serie;
    }

    @Override
    public String invertir(String texto) {
        String textoInvertirdo = "";
        for (int i = texto.length(); i > 0; i--) {
            textoInvertirdo += texto.substring(i-1 ,i);
        }
        return textoInvertirdo;
    }

    @Override
    public String mostrarLista(int... arreglo) {
        String texto = "";
        for (int i = 0; i < arreglo.length; i++) {
            texto += arreglo[i] + ", ";
        }
        return texto.substring(0,texto.length()-2);
    }
}
