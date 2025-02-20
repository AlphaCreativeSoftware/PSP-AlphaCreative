package com.alphacreative.hilos5;
public class Cliente extends Thread{
    
    private int id;
    private Gym gym;

    public Cliente(int id, Gym gym){
        this.id = id;
        this.gym = gym;
    }

    @Override
    public void run(){
        try {
            gym.entrar(id);
            sleep(2000);
            gym.salir(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
