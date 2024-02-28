import javax.swing.*;

public class Marcas extends JLabel {
    private int turno;
    public Marcas (){}

    public Marcas (int x, int y){
        setBounds(x,y,100,100);

    }
    public void marcarMovimiento () {
        if (turno == 0) {
            setIcon(new ImageIcon("ImagenX.jpg"));
            setName("X");
        } else {
            setIcon(new ImageIcon("ImagenO.jpg"));
            setName("O");
        }
        turno = 3;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

}
