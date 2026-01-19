package socketUDPmultihilo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class GestorConversacionDATAGRAMAS implements Runnable {

    DatagramPacket dataGram;
    DatagramSocket socket;

    public GestorConversacionDATAGRAMAS(DatagramPacket dpEnvio, DatagramSocket socket) {
        this.dataGram = dpEnvio;
        this.socket = socket;
    }


    @Override
    public void run() {
        try {

        String datosCliente = new String(dataGram.getData(), 0, dataGram.getLength());
        System.out.println("Recibo del cliente "+  ": " +  datosCliente);

        String datosEnviar = "Adiós cliente";

        byte[] datosEnviarBytes = datosEnviar.getBytes();

        DatagramPacket dpEnviar = new DatagramPacket(datosEnviarBytes, datosEnviarBytes.length, dataGram.getAddress(), dataGram.getPort());
        System.out.println("Le envio al cliente: " + datosEnviar);

            socket.send(dpEnviar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
