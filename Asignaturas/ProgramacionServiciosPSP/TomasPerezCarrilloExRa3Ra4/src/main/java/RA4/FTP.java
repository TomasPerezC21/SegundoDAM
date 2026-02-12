package RA4;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class FTP {

    public static void main(String[] args) {
        try {
            FTPClient ftp = conexion();

            if (ftp == null) return;

            String usuario = "13788476";


            //DESCOMENTA ESTOOO


            ejercicio1(ftp, usuario);

            //ejercicio2(ftp,usuario);

            //ejercicio3(ftp,usuario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void ejercicio3(FTPClient ftp, String usuario) throws IOException {

        ftp.changeWorkingDirectory(usuario);
        System.out.println(ftp.printWorkingDirectory());

        ftp.changeWorkingDirectory("guillermo");
        System.out.println(ftp.printWorkingDirectory());

        ftp.makeDirectory("examen");
        ftp.changeWorkingDirectory("examen");
        System.out.println(ftp.printWorkingDirectory());


        ftp.changeWorkingDirectory("..");
        System.out.println(ftp.printWorkingDirectory());

        String fichero1 = "3a.md";

        BufferedWriter bw = new BufferedWriter(new FileWriter(fichero1));

        FTPFile[] files = ftp.listFiles();

        ArrayList<String> fichero1Lista = new ArrayList<>();

        for (FTPFile file : files) {
            if (file.getSize() > 2500 && file.isFile()){
                fichero1Lista.add("Nombre: " + file.getName() + " Usuario Propietario: " + file.getUser() +
                        " Tamaño: " + file.getSize() + "Bytes");
            }
        }

        Collections.sort(fichero1Lista);

        for (String fichero :  fichero1Lista) {
            bw.write(fichero);
            bw.newLine();
        }


        ftp.changeWorkingDirectory("examen");
        boolean archivo1Subido = subirFichero(ftp, fichero1, "examen");

        if (archivo1Subido){
            System.out.println("Archivo 1 subido,");
        }else{
            System.out.println("Archivo 1 sin subir al server.");
        }

        String fichero2 = "3b.md";

        ftp.changeWorkingDirectory("..");

        FTPFile[] files2 = ftp.listFiles();

        ArrayList<String> fichero2Lista = new ArrayList<>();

        for (FTPFile file : files2) {
            fichero2Lista.add(file.getName() + file.getSize() +  "Bytes" + file.getTimestamp() + " Propietario: " + file.getUser());
        }

        Collections.sort(fichero2Lista);
        for (String fichero :  fichero2Lista) {
            bw.write(fichero);
            bw.newLine();
        }
        bw.close();

        ftp.changeWorkingDirectory("examen");
        boolean archivo2Subido = subirFichero(ftp, fichero1, "examen");

        if (archivo2Subido){
            System.out.println("Archivo 2 subido,");
        }else{
            System.out.println("Archivo 2 sin subir al server.");
        }

    }

    private static void ejercicio2(FTPClient ftp, String usuario) throws IOException {

        ftp.changeWorkingDirectory(usuario);
        System.out.println(ftp.printWorkingDirectory());

        ftp.changeWorkingDirectory("vicente");
        System.out.println(ftp.printWorkingDirectory());

        ftp.changeWorkingDirectory("medina");
        System.out.println(ftp.printWorkingDirectory());

        double mediaTamanio = 0;

        FTPFile[] ficheros = ftp.listFiles();

        ArrayList<Double> tamanios = new ArrayList<>();

        for (FTPFile f : ficheros){
            tamanios.add((double) f.getSize());
        }

        //calculamos media
        for (Double tamanio : tamanios) {
            mediaTamanio += tamanio;
        }

        double mediaFinal = mediaTamanio / tamanios.size();

        System.out.println("Tamaño medio de los archivos en: " + ftp.printWorkingDirectory() +" es: " + mediaFinal +" Bytes.");

        double mayor = ficheros[0].getSize();
        String ficheroMayor  = ficheros[0].getName();
        for (FTPFile f : ficheros){
            if (f.getSize()>mayor){
                mayor = f.getSize();
                ficheroMayor = f.getName();
            }
        }

        System.out.println("Fichero con mayor tamaño: " + ficheroMayor + " con: " + mayor + " Bytes.") ;





    }

    private static void ejercicio1(FTPClient ftp, String usuario) throws IOException {

        ftp.changeWorkingDirectory("/"+usuario);

        System.out.println(ftp.printWorkingDirectory());
        FTPFile[] ficheros = ftp.listFiles();

        ArrayList<String> carpetas = new ArrayList<>();

        for (FTPFile file : ficheros){
            if (file.isDirectory()){
                carpetas.add(file.getName());
            }
        }
        Collections.sort(carpetas);

        String carpeta1 = carpetas.getFirst();

        ftp.changeWorkingDirectory(carpeta1);
        System.out.println(ftp.printWorkingDirectory());


        //Creamos fichero

        String fichero = "subida.md";

        File f =  new File(fichero);

        BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
        bw.write("Tomás");
        bw.newLine();
        bw.write("Pérez Carrillo");
        bw.newLine();
        bw.write("NTT DATA");
        bw.newLine();
        bw.close();

        boolean subido = subirFichero(ftp, fichero, carpeta1);

        if (subido){
            System.out.println("Archivo subido");
        }else{
            System.out.println("Error al subir archivo");
        }

        //Mostrar tamaño fichero
        FTPFile[] ficheros2 = ftp.listFiles();
        for (FTPFile file : ficheros2){
            System.out.println(file.getName() + ": Tamaño: "+ file.getSize() + " Bytes");
        }

        //Descargar fichero

        String archivoServidor = ficheros2[1].getName(); //1 porque he subido dos antes
        String rutaDescarga = "src/main/resources/Descargas/descarga.md";
        boolean descargado = descargarFichero(ftp, archivoServidor, rutaDescarga);

        if (descargado){
            System.out.println("Archivo descargado.");
        }else{
            System.out.println("Error al descargar fichero");
        }

        //Mostrar contenido
        System.out.println("Contenido del fichero: ");

        BufferedReader br = new BufferedReader(new FileReader(rutaDescarga));
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }

    }

    private static boolean descargarFichero(FTPClient ftp, String nombreArchivoServer, String rutaDescarga) throws IOException {

        ftp.setFileType(org.apache.commons.net.ftp.FTP.ASCII_FILE_TYPE);

        File descargaLocal = new File(rutaDescarga);

        OutputStream output = new FileOutputStream(descargaLocal);
        boolean download = ftp.retrieveFile(nombreArchivoServer, output);
        output.close();
        return download;

    }

    private static boolean subirFichero(FTPClient ftp, String rutaFichero, String rutaDestino) throws IOException {

        ftp.setFileType(FTPSClient.ASCII_FILE_TYPE);

        File f = new  File(rutaFichero);

        InputStream in = new FileInputStream(f);
        boolean upload = ftp.storeFile(rutaDestino+".md",in);

        in.close();
        return upload;

    }

    private static FTPClient conexion() {
        try {

            String server = "192.168.0.2";
            int port = 21;
            String username = "13788476";
            String password = "agosto";

            FTPClient ftp = new FTPClient();

            ftp.connect(server, port);
            int codigo = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(codigo)) {
                ftp.disconnect();
            }

            boolean login = ftp.login(username, password);
            System.out.println("Se ha conectado el usuario " + username + ": " + login);

            if (!login) {
                ftp.enterLocalPassiveMode();
                ftp.disconnect();
                return null;
            }

            return ftp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}