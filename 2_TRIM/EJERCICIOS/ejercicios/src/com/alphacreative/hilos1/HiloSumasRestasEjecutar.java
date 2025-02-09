package com.alphacreative.hilos1;
import com.alphacreative.hilos1.hilos.*;
public class HiloSumasRestasEjecutar {
    public static void main(String[] args) {

        Thread hiloSuma1 = new Thread(new HiloSumasRestas(100, "+"), "HiloSuma1");
        Thread hiloResta2 = new Thread(new HiloSumasRestas(100, "-"), "HiloResta2");
        Thread hiloSuma3 = new Thread(new HiloSumasRestas(1, "+"), "HiloSuma3");
        Thread hiloResta4 = new Thread(new HiloSumasRestas(1, "-"), "HiloResta4");
        
        hiloSuma1.start();
        hiloResta2.start();
        hiloSuma3.start();
        hiloResta4.start();

        try
        {
            hiloSuma1.join();
            hiloResta2.join();
            hiloSuma3.join();
            hiloResta4.join();
        }
        catch(Exception e)
        {
            
        }
        
    }

}
