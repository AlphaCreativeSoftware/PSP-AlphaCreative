import java.io.*;
import java.net.*;
import java.time.LocalDate;

public class ClienteUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("--- CLIENTE UDP ---");
        DatagramSocket socket = new DatagramSocket();
        InetAddress host = InetAddress.getLocalHost();
        int port = 5000;

        //Datos(salida)
        Factura factura = new Factura("F-2023-01", LocalDate.of(2023, 2, 25), 250, TipoIVA.IGC);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(factura);
        oos.flush();
        byte[] facturaBytes = bos.toByteArray();
        
        //Envió
        DatagramPacket paqueteEnvio = new DatagramPacket(facturaBytes, facturaBytes.length, host, port);
        socket.send(paqueteEnvio);
        System.out.println("Factura enviada al servidor...");
        
        //Datos(Entrada)
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteRecepcion = new DatagramPacket(buffer, buffer.length);
        socket.receive(paqueteRecepcion);
        
        ByteArrayInputStream bis = new ByteArrayInputStream(paqueteRecepcion.getData());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Factura facturaRecibida = (Factura) ois.readObject();
        
        System.out.println("Factura recibida del servidor...");
        System.out.println("Numero de factura: " + facturaRecibida.getNumeroFactura());
        System.out.println("Fecha de factura: " + facturaRecibida.getFechaFactura());
        System.out.println("Importe de factura: " + facturaRecibida.getImporteFactura());
        System.out.println("Tipo de IVA: " + facturaRecibida.getTipoIVA());
        System.out.println("IVA: " + facturaRecibida.getIVA());
        System.out.println("Importe total: " + facturaRecibida.getImporteTotal());
        
        socket.close();
    }
}