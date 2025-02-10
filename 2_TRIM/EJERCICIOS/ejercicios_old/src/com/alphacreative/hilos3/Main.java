package com.alphacreative.hilos3;

public class Main {

    public static void main(String[] args)
    {
        Productor productor = new Productor();
        Thread productorThread = new Thread(productor);
        productorThread.start();
    }

}
