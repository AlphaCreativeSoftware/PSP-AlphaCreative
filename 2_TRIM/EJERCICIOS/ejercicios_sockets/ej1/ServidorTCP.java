import java.io.*;
import java.net.*;

public class ServidorTCP {

    public static void main(String[] args) {
        try {
            ServerSocket servidor1 = new ServerSocket(6666);
            ServerSocket servidor2 = new ServerSocket(6667);
            System.out.println("Servidor Tuberia para Cliente(1) iniciado en el puerto " + 6666);

            //Conectamos con Cliente(1)
            System.out.println("Esperando conexión de Cliente 1...");
            Socket cliente1 = servidor1.accept();
            System.out.println("Cliente 1 conectado...");

            DataInputStream entradaCliente1 = new DataInputStream(cliente1.getInputStream());
            int numeroRecibido = entradaCliente1.readInt();
            System.out.println("Numero recibido de Cliente 1: " + numeroRecibido);

            

            
            System.out.println("Servidor Tuberia para Cliente(2) iniciado en el puerto " + 6667);
            //Conectamos con Cliente(2)
            System.out.println("Esperando conexión de Cliente 2...");
            Socket cliente2 = servidor2.accept();
            System.out.println("Cliente 2 conectado...");

            DataOutputStream salidaCliente2 = new DataOutputStream(cliente2.getOutputStream());
            salidaCliente2.writeInt(numeroRecibido);
            System.out.println("Número enviado a Cliente 2...");

            //Reconectamos con Cliente(2)
            DataInputStream entradaCliente2 = new DataInputStream(cliente2.getInputStream());
            int numeroProcesado = entradaCliente2.readInt();
            System.out.println("Número procesado recibido de Cliente 2: " + numeroProcesado);
            cliente2.close();

            //Reconectamos con Cliente(1)
            System.out.println("Esperando reconexión de Cliente 1...");
            cliente1 = servidor1.accept();
            System.out.println("Cliente 1 reconectado.");

            DataOutputStream salidaCliente1 = new DataOutputStream(cliente1.getOutputStream());
            salidaCliente1.writeInt(numeroProcesado);
            System.out.println("Número procesado enviado a Cliente 1: " + numeroProcesado);

            cliente1.close();

        }
        catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
