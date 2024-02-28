

public class Metodos {
        private int temporadas;
        private long duracion;
        private int [] capitulos;
        private long minutos;
        private int indice;
        private String textoResultado = "";

        public void obtenerLinea1 (String texto){
            indice = texto.indexOf(" ");
            temporadas = Integer.parseInt(texto.substring(0,indice));
            duracion = Long.parseLong(texto.substring(indice + 1, texto.length()));
        }

        public void obtenerLinea2 (String texto2){
            capitulos = new int[temporadas];
            boolean validador = true;
            int contador = 0;
            texto2 = texto2 + "  ";
            indice = texto2.indexOf(" ");
            while (validador){
                capitulos[contador] = Integer.parseInt(texto2.substring(0, indice));
                texto2 = texto2.substring(indice + 1, texto2.length());
                if (texto2.length() <= 1){
                    validador = false;
                }
                contador ++;
            }
        }

        public long obtenerDuracion (){
            minutos = 0;
            for (int contador = 0; contador < temporadas; contador ++){
                for (int contador2 = 0; contador2 <= contador; contador2 ++){
                    minutos += capitulos[contador2] * duracion;
                }
            }
            return  minutos;
        }
        public String guardarDuracion(){
            return textoResultado += minutos + "" + "\n";
        }

    public String getTextoResultado() {
        return textoResultado;
    }
}
