package com.alphacreative.hilos5;
public class Gym {

    private boolean[] aforo;
    private int contador;

    public Gym (int aforo){
        this.aforo = new boolean[aforo];
        this.contador=0;
        for (int i = 0;i<this.aforo.length;i++){
            this.aforo[i]=true;
        }
    }

    public synchronized void entrar(int id) throws Exception{

        while (contador==aforo.length) {
            System.out.println("W: Cola llena, cliente("+id+") esperando.");
            wait();
        }

        boolean haEntrado = false;

        for (int i = 0; i < aforo.length &&!haEntrado ;i++){
            if (aforo[i]) {
                aforo[i] = false;
                haEntrado = true;
                System.out.println("E: cliente("+id+") ha entado.");
                contador+=1;
            }
        }

    }

    public synchronized void salir(int id){
        boolean haSalido = false;

        for (int i = 0; i < aforo.length &&!haSalido ;i++){
            if (!aforo[i]) {
                aforo[i] = true;
                System.out.println("S: cliente("+id+") ha terminado de entrenar y ha salido.");
                notify();
                haSalido = true;
                contador-=1;
            }
        }
    }

}
