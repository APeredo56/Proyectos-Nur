import java.util.Arrays;
import java.util.HashMap;

public class Cambiador {

    private Integer[] cortes;
    private Comparador c = new Comparador();

    public Cambiador(Integer... cortes){
        this.cortes = cortes;
        Arrays.sort(this.cortes, c);

    }

    public HashMap<Integer, Integer> cambiar(int valor){
        HashMap<Integer, Integer> cambio = new HashMap<>();
        //Ejemplo para cambiar 56$ con cortes de 20,10,5,1
        //Corte: Cantidad
        //20 : 2
        //10 : 1
        //5 : 1
        //1 : 1
        int valorActual = 0;
        int monedaActual = 0;
        while (valorActual < valor){
            valorActual += cortes[monedaActual];
            cambio.put(cortes[monedaActual], 1);
            if (valorActual > valor){
                valorActual -= cortes[monedaActual];
                monedaActual ++;
            }
            System.out.println(valorActual);
        }
        return cambio;
    }


    public static void main(String[] args) {
        Cambiador cambiador = new Cambiador( 20,10,5,2,1);
        HashMap<Integer, Integer> cambio = cambiador.cambiar(36);
        for(Integer corte : cambio.keySet()){
            System.out.println(corte + " : " + cambio.get(corte));
        }
    }

}