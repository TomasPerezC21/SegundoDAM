package socketUDP;

import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClienteUDP {

    public static void main(String[] args) {

        try {
            String cadena = "Hola";
            byte[] cadenaBytes = cadena.getBytes();

            InetAddress inet = Inet4Address.getByName("localhost");

            DatagramPacket dp = new DatagramPacket(cadenaBytes, cadenaBytes.length,  inet, 12345);



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }



    }

}
