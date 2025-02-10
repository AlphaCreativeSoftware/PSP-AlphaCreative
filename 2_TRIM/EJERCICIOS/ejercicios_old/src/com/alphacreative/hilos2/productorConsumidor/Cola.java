package com.alphacreative.hilos2.productorConsumidor;

import java.util.concurrent.ArrayBlockingQueue;

public class Cola {
    public ArrayBlockingQueue<Integer> cola = new ArrayBlockingQueue<Integer>(10);
    public int producir(int number) {
        try {
            cola.put(number);
            return number;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int consumir() {
        try {
            return cola.take();
            } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
