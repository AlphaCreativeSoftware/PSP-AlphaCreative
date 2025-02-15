import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
public class ServidorTCP {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        System.out.println("--- SERVIDOR TCP ---");
        System.out.println("---- PORT: 5000 ----");
        ServerSocket servidor = new ServerSocket(5000);

        int counter = 0;
        while (counter < 10) {
            //Conexión con algun cliente
            System.out.println("Esperando conexión de algun cliente...");
            Socket client = servidor.accept();
            System.out.println("Cliente conectado en puerto " + client.getPort() + "...");
            //Datos (Entrada)
            System.out.println("Esperando datos...");
            try {
                ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
                System.out.println("Recibiendo datos del Cliente...");
                Factura factura = (Factura) entrada.readObject();
                System.out.println("Datos de factura recibidos...");
                double total = factura.getImporteTotal(); //Este método ademas de calcular el importe total, lo devuelve;
                int iva = factura.getIVA(); //Este método lo mismo pero con el IVA
                System.out.println("Importe total: " + total + " IVA: " + iva + "%");
                System.out.println("Importe anterior: " + factura.getImporteFactura());
                //Como ya hemos calculado todo realmente dentro del objeto estan asignadas las cosas ya que factura ha hecho y asignado el calculo, ahora solo tenemos que devolver el objeto
                //Datos (Salida)
                System.out.println("Enviando datos al Cliente...");
                ObjectOutputStream salida = new ObjectOutputStream(client.getOutputStream());
                salida.writeObject(factura);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            counter += 1;
        }
        servidor.close();
    }
}
