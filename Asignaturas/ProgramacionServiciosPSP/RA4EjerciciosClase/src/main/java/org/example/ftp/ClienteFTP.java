package org.example.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.util.Properties;

public class ClienteFTP {

    static void main() {
        gestionDirectorios();

    }
    private static void gestionDirectorios() {
        FTPClient ftp = conexion();
        try {
            System.out.println("Directorio de trabajo: " + ftp.printWorkingDirectory());


            for (FTPFile fichero:  ftp.listFiles()) {
                System.out.println(fichero.getName());
            }


            ftp.changeWorkingDirectory("/datosFTP");
            System.out.println("Directorio de trabajo: " + ftp.printWorkingDirectory());

            for (String str:  ftp.listNames()){
                System.out.println(str);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static FTPClient conexion(){
        FTPClient ftp = new FTPClient();

        try {
            Properties prop = new Properties();
            prop.load(ClienteFTP.class.getClassLoader().getResourceAsStream("ftp.properties"));

            String server = prop.getProperty("ftp.server");
            int port = Integer.parseInt(prop.getProperty("ftp.port"));
            String username = prop.getProperty("ftp.user");
            String password = prop.getProperty("ftp.password");

            ftp.connect(server,port);
            int codigo = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(codigo)) {
                ftp.disconnect();
            }

            boolean login = ftp.login(username,password);

            if (!login) {
                return null;
            }

            return  ftp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
