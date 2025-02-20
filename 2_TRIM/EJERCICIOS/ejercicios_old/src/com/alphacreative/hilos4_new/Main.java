package com.alphacreative.hilos4_new;
public class Main {
    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado();
        Cliente[] clientes = new Cliente[15];

        for (int i = 0; i < 15; i++) {
            clientes[i] = new Cliente(supermercado, i + 1);
            clientes[i].start();
        }


        for (int i = 0; i < 15; i++) {
            try {
                clientes[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("El supermercado ha cerrado.");
    }
}
