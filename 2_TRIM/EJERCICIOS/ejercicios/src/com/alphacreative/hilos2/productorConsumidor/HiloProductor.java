package com.alphacreative.hilos2.productorConsumidor;

import java.util.Random;

public class HiloProductor extends Thread {
    Cola cola;
    public HiloProductor(Cola cola) {
        this.cola = cola;
    }
    @Override
    public void run() {
        try {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int numero = random.nextInt(100);
                cola.producir(numero);
                System.out.println("Productor produjo: " + numero);
                sleep(160);
            }
        } catch (Exception e) {
        }
  
    }
}
