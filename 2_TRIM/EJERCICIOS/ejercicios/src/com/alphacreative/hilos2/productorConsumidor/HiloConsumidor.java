package com.alphacreative.hilos2.productorConsumidor;

import java.util.Random;

public class HiloConsumidor extends Thread {
    Cola cola;
    public HiloConsumidor(Cola cola) {
        this.cola = cola;
    }
    @Override
    public void run() {
        try {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int obtenido = cola.consumir();
                System.out.println("Consumidor consume: " + obtenido);
                int randSleep = random.nextInt(1800);
                sleep(randSleep);
            }
        } catch (Exception e) {
        }
  
    }
}
