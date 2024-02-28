import javax.swing.*;
import java.text.DecimalFormat;

public class Cronometro extends Thread {
    private double decimal = 0;
    private JLabel etiqueta;
    private boolean parar = false;
    private Semaforo semaforo;
    private int decimalActual;
    private JPanel panelPrincipal;

    public Cronometro(JLabel etiqueta) {
        this.etiqueta = etiqueta;
        start();
    }

    public Cronometro(JLabel etiqueta, Semaforo semaforo, JPanel panelPrincipal) {
        this.etiqueta = etiqueta;
        this.semaforo = semaforo;
        this.panelPrincipal = panelPrincipal;
        start();
    }

    public void detener() {
        this.parar = true;
    }

    @Override
    public void run() {
        while (!parar) {
            String entero = etiqueta.getText().substring(0, etiqueta.getText().indexOf(","));
            decimalActual = Integer.parseInt(entero);
            try {
                Thread.sleep(10);
                decimal += 0.01;
                DecimalFormat df = new DecimalFormat("0.000");
                etiqueta.setText(df.format(decimal));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            entero = etiqueta.getText().substring(0, etiqueta.getText().indexOf(","));
            if (semaforo != null) {
                if (decimalActual != Integer.parseInt(entero)) {
                    semaforo.aumentarTemporizador();
                    if(semaforo.getTemporizador() == 3){
                        panelPrincipal.getComponent(4).setVisible(true);
                        panelPrincipal.getComponent(3).setVisible(false);
                    } else {
                        panelPrincipal.getComponent(4).setVisible(false);
                        panelPrincipal.getComponent(3).setVisible(true);

                    }
                    semaforo.repaint();
                }
                }
            }
        }


        public JLabel getEtiqueta () {
            return etiqueta;
        }

        public void setParar ( boolean parar){
            this.parar = parar;
        }

        public boolean isParar () {
            return parar;
        }

        public double getDecimal () {
            return decimal;
        }
    }


