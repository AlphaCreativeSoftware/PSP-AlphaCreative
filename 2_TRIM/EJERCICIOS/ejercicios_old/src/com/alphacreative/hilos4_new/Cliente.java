package com.alphacreative.hilos4_new;
public class Cliente extends Thread {
    private final Supermercado supermercado;
    private final int id;

    public Cliente(Supermercado supermercado, int id) {
        this.supermercado = supermercado;
        this.id = id;
    }

    @Override
    public void run() {
        int caja = supermercado.entrarCaja(id);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        supermercado.salirCaja(id, caja);
    }
}
