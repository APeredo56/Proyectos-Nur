public class Herramientas {
    private int limite;
    private int elemento;
    private boolean validador;
    private String textoComparador = "";

    public Herramientas() {
    }

    public int definirLimite(String conjunto) {
        int indice = conjunto.indexOf(" ");
        this.limite = Integer.parseInt(conjunto.substring(0, indice));
        return this.limite;
    }

    public String generarTextoComparador() {
        int contador = this.limite;
        while (contador >= 2) {
            String auxiliar = contador + "";
            this.textoComparador = this.textoComparador + auxiliar + " ";
            contador -= 1;
        }
        return this.textoComparador;
    }

    public int encontrarElemento(String conjunto) {
        int longitud;
        String auxiliar = limite + "";
        String comparador = "";
        String auxiliarComparador = "";
        int indice;
        while (!validador) {
            indice = textoComparador.indexOf(" ");
            comparador = this.textoComparador.substring(0, indice);
            longitud = this.textoComparador.length();
            if (conjunto.contains(comparador)) {
                this.textoComparador = this.textoComparador.substring(indice + 1, longitud);
            } else {
                validador = true;
                this.elemento = Integer.parseInt(comparador);
            }

        }
        return this.elemento;
    }

    public int getElemento() {
        return this.elemento;
    }
}
