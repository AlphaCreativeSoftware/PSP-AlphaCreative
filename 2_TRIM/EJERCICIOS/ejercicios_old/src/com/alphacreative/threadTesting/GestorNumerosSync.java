package com.alphacreative.threadTesting;

import java.util.ArrayList;

public class GestorNumerosSync {
    private ArrayList<Integer> numeros;

    public GestorNumerosSync() {
        this.numeros = new ArrayList<>();
    }

    // Método sincronizado para agregar un número a la lista
    public synchronized void add(int numero) {
        numeros.add(numero);
        System.out.println("Número añadido: " + numero);
        notifyAll();
    }

    // Método sincronizado para eliminar un número en una posición específica
    public synchronized boolean removeAt(int index) {
        if (index >= 0 && index < numeros.size()) {
            int removedValue = numeros.remove(index); // Eliminar el número en el índice
            System.out.println("Número eliminado en la posición " + index + ": " + removedValue);
            return true;
        } else {
            System.out.println("Índice fuera de rango");
            return false;
        }
    }

    // Método sincronizado para recorrer la lista e imprimir sus valores
    public synchronized void printList() {
        System.out.println("Contenido de la lista:");
        for (int num : numeros) {
            System.out.println(num);
        }
    }

    public synchronized ArrayList<Integer> getNumeros() {
        return this.numeros;
    }
    public synchronized ArrayList<Integer> setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
        return this.numeros;
    }
}
