package Mapa;

import Naves.Balas;
import Naves.NaveEnemiga;
import Naves.NaveJugador;
import Naves.Potenciadores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Mapa extends JPanel implements Runnable, KeyListener {
    private int puntuacion = 0;
    private int nivel = 1;
    private int x;
    private int y;
    private int limiteDerecho;
    private int espacioRecorrido = 0;
    private int velocidadDisparo = 25;
    private ArrayList<NaveEnemiga> olaEnemigos = new ArrayList<>();
    private ArrayList<Balas> balasJugador = new ArrayList<>();
    private ArrayList<Balas> balasEnemigos = new ArrayList<>();
    private ArrayList<Potenciadores> listaPotenciadores = new ArrayList<>();
    private NaveJugador nave;
    private JLabel lbVidas = new JLabel();
    private JLabel lbPuntuacion = new JLabel("Puntuacion: 0");
    private JButton btSalir = new JButton("Volver al menu principal");
    private NaveEnemiga.SENTIDO sentido = NaveEnemiga.SENTIDO.DERECHA;

    public Mapa() {
    }

    public void cargarElementos() {
        setLayout(null);
        setBackground(Color.black);
        this.nave = new NaveJugador(633, 655);
        lbVidas.setBounds(1250, 20,100,30);
        lbVidas.setForeground(Color.WHITE);
        lbVidas.setText("Vidas: " + nave.getVidas());
        lbVidas.setFont(new Font("Arial", Font.PLAIN, 18));
        lbPuntuacion.setBounds(1050,20,150,30);
        lbPuntuacion.setForeground(Color.WHITE);
        lbPuntuacion.setFont(new Font("Arial", Font.PLAIN, 18));
        btSalir.setBounds(0,0,250,30);
        btSalir.setForeground(Color.WHITE);
        btSalir.setBackground(Color.gray.darker());
        btSalir.setFont(new Font("Arial", Font.PLAIN,18));
        nave.setOpaque(true);
        add(nave);
        add(lbVidas);
        add(lbPuntuacion);
        add(btSalir);
    }

    public void generarNaves() {
        x = -950;
        y = 60;
        int contador = 0;
        for (int i = 0; i < 40; i++) {
            NaveEnemiga naveEnemiga = new NaveEnemiga(nivel, x, y, sentido);
            //naveEnemiga.setOpaque(true);
            this.add(naveEnemiga);
            olaEnemigos.add(naveEnemiga);
            x += 100;
            contador++;
            if ((contador == 10) && (sentido == NaveEnemiga.SENTIDO.DERECHA)) {
                sentido = NaveEnemiga.SENTIDO.IZQUIERDA;
                contador = 0;
                x = this.getWidth();
                y += 100;
            }
            if ((contador == 10) && (sentido == NaveEnemiga.SENTIDO.IZQUIERDA)) {
                sentido = NaveEnemiga.SENTIDO.DERECHA;
                contador = 0;
                x = -950;
                y += 100;
            }
        }
    }

    public void acomodarNaves() {
        int contador = this.getWidth();
        while (contador >= (this.getWidth() - 950) / 2) {
            try {
                Thread.sleep(3);
                for (NaveEnemiga aux : olaEnemigos) {
                    aux.mover();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador -= nivel;
        }
        limiteDerecho = this.getWidth() - olaEnemigos.get(9).getX() - olaEnemigos.get(9).getTamaño();
        this.repaint();
    }

    public void moverNaves() {

        try {
            Thread.sleep(10);
            for (NaveEnemiga aux : olaEnemigos) {
                if (espacioRecorrido > limiteDerecho) {
                    if (aux.getSentido() == NaveEnemiga.SENTIDO.DERECHA) {
                        aux.setSentido(NaveEnemiga.SENTIDO.IZQUIERDA);
                    } else {
                        aux.setSentido(NaveEnemiga.SENTIDO.DERECHA);
                    }
                }
                aux.mover();
            }
            if (espacioRecorrido > limiteDerecho) {
                espacioRecorrido = 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void dispararJugador() {
        for (Balas aux : balasJugador) {
            aux.setY(aux.getY() - 3);
        }
        //retraso el hilo por que si no da un error que no pude identificar
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (balasJugador.size() != 0) {
            if (balasJugador.get(0).getY() < -20) {
                this.remove(balasJugador.get(0));
                balasJugador.remove(0);
            }
        }

    }

    public void generarBalasEnemigos (){
        if(balasEnemigos.size() < nivel * 5 && balasEnemigos.size() < olaEnemigos.size()) {
            for (NaveEnemiga aux : olaEnemigos) {
                Random numAleatorio = new Random();
                int num = numAleatorio.nextInt(100) + 1;
                if (num < 5) {
                    Balas balas = new Balas(aux.getX(), aux.getY()+ aux.getTamaño() + 20, false);
                    add(balas);
                    balasEnemigos.add(balas);
                }
            }
        }
    }

    public void dispararEnemigos (){
        for (Balas aux : balasEnemigos) {
            aux.setY(aux.getY() + 3);
        }
        if (balasEnemigos.size() != 0) {
            if (balasEnemigos.get(0).getY() > 700) {
                this.remove(balasEnemigos.get(0));
                balasEnemigos.remove(0);
            }
        }
    }

    public void moverPotenciadores (){
        for (Potenciadores aux: listaPotenciadores) {
            aux.setY(aux.getY() + 3);
        }
        if (listaPotenciadores.size() != 0){
            if (listaPotenciadores.get(0).getY() > 700){
                this.remove(listaPotenciadores.get(0));
            }
        }
    }

    public void revisarColision (){
        //Si el jugador le disparo a una nave
        Balas indiceBala = null;
        Rectangle naveJugador = new Rectangle(nave.getX(),nave.getY(),nave.getWidth(), nave.getHeight());
        if (balasJugador.size() != 0) {
            for (Balas aux : balasJugador) {
                Rectangle bala = new Rectangle(aux.getX(), aux.getY(), aux.getWidth(), aux.getHeight());
                for (NaveEnemiga aux2 : olaEnemigos) {
                    Rectangle nave = new Rectangle(aux2.getX(), aux2.getY(), aux2.getWidth(), aux2.getHeight());
                    if (bala.intersects(nave)) {
                        indiceBala = aux;
                        this.remove(aux);
                        aux2.setVida(aux2.getVida() - 1);
                        if (aux2.getVida() == 0) {
                            puntuacion += 50 * aux2.getNivel();
                            lbPuntuacion.setText("Puntuacion: " + puntuacion);
                            if (aux2.soltarPotenciador()){
                                Potenciadores potenciador = new Potenciadores(aux2.getX() + aux2.getTamaño()/2,
                                        aux2.getY() + aux2.getTamaño());
                                listaPotenciadores.add(potenciador);
                                this.add(potenciador);
                            }
                            this.remove(aux2);
                            olaEnemigos.remove(aux2);
                            break;
                        }
                    }
                }
            }
        }
        if (indiceBala != null) {
            balasJugador.remove(indiceBala);
        }
        //Si el jugador recibio la bala
        if (balasEnemigos.size() != 0){
            for (Balas aux: balasEnemigos) {
                Rectangle bala = new Rectangle(aux.getX(),aux.getY(),aux.getWidth(),aux.getHeight());
                if (bala.intersects(naveJugador)) {
                    nave.setVidas(nave.getVidas() - 1);
                    lbVidas.setText("Vidas: " + nave.getVidas());
                    balasEnemigos.remove(aux);
                    this.remove(aux);
                    break;
                }
            }
        }
        //Si el jugador se choca con una nave
        if (olaEnemigos.size() != 0) {
            for (NaveEnemiga aux : olaEnemigos) {
                Rectangle naveEnemiga = new Rectangle(aux.getX(), aux.getY(), aux.getWidth(), aux.getHeight());
                if (naveJugador.intersects(naveEnemiga)){
                    nave.setVidas(nave.getVidas() - 1);
                    lbVidas.setText("Vidas: " + nave.getVidas());
                    this.remove(aux);
                    olaEnemigos.remove(aux);
                    break;
                }
            }

        }
        //Si el jugador recoge un potenciador
        if (listaPotenciadores.size() != 0){
            for (Potenciadores aux: listaPotenciadores) {
                Rectangle pot = new Rectangle(aux.getX(),aux.getY(), aux.getTamaño(), aux.getTamaño());
                if (pot.intersects(naveJugador)) {
                    if (velocidadDisparo > 10) {
                        velocidadDisparo -= 3;
                    } else {
                        puntuacion += 500;
                        lbPuntuacion.setText("Puntuacion: " + puntuacion);
                    }
                    this.remove(aux);
                    listaPotenciadores.remove(aux);
                    break;
                }
            }

        }
    }
    public void terminarPartida (){
        nave.setVidas(0);
        nivel = 1;
    }




    @Override
    public void run() {
        puntuacion = 0;
        int contador = 25;
        while (nave.getVidas() > 0){
            if (olaEnemigos.size() == 0){
                while (balasEnemigos.size() != 0 || balasJugador.size() != 0){
                    if (balasEnemigos.size() != 0) {
                        this.remove(balasEnemigos.get(0));
                        balasEnemigos.remove(0);
                    }
                    if (balasJugador.size() != 0){
                        this.remove(balasJugador.get(0));
                        balasJugador.remove(0);
                    }
                }
                repaint();
                espacioRecorrido = 0;
                generarNaves();
                acomodarNaves();
            }
            moverNaves();
            espacioRecorrido += olaEnemigos.get(0).getVelocidad();
            //generar las balas del jugador
                if (contador == velocidadDisparo){
                    Balas bala = new Balas(nave.getX() + 25, nave.getY(), true);
                    add(bala);
                    balasJugador.add(bala);
                    Balas bala2 = new Balas(nave.getX(), nave.getY(), true);
                    add(bala2);
                    balasJugador.add(bala2);
                    Balas bala3 = new Balas(nave.getX() + 50, nave.getY(), true);
                    add(bala3);
                    balasJugador.add(bala3);
                    contador = 0;
                }

            contador ++;
            dispararJugador();
            generarBalasEnemigos();
            dispararEnemigos();
            moverPotenciadores();
            revisarColision();
            if(puntuacion >= 3500){
                nivel = 2;
            }
            if(puntuacion >= 11500){
                nivel = 3;
            }
        }
        JOptionPane.showMessageDialog(null,"Fin del Juego" + "\n " +
                "Puntuacion final: " + puntuacion);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (nave.getY() - 25 > 0) {
                    nave.mover(NaveJugador.SENTIDO.ARRIBA);
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (nave.getY() + nave.getTamaño() + 25 < this.getHeight()) {
                    nave.mover(NaveJugador.SENTIDO.ABAJO);
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (nave.getX() + nave.getTamaño() + 25 < this.getWidth()) {
                    nave.mover(NaveJugador.SENTIDO.DERECHA);
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (nave.getX() - 25 > 0) {
                    nave.mover(NaveJugador.SENTIDO.IZQUIERDA);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public JButton getBtSalir() {
        return btSalir;
    }
}
