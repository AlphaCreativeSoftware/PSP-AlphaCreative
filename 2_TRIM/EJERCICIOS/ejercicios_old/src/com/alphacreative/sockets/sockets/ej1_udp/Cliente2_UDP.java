import java.io.*;
import java.net.*;

public class Cliente2_UDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6667);
        byte[] bufferEntrada = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        System.out.println("Esperando número del servidor...");
        socket.receive(paqueteEntrada);

        String numeroStr = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength()).trim();
        int numero = Integer.parseInt(numeroStr);
        System.out.println("Número recibido del servidor: " + numero);

        int resultado = factorial(numero);
        String resultadoStr = String.valueOf(resultado);
        InetAddress direccionServidor = InetAddress.getLocalHost();
        DatagramPacket paqueteSalida = new DatagramPacket(resultadoStr.getBytes(), resultadoStr.length(), direccionServidor, 6666);
        socket.send(paqueteSalida);
        System.out.println("Número procesado enviado al servidor: " + resultado);
        socket.close();
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}