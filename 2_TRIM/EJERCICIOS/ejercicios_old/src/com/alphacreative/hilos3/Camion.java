package com.alphacreative.hilos3;

import java.util.Random;

public class Camion extends Thread{
    private int id;
    private Deposito deposito;
    private int tanque = 0;
    public Camion(int id, Deposito deposito) {
        this.deposito = deposito;
        this.id = id;
    }
    @Override
    public void run() {
        for(int i = 0; i < 5; i++)
        {
            int fuel = deposito.getFuel();
            deposito.removeFuel(fuel,this.id,true);
            this.tanque += fuel;
            System.out.println("----------------------------------------------");
            System.out.println("(Camion " + this.id + ")" + " ha cargado " + fuel + " litros");
            System.out.println("(Camion " + this.id + ") Tanque actual: " + this.tanque + " litros");
            System.out.println("(Deposito) " + deposito.getFuel() + " litros | (Deposito) antes: " + fuel + " litros");
            System.out.println("----------------------------------------------");
            try {
                sleep(randomNumber(250,1000));
            } catch (Exception e) {
            }
        }
        System.out.println("(Camion " + this.id + ") " + this.tanque + " litros" + " está LISTO, hasta luego...");
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
