import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class ServidorUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        System.out.println("--- Servidor UDP ---");
        System.out.println("---- PORT: 5000 ----");

        DatagramSocket socket = new DatagramSocket(5000); //Abrimos un socket para el servidor en el puerto 5000 (orient)
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        int counter = 0;
        while (counter < 10) {
            //Datos(entrada)
            System.out.println("Esperando datos del cliente...");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            //Obtenemos datos del cliente
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            System.out.println("Datos recibidos...");

            ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            Object receivedObject = objectStream.readObject();
            int[] recivedArray = new int[10];
            try {
                recivedArray = (int[]) receivedObject;
                for (int i = 0; i < 10; i++) 
                {
                    System.out.println("(Recibido) Elemento " + i + ": " + recivedArray[i]);
                }
                int suma = 0;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int i = 0; i < recivedArray.length; i++) {
                    suma += recivedArray[i];
                    if(recivedArray[i] < min)
                    {
                        min = recivedArray[i];
                    }
                    if(recivedArray[i] > max)
                    {
                        max = recivedArray[i];
                    }
                }
                System.out.println("SUMA: " + suma);
                System.out.println("MINIMO: " + min);
                System.out.println("MAXIMO: " + max);
                int[] resultArray = {suma, min, max};

                
                //Datos(salida)
                System.out.println("Enviando datos al cliente...");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(resultArray);
                sendData = byteArrayOutputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
                System.out.println("Datos enviados...");

            } catch (Exception e) {
                System.out.println("Error al Transformar los datos recibidos en un objeto Array Int[10]");
            }

            


            counter += 1;
        }
    
    }
}
