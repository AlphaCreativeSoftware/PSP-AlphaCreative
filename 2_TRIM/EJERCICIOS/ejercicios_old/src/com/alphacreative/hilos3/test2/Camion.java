package com.alphacreative.hilos3.test2;

import java.util.Random;

public class Camion extends Thread {
    private int id;
    private Deposito deposito;
    private int tanque = 0;
    
    public Camion(int id, Deposito deposito) {
        this.id = id;
        this.deposito = deposito;
    }

    public int ID() {
        return id;
    }

    public void addFuel(int amount) {
        this.tanque += amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            deposito.transferFuel(this);
            try {
                sleep(randomNumber(250, 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("(Camion " + this.id + ") FINALIZADO con " + this.tanque + " litros.");
    }

    private static int randomNumber(int min, int max) {
        if (min > max) {
            int tmin = min, tmax = max;
            max = tmin;
            min = tmax;
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}