package com.alphacreative.hilo6_super;
import java.util.Random;

public class Cliente extends Thread {
    private final Supermercado supermercado;
    private final int clienteId;
    private final int tiempoEsperado;

    public Cliente(Supermercado supermercado, int clienteId, int tiempoEsperado) {
        this.supermercado = supermercado;
        this.clienteId = clienteId;
        this.tiempoEsperado = tiempoEsperado;
    }

    @Override
    public void run() {
        try {
            supermercado.atenderCliente(clienteId, tiempoEsperado); // El cliente es atendido
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
