package pruebasHilosSockets.pruebas1;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
@SuppressWarnings("serial")
public class Main {
    public static void main(String[] args){
        Escenario escenario = new Escenario(30,30);
        ServerBackground server = new ServerBackground(escenario);
        Client client = new Client();
        server.start();
        try {
            server.join(1000);
        } catch (Exception e) {
        }
        client.start();

    }
    
}
class ServerBackground extends Thread
{
    private Escenario escenario;
    public ServerBackground(Escenario escenario)
    {
        this.escenario = escenario;
    }
    @Override
    public void run() {
        try 
        {
            System.out.println("--- SERVIDOR UDP ---");
            DatagramSocket socket = new DatagramSocket(5000);
            System.out.println("---- PORT: "+socket.getLocalPort()+" ----");
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while(true){
                //Datos(Entrada)
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                //Obtenemos datos del cliente
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.println("(Servidor) Datos recibidos...");
    
                ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectStream = new ObjectInputStream(byteStream);
                Object receivedObject = objectStream.readObject();
                Player player;
                player = (Player) receivedObject;
                System.out.println("(Servidor) Player conectado...");
                System.out.println("(Servidor) Player Id: "+player.getID());

                //Datos(salida)
                /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(escenario);
                byte[] facturaBytes = bos.toByteArray();
                
                DatagramPacket paqueteEnvio = new DatagramPacket(facturaBytes, facturaBytes.length, paqueteRecepcion.getAddress(), paqueteRecepcion.getPort());
                socket.send(paqueteEnvio);
                System.out.println("Escenario enviado al cliente...");*/
            }
        }
        catch (Exception e)
        {
            System.out.println("Error servidor...");
            e.printStackTrace();
            return;
        }
    }
}

class Client extends Thread
{
    @Override
    public void run() 
    {
        try
        {
            System.out.println("--- CLIENTE UDP ---");
            byte[] sendData = new byte[1024];
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverHost = InetAddress.getLocalHost();
            int serverPort = 5000;

            //Datos(salida)
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            Player player = new Player("lucas");
            objectOutputStream.writeObject(player);
            sendData = byteArrayOutputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverHost, serverPort);
            socket.send(sendPacket);
            System.out.println("(Cliente) Player conectado al servidor...");
        }
        catch (Exception e)
        {
            System.out.println("Error cliente...");
            e.printStackTrace();
            return;
        }
    }
}


class Escenario implements Serializable
{
    private Player[][] tablero;
    public Escenario(int x, int y)
    {
        super();
        this.tablero = new Player[x][y];
    }
    public void draw()
    {
        for(int i = 0; i < tablero.length; i++)
        {
            for(int j = 0; j < tablero[i].length; j++)
            {
                if(tablero[i][j] != null)
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public void SpawnPlayer(Player player, int xCoord, int yCoord)
    {
        for(int i = 0; i < tablero.length; i++) //Check if player already exists
        {
            for(int j = 0; j < tablero[i].length; j++)
            {
                if(tablero[i][j] == player)
                {
                    System.out.println("(Debug) Player already exists");
                    return;
                }
            }
        }
        tablero[xCoord][yCoord] = player;
        player.SetVector(xCoord, yCoord);
    }
}

class Player implements Serializable
{
    private String id;
    private int x;
    private int y;
    public Player(String name)
    {
        super();
        Random random = new Random();
        this.id = name + "_" + (random.nextInt(2000));
    }
    public void SetVector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public String getID()
    {
        return this.id;
    }
}