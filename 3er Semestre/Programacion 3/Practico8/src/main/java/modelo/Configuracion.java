package modelo;

import java.io.Serializable;

public final class Configuracion implements Serializable {
    private static Configuracion instance;
    private String directorioBase = "C:\\Users\\USUARIO\\IdeaProjects\\Practico6\\src\\Repositorio\\";

    private Configuracion(){}

    public static Configuracion getInstance(){
        if (instance == null){
            instance = new Configuracion();
        }
        return instance;
    }

    public String getDirectorioBase() {
        return directorioBase;
    }

    public void setDirectorioBase(String directorioBase) {
        this.directorioBase = directorioBase;
    }
}
