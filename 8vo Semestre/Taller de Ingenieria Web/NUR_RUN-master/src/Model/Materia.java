package Model;

public class Materia {
    private String cod;
    private String nombre;
    private Docente docente;

    public Materia(String cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Docente getDocente() {
        return docente;
    }
}
