package com.alphacreative.hilos3.test2;

import java.util.Random;

public class Productor extends Thread {
    private Deposito deposito;
    
    public Productor(Deposito deposito) {
        this.deposito = deposito;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            while (deposito.getQueue().isEmpty()) {
                try {
                    wait();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            int fuel = randomNumber(0, 1000);
            deposito.addFuel(fuel);
            try {
                sleep(randomNumber(250, 500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
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