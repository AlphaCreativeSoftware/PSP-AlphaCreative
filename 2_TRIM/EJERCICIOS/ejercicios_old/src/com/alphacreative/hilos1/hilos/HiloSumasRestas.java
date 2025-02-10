package com.alphacreative.hilos1.hilos;

public class HiloSumasRestas implements Runnable {
    private int numero = 1000;
    private int numveces;
    private String operacion;

    // Constructor de la clase
    public HiloSumasRestas(int numveces, String operacion) {
        this.numveces = numveces;
        this.operacion = operacion;
    }
    public synchronized int incrementar(int numveces) {
        for (int i = 0; i < numveces; i++) {
            numero++;
        }
        return numero;
    }
    public synchronized int decrementar(int numveces) {
        for (int i = 0; i < numveces; i++) {
            numero--;
        }
        return numero;
    }
    @Override
    public void run() {
        if (operacion.equals("+")) {
            incrementar(numveces);
            System.out.println("Incrementado a: " + numero);
        } else if (operacion.equals("-")) {
            decrementar(numveces);
            System.out.println("Decrementado a: " + numero);
        } else {
            System.out.println("Operación desconocida: " + operacion);
        }
    }

    // Método para obtener el valor actual de numero (opcional, para depuración)
    public int getNumero() {
        return numero;
    }
}
