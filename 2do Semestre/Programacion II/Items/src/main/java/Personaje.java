import items.Item;
import items.Pocion;

public class Personaje {
    private String nombre;
    private int vida;
    private int vida_max;
    private Item[] mochila = new Item[5];

    public Personaje(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
        this.vida_max = vida;
    }

    public void guardar(Item objeto) {
        for (int i = 0; i < mochila.length; i++) {
            if (mochila[i] == null) {
                mochila[i] = objeto;
                return;
            }
        }
    }

    public void crearPocion(String nombre) {
        int cura=(int)(Math.random()*15)+5;
        Pocion invento=new Pocion(nombre,cura);
        guardar(invento);
    }

    public Item botar(int pos) {
        Item aux = mochila[pos];
        mochila[pos] = null;
        return aux;
    }

    public Item getItem(int pos){
        return mochila[pos];
    }

    public void usar(int bolsillo) {
        if(vida <0){
            System.out.println("tu personaje se ha estido");
            return;
        }
        Item aux = mochila[bolsillo];
        vida += aux.usar();
        if (vida > vida_max){
            vida=vida_max;
        }
        //botar(bolsillo);
    }
    public void usar(int bolsillo, Personaje otro_jugador){
        Item aux= mochila[bolsillo];
        otro_jugador.usar(aux);
    }

    public void usar(String nombre) {
        for (int i = 0; i < mochila.length; i++) {
            if (mochila[i] != null && mochila[i].getNombre().equalsIgnoreCase(nombre)) {
                usar(i);
            }
        }
    }
    public void usar(Item objeto){
        vida += objeto.usar();
    }

    public int getVida() {
        return vida;
    }

    public String verMochila() {
        String texto = "";
        for (int i = 0; i < mochila.length; i++) {
            Item aux = mochila[i];
            if (aux != null) {
                texto += aux.inspeccionar() + "\n";
            }
        }
        return texto;
    }
}
