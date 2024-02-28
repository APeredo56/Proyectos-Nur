import java.util.ArrayList;

public class Tienda {

    private Producto[] lista = new Producto[5];
    private ArrayList<Producto> lista_dinamica=new ArrayList<>();

    public Tienda() {

    }

    public Tienda(int tamaño) {
        lista = new Producto[tamaño];
    }


    private void addProducto(Producto objeto) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                lista[i] = objeto;
                return;
            }
        }
    }

    public void addProducto(String nombre, float precio) {
        Producto nuevo = new Producto(nombre, precio);
        // D.R.Y --> Don't Repeat Yourself
        addProducto(nuevo);
    }

    public Producto getProducto(int pos) {
        return lista[pos];
    }
    public Producto getProducto(String nombre){
        return null;
    }

    public boolean eliminarProducto(int pos){
        return  false;
    }

    public String verLista() {
        String info = "";
        for (int i = 0; i < lista.length; i++) {
            Producto aux = lista[i];
            if (aux != null) {
                info += aux.verInfo() + "\n";
            } else {
                info += "--  vacio  --\n";
            }
        }
        return info;
    }

    public String verLista(String categoria){
        return  "";
    }

    public float getTotalPrecioProductos() {
        float total = 0;
        for (int i = 0; i < lista.length; i++) {
            Producto aux = lista[i];
            if (aux != null) {
                total += aux.getPrecio();
            }
        }
        return total;
    }
}
