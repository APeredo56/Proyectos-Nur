package elementos;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pieza extends ElementoTablero {
    private int pasos = 0;
    private Shape cabeza;
    private Shape base;
    private Polygon cuerpo = new Polygon();

    int sizeCabeza;
    int altoBase;
    int anchoBase;
    int yBase;

    public Pieza(int posX, int posY, int size, Color color) {
        super(posX, posY, size, color);
    }

    public Pieza(Color color){
        super(color);
        cuerpo.npoints = 3;
    }


    @Override
    public void dibujar(Graphics g){
        g.setColor(Color.black);
        //Cabeza
        g.drawOval(posX + size/2 - sizeCabeza/2, posY - sizeCabeza*3/4, sizeCabeza, sizeCabeza);
        //Base
        g.drawOval(posX + sizeCabeza/2, yBase, anchoBase, altoBase);
        //Cuerpo
        g.drawPolygon(new int[]{posX + sizeCabeza/2, posX + size/2, posX + sizeCabeza/2 + anchoBase},
                new int[]{yBase + altoBase/2, posY - sizeCabeza/2 , yBase + altoBase/2}, 3);

        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        //Cabeza
        g2d.fill(cabeza);
        //g.fillOval(posX+1 + size/2 - sizeCabeza/2, posY+1 - sizeCabeza*3/4, sizeCabeza-1, sizeCabeza-1);
        //Base
        g2d.fill(base);
        //g.fillOval(posX+1 + sizeCabeza/2, yBase+1, anchoBase-1, altoBase-1);
        //Cuerpo
        //g.fillPolygon(new int[]{posX+2 + sizeCabeza/2, posX+1 + size/2, posX-1 + sizeCabeza/2 + anchoBase},
                //new int[]{yBase-1 + altoBase/2, posY - sizeCabeza/2+1 , yBase-1 + altoBase/2}, 3);
        g.fillPolygon(cuerpo);
    }

    public void acomodar(int x, int y, int size){
        this.posX = x;
        this.posY = y;
        this.size = size;

        sizeCabeza = (int) (size /2.5);
        altoBase = sizeCabeza;
        anchoBase = size - sizeCabeza;
        yBase = (int) (posY + size - sizeCabeza*1.5);

        cabeza = new Ellipse2D.Float(posX+1 + size/2 - sizeCabeza/2, posY+1 - sizeCabeza*3/4,
                sizeCabeza-1, sizeCabeza-1);
        base = new Ellipse2D.Float(posX+1 + sizeCabeza/2, yBase+1, anchoBase-1, altoBase-1);
        cuerpo.xpoints = new int[]{posX+2 + sizeCabeza/2, posX+1 + size/2, posX-1 + sizeCabeza/2 + anchoBase};
        cuerpo.ypoints = new int[]{yBase-1 + altoBase/2, posY - sizeCabeza/2+1 , yBase-1 + altoBase/2};
    }


    public void setPosX(int posX){
        this.posX = posX;
        acomodar(posX, posY, size);
    }

    public void setPosY(int posY){
        this.posY = posY;
        acomodar(posX, posY, size);
    }

    public boolean presionado(Point puntoClick){
        return cabeza.contains(puntoClick) || cuerpo.contains(puntoClick) || base.contains(puntoClick);
    }


}
