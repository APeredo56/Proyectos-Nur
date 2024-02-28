package paint.modelo.transformaciones;

import paint.modelo.Imagen;

public class TransformarEspejoHorizontal extends Transformacion{
    private int [][] pixelesCambiados;

    public TransformarEspejoHorizontal(Imagen img){
        this.imagen = img;
        pixelesCambiados = new int[img.getAncho()][img.getAlto()];
        pixelesOriginales = new int[img.getAncho()][img.getAlto()];
        pixelesOriginales = img.getPixeles();
    }

    @Override
    public void transformar() {
        int contador = -1;
        for (int i = imagen.getAncho(); i > 0; i--) {
            contador += 1;
            for (int j = 0; j < imagen.getAlto(); j++) {
                pixelesCambiados [contador][j] = imagen.getPixeles()[i-1][j];
            }

        }
        imagen.setPixeles(pixelesCambiados);
        imagen.transformada();
    }
}
