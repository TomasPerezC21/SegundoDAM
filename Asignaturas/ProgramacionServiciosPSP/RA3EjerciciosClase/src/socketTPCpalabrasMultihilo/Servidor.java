package socketTPCpalabrasMultihilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        int puertoServidor = 3000;
        ServerSocket socket = null;
        Socket socketCliente = null;

        try {
            socket = new ServerSocket(puertoServidor);

            while(true){
                socketCliente = socket.accept();
                GestorPalabras gp = new GestorPalabras(socketCliente);
                new Thread(gp).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

}
