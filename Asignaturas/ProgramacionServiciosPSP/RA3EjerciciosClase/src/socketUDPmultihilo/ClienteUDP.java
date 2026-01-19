package socketUDPmultihilo;

import java.net.*;

public class ClienteUDP {

    public static void main(String[] args) {


        try{
            int puertoServidor = 5555;
            String cadenaHola = "=======HOLA SOY TOMASSSS=========";

            byte[] cadenaBytes = cadenaHola.getBytes();

            InetAddress inet = InetAddress.getByName("192.168.0.2");

            System.out.println("============ Le envío al servidor: " + cadenaHola + " soy TOMÁSSSSS al servidor. =========");
            DatagramSocket socket = new DatagramSocket();

            DatagramPacket dpEnvio = new  DatagramPacket(cadenaBytes, cadenaBytes.length, inet, puertoServidor);

            socket.send(dpEnvio);

            //Recibir del servidor

            DatagramPacket dpRecibo = new DatagramPacket(new byte[1024], 1024);
            byte[] datosRecibido = dpRecibo.getData();


            socket.receive(dpRecibo);

            String datosCliente = new String(datosRecibido,0, dpRecibo.getLength());

            System.out.println("Recibo del servidor: " +   datosCliente );

            socket.close();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }


    }

}
