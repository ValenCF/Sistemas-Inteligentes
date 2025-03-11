package Modelo;

import Vista.Boton;

/**
 *
 * @author Constantino Byelov Serdiuk & Valentino Coppola
 */

public class Modelo {
    
    public final int PISOS= 4;
    
    private Boton [] solicitudes;

    public Modelo (int tamaño){
        solicitudes = new Boton [tamaño];
    }

    /**
     * Ahora declaramos las reglas del que el agente ascensor va ha seguir 
     * @param pisoActual
     * @return 
     */

     public int siguiente(int pisoActual){
        for (int i = pisoActual + 1; i < solicitudes.length; i++) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1; 
    }

    public int anterior(int pisoActual){
        for (int i = pisoActual - 1; i >= 0; i--) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1;
    }
    
    public int max (){
        for (int i = solicitudes.length - 1; i >= 0; i--) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1;
    }
    
    public int min (){
        for (int i = 0; i < solicitudes.length; i++) {
            if(solicitudes[i].isActivado()){
                return i;
            }
        }
        return -1;
    }

    public Boton getSolicitud (int pos){
        return solicitudes[pos];
    }
    
    public void setSolicitud (int pos,Boton boton){
        solicitudes[pos] = boton;
    }
    
    public int longitud (){
        return solicitudes.length;
    }

    
}
