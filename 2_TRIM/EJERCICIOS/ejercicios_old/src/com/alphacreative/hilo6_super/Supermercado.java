package com.alphacreative.hilo6_super;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Supermercado {
    private final BlockingQueue<Integer> cajas;
    public Supermercado(int numCajas) {
        this.cajas = new ArrayBlockingQueue<>(numCajas);
    }

    public synchronized void atenderCliente(int clienteId, int tiempoEsperado) throws InterruptedException {
        System.out.println("Cliente " + clienteId + " está buscando una caja.");

        cajas.put(clienteId);

        System.out.println("Cliente " + clienteId + " está siendo atendido.");


        Thread.sleep(tiempoEsperado); 

        cajas.take();
        System.out.println("Cliente " + clienteId + " ha terminado y liberó la caja.");
    }
}