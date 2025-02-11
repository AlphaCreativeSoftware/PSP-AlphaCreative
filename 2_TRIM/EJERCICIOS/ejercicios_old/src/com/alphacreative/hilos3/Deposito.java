package com.alphacreative.hilos3;

public class Deposito {

    private int tanque = 0;
    private final int maxTank;
    private boolean siendoLlenado = false;
    private boolean siendoVaciado = false;
    public Deposito(int maxTank) {
        this.maxTank = maxTank;
    }
    public synchronized int getFuel()
    {
        return this.tanque;
    }

    public synchronized void addFuel(int amount, boolean message) {
        while (siendoLlenado || tanque + amount > maxTank) {
            try {
                System.out.println("(Deposito) Deposito lleno, esperando...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        siendoLlenado = true;
        tanque += amount;
        if(message)
        {
            System.out.println("----------------------------------------------");
            System.out.println("Depósito llenado con " + amount + " litros");
            System.out.println("Cantidad actual: " + this.tanque + " litros");
            System.out.println("----------------------------------------------");
        }
        siendoLlenado = false;
        notifyAll();
    }
    public synchronized void removeFuel(int amount,int camionID, boolean message) {
        while (siendoLlenado || tanque < amount) {
            try {
                System.out.println("(Deposito) Cantidad insuficiente para vaciar el deposito, esperando...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        siendoVaciado = true;
        tanque -= amount;
        if(message)
        {
            System.out.println("----------------------------------------------");
            System.out.println("Depósito vaciado con " + amount + " litros por Camion(" + camionID + ")");
            System.out.println("Cantidad actual: " + this.tanque + " litros");
            System.out.println("----------------------------------------------");
        }
        siendoVaciado = false;
        notifyAll();
    }

    public synchronized boolean siendoLlenado() {
        return siendoLlenado;
    }
    public synchronized boolean siendoVaciado() {
        return siendoVaciado;
    }

}
