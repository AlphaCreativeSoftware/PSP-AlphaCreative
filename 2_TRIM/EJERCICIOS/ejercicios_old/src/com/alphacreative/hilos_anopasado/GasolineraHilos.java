package com.alphacreative.hilos_anopasado;
public class GasolineraHilos {
    public static void main(String[] args) {
        
	Gasolinera gas = new Gasolinera();

        Cliente c1 = new Cliente(gas, "Cliente 1");
        Cliente c2 = new Cliente(gas, "Cliente 2");
        Cliente c3 = new Cliente(gas, "Cliente 3");
        Cliente c4 = new Cliente(gas, "Cliente 4");
        Cliente c5 = new Cliente(gas, "Cliente 5");
        Cliente c6 = new Cliente(gas, "Cliente 6");
        Cliente c7 = new Cliente(gas, "Cliente 7");
        Cliente c8 = new Cliente(gas, "Cliente 8");
        Cliente c9 = new Cliente(gas, "Cliente 9");
        Cliente c10 = new Cliente(gas, "Cliente 10");
         
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
        c8.start();
        c9.start();
        c10.start();  
    }
}
