import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Tablero extends JPanel implements MouseListener {
    private int filas;
    private int columnas;
    private int numeroBombas;
    private Casilla[][] matrizCasillas;
    private int alto;
    private int ancho;
    private JLabel lbMinas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        ancho = filas * Casilla.tamano;
        alto = columnas * Casilla.tamano;
        setPreferredSize(new Dimension(ancho, alto));
        setLayout(null);
        matrizCasillas = new Casilla[filas][columnas];
        numeroBombas = (int) ((filas * columnas) / 10);
        lbMinas =  new JLabel("Minas: " + numeroBombas);
        cargarCasillas();
        generarBombas();
        generarNumeros();
    }

    private void cargarCasillas() {
        //rellena la matriz de casillas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla casilla = new Casilla(i * Casilla.tamano, j * Casilla.tamano);
                matrizCasillas[i][j] = casilla;
                this.add(casilla);
                casilla.addMouseListener(this);
            }
        }
    }

    private void generarBombas() {
        //genera la cantidad de bombas determinada en posiciones al azar
        int conteoBombas = 0;
        Random r = new Random();
        while (conteoBombas <= numeroBombas) {
            conteoBombas++;
            int x = r.nextInt(filas);
            int y = r.nextInt(columnas);
            matrizCasillas[x][y].setTexto("M");
        }
    }

    private void generarNumeros() {
        //recorre cada casilla de la matriz y al hacerlo cuenta la cantidad de bombas que hay alrededor de la casilla
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!matrizCasillas[i][j].getTexto().equals("M")) {
                    matrizCasillas[i][j].setTexto(contarAlrededor(i, j) + "");
                }
                if (matrizCasillas[i][j].getTexto().equals("0"))
                    matrizCasillas[i][j].setTexto(" ");
            }
        }
    }

    private int contarAlrededor(int i, int j) {
        //recorre las casillas que estan alrededor de la casilla dada
        int conteo = 0;
        if (i + 1 != filas && matrizCasillas[i + 1][j].getTexto().equals("M"))
            conteo++;
        if (i + 1 != filas && j + 1 != columnas && matrizCasillas[i + 1][j + 1].getTexto().equals("M"))
            conteo++;
        if (i - 1 != -1 && j + 1 != columnas && matrizCasillas[i - 1][j + 1].getTexto().equals("M"))
            conteo++;
        if (i + 1 != filas && j - 1 != -1 && matrizCasillas[i + 1][j - 1].getTexto().equals("M"))
            conteo++;
        if (i - 1 != -1 && j - 1 != -1 && matrizCasillas[i - 1][j - 1].getTexto().equals("M"))
            conteo++;
        if (i - 1 != -1 && matrizCasillas[i - 1][j].getTexto().equals("M"))
            conteo++;
        if (j + 1 != columnas && matrizCasillas[i][j + 1].getTexto().equals("M"))
            conteo++;
        if (j - 1 != -1 && matrizCasillas[i][j - 1].getTexto().equals("M"))
            conteo++;
        return conteo;
    }

    public void revelarCasillasContinuas(int i, int j) {
        //si la posicion que se le pasa esta fuera de la matriz o la casilla ya fue revelada, se salta el metodo
        if (i == -1 || i == filas || j == -1 || j == columnas || matrizCasillas[i][j].isRevelada())
            return;
        //si la casilla tiene un numero revela la casilla y se salta el metodo
        if (!matrizCasillas[i][j].getTexto().equals(" ")) {
            matrizCasillas[i][j].revelarTexto();
            return;
        }
        //si la casilla esta vacia revela las otras casillas que estan a su alrededor
        matrizCasillas[i][j].revelarTexto();
        for (int p = -1; p <= 1; p++) {
            for (int q = -1; q <= 1; q++) {
                revelarCasillasContinuas(i + p, j + q);
            }
        }
    }

    private void gameOver(boolean victoria) {
        //elimina el mouseListener de todas las casillas
        for (int i = 0; i < matrizCasillas.length; i++) {
            for (int j = 0; j < matrizCasillas[0].length; j++) {
                matrizCasillas[i][j].revelarTexto();
                matrizCasillas[i][j].removeMouseListener(this);
            }
        }
        if(!victoria){
            JOptionPane.showMessageDialog(null, "Perdiste");
        } else {
            JOptionPane.showMessageDialog(null, "Ganaste");
        }
    }
    private boolean quedanCasillas() {
        for (int i = 0; i < matrizCasillas.length; i++) {
            for (int j = 0; j < matrizCasillas[0].length; j++) {
                //si hay una mina comprueba la siguiente posicion
                if (matrizCasillas[i][j].getTexto().equals("M")){
                    continue;
                }
                //si la casilla no esta revelada regresa valor true
                if(!matrizCasillas[i][j].isRevelada()){
                    return true;
                }
            }
        }
        return  false;
    }

    public void reiniciarTablero(){
        //elimina las casillas actuales y crea una nueva matriz para rellenar con nuevas casillas
        this.removeAll();
        matrizCasillas = new Casilla[filas][columnas];
        numeroBombas = (int) ((filas * columnas) / 10);
        cargarCasillas();
        generarBombas();
        generarNumeros();
        lbMinas.setText("Minas: " + numeroBombas);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Casilla aux = (Casilla) e.getSource();
        //si se hace click izquierdo revela la casilla
        if (e.getButton() == 1) {
            //si la casilla esta bloqueada se salta el metodo
            if (aux.getText().equals("!")){
                return;
            }
            //si la casilla esta vacia encuentra su posicion en la matriz y llama al metodo para revelar las continuas
            if (aux.getTexto().equals(" ")) {
                for (int i = 0; i < matrizCasillas.length; i++) {
                    for (int j = 0; j < matrizCasillas[0].length; j++) {
                        if (aux == matrizCasillas[i][j]) {
                            revelarCasillasContinuas(i, j);
                            return;
                        }
                    }
                }
            } else {
                //si la casilla tiene una mina termina el juego
                aux.revelarTexto();
                if (aux.getTexto().equals("M")) {
                    aux.setBackground(Color.red);
                    gameOver(false);
                    return;
                }
            }
            //si ya no hay mas casillas
            if(!quedanCasillas()){
                gameOver(true);
            }
        }
        //si se hace clic derecho se bloquea la casilla
        if (e.getButton() == 3 && !aux.isRevelada()){
            if (!aux.getText().equals("!") ){
                aux.setText("!");
                numeroBombas --;
            } else {
                //si se hace clic derecho y la casilla ya estaba bloqueada la desbloquea
                aux.setText(" ");
                numeroBombas ++;

            }
            lbMinas.setText("Minas: " + numeroBombas);
        }
    }




    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public JLabel getLbMinas() {
        return lbMinas;
    }
}
