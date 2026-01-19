package socketUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorGracias {

    public static void main(String[] args) {


        try{

            byte[] datosRecibido = new byte[1024];

            DatagramSocket socket = new DatagramSocket(5555);

            DatagramPacket dpRecibo = new DatagramPacket(datosRecibido, datosRecibido.length);

            socket.receive(dpRecibo);

            datosRecibido = dpRecibo.getData();

            String datosCliente = new String(datosRecibido, 0, dpRecibo.getLength());
            System.out.println("Recibo del cliente: " +  datosCliente);

            String datosEnviar = "Adiós cliente";

            byte[] datosEnviarBytes = datosEnviar.getBytes();

            DatagramPacket dpEnviar = new DatagramPacket(datosEnviarBytes, datosEnviarBytes.length, dpRecibo.getAddress(), dpRecibo.getPort());
            System.out.println("Le envio al cliente: " + datosEnviar);

            socket.send(dpEnviar);

            socket.close();

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


}
