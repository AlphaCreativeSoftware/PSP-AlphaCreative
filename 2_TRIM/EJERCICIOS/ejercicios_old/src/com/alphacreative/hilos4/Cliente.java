package com.alphacreative.hilos4;
import java.util.Random;
public class Cliente extends Thread {
    private int id;
    private Caja[] cajas;
    public Cliente(int id, Caja[] cajas) {
        this.id = id;
        this.cajas = cajas;
    }
    @Override
    public void run() {
        Random random = new Random();
        Caja cajaSeleccionada = cajas[random.nextInt(cajas.length)];
        while(cajaSeleccionada.Disponible() == false) {
            try {
                cajaSeleccionada = cajas[random.nextInt(cajas.length)];
            } catch (Exception e) {
            }
        }
        cajaSeleccionada.atenderCliente(this);
        try {
            sleep(1000);
        } catch (Exception e) {
        }
        cajaSeleccionada.liberarCaja();
        
    }

    public int getClientID()
    {
        return this.id;
    }
}