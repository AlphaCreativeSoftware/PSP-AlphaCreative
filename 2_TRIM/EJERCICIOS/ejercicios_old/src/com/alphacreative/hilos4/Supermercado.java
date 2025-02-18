package com.alphacreative.hilos4;

public class Supermercado {
    public static void main(String[] args) {
        final int NUM_CAJAS = 3;
        final int NUM_CLIENTES = 15;

        Caja[] cajas = new Caja[NUM_CAJAS];
        for (int i = 0; i < NUM_CAJAS; i++) {
            cajas[i] = new Caja(i+1);
        }
        Cliente cliente = new Cliente(1, cajas);
        cliente.start();

        Cliente cliente2 = new Cliente(2, cajas);
        cliente2.start();

        Cliente cliente3 = new Cliente(3, cajas);
        cliente3.start();

        Cliente cliente4 = new Cliente(4, cajas);
        cliente4.start();

        Cliente cliente5 = new Cliente(5, cajas);
        cliente5.start();

        Cliente cliente6 = new Cliente(6, cajas);
        cliente6.start();

        try {
            cliente.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
            cliente5.join();
            cliente6.join();
        } catch (Exception e) {
        }


        System.out.println("Supermercado cerrado.");
    }
}