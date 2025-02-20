package com.alphacreative.hilos4_new;
public class Supermercado {
    private static boolean[] cajas = {true, true, true}; // true indica libre

    public synchronized int entrarCaja(int clienteId) {
        int cajaAsignada = -1;
        while ((cajaAsignada = buscarCajaLibre()) == -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        cajas[cajaAsignada] = false;
        System.out.println("Cliente " + clienteId + " entrando a la caja " + (cajaAsignada + 1));
        notifyAll();
        return cajaAsignada;
    }

    public synchronized void salirCaja(int clienteId, int caja) {
        System.out.println("Cliente " + clienteId + " ha terminado y sale de la caja " + (caja + 1));
        cajas[caja] = true;
        notifyAll();
    }

    private int buscarCajaLibre() {
        for (int i = 0; i < cajas.length; i++) {
            if (cajas[i]) return i;
        }
        return -1;
    }

}