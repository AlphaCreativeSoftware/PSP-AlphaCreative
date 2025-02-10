package com.alphacreative.pointerTesting;

import java.util.ArrayList;

public class MainPointers {

    public static ArrayList<Persona> list = new ArrayList<Persona>();

    public static void main(String[] args) {
        list.add(new Persona("Manolo", 20));
        list.add(new Persona("Juan", 25));
        printPersonas();

        Persona persona = list.get(0);
        persona.setName("Luis");

        printPersonas();
    }

    public static void printPersonas()
    {
        System.out.println("----------");
        for(Persona p : list)
        {
            System.out.println(p);
        }
        System.out.println("----------");
    }



}
