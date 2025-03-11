package Vista;

import Modelo.Datos;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.geom.Rectangle2D;


/**
 *
 * @author Constantino Byelov Serdiuk & Valentino Coppola
 */

public class Panel extends JPanel {
    
    public final int ANCHO = 800;
    final int PISOS = 4;
    
    private final Datos datos;

    public Panel(Datos datos) {
        this.setPreferredSize(new Dimension(10, 10));
        this.setBounds(500, 140, 110, PISOS * 80 + 10);
        this.datos = datos;
        int aux = 150;
        datos.botonesPanel.setSolicitud(3,new Boton(new Rectangle2D.Float(525, aux, 70, 70), 3,"src/Imagenes/numeros/3_norm.png","src/Imagenes/numeros/3_inv.png"));
        aux += 80;
        datos.botonesPanel.setSolicitud(2,new Boton(new Rectangle2D.Float(525, aux, 70, 70), 2,"src/Imagenes/numeros/2_norm.png","src/Imagenes/numeros/2_inv.png")); 
        aux += 80;
        datos.botonesPanel.setSolicitud(1,new Boton(new Rectangle2D.Float(525, aux, 70, 70), 1,"src/Imagenes/numeros/1_norm.png","src/Imagenes/numeros/1_inv.png")); 
        aux += 80;
        datos.botonesPanel.setSolicitud(0,new Boton(new Rectangle2D.Float(525, aux, 70, 70), 0,"src/Imagenes/numeros/0_norm.png","src/Imagenes/numeros/0_inv.png")); 
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
       Graphics g2d = (Graphics2D) g;
        g2d.drawImage(redimensionarImagen(new ImageIcon("src/Imagenes/FondoInterfaz.png"), this.getWidth(), this.getHeight()), 280, 0, null);
        //marble like color
        g.setColor(new java.awt.Color(0, 0, 0, 100));
        
        g.fillRect(505, 140, 110, 80 * PISOS + 10);
        for (int i = 0; i < datos.botonesPanel.longitud(); i++) {
            datos.botonesPanel.getSolicitud(i).paint(g);
        }
    }

    private Image redimensionarImagen(ImageIcon imagen, int ancho, int alto) {
        Image imgEscalada = imagen.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(imgEscalada).getImage();
    }
}