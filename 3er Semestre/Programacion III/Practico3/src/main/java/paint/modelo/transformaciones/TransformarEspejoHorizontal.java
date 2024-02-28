package paint.modelo.transformaciones;

import paint.modelo.Imagen;

public class TransformarEspejoHorizontal extends Transformacion{
    private int [][] espejo;

    public TransformarEspejoHorizontal(Imagen img){
        this.imagen = img;
        espejo = new int[img.getAncho()][img.getAlto()];
    }

    @Override
    public void transformar() {
        int contador = -1;
        for (int i = imagen.getAncho(); i > 0; i--) {
            contador += 1;
            for (int j = 0; j < imagen.getAlto(); j++) {
                espejo [contador][j] = imagen.getPixeles()[i-1][j];
            }

        }
        imagen.setPixeles(espejo);
        imagen.transformada();
    }
}
