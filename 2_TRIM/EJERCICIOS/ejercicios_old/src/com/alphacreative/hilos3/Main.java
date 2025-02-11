package com.alphacreative.hilos3;

public class Main {

    public static void main(String[] args)
    {
        Deposito deposito = new Deposito(10000000);

        Productor productor = new Productor(deposito);
        
        Camion camion1 = new Camion(1, deposito);
        Camion camion2 = new Camion(2, deposito);
        Camion camion3 = new Camion(3, deposito);
        
        productor.start();
        camion1.start();
        camion2.start();
        camion3.start();
    }

}
