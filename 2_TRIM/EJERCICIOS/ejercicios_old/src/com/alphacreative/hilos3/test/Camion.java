package com.alphacreative.hilos3.test;

import java.util.Random;

public class Camion extends Thread {
    private int id;
    private Deposito deposito;
    private int tanque = 0;

    public Camion(int id, Deposito deposito) {
        this.id = id;
        this.deposito = deposito;
    }

    public int Id() {
        return id;
    }
    public void AddFuel(int amount) {
        this.tanque += amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            while (deposito.IsLocked() && !deposito.Lleno()) {
                try {
                    wait();
                } catch (Exception e) {
                }
            }
            int fuel = deposito.getFuel();
            deposito.transferFuel(fuel,this);
            deposito.SetLock(true);
            this.tanque += fuel;
            //System.out.println("(Camion " + this.id + ") Tanque actual: " + this.tanque + " litros");
            /*try {
                sleep(randomNumber(250, 350));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }*/
            deposito.SetLleno(false);
        }
        System.out.println("(Camion " + this.id + ") " + this.tanque + " litros" + " está LISTO, hasta luego...");
        deposito.SetLock(false);
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