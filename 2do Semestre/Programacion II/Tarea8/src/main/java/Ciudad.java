public class Ciudad {
    private String nombre;
    private int habitantes;
    private boolean capital;

    public Ciudad(String nombre,int hab, boolean capital) {
        this.nombre = nombre;
        this.habitantes = hab;
        this.capital = capital;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHabitantes() {
        return habitantes;
    }
    public boolean esCapital(){
        if (this.capital){
            return true;
        } else {
            return false;
        }
    }
}
