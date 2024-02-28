package elemento;

import modelo.Configuracion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class Archivo extends Elemento {
    private String nombreCambiado = "";
    private File contenido;

    public Archivo (String ruta, Configuracion configuracion){
        File aux = new File(ruta);
        this.nombreReal = aux.getName();
        this.configuracion = configuracion;
        generarNombre();
        this.tipo = nombreReal.substring(nombreReal.indexOf(".")+1);
        peso = aux.length();
        Path origenPath = Paths.get(ruta);
        Path destinoPath = Paths.get(configuracion.getDirectorioBase()+ nombreReal );
        this.ruta = destinoPath.toString();
        try {
            Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            contenido = new File(destinoPath.toString());
            contenido.renameTo(new File(configuracion.getDirectorioBase() + nombreCambiado));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarNombre(){
        int contador = 0;
        int indice;
        Random random = new Random();
        while (contador < nombreReal.length()){
            indice = random.nextInt(1,26);
            switch (indice){
                case 1 -> nombreCambiado += "a";
                case 2 -> nombreCambiado += "b";
                case 3 -> nombreCambiado += "c";
                case 4 -> nombreCambiado += "d";
                case 5 -> nombreCambiado += "e";
                case 6 -> nombreCambiado += "f";
                case 7 -> nombreCambiado += "g";
                case 8 -> nombreCambiado += "h";
                case 9 -> nombreCambiado += "i";
                case 10 -> nombreCambiado += "j";
                case 11 -> nombreCambiado += "k";
                case 12 -> nombreCambiado += "l";
                case 13 -> nombreCambiado += "m";
                case 14 -> nombreCambiado += "n";
                case 15 -> nombreCambiado += "o";
                case 16 -> nombreCambiado += "p";
                case 17 -> nombreCambiado += "q";
                case 18 -> nombreCambiado += "r";
                case 19 -> nombreCambiado += "s";
                case 20 -> nombreCambiado += "t";
                case 21 -> nombreCambiado += "u";
                case 22 -> nombreCambiado += "v";
                case 23 -> nombreCambiado += "w";
                case 24 -> nombreCambiado += "x";
                case 25 -> nombreCambiado += "y";
                case 26 -> nombreCambiado += "z";
            }
            contador++;
        }
    }

    public String getNombreCambiado() {
        return nombreCambiado;
    }

    public File getContenido() {
        return contenido;
    }
}
