package paint.modelo.transformaciones;

import paint.modelo.Imagen;

public class TransformarEspejoVertical extends Transformacion{
    private int [][] espejo;

    public TransformarEspejoVertical(Imagen img){
        this.imagen = img;
        espejo = new int[img.getAncho()][img.getAlto()];
    }
    @Override
    public void transformar() {
        for (int i = 0; i < imagen.getAncho(); i++) {
            int contador = -1;
            for (int j = imagen.getAlto(); j > 0; j--) {
                contador += 1;
                espejo [i][contador] = imagen.getPixeles()[i][j-1];
            }

        }
        imagen.setPixeles(espejo);
        imagen.transformada();
    }

}
