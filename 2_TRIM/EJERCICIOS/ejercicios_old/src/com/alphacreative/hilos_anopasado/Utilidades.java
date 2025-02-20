package com.alphacreative.hilos_anopasado;
import java.net.*;
import java.io.*;

public class Utilidades {

    // Método para calcular el factorial de un número
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Número debe ser no negativo");
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // Método para calcular el término n de Fibonacci de forma iterativa
    public static long fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("Número debe ser no negativo");
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        long a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    // Método para verificar si un número es primo
    public static boolean esPrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Método para iniciar un servidor con sockets en un puerto específico
    public static void iniciarServidor(int puerto) {
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                new Thread(new ManejadorCliente(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para conectar un cliente a un servidor
    public static void conectarCliente(String host, int puerto) {
        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
            
            salida.println("Hola, servidor!");
            System.out.println("Servidor dice: " + entrada.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Clase para manejar clientes en un servidor multihilo
class ManejadorCliente implements Runnable {
    private Socket cliente;

    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {
            
            String mensaje = entrada.readLine();
            System.out.println("Mensaje recibido: " + mensaje);
            salida.println("Mensaje recibido!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
