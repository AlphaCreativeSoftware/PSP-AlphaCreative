package com.alphacreative.hilos1;
import com.alphacreative.hilos1.hilos.*;
public class HiloParImparEjecutar extends Thread{
    public static void main(String[] args) {
        
        HiloParImpar hiloPar = new HiloParImpar(1,"Hilo Par");

        HiloParImpar hiloImpar = new HiloParImpar(2,"Hilo Impar");

        Thread t1 = new Thread(hiloPar);
        Thread t2 = new Thread(hiloImpar);

        t1.setPriority(MIN_PRIORITY); //MINIMA PRIORIDAD PARA QUE SE VEAN DESPUES DE LOS PARES
        t2.setPriority(MAX_PRIORITY); //MAXIMA PRORIEDAD PARA QUE SE VEAN PRIMERO LOS IMPARES

        t2.start();
        
        try
        {
            t2.join(); //Modificacion para que se ejecuten los impares en primer lugar
        }
        catch(Exception e)
        {

        }
        t1.start();
    }

}
