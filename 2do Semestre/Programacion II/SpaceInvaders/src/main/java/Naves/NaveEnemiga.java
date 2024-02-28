package Naves;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NaveEnemiga extends JLabel {
    public enum SENTIDO {DERECHA, IZQUIERDA}

    private int tamaño = 50;
    private int vida;
    private int velocidad;
    private int nivel;
    private SENTIDO sentido;
    private int x;
    private int y;

    public NaveEnemiga (int x, int y){
        this.setBounds(x, y, tamaño,tamaño);
        ImageIcon imagen = new ImageIcon("naveEnemiga.png");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        this.setIcon(icono);
    }

    public NaveEnemiga (int nivel, int x, int y, SENTIDO sentido){
        ImageIcon imagen = null;
        switch (nivel){
            case 1:
                this.vida = 1;
                this.velocidad = 1;
                imagen = new ImageIcon("naveEnemiga.png");
                break;
            case 2:
                this.vida = 3;
                this.velocidad = 2;
                imagen = new ImageIcon("naveEnemiga2.jpg");
                break;
            case 3:
                this.vida = 5;
                this.velocidad = 3;
                imagen = new ImageIcon("naveEnemiga3.png");
                break;
        }
        this.x = x;
        this.y = y;
        this.nivel = nivel;
        this.sentido = sentido;
        this.setBounds(this.x, this.y, tamaño,tamaño);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        this.setIcon(icono);
    }
    public void mover (){
        if (sentido == SENTIDO.DERECHA){
            x += velocidad;
        } else {
            x -= velocidad;
        }
        this.setBounds(x,y,tamaño,tamaño);
    }

    public boolean soltarPotenciador (){
        Random numAleatorio = new Random();
        int num = numAleatorio.nextInt(100);
        if (num <= 3){
            return true;
        } else{
            return false;
        }
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getTamaño() {
        return tamaño;
    }

    public SENTIDO getSentido() {
        return sentido;
    }

    public void setSentido(SENTIDO sentido) {
        this.sentido = sentido;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
