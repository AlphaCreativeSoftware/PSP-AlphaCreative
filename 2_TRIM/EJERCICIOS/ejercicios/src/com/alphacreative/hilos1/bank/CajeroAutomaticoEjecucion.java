package com.alphacreative.hilos1.bank;
public class CajeroAutomaticoEjecucion {

    public static void main(String[] args)
    {
        // Declarar un objeto de la clase Banca y pasar el objeto
        // junto con otros parámetros a la clase ThreadWithdrawal
        // y ThreadDeposit. Esto será necesario para llamar
        // a los métodos withdrawn y deposit
        // de esas clases
        // Creación de un objeto de la clase Banca
        CuentaBancaria micuenta= new CuentaBancaria(1000);
        System.out.println("Saldo actual: " + micuenta.getSaldo());

        HiloIngresarDinero t1 = new HiloIngresarDinero(micuenta, "Padre", 200);
        HiloSacarDinero t2 = new HiloSacarDinero(micuenta, "Madre", 800);
        HiloIngresarDinero t3 = new HiloIngresarDinero(micuenta, "Hijo1", 300);
        HiloSacarDinero t4  = new HiloSacarDinero(micuenta, "Hijo2", 800);
        HiloSacarDinero t5  = new HiloSacarDinero(micuenta, "Abuelo", 600);
            // Cuando un programa llama al método start(),
            // se crea un nuevo hilo y
            // luego se ejecuta el método run()
            // Inicio de los hilos creados anteriormente
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
