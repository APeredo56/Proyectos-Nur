package items;

public class Arma extends Item{
    private int precision;
    private int usos;

    public Arma(String nombre, int daño, int usos) {
        this.nombre=nombre;
        this.vida=daño;
        this.usos = usos;
    }

    @Override
    public int usar(){
        if(usos>0){
            System.out.println("BANG!!!");
            usos--;
            return -vida;
        }
        System.out.println(".....click...");
        return 0;
    }

    public void recargar(){
        usos = 2;
    }
}
