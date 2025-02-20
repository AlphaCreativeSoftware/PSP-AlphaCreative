package com.alphacreative.hilos5;
public class Main {
    public static void main(String[] args) {

        Gym gym = new Gym(5);

        Cliente cliente1 = new Cliente(1, gym);
        Cliente cliente2 = new Cliente(2, gym);
        Cliente cliente3 = new Cliente(3, gym);
        Cliente cliente4 = new Cliente(4, gym);
        Cliente cliente5 = new Cliente(5, gym);
        Cliente cliente6 = new Cliente(6, gym);
        Cliente cliente7 = new Cliente(7, gym);
        Cliente cliente8 = new Cliente(8, gym);
        Cliente cliente9 = new Cliente(9, gym);
        Cliente cliente10 = new Cliente(10, gym);
        Cliente cliente11 = new Cliente(11, gym);
        Cliente cliente12 = new Cliente(12, gym);
        Cliente cliente13 = new Cliente(13, gym);
        Cliente cliente14 = new Cliente(14, gym);
        Cliente cliente15 = new Cliente(15, gym);
        Cliente cliente16 = new Cliente(16, gym);
        Cliente cliente17 = new Cliente(17, gym);
        Cliente cliente18 = new Cliente(18, gym);
        Cliente cliente19 = new Cliente(19, gym);
        Cliente cliente20 = new Cliente(20, gym);
        
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();
        cliente6.start();
        cliente7.start();
        cliente8.start();
        cliente9.start();
        cliente10.start();
        cliente11.start();
        cliente12.start();
        cliente13.start();
        cliente14.start();
        cliente15.start();
        cliente16.start();
        cliente17.start();
        cliente18.start();
        cliente19.start();
        cliente20.start();
        
        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
            cliente5.join();
            cliente6.join();
            cliente7.join();
            cliente8.join();
            cliente9.join();
            cliente10.join();
            cliente11.join();
            cliente12.join();
            cliente13.join();
            cliente14.join();
            cliente15.join();
            cliente16.join();
            cliente17.join();
            cliente18.join();
            cliente19.join();
            cliente20.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

    }
}
