package com.alphacreative.hilos1;
import com.alphacreative.hilos1.hilos.*;
public class HiloSumasRestasEjecutar {
    public static void main(String[] args) {

        Thread hiloSuma1 = new Thread(new HiloSumasRestas(100, "+"), "HiloSuma1");
        Thread hiloResta2 = new Thread(new HiloSumasRestas(100, "-"), "HiloResta2");
        Thread hiloSuma3 = new Thread(new HiloSumasRestas(1, "+"), "HiloSuma3");
        Thread hiloResta4 = new Thread(new HiloSumasRestas(1, "-"), "HiloResta4");
        
        hiloSuma1.start();


        try {
            hiloSuma1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hiloSuma3.start();
        try
        {
            hiloSuma3.join();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        hiloResta4.start();
        

        try
        {
            hiloResta4.join();
            hiloResta2.start();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }

}
