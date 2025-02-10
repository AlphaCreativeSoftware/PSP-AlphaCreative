package com.alphacreative.hilos1.hilos;

public class HiloParImpar implements Runnable {

    private int tipo;
    private String name;

    public HiloParImpar(int tipo,String name) {
        this.tipo = tipo;
        this.name = name;
    }

    @Override
    public void run() {

        switch (tipo)
        {
            case 1:
                for (int i = 1; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(name +  "(" + Thread.currentThread().getId() + "): " + i);
                    }
                }
            break;

            case 2:
                for (int i = 101; i <= 200; i++) {
                    if (i % 2 != 0) {
                        System.out.println(name +  "(" + Thread.currentThread().getId() + "): " + i);
                    }
                }
            break;

            default:
            break;
        }
    }
}
