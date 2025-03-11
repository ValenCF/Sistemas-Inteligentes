package Vista;

import Modelo.Datos;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Constantino Byelov Serdiuk & Valentino Coppola
 */

public class Interfaz extends JPanel{
    
    final int ANCHO = 800;
    final int ALTO = 600;
    final int PISOS = 4;
    
    private int tamañoPorPiso;
    private Datos datos;
    private AscensorVista ascensor;

        public Interfaz (Datos datos, AscensorVista asc){
        ascensor = asc;
        ascensor.x1 = 75;
        ascensor.x2 = 75 + 125/2;
        this.tamañoPorPiso = ALTO / PISOS;
        ascensor.y = ALTO - tamañoPorPiso;
        this.datos = datos;
        this.setSize(ANCHO, ALTO);
        datos.botonesBajada.setSolicitud(2,new Boton(new Rectangle2D.Float(25,tamañoPorPiso - 70, 35, 35), 3,"src/Imagenes/Bajar.png","src/Imagenes/Bajar_activo.png"));
        datos.botonesBajada.setSolicitud(1,new Boton(new Rectangle2D.Float(25,tamañoPorPiso * 2 - 60, 35, 35), 2,"src/Imagenes/Bajar.png","src/Imagenes/Bajar_activo.png"));
        datos.botonesBajada.setSolicitud(0,new Boton(new Rectangle2D.Float(25,tamañoPorPiso * 3 - 60, 35, 35), 1,"src/Imagenes/Bajar.png","src/Imagenes/Bajar_activo.png"));
        
        datos.botonesSubida.setSolicitud(2,new Boton(new Rectangle2D.Float(225,tamañoPorPiso * 2 - 60, 35, 35), 2,"src/Imagenes/Subir.png","src/Imagenes/Subir_activo.png"));
        datos.botonesSubida.setSolicitud(1,new Boton(new Rectangle2D.Float(225,tamañoPorPiso * 3 - 60, 35, 35),1,"src/Imagenes/Subir.png","src/Imagenes/Subir_activo.png"));
        datos.botonesSubida.setSolicitud(0,new Boton(new Rectangle2D.Float(225, ALTO - 60, 35, 35),0,"src/Imagenes/Subir.png","src/Imagenes/Subir_activo.png"));
    }    
    @Override
    public void paint(Graphics g) {
        Graphics g2d = (Graphics2D) g;
        g2d.drawImage(redimensionarImagen(new ImageIcon("src/Imagenes/fondo.png"),125,ALTO), 75, 0, null);
        g.setColor(Color.white);
        g.setFont(new Font("Monospaced", Font.BOLD, 36));
        int num = 0;
        for (int i = tamañoPorPiso/2; num < PISOS; i+= tamañoPorPiso) {
            g.drawString(String.valueOf(num), 70 + 125/2,ALTO - i);
            num++;
        }
        ascensor.paint(g);
        g2d.drawImage(redimensionarImagen(new ImageIcon("puerta.jpg"),ascensor.ANCHO_ASCENSOR/2 - ascensor.restador,tamañoPorPiso), ascensor.x1, ascensor.y, null);
        g2d.drawImage(redimensionarImagen(new ImageIcon("puerta.jpg"),ascensor.ANCHO_ASCENSOR/2 + 1, tamañoPorPiso), ascensor.x2, ascensor.y, null);
        g.setColor(Color.black);
        g.drawRect(ascensor.x1, ascensor.y, ascensor.ANCHO_ASCENSOR/2 - ascensor.restador, tamañoPorPiso);
        g.drawRect(ascensor.x2, ascensor.y, ascensor.ANCHO_ASCENSOR/2 + 1, tamañoPorPiso);
        g2d.drawImage(redimensionarImagen(new ImageIcon("src/Imagenes/FondoBotones.png"), 80, ALTO), 0, 0, null);
        g2d.drawImage(redimensionarImagen(new ImageIcon("src/Imagenes/FondoBotones.png"), 80, ALTO), 200, 0, null);
      
        for (int i = 0; i < PISOS - 1; i++) {
            datos.botonesBajada.getSolicitud(i).paint(g);
            datos.botonesSubida.getSolicitud(i).paint(g);
        }
    }
    
    private Image redimensionarImagen(ImageIcon imagen,int ancho,int alto) {
        Image imgEscalada = imagen.getImage().getScaledInstance(ancho,
            alto, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(imgEscalada).getImage(); 
    }
}