import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("--- SERVIDOR UDP ---");
        System.out.println("---- PORT: 5000 ----");
        DatagramSocket socket = new DatagramSocket(5000);
        byte[] buffer = new byte[1024];
        while (true) {
            //Datos(Entrada)
            DatagramPacket paqueteRecepcion = new DatagramPacket(buffer, buffer.length);
            System.out.println("Esperando datos del cliente...");
            socket.receive(paqueteRecepcion);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(paqueteRecepcion.getData());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Factura factura = (Factura) ois.readObject();
            System.out.println("Factura recibida del cliente...");
            
            double total = factura.getImporteTotal();
            int iva = factura.getIVA();
            System.out.println("Importe total: " + total + " IVA: " + iva + "%");
            
            //Datos(Salida)
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(factura);
            //oos.flush();
            byte[] facturaBytes = bos.toByteArray();
            
            DatagramPacket paqueteEnvio = new DatagramPacket(facturaBytes, facturaBytes.length, paqueteRecepcion.getAddress(), paqueteRecepcion.getPort());
            socket.send(paqueteEnvio);
            System.out.println("Factura enviada de vuelta al cliente...");
        }
    }
}