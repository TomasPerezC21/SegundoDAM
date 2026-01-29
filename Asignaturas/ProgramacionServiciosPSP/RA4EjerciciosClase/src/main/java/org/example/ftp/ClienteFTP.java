package org.example.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.*;
import java.util.Properties;

public class ClienteFTP {

    static void main() {
        gestionDirectorios();

    }
    private static void gestionDirectorios() {
        FTPClient ftp = conexion();
        try {
            System.out.println("Directorio de trabajo: " + ftp.printWorkingDirectory());

            System.out.println("FICHEROS: ");
            for (FTPFile fichero:  ftp.listFiles()) {
                System.out.println(fichero.getName());
            }

            ftp.changeWorkingDirectory("/datosFTP");
            System.out.println("Directorio de trabajo: " + ftp.printWorkingDirectory());

            for (String str:  ftp.listNames()){
                System.out.println(str);
            }

            String directorioActual = System.getProperty("user.dir");
            System.out.println("Directorio Actual en local: " + directorioActual);

            File f = new File(directorioActual);
            for (File fichero:  f.listFiles()){
                System.out.println(fichero.getName());
            }

//            boolean subida = subirFicheroASCII(ftp, directorioActual+"\\pom.xml", "pepico.xml");
//
//            if (subida){
//                System.out.println("Subida con éxito");
//            }else{
//                System.out.println("No se ha subido.");
//            }

            boolean resultado = subirFicheroBinario(ftp,"C:\\Users\\alumno\\Downloads\\Gemini_Generated_Image_5evwyy5evwyy5evw.png", "tpcInformatica.png");

            if (resultado){
                System.out.println("Subida foto");
            }else{
                System.out.println("Foto no subida");
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

    private static boolean subirFicheroASCII(FTPClient ftp, String rutaAbsolutaNombre, String nombreServidor) throws IOException{

        ftp.setFileType(FTPSClient.ASCII_FILE_TYPE);

        File f = new File(rutaAbsolutaNombre);

        InputStream in = new FileInputStream(f);
        boolean upload = ftp.storeFile(nombreServidor,in);

        in.close();
        return upload;
    }

    private static boolean subirFicheroBinario(FTPClient ftp, String rutaAbsolutaNombre, String nombreServidor) throws IOException{

        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        File f = new File(rutaAbsolutaNombre);
        InputStream in = new FileInputStream(f);
        OutputStream out = ftp.storeFileStream(nombreServidor);

        byte[] buffer = new byte[4096];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
        return ftp.completePendingCommand();
    }

}
