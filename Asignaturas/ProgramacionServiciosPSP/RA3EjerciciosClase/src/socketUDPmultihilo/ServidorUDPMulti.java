package socketUDPmultihilo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDPMulti {

    public static void main(String[] args) {

        try{
            DatagramSocket socket = new DatagramSocket(5555);
            while(true){

                DatagramPacket dpRecibo = new DatagramPacket(new byte[1024], 1024);

                socket.receive(dpRecibo);
                new Thread(new GestorConversacionDATAGRAMAS(dpRecibo, socket)).start();
            }


        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


}
