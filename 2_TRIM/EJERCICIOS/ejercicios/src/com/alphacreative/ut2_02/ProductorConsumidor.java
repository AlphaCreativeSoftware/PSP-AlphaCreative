package com.alphacreative.ut2_02;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Productor extends Thread {
    private ArrayBlockingQueue<Integer> cola;
    private Random random = new Random();

    public Productor(ArrayBlockingQueue<Integer> cola) {
        this.cola = cola;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int numero = random.nextInt(100);
                cola.put(numero);
                System.out.println("Productor produjo: " + numero);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumidor extends Thread {
    private BlockingQueue<Integer> cola;
    private int suma = 0;

    public Consumidor(BlockingQueue<Integer> cola) {
        this.cola = cola;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int numero = cola.take(); // Bloquea si la cola está vacía
                suma += numero;
                System.out.println("Consumidor consumió: " + numero + " | Suma acumulada: " + suma);
                Thread.sleep(700); // Simula tiempo de consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProductorConsumidor {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> cola = new ArrayBlockingQueue<>(1);
        Productor productor = new Productor(cola);
        Consumidor consumidor = new Consumidor(cola);

        productor.start();
        consumidor.start();
    }
}

