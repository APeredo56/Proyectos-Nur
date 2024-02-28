package elemento;

import modelo.Configuracion;

public class Elemento {
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
}
