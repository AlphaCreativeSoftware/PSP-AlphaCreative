package com.alphacreative.hilo6_super;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int NUM_CLIENTES = 10; // Número de clientes que llegarán al supermercado

        Supermercado supermercado = new Supermercado(1);

        // Crear y lanzar hilos para cada cliente
        for (int i = 0; i < NUM_CLIENTES; i++) {
            final int clienteId = i + 1;
            int tiempoEsperado = (new Random()).nextInt(3000) + 1000;
            new Cliente(supermercado, clienteId, tiempoEsperado).start();
        }
    }
    
}
