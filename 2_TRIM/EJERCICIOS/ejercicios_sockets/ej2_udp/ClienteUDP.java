import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
public class ClienteUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        System.out.println("--- Cliente UDP ---");
        //Inicializacion de variabels
        Random random = new Random();
        DatagramSocket socket = new DatagramSocket(6001);
        InetAddress IPAddress = InetAddress.getLocalHost();
        int serverPort = 5000;
        
        //Datos(salida)
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        int[] numeros = new int[10];
        for (int i = 0; i < 10; i++) 
        {
            numeros[i] = random.nextInt(100);
            System.out.println("(Enviado al servidor) Elemento " + i + ": " + numeros[i]);
        }
        objectStream.writeObject(numeros);
        byte[] sendData = byteStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
        socket.send(sendPacket);

        //Datos(entrada)
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivePacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object receivedObject = objectInputStream.readObject();
        try {
            int[] recivedArray = (int[]) receivedObject;
            System.out.println("(Recibido del servidor) SUMA: " + recivedArray[0] + ", MINIMO: " + recivedArray[1] + ", MAXIMO: " + recivedArray[2]);
        } catch (Exception e) {
            System.out.println("Error al convertir datos suma, minimo, maximo recibidos en Array[] int...");
            e.printStackTrace();
        }
        socket.close();
    }
    
}
