package paint.modelo.transformaciones;

import paint.modelo.Imagen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class TransformarTonosRGB extends Transformacion {
    private String color;


    public TransformarTonosRGB(Imagen img, String color) {
        this.imagen = img;
        pixelesCambiados = new int[img.getAncho()][img.getAlto()];
        pixelesOriginales = img.getPixeles();
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
                    pixelesCambiados[i][j] = red | red | red;
                } else {
                    if (color.equals("green")){
                        pixelesCambiados[i][j] = green | green | green;
                    } else {
                        pixelesCambiados[i][j] = blue | blue | blue;
                    }
                }
            }
        }
        imagen.setPixeles(pixelesCambiados);
        imagen.transformada();
    }

}
