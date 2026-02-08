package Parte2Correo;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class ejerciciosMail {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Properties prop = datosSessionLectura();
        Session sesion = crearSession(prop);

        //ArrayList<String> datosDestino = obtenerDatos(sc);

//        try {
//            ejercicio1(datosDestino);
//        } catch (MessagingException | IOException e) {
//            throw new RuntimeException(e);
//        }


//        try {
//            ejercicio2();
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {

            Store store = sesion.getStore("imap");
            store.connect(prop.getProperty("mail.imap.host"),
                    prop.getProperty("mail.imap.username"),
                    prop.getProperty("mail.imap.password"));

            Folder carpeta = store.getFolder("INBOX");
            carpeta.open(Folder.READ_ONLY);


            Message[] listaMensajes = ejercicio3(sc, carpeta);

            //Formato para la fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            for (Message mensaje : listaMensajes) {

                if (mensaje == null) continue; //Por si hay menos de 10 mensajes que el programa no reviente

                System.out.println("Fecha: " + sdf.format(mensaje.getSentDate()));
                System.out.println("Remitente: " + Arrays.toString(mensaje.getFrom()));
                System.out.println("Asunto: " + mensaje.getSubject());

                //Las dos primeras lineas

                String contenido = obtenerTextoMensaje(mensaje);
                String[] lineas = contenido.trim().split(System.lineSeparator());//separo por espacio
                System.out.println("DOS PRIMERAS LÍNEAS DEL MENSAJE: ");

                for (int i = 0; i < 2; i++) {
                    System.out.println(lineas[i].trim());
                }
            }

            carpeta.close(false);
            store.close();

        } catch (MessagingException | IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    //INVESTIGACIÓN: Esta funciona me la ha hecho la IA porque hacia falta para extraer texto plano ya que los correos
    // modernos suelen ser multipart .
    private static String obtenerTextoMensaje(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/plain")) {
            return (String) p.getContent();
        }
        if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = obtenerTextoMensaje(mp.getBodyPart(i));
                if (s != null) return s;
            }
        }
        return "";
    }


    //Metodo que devuelve una lista de 10 mensajes con los filtros aplicados y ordenados de mas recientes a antiguos
    private static Message[] ejercicio3(Scanner sc, Folder carpeta) throws MessagingException {
        System.out.println("Introduce el asunto para filtrar: ");
        String filtroAsunto = sc.nextLine().trim().toLowerCase();

        System.out.println("Introduce el remitente para filtrar: ");
        String filtroRemitente = sc.nextLine().trim().toLowerCase();

        Message[] listaMensajes = new Message[10]; //10 mensajes (enunciado)

        int contadorMensajes = 0; //Variable para controlar los mensajes que entran a la lista

        Message[] listaCompleta = carpeta.getMessages(); //Obtengo todos los mensajes

        //Aquí se aplican los filtros y se llena la listaMensajes. Se recorre al revés para obtener los recientes
        for (int i = listaCompleta.length -1 ; i >= 0 && contadorMensajes < 10 ; i--) {

            Message msg = listaCompleta[i];
            String asuntoMsg = msg.getSubject().toLowerCase();
            String remitenteMsg = msg.getFrom()[0].toString().toLowerCase();

            if (asuntoMsg.contains(filtroAsunto) && remitenteMsg.contains(filtroRemitente)) {
                listaMensajes[contadorMensajes] = msg;
                contadorMensajes++;
            }

        }

        return listaMensajes;
    }

    private static Properties datosSessionLectura(){

        Properties prop = new Properties();

        prop.put("mail.imap.host", "imap.gmail.com");
        prop.put("mail.imap.auth", "true");
        prop.put("mail.imap.ssl.enable", "true");
        prop.put("mail.imap.port", "993");
        prop.put("mail.imap.username", System.getenv("MAIL_USER"));
        prop.put("mail.imap.password", System.getenv("MAIL_PASSWORD"));


        return prop;

    }

    private static void ejercicio2() throws MessagingException, IOException {

        Properties prop = datosServidor();
        Session session = crearSession(prop);

        MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(prop.getProperty("mail.smtp.username"))); //Mi variable de entorno

        msg.setSubject("Ejercicio 2 (HTML Y 3 ADJUNTOS)");
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("13788476@alu.murciaeduca.es"));

        //cuerpo del mensaje (html) y adjuntos
        Multipart multipart = new MimeMultipart();

        MimeBodyPart parteTexto = new MimeBodyPart();

        String textoHTML = "<h1 style='color: #2980b9;'> Buenas tardes </h1>" +
                "<p> Este es un correo de <strong> prueba </strong> enviado desde mi aplicación java </p> <br>" +
                "Un saludo, <br>" +
                "Tomás Pérez";

        parteTexto.setContent(textoHTML, "text/html; charset=utf-8");
        multipart.addBodyPart(parteTexto);

        MimeBodyPart adjunto1 = new MimeBodyPart();
        adjunto1.attachFile("src/main/resources/archivosCorreo/4. Generación de servicios en red.pdf");
        multipart.addBodyPart(adjunto1);

        MimeBodyPart adjunto2 = new MimeBodyPart();
        adjunto2.attachFile("src/main/resources/archivosCorreo/prueba.txt");
        multipart.addBodyPart(adjunto2);

        MimeBodyPart adjunto3 = new MimeBodyPart();
        adjunto3.attachFile("src/main/resources/archivosCorreo/Gemini_Generated_Image_tu8iuitu8iuitu8i-removebg-preview.png");
        multipart.addBodyPart(adjunto3);


        msg.setContent(multipart);
        Transport.send(msg);

    }

    private static void ejercicio1(ArrayList<String> datosDestino) throws MessagingException, IOException {

        //Mapeo de los datos
        String destinatario = datosDestino.get(0);
        String destinatarioOculto = datosDestino.get(1);
        String asunto  = datosDestino.get(2);
        String cuerpo = datosDestino.get(3);
        String rutaArchivo = datosDestino.get(4);


        //Obtener propiedades y sesión
        Properties prop = datosServidor();
        Session session = crearSession(prop);

        MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(prop.getProperty("mail.smtp.username"))); //Mi variable de entorno
        msg.setSubject(asunto);
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(destinatarioOculto));

        //cuerpo y posible adjunto del correo
        Multipart mp = new MimeMultipart();

        MimeBodyPart parteTexto = new MimeBodyPart();

        parteTexto.setText(cuerpo);
        mp.addBodyPart(parteTexto);

        //En el caso de que el usuario ponga ruta del archivo la tratamos. Si esta vacia la variable no hacemos nada
        if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
            MimeBodyPart parteArchivo = new MimeBodyPart();
            parteArchivo.attachFile(rutaArchivo);
            mp.addBodyPart(parteArchivo);
        }

        msg.setContent(mp);
        Transport.send(msg);

    }

    //metodo para obtener los datos en un ArrayList
    private static ArrayList<String> obtenerDatos(Scanner sc) {

        ArrayList<String> datos = new ArrayList<>();
        System.out.println("Introduce el destinatario(email):");
        datos.add(sc.nextLine());
        System.out.println("Introduce el destinatario oculto (email):");
        datos.add(sc.nextLine());
        System.out.println("Introduce el asunto del mensaje: ");
        datos.add(sc.nextLine());
        System.out.println("Introduce el cuerpo del mensaje (texto plano): ");
        datos.add(sc.nextLine());
        System.out.println("Introduce la ruta de un archivo local para adjuntar: ");
        datos.add(sc.nextLine());

        return datos;
    }

    //metodo para obtener las propiedas
    private static Properties datosServidor(){

        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.username", System.getenv("MAIL_USER"));
        prop.put("mail.smtp.password", System.getenv("MAIL_PASSWORD"));


        return prop;

    }

    //metodo para obtener la sesion
    private static Session crearSession(Properties prop){

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("MAIL_USER"),
                        System.getenv("MAIL_PASSWORD"));
            }
        };

        return Session.getInstance(prop, auth);

    }

}
