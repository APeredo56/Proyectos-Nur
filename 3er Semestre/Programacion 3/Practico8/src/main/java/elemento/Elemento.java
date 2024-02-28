package elemento;

import modelo.Configuracion;

import java.io.Serializable;

public class Elemento implements Serializable {
    protected String nombreReal;
    protected long peso;
    protected Configuracion configuracion;
    protected String ruta;
    protected String tipo;

    public String getNombreReal() {
        return nombreReal;
    }

    public long getPeso() {
        return peso;
    }

    public String getTipo() {
        return tipo;
    }

    public String getRuta() {
        return ruta;
    }
}
