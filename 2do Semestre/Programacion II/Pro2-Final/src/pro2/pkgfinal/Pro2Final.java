/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro2.pkgfinal;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel;

/**
 *
 * @author BlueFox
 */
public class Pro2Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
           // Logger.getLogger(Pro2Final.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana();
            }
        });
    }
}
