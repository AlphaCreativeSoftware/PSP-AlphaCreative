package com.alphacreative.hilos1.bank;

public class CuentaBancaria 
{
    int saldo = 1000;

    public CuentaBancaria (int saldoInicial) {
        this.saldo = saldoInicial;
    }
    public synchronized void sacarDinero (String nombre, int importe)
    {
        if (saldo >= importe) {
            System.out.println(nombre + " sacó de la cuenta " + importe +"$");
            saldo = saldo - importe;
            System.out.println("Saldo actual cuenta= "+saldo +"$");

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            
            System.out.println(nombre + " no puede sacar " + importe +"$ -> NO HAY SALDO SUFICIENTE");
            System.out.println("Saldo actual cuenta= "+saldo +"$");
            
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void ingresarDinero (String nombre, int importe)
    {
        System.out.println(nombre + " ingresó en la cuenta "+ importe +"$");
        saldo = saldo + importe;
        System.out.println("Saldo actual cuenta= "+saldo +"$");
                
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getSaldo() {
        return saldo;
    }
}
   