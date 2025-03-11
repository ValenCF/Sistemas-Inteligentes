package Vista;

import Modelo.Datos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import java.awt.Dimension;

/**
 *
 * @author Constantino Byelov Serdiuk & Valentino Coppola
 */

public class Vista extends JFrame implements MouseListener {

    final int ANCHO = 800;
    final int ALTO = 600;
    final int PISOS = 4;
    
    private Datos datos;
    private AscensorVista ascensor;
    private Panel panel;
    private Interfaz interfaz;

    public Vista(Datos datos, AscensorVista ascensor) {
        this.datos = datos;
        this.ascensor = ascensor;
        panel = new Panel(datos);
        
        this.setTitle("Práctica 1 - Ascensor");
        setSize(ANCHO, ALTO + 50);
        this.setPreferredSize(new Dimension(ANCHO, ALTO + 50));
        setResizable(false);
        setLocationRelativeTo(null);
        interfaz = new Interfaz(datos, ascensor);
        interfaz.addMouseListener(this);
        panel.addMouseListener(this);
        add(interfaz);
        add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void repintar() {
        interfaz.repaint();
        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
         if (e.getButton() == MouseEvent.BUTTON1) {
            int x = e.getX();
            int y = e.getY();

            boolean trobat = false;
            boolean bajada = false;
            int i;
            for (i = 0; i < PISOS - 1 && !trobat; i++) {
                trobat = datos.botonesBajada.getSolicitud(i).getRec().contains(x, y);
                if (trobat) {
                    bajada = true;
                    break;
                }
            }
            for (int j = 0; j < PISOS - 1 && !trobat; j++) {
                trobat = datos.botonesSubida.getSolicitud(j).getRec().contains(x, y);
                if (trobat) {
                    bajada = false;
                    i = j;
                    break;
                }
            }
            if (trobat) {
                if (bajada) {
                    datos.botonesBajada.getSolicitud(i).setActivado(true);

                } else {
                    datos.botonesSubida.getSolicitud(i).setActivado(true);

                }
            } else { //para panel
                int j;
                for (j = 0; j < datos.botonesPanel.longitud(); j++) {
                    trobat = datos.botonesPanel.getSolicitud(j).getRec().contains(x, y);
                    if (trobat) {
                        break;
                    }
                }
                if (trobat) {
                    datos.botonesPanel.getSolicitud(j).setActivado(true);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
