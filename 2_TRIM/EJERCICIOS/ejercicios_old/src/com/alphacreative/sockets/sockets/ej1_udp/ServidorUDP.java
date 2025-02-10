import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServidorUDP {
    private static final int PUERTO = 6666;

    public static void main(String[] args) {
        try (DatagramSocket servidor = new DatagramSocket(PUERTO))
        {
            System.out.println("Servidor UDP esperando conexiones...");
            
            byte[] buffer = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
            servidor.receive(paqueteEntrada);
            String mensajeRecibido = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength()).trim();
            int numeroRecibido = Integer.parseInt(mensajeRecibido);
            System.out.println("Número recibido de Cliente 1: " + numeroRecibido);

            InetAddress direccionCliente2 = InetAddress.getLocalHost();
            DatagramPacket paqueteSalida = new DatagramPacket(mensajeRecibido.getBytes(), mensajeRecibido.length(), direccionCliente2, 6667);
            servidor.send(paqueteSalida);
            System.out.println("Número enviado a Cliente 2");

            servidor.receive(paqueteEntrada);
            String numeroProcesadoStr = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength()).trim();
            int numeroProcesado = Integer.parseInt(numeroProcesadoStr);
            System.out.println("Número procesado recibido de Cliente 2: " + numeroProcesado);

            DatagramPacket paqueteSalida2 = new DatagramPacket(numeroProcesadoStr.getBytes(), numeroProcesadoStr.length(), paqueteEntrada.getAddress(), 6668);
            servidor.send(paqueteSalida2);
            System.out.println("Número procesado enviado a Cliente 1");
        }
        catch (IOException e)
        {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}