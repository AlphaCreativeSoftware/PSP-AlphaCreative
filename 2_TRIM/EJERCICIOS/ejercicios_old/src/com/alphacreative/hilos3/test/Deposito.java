package com.alphacreative.hilos3.test;

public class Deposito {

    private int tanque = 0;
    private final int maxTank;
    private boolean blocked = false;
    private boolean lleno = false;
    private Camion actualCamion;

    public Deposito(int maxTank) {
        this.maxTank = maxTank;
    }

    public synchronized int getFuel() {
        return this.tanque;
    }

    public synchronized void addFuel(int amount) {
        while (tanque + amount > maxTank) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        tanque += amount;
        System.out.println("(Productor) Deposito llenado con " + amount + " litros. Total: " + tanque + " litros");
        notifyAll();
    }
    public synchronized void removeFuel(int amount, int camionId) {
        while (tanque < amount) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        tanque -= amount;
        System.out.println("(Camion " + camionId + ") ha cargado " + amount + " litros. Total en deposito: " + tanque + " litros");
        notifyAll();
    }
    public synchronized void transferFuel(int amount, Camion camion) {
        while (tanque < amount) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        actualCamion = camion;
        tanque -= amount;
        camion.AddFuel(amount);
        System.out.println("(Camion " + camion.Id() + ") ha cargado " + amount + " litros. Total en deposito: " + tanque + " litros");
        actualCamion = null;
        notifyAll();
    }
    public synchronized void SetLock(boolean lock) { this.blocked = lock; }
    public synchronized boolean IsLocked() { return this.blocked; }
    public synchronized boolean Lleno(){ return this.lleno; }
    public synchronized void SetLleno(boolean lleno){ this.lleno = lleno; }
    public synchronized Camion GetActualTruck(){ return this.actualCamion; }
}