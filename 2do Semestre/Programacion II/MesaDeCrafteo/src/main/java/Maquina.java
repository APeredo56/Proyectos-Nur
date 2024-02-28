public class Maquina {
    private int madera;
    private int metal;
    private Receta[] libro = new Receta[10];

    public Maquina(int madera, int metal) {
        this.madera = madera;
        this.metal = metal;
    }

    public void crearReceta(String nombre, int madera, int metal) {
        for (int contador = 0; contador < libro.length; contador++) {
            if (libro[contador] == null) {
                libro[contador] = new Receta(nombre, madera, metal);
                return;
            }
        }

    }

    public String producir(String receta) {
        for (int contador = 0; contador < libro.length; contador++) {
            if (libro[contador] != null) {
                if (receta.equals(libro[contador].getNombre())) {
                    if (this.madera < libro[contador].getMadera()) {
                        return "No hay suficiente madera";
                    }
                    if (this.metal < libro[contador].getMetal()) {
                        return "No hay suficiente metal para crear el objeto " + libro[contador].getNombre();
                    }
                    this.madera -= libro[contador].getMadera();
                    this.metal -= libro[contador].getMetal();
                    return "Se ha creado el objeto " + libro[contador].getNombre();
                }
            }
        }
        return "No se encontro la receta especificada";
    }

    public String verRecetas() {
        String texto = "";
        for (int contador = 0; contador < libro.length; contador++) {
            texto += contador + 1 + ".- " + libro[contador].getNombre();
        }
        return texto;
    }

    public void addMadera(int madera) {
        this.madera = this.madera + madera;
    }

    public void addMetal(int metal) {
        this.metal = this.metal + metal;
    }
}


