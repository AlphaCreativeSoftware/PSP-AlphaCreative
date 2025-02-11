package com.alphacreative.hilos3.test2;

import java.util.LinkedList;
import java.util.Queue;

public class Deposito {
    private int tanque = 0;
    private final int maxTank;
    private boolean bloqueado = false;
    private Queue<Camion> colaCamiones = new LinkedList<>();

    public Deposito(int maxTank) {
        this.maxTank = maxTank;
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

    public synchronized void transferFuel(Camion camion) {
        colaCamiones.add(camion);
        while (colaCamiones.peek() != camion || tanque <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int fuel = tanque; // Cada camión carga hasta 1000 litros
        tanque -= fuel;
        camion.addFuel(fuel);
        System.out.println("(Camion " + camion.ID() + ") ha cargado " + fuel + " litros. Total en deposito: " + tanque + " litros");
        colaCamiones.poll();
        notifyAll();
    }

    public synchronized Queue<Camion> getQueue() {
        return this.colaCamiones;
    }
}