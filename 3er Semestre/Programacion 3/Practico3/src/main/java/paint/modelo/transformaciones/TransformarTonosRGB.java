package paint.modelo.transformaciones;

import paint.modelo.Imagen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class TransformarTonosRGB extends Transformacion {
    private int[][] cambios;
    private String color;

    public TransformarTonosRGB(Imagen img, String color) {
        this.imagen = img;
        cambios = new int[img.getAncho()][img.getAlto()];
        this.color = color;
    }

    @Override
    public void transformar() {

        for (int j = 0; j < imagen.getAlto(); j++) {
            for (int i = 0; i < imagen.getAncho(); i++) {
                int bgr = imagen.getPixeles()[i][j];
                int blue = (bgr >> 16) & 0x000000ff;
                int green = (bgr & 0x0000ff00);
                int red = (bgr & 0x000000ff) << 16;
                if (color.equals("red")){
                    cambios[i][j] = red | red | red;
                } else {
                    if (color.equals("green")){
                        cambios[i][j] = green | green | green;
                    } else {
                        cambios[i][j] = blue | blue | blue;
                    }
                }
            }
        }
        imagen.setPixeles(cambios);
        imagen.transformada();
    }
}
