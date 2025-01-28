package com.alphacreative.hilos1.bank;

public class HiloSacarDinero extends Thread
{
    private CuentaBancaria cuenta;
    private String nombre;
    private int cantidad;

    HiloSacarDinero(CuentaBancaria micuenta, String nombre, int cantidad)
    {
        this.cuenta = micuenta;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    @Override
    public void run() { 
        cuenta.sacarDinero(nombre,cantidad);
    }
}
