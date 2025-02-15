import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
public class ClienteTCP {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        //Iniciales
        System.out.println("--- CLIENTE TCP ---");
        Random random = new Random();
        Scanner in = new Scanner(System.in);
        String host = InetAddress.getLocalHost().getHostAddress();
        int port = 5000; //Server port


        //Conexión con Servidor
        System.out.println("Cliente(1) Iniciado....");
        Socket cliente = new Socket(host, port);
        System.out.println("Conectado a " + host + ":" + port);

        //Datos(Salida)
        try {
            Factura factura = new Factura("F-2023-01",LocalDate.of(2023,02,25),250,TipoIVA.IGC);
            ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
            output.writeObject(factura);
            System.out.println("Factura enviada al servidor...");
        } catch (Exception e) {
            System.out.println("Error al enviar datos al servidor " + e.getMessage());
        }

        //Datos(Entrada)
        try {
            System.out.println("Esperando factura del servidor...");
            ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
            Factura factura = (Factura) input.readObject();
            System.out.println("Factura recibida del servidor...");
            System.out.println("Numero de factura: " + factura.getNumeroFactura());
            System.out.println("Fecha de factura: " + factura.getFechaFactura());
            System.out.println("Importe de factura: " + factura.getImporteFactura());
            System.out.println("Tipo de IVA: " + factura.getTipoIVA());
            System.out.println("IVA: " + factura.getIVA());
            System.out.println("Importe total: " + factura.getImporteTotal());
        } catch (Exception e) {
            System.out.println("Error al recibir datos del servidor " + e.getMessage());
        }


        cliente.close();
    }
    
}
