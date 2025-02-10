package com.alphacreative.hilos2.productorConsumidor;

import java.util.concurrent.BlockingQueue;

public class Ejecucion {
    public static void main(String[] args) {
        Cola cola = new Cola();
        HiloProductor productor = new HiloProductor(cola);
        HiloConsumidor consumidor = new HiloConsumidor(cola);
        productor.start();
        consumidor.start();
    }
}
