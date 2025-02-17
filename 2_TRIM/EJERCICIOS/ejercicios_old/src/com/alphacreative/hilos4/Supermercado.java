package com.alphacreative.hilos4;

public class Supermercado {
    public static void main(String[] args) {
        final int NUM_CAJAS = 3;
        final int NUM_CLIENTES = 15;

        Caja[] cajas = new Caja[NUM_CAJAS];
        for (int i = 0; i < NUM_CAJAS; i++) {
            cajas[i] = new Caja(i+1);
        }

        for (int i = 0; i < NUM_CLIENTES; i++) {
            new Thread(new Cliente(i, cajas)).start();
        }
        System.out.println("Supermercado cerrado.");
    }
}