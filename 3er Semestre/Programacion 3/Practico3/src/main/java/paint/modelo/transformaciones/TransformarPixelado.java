package paint.modelo.transformaciones;

import paint.modelo.Imagen;

public class TransformarPixelado extends Transformacion {

    private int color;
    private int[][] nuevosPixeles;

    public TransformarPixelado(Imagen img) {
        this.imagen = img;
        nuevosPixeles = new int[imagen.getAncho()][imagen.getAlto()];

    }

    @Override
    public void transformar() {
        for (int y = 0; y < imagen.getAlto(); y += 3) {
            for (int x = 0; x < imagen.getAncho(); x += 3) {
                color = imagen.getPixeles()[x][y];

                for (int j = y; j <= y + 3 && j < imagen.getAlto(); j++) {
                    for (int i = x; i <= x + 3 && i < imagen.getAncho(); i++) {
                        nuevosPixeles[i][j] = color;
                    }

                }
            }
        }
        imagen.setPixeles(nuevosPixeles);
        imagen.transformada();

    }
}
