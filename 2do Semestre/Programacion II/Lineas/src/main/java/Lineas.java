import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Lineas extends JFrame implements Runnable{
    private JButton btOrdenar = new JButton("Ordenar");

    private ArrayList<Integer> longitudes = new ArrayList<>();
    private ArrayList<JLabel> lineas = new ArrayList<>();
    private ArrayList<JLabel> lineasOrdenadas = new ArrayList<>();
    private Logger logger = new

    public Lineas() {
        setTitle("Lineas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1220, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        btOrdenar.setBounds(0,0,120,30);
        add(btOrdenar);
        generarLongitud();
        pintarLineas(0);
        Thread hilo = new Thread(this);
        btOrdenar.addActionListener(e -> {
            hilo.start();
        });


    }

    public void generarLongitud() {
        Random numAleatorio = new Random();
        int numGenerado;

        while (longitudes.size() < 100) {
            boolean repetido = true;
            numGenerado = numAleatorio.nextInt(100) + 1;
            if (longitudes.size() != 0) {
                while (repetido) {
                    repetido = false;
                    for (int aux : longitudes) {
                        if (aux == numGenerado) {
                            repetido = true;
                            numGenerado = numAleatorio.nextInt(100) + 1;
                        }
                    }
                }
            }
            longitudes.add(numGenerado);
        }
    }
    public void pintarLineas(int x){
        for (int aux:longitudes) {
            JLabel lbLinea = new JLabel();
            lbLinea.setBackground(Color.red);
            lbLinea.setOpaque(true);
            lbLinea.setBounds(x, this.getHeight() - aux *5 ,10,aux*5);
            this.add(lbLinea);
            lineas.add(lbLinea);
            x += 12;

        }
        repaint();
    }
    public void pintarLineasOrdenadas(){
        int x = 0;
        int y;
        int alto;
        for (JLabel aux:lineasOrdenadas) {
            y = this.getHeight() - aux.getHeight();
            alto = aux.getHeight();
            aux.setBounds(x, y,10,alto);
            x += 12;
        }
        repaint();
    }

    public static void main(String[] args) {
        new Lineas();
    }

    @Override
    public void run() {
        while (true) {
            int menor = longitudes.size()*5 + 1;
            int posicion = 0;
            int contador = 0;
            try {
                Thread.sleep(100);
                for (JLabel aux:lineas) {
                    if (aux.getHeight() < menor){
                        menor = aux.getHeight();
                        posicion = contador;
                    }
                    contador ++;
                }
                lineasOrdenadas.add(lineas.get(posicion));
                lineas.remove(posicion);
                pintarLineasOrdenadas();
                int x = lineasOrdenadas.size() * 12;
                for (JLabel aux: lineas) {
                    aux.setBounds(x,this.getHeight() - aux.getHeight(), 10, aux.getHeight());

                    x += 12;

                }
                repaint();
                if (lineas.size() == 0){
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
