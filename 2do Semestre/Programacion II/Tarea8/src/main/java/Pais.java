public class Pais {
    private String nombre;
    private Ciudad[] ciudades = new Ciudad[3];

    public String agregarCiudad(String nombre, int hab, boolean capital) {
        for (int contador = 0; contador < ciudades.length; contador++) {
            if (ciudades[contador] == null) {
                ciudades[contador] = new Ciudad(nombre, hab, capital);
                return "Se ha agregado la ciudad";
            }
        }
        return "La lista esta llena";
    }

    public int getTotalHabitantes() {
        int total = 0;
        for (int contador = 0; contador < ciudades.length; contador++) {
            if (ciudades[contador] != null) {
                total += ciudades[contador].getHabitantes();
            }
        }
        return total;
    }

    public String getNombreCapital() {
        for (int contador = 0; contador < ciudades.length; contador++) {
            if (ciudades[contador] != null) {
                if (ciudades[contador].esCapital()) {
                    return ciudades[contador].getNombre();
                }
            }
        }
        return "La capital no fue especificada";
    }
}
