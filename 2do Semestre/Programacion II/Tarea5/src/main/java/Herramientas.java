public class Herramientas {
    public Herramientas() {
    }

    private int numeroPalabras;
    private int vocales;
    private int espacios;
    private int longitud;
    private int indice;
    private String auxiliarTexto;
    private String letra;

    public int contarPalabras (String texto){
        auxiliarTexto = texto + " ";
        longitud = auxiliarTexto.length();
        int contador = 0;
        while (longitud >= 1){
            indice = auxiliarTexto.indexOf(" ");
            auxiliarTexto = auxiliarTexto.substring(indice + 1, longitud);
            longitud = auxiliarTexto.length();
            contador += 1;
        }
        numeroPalabras = contador;
        return numeroPalabras;
    }
    public int contarVocales (String texto){
        auxiliarTexto = texto.toLowerCase();
        vocales = 0;
        for (int contador = 0; contador < texto.length(); contador += 1){
            letra = auxiliarTexto.substring(contador, contador + 1);
            if (letra.equals("a") || letra.equals("e") || letra.equals("i") || letra.equals("o") || letra.equals("u")){
                vocales += 1;
            }
        }
        return vocales;
    }
    public int contarEspacios(String texto){
        espacios = 0;
        for (int contador = 0; contador < texto.length(); contador += 1){
            letra = auxiliarTexto.substring(contador, contador + 1);
            if (letra.equals(" ")){
                espacios += 1;
            }
        }
        return espacios;
    }

    public String convertirMayuscula(String texto){

        texto = texto.toUpperCase();
        return texto;
    }
    public String convertirMinuscula(String texto){
        texto = texto.toLowerCase();
        return texto;
    }
    public String capitalizarTexto(String texto){
        texto = texto.toUpperCase() + " ";
        longitud = texto.length();
        auxiliarTexto = "";
        while (longitud >= 1){
            indice = texto.indexOf(" ");
            auxiliarTexto = auxiliarTexto + texto.substring(0, 1);
            auxiliarTexto = auxiliarTexto + texto.toLowerCase().substring(1, indice) + " ";
            texto = texto.substring(indice + 1, longitud);
            longitud = texto.length();
        }
        texto = auxiliarTexto;
        return texto;
    }

    public int getEspacios() {
        return espacios;
    }

    public int getNumeroPalabras() {
        return numeroPalabras;
    }

    public int getVocales() {
        return vocales;
    }
}
