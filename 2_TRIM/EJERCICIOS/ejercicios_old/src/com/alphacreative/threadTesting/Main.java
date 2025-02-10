package com.alphacreative.threadTesting;
import java.util.ArrayList;
import java.util.Random;
import static com.alphacreative.threadTesting.Utility.*;
public class Main {

    public static void main(String[] args) {
        GestorNumerosSync gestor = new GestorNumerosSync();

        HiloIngresor hiloIngresor = new HiloIngresor(gestor);
        HiloExtractor hiloExtractor = new HiloExtractor(gestor);
        HiloLector hiloLector = new HiloLector(gestor);
        hiloExtractor.start();
        hiloIngresor.start();
        hiloLector.start();
        
    }

}


class HiloExtractor extends Thread
{
    private GestorNumerosSync gestor;
    public HiloExtractor(GestorNumerosSync gestorP) {
        this.gestor = gestorP;
    }
    @Override
    public void run() {

        System.out.println("Hilo Extractor");
        for (int i = gestor.getNumeros().size() - 1; i >= 0; i--) {
            gestor.removeAt(i);
        }
        
    }
}

class HiloIngresor extends Thread
{
    private GestorNumerosSync gestor;
    public HiloIngresor(GestorNumerosSync gestorP) {
        this.gestor = gestorP;
    }
    @Override
    public void run() {
        synchronized(gestor)
        {
            System.out.println("Hilo Ingresor");
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                    gestor.add(random.nextInt(100));
            }
        }
    }
}

class HiloLector extends Thread
{
    private GestorNumerosSync gestor;
    public HiloLector(GestorNumerosSync gestorP) {
        this.gestor = gestorP;
    }
    @Override
    public void run() {
        synchronized(gestor)
        {
            System.out.println("Hilo Lector");
            gestor.printList();
        }
    }
}
