package com.alphacreative.hilos1.bank;

public class HiloIngresarDinero extends Thread {
    private CuentaBancaria cuenta;
    private String nombre;
    private int cantidad;

    HiloIngresarDinero (CuentaBancaria micuenta, String nombre, int cantidad)
    {
        this.cuenta = micuenta;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    @Override
    public void run() { 
        cuenta.ingresarDinero(nombre,cantidad);
    }

}
