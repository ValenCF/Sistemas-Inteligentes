package Controlador;

import Modelo.Datos;
import Vista.AscensorVista;
import Vista.Vista;

/**
 *
 * @author Constantino Byelov Serdiuk & Valentino Coppola
 */

public class Ascensor {

    static boolean pensar = true;

    public enum ESTADO {
        SUBIR, BAJAR
    };

    public static final int ANCHO = 800;
    public static final int ALTO = 600;
    public static final int PISOS = 4;

    public static void main(String[] args) throws InterruptedException {
        
        Datos datos = new Datos();
        AscensorVista ascensor= new AscensorVista();
        Vista view = new Vista(datos, ascensor);
        while (true) {
            if (!pensar) {
                moverAscensor(ascensor);
                Thread.sleep(ascensor.getVelocitat());
                view.repintar();
            }else{
                 if (abrirPuerta(datos,ascensor)) {
                        simulacioAturada(ascensor, view);
                        datos.botonesPanel.getSolicitud(ascensor.getPisoActual()).setActivado(false);
                        if(ascensor.getPisoActual() != 0){
                            datos.botonesBajada.getSolicitud(ascensor.getPisoActual() - 1).setActivado(false);
                        }
                        if(ascensor.getPisoActual() != PISOS - 1){
                            datos.botonesSubida.getSolicitud(ascensor.getPisoActual()).setActivado(false);
                        }
                    }
                    if (ascensor.getEstat() == ESTADO.SUBIR) {
                        if (datos.botonesSubida.siguiente(ascensor.getPisoActual()) != -1 || datos.botonesPanel.siguiente(ascensor.getPisoActual()) != -1 || 
                                (datos.botonesBajada.max() + 1 > ascensor.getPisoActual() )) {
                            ascensor.setPisoActual(ascensor.getPisoActual() + 1);
                            pensar = false;
                        }else{
                            ascensor.setEstat(ESTADO.BAJAR);
                        }
                    }else{ // estat == estado.BAJAR
                        if (datos.botonesBajada.anterior(ascensor.getPisoActual() - 1) != -1 || datos.botonesPanel.anterior(ascensor.getPisoActual()) != -1 || 
                                (datos.botonesSubida.min() < ascensor.getPisoActual() && datos.botonesSubida.min() != -1)) {
                            ascensor.setPisoActual(ascensor.getPisoActual() - 1);
                            pensar = false;
                        }else{
                            ascensor.setEstat(ESTADO.SUBIR);
                        }
                    }    
                    view.repintar();
                }
        }
    }
    
    public static boolean abrirPuerta(Datos datos,AscensorVista ascensor){
        switch (ascensor.getPisoActual()) {
            case 0 -> {
                if(datos.botonesSubida.getSolicitud(ascensor.getPisoActual()).isActivado() || datos.botonesPanel.getSolicitud(ascensor.getPisoActual()).isActivado()){
                    return true;
                }
            }
            case PISOS - 1 -> {
                if(datos.botonesBajada.getSolicitud(ascensor.getPisoActual() - 1).isActivado() || datos.botonesPanel.getSolicitud(ascensor.getPisoActual()).isActivado()){
                    return true;
                }
            }
            default -> {
                if(ascensor.getEstat() == ESTADO.SUBIR){
                    if(datos.botonesSubida.getSolicitud(ascensor.getPisoActual()).isActivado() || datos.botonesPanel.getSolicitud(ascensor.getPisoActual()).isActivado()){
                        return true;
                    }else if(datos.botonesBajada.max() + 1 == ascensor.getPisoActual() && datos.botonesSubida.max() < ascensor.getPisoActual() && 
                            datos.botonesPanel.max() < ascensor.getPisoActual()){
                        return true;
                    }
                }else{
                    if(datos.botonesBajada.getSolicitud(ascensor.getPisoActual() - 1).isActivado() || datos.botonesPanel.getSolicitud(ascensor.getPisoActual()).isActivado()){
                        return true;
                    }else if(datos.botonesSubida.min() == ascensor.getPisoActual()&& datos.botonesSubida.min() > ascensor.getPisoActual() && 
                            datos.botonesPanel.min() > ascensor.getPisoActual()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void simulacioAturada(AscensorVista asc, Vista v) throws InterruptedException {
        for (int i = 0; i < asc.ANCHO_ASCENSOR / 2 - 1; i++) {
            asc.x2++;
            asc.restador++;
            Thread.sleep(50);
            v.repaint();
        }
        Thread.sleep(1000);
        for (int i = 0; i < asc.ANCHO_ASCENSOR / 2 - 1; i++) {
            asc.x2--;
            asc.restador--;
            Thread.sleep(50);
            v.repaint();
        }
    }
    
    public static void moverAscensor(AscensorVista asc) throws InterruptedException {
        
        
        if (asc.getEstat() == ESTADO.BAJAR) {
            asc.y++;
            asc.getRec().y = asc.y;
            if (asc.y >= ALTO - (ALTO / PISOS * (asc.getPisoActual() + 1))) {
                pensar = true;
            }
        }
        if (asc.getEstat() == ESTADO.SUBIR) {
            asc.y--;
            asc.getRec().y = asc.y;
            if (asc.y <= ALTO - (ALTO / PISOS * (asc.getPisoActual() + 1))) {
                pensar = true;
            }
        }
    }
    
}
