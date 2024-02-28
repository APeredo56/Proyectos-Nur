package paint.modelo.transformaciones;

import paint.modelo.Imagen;

public class TransformarPixelado extends Transformacion {

    private int color;
    private int[][] pixelesCambiados;

    public TransformarPixelado(Imagen img) {
        this.imagen = img;
        pixelesCambiados = new int[imagen.getAncho()][imagen.getAlto()];
        pixelesOriginales = new int[img.getAncho()][img.getAlto()];
        pixelesOriginales = img.getPixeles();
    }

    @Override
    public void transformar() {
        for (int y = 0; y < imagen.getAlto(); y += 3) {
            for (int x = 0; x < imagen.getAncho(); x += 3) {
                color = imagen.getPixeles()[x][y];

                for (int j = y; j <= y + 3 && j < imagen.getAlto(); j++) {
                    for (int i = x; i <= x + 3 && i < imagen.getAncho(); i++) {
                        pixelesCambiados[i][j] = color;
                    }

                }
            }
        }
        imagen.setPixeles(pixelesCambiados);
        imagen.transformada();

    }
}
