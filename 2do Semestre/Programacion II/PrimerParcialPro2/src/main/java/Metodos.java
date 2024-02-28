public class Metodos {

    public int letrasEnPalabra(String palabra, char letra) {
        int numLetras = 0;
        for (int contador = 0; contador < palabra.length(); contador++) {
            if (palabra.charAt(contador) == letra) {
                numLetras++;
            }
        }
        return numLetras;
    }

    public String eliminarPares(String numeros) {
        String textoResultado = "";
        String numActual;
        for (int contador = 0; contador < numeros.length(); contador++) {
            numActual = numeros.substring(contador, contador + 1);
            if (Integer.parseInt(numActual) % 2 != 0) {
                textoResultado += numeros.substring(contador, contador + 1);
            }
        }
        return textoResultado;
    }

    public String repetirVocales(String texto, int veces) {
        String letraActual = "";
        String textoResultado = "";
        for (int contador = 0; contador < texto.length(); contador++) {
            letraActual = texto.substring(contador, contador + 1);
            switch (letraActual) {
                case "A":
                case "a":
                case "E":
                case "e":
                case "I":
                case "i":
                case "O":
                case "o":
                case "U":
                case "u":
                    textoResultado += letraActual.repeat(veces);
                    break;
                default:
                    textoResultado += letraActual;
            }
        }
        return textoResultado;
    }
}
