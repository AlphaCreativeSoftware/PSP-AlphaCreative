package com.alphacreative.hilos3;

import java.util.Random;

public class Productor extends Thread{
    private Deposito deposito;

    public Productor(Deposito deposito) {
        this.deposito = deposito;
    }

    @Override
    public void run() {
        for(int i = 0; i < 15; i++) {
            
            int randomNumber = randomNumber(0,1000);
            deposito.addFuel(randomNumber, false);
            System.out.println("(Productor) Deposito llenado con " + randomNumber + " litros");
            try {
                sleep(randomNumber(250,1000));
            } catch (Exception e) {
            }
        }
    }

    //FUNCIONES AUXILIARES
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
