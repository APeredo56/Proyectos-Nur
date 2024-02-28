package elementos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Dado implements Serializable {
    private transient BufferedImage[] imagenesNumeros = new BufferedImage[6];
    private transient BufferedImage imagenSeleccionada;
    private int x;
    private int y;
    private int size;
    private Random random = new Random();

    public Dado(){
        for (int i = 1; i <= 6 ; i++) {
            try {
                BufferedImage imagen;
                String ruta = "src\\imagenes\\dado" + i + ".png";
                File file = new File(ruta);
                imagen = ImageIO.read(file);
                imagenesNumeros[i-1] = imagen;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imagenSeleccionada = imagenesNumeros[0];
    }

    public void acomodar(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void dibujar(Graphics g){
        g.drawImage(imagenSeleccionada, x, y, size, size, null);
    }

    public boolean presionado(Point puntoClick){
        if (puntoClick.x > x && puntoClick.x < x + size && puntoClick.y > y && puntoClick.y < y + size) {
            return true;
        }
        return false;
    }

    public int tirar(){
        int numero = random.nextInt(1, 6);
        imagenSeleccionada = imagenesNumeros[numero -1];
        return numero;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(imagenesNumeros.length); // how many images are serialized?
        for (BufferedImage eachImage : imagenesNumeros) {
            ImageIO.write(eachImage, "png", out); // png is lossless
        }
        imagenSeleccionada = imagenesNumeros[0];
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        final int imageCount = in.readInt();
        imagenesNumeros = new BufferedImage[imageCount];
        for (int i=0; i<imageCount; i++) {
            imagenesNumeros[i] = ImageIO.read(in);
        }
        imagenSeleccionada = imagenesNumeros[0];
    }


}
