package elemento;

import listas.Lista;
import modelo.Configuracion;

public class Carpeta extends Elemento {


    private Lista<Elemento> archivosEnCarpeta = new Lista<>();

    public Carpeta(Configuracion configuracion, String ruta, String nombre){
        this.tipo = "DIR";
        this.configuracion = configuracion;
        this.ruta = ruta;
        this.nombreReal = nombre;
    }

    public Lista<Elemento> getArchivosEnCarpeta() {
        return archivosEnCarpeta;
    }
}
