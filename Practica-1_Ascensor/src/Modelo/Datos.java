
package Modelo;

/**
 *
 * @author Constantino Byelov Serdiuk & Valentino Coppola
 */
public class Datos {
    public final int PISOS= 4;
    
    public Modelo botonesSubida = new Modelo(PISOS - 1);
    public Modelo botonesBajada = new Modelo(PISOS - 1);
    public Modelo botonesPanel = new Modelo(PISOS);
}
