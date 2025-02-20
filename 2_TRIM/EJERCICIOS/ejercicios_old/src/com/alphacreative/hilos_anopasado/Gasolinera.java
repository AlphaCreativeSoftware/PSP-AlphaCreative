package com.alphacreative.hilos_anopasado;
class Gasolinera {

    private static int surtidores = 3;
    
    //método que simula la entrada en el surtidor y el repostaje de gasolina
    public synchronized void entrarSurtidor(String nombre){
        while (surtidores <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } 
        }
        System.out.println(nombre + " entrando al surtidor");
        surtidores = surtidores - 1;
        System.out.println("Surtidores disponibles " + surtidores);
        notifyAll();
    }

    //método que simula la salida del surtidor
    public synchronized void salirSurtidor(String nombre) {
        System.out.println(nombre + " termina echar gasolina y sale del surtidor");
        surtidores = surtidores + 1;
        System.out.println("Surtidores disponibles " + surtidores);
        notifyAll();    
    }
}
