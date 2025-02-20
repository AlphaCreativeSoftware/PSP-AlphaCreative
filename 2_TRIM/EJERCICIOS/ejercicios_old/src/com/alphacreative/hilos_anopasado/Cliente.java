package com.alphacreative.hilos_anopasado;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase que implementa el vehículo/cliente
class Cliente extends Thread {
    
    private final Gasolinera gas;
    private String nombre;

    public Cliente(Gasolinera gas, String nombre) {
        this.gas = gas;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        gas.entrarSurtidor(nombre);
        try {
            sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
        gas.salirSurtidor(nombre);  
    }
}
