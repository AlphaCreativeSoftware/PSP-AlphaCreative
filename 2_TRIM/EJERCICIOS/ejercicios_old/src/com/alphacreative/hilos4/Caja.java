package com.alphacreative.hilos4;

import java.util.LinkedList;
import java.util.Queue;

public class Caja {
    private int id;
    private boolean disponible = true;
    private Queue<Cliente> colaClientes = new LinkedList<>();

    public Caja(int id) {
        this.id = id;
    }
    public synchronized void atenderCliente(Cliente cliente) {
        colaClientes.add(cliente);
        while (colaClientes.peek() != cliente || !disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("(Caja("+ id +") atendió a " + " Cliente("+ cliente.getClientID() + ")");
        colaClientes.poll();
        notifyAll();
    }
    public synchronized Queue<Cliente> getQueue() {
        return this.colaClientes;
    }
    public synchronized void SetDisponible(boolean disponible) { this.disponible = disponible; }
}