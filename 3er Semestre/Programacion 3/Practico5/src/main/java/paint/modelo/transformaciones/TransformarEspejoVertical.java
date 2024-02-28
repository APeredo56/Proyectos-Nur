package paint.modelo.transformaciones;

import paint.modelo.Imagen;

public class TransformarEspejoVertical extends Transformacion{
    private int [][] pixelesCambiados;

    public TransformarEspejoVertical(Imagen img){
        this.imagen = img;
        pixelesCambiados = new int[img.getAncho()][img.getAlto()];
        pixelesOriginales = new int[img.getAncho()][img.getAlto()];
        pixelesOriginales = img.getPixeles();
    }
    @Override
    public void transformar() {
        for (int i = 0; i < imagen.getAncho(); i++) {
            int contador = -1;
            for (int j = imagen.getAlto(); j > 0; j--) {
                contador += 1;
                pixelesCambiados [i][contador] = imagen.getPixeles()[i][j-1];
            }

        }
        imagen.setPixeles(pixelesCambiados);
        imagen.transformada();
    }

}
