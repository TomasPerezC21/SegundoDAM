package Parte1FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Tomas {

    public static void main(String[] args) {
        try {
        FTPClient ftp = conexion();

        if (ftp == null) return; //Por si falla la conex

        String username = System.getenv("FTP_USERTOMAS");

        mostrarDirectorioActual(ftp);

        ArrayList<String> carpetasCreadas = creacionCarpetas(ftp);

            // --- PUNTO 11: RENOMBRAR ---
            System.out.println("--- RENOMBRANDO CARPETA ---");
            String carpetaVieja = "prueba" + username;
            String carpetaNueva = "borrar" + username;

            ftp.changeWorkingDirectory("/Tomas");

            if (ftp.rename(carpetaVieja, carpetaNueva)) {
                System.out.println("ÉXITO: Carpeta renombrada a " + carpetaNueva);
            } else {
                System.out.println("ERROR: No se pudo renombrar " + carpetaVieja);
            }



        String carpetaMD =  carpetasCreadas.get(0);
        String carpetaFotos = carpetasCreadas.get(1);

        String rutaFicheroMD = "src/main/resources/pruebas.md";
        String rutaFicheroPNG = "src/main/resources/TPCinf.png";

        boolean subidoASCII = subirFicheroASCII(ftp, rutaFicheroMD, "textoMDPRUEBAS.md", carpetaMD);

           if (subidoASCII) {
               System.out.println("Fichero de texto subido con éxito.");
           }else{
               System.out.println("Error al subir fichero de texto.");
           }

        boolean subidoPNG = subirFicheroBinario(ftp, rutaFicheroPNG, "imagenJPGPRUEBAS.png", carpetaFotos);

           if (subidoPNG) {
               System.out.println("Fichero de imagen subido con éxito.");
           }else{
               System.out.println("Error al subir fichero de imagen.");
           }


            System.out.println("--- INICIANDO DESCARGAS ---");


            String destinoMD = "src/main/resources/descargasUsuario/descargado_pruebas.md";
            boolean bajadoMD = descargarFichero(ftp, carpetaMD, "textoMDPRUEBAS.md", destinoMD, FTPSClient.ASCII_FILE_TYPE);

            if (bajadoMD) System.out.println("MD Descargado correctamente en: " + destinoMD);
            else System.out.println("Error descargando MD.");


            String destinoPNG = "src/main/resources/descargasUsuario/descargado_imagen.png";
            boolean bajadoPNG = descargarFichero(ftp, carpetaFotos, "imagenJPGPRUEBAS.png", destinoPNG, FTPClient.BINARY_FILE_TYPE);

            if (bajadoPNG){
                System.out.println("PNG Descargado correctamente en: " + destinoPNG);
            }else{
                System.out.println("Error descargando PNG.");
            }

            System.out.println("--- MOSTRANDO INFORMACIÓN DE ARCHIVOS ---");

            mostrarFicheros(ftp, carpetaMD, carpetaFotos);


            System.out.println("--- BORRANDO DIRECTORIOS ---");


            borrarCarpeta(ftp, "md" + username);

            borrarCarpeta(ftp, "fotos" + username);

            borrarCarpeta(ftp, "borrar" + username);


            ftp.disconnect();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private static void borrarCarpeta(FTPClient ftp, String carpeta) {

        try {
            ftp.changeWorkingDirectory("/tomas");

            FTPFile[] archivos = ftp.listFiles(carpeta);

            if (archivos != null && archivos.length > 0) {
                for (FTPFile f : archivos) {
                    String rutaArchivo = carpeta + "/" + f.getName();
                    ftp.deleteFile(rutaArchivo);
                }
            }

            if (ftp.removeDirectory(carpeta)) {
                System.out.println("Carpeta eliminada: " + carpeta);
            } else {
                System.out.println("No se pudo eliminar: " + carpeta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarFicheros(FTPClient ftp, String carpetaRevisarMD, String carpetaRevisarPNG) {

        try {
            ftp.changeWorkingDirectory("/Tomas");//Volvemos a la raiz del usuario

            ftp.changeWorkingDirectory(carpetaRevisarMD);

            System.out.println("FICHEROS DE " + carpetaRevisarMD + " del usuario: " + System.getenv("FTP_USERTOMAS"));


            for (FTPFile fichero:  ftp.listFiles()) {

                System.out.println("Nombre: " + fichero.getName() + " .Tamaño: " + fichero.getSize() + " bytes.");

                // Formatear la fecha para que sea legible
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                if (fichero.getTimestamp() != null) {
                    String fecha = formato.format(fichero.getTimestamp().getTime());
                    System.out.println("Fecha Modificación: " + fecha);
                }
            }

            System.out.println("==========================================");

            ftp.changeWorkingDirectory("/Tomas");//Volvemos a la raiz del usuario

            ftp.changeWorkingDirectory(carpetaRevisarPNG);

            System.out.println("FICHEROS DE " + carpetaRevisarPNG + " del usuario: " + System.getenv("FTP_USERTOMAS"));


            for (FTPFile fichero:  ftp.listFiles()) {

                System.out.println("Nombre: " + fichero.getName() + " .Tamaño: " + fichero.getSize() + " bytes.");

                // Formatear la fecha para que sea legible
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                if (fichero.getTimestamp() != null) {
                    String fecha = formato.format(fichero.getTimestamp().getTime());
                    System.out.println("Fecha Modificación: " + fecha);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static boolean descargarFichero(FTPClient ftp, String carpetaRemota, String nombreArchivoEnServidor, String rutaLocalDestino, int tipoFichero) throws IOException {

        ftp.changeWorkingDirectory("/Tomas");

        ftp.changeWorkingDirectory(carpetaRemota);

        ftp.setFileType(tipoFichero);//En base a parametro recibido por si es md o png

        File archivoLocal = new File(rutaLocalDestino);

        OutputStream output = new FileOutputStream(archivoLocal);
        boolean download = ftp.retrieveFile(nombreArchivoEnServidor, output);
        output.close();
        return download;

    }

    private static boolean subirFicheroBinario(FTPClient ftp, String rutaAbsolutaNombre, String nombreServidor, String carpetaDestino) throws IOException{

        ftp.changeWorkingDirectory("/Tomas");
        System.out.println("DIRECTORIO" + ftp.printWorkingDirectory());

        ftp.changeWorkingDirectory(carpetaDestino);
        System.out.println("DIRECTORIO ACTUAL" + ftp.printWorkingDirectory());

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

    private static boolean subirFicheroASCII(FTPClient ftp, String rutaAbsolutaNombre, String nombreServidor, String carpetaDestino) throws IOException{

        ftp.changeWorkingDirectory("/Tomas");
        System.out.println("DIRECTORIO" + ftp.printWorkingDirectory());

        ftp.changeWorkingDirectory(carpetaDestino);
        System.out.println("DIRECTORIO ACTUAL" + ftp.printWorkingDirectory());

        ftp.setFileType(FTPSClient.ASCII_FILE_TYPE);

        File f = new File(rutaAbsolutaNombre);

        InputStream in = new FileInputStream(f);
        boolean upload = ftp.storeFile(nombreServidor,in);

        in.close();
        return upload;
    }

    private static void mostrarDirectorioActual(FTPClient ftp){
        try {
            System.out.println("Directorio de trabajo: " + ftp.printWorkingDirectory());

            ftp.changeWorkingDirectory("/tomas");
            System.out.println("Directorio de trabajo: " + ftp.printWorkingDirectory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<String> creacionCarpetas(FTPClient ftp){

        ArrayList<String> carpetas = new ArrayList<>();

        try {
        String username = System.getenv("FTP_USERTOMAS");

        String carpetaMd = "md" + username;
        String carpetaFotos = "fotos" + username;

        String carpetaPrueba = "prueba" + username;

        carpetas.add(carpetaMd);
        carpetas.add(carpetaFotos);

        boolean creada1 = ftp.makeDirectory(carpetaMd);
        boolean creada2 = ftp.makeDirectory(carpetaFotos);
        boolean creada3 = ftp.makeDirectory(carpetaPrueba);

        if (creada1) {
            System.out.println("Carpeta creada con éxito: " + carpetaMd);
        } else {
            System.out.println("No se pudo crear la carpeta: " + carpetaMd + " (quizás ya existe o falta permiso)");
        }

        if (creada2) {
            System.out.println("Carpeta creada con éxito: " + carpetaFotos);
        } else {
            System.out.println("No se pudo crear la carpeta: " + carpetaFotos);
        }

        if (creada3) {
            System.out.println("Carpeta de prueba (PUNTO 10) creada con exito: " + carpetaPrueba);
        }else{
            System.out.println("No se pudo crear la carpeta: " + carpetaPrueba);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

        return carpetas;
    }

    private static FTPClient conexion(){
        try {

            String server = System.getenv("FTP_SERVER");
            int port = Integer.parseInt(System.getenv("FTP_PORT"));
            String username = System.getenv("FTP_USERTOMAS"); //Esto es lo que cambiará
            String password = System.getenv("FTP_PASSWORD");

            FTPClient ftp = new FTPClient();

            ftp.connect(server,port);
            int codigo = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(codigo)) {
                ftp.disconnect();
            }

            boolean login = ftp.login(username,password);
            System.out.println("Se ha conectado el usuario " + username + ": " + login);

            if (!login) {
                ftp.enterLocalPassiveMode();
                ftp.disconnect();
                return null;
            }

            return  ftp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
