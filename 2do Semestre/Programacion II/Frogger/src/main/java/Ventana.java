import javax.swing.*;

public class Ventana extends JFrame {
    private Carretera panel_principal;

    public Ventana(){
        setTitle("Frogger");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400);
        setLocationRelativeTo(null);
        panel_principal=new Carretera();
        add(panel_principal);
        setVisible(true);
        panel_principal.cargarElementos();
        this.addKeyListener(panel_principal);
        Thread hilo=new Thread(panel_principal);
        hilo.start();
    }

    public static void main(String[] args) {
        new Ventana();
    }
}
