package org.example.ftp.correo;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.IOException;
import java.util.Properties;

public class EnvioMail {

    static void main(String[] args) {

        lectura();

    }

    private static void lectura() {

        Properties prop = new Properties();
        prop = datosSessionEnvio();

        Session sesion = crearSession(prop);

        try {
            Store store = sesion.getStore("imap");

            store.connect(prop.getProperty("mail.imap.host"), prop.getProperty("mail.imap.username"),
                    prop.getProperty("mail.imap.password"));

            Folder carpeta = store.getFolder("INBOX");
            carpeta.open(Folder.READ_ONLY);

            Message[] listaMailInbox = carpeta.getMessages();

            for (Message mensaje : listaMailInbox) {
                System.out.println(mensaje.getSubject());
            }



        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private static void envio(){
        Properties prop = new Properties();
        prop = datosSessionEnvio();

        Session sesion = crearSession(prop);

        try {
            Message msg = new MimeMessage(sesion);

            msg.setFrom(new InternetAddress(System.getenv("MAIL_USERNAME")));
            msg.setSubject("Prueba");


            InternetAddress[] toMail = InternetAddress.parse("tomasperezc21@gmail.com, frantimo10@gmail.com");

            msg.setRecipients(Message.RecipientType.TO, toMail);
            msg.setRecipients(Message.RecipientType.CC, toMail);
            msg.setRecipients(Message.RecipientType.BCC, toMail);

            MimeMultipart mp = new MimeMultipart();
            MimeBodyPart mbTexto = new MimeBodyPart();
            MimeBodyPart mbAdjunto = new MimeBodyPart();
            mbTexto.setText("Texto de prueba");
            mbAdjunto.attachFile("C:\\Users\\alumno\\Downloads\\TOMAS PEREZ CARRILLO - \uD83D\uDCDDTAREA Nº4_ FORMA JURÍDICA DE LA EMPRESA.pdf");

            mp.addBodyPart(mbTexto);
            mp.addBodyPart(mbAdjunto);

            msg.setContent(mp);

            Transport.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static Properties datosSessionEnvio(){

        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.username", System.getenv("MAIL_USERNAME"));
        prop.put("mail.smtp.password", System.getenv("MAIL_PASSWORD"));
        System.out.println(System.getenv("MAIL_USERNAME"));


        return prop;

    }

    private static Session crearSession(Properties prop){

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("MAIL_USERNAME"), System.getenv("MAIL_PASSWORD"));
            }
        };

        return Session.getInstance(prop, auth);

    }

    private static Properties datosSessionLectura(){

        Properties prop = new Properties();

        prop.put("mail.imap.host", "imap.gmail.com");
        prop.put("mail.imap.auth", "true");
        prop.put("mail.imap.ssl.enable", "true");
        prop.put("mail.imap.port", "993");
        prop.put("mail.imap.username", System.getenv("MAIL_USERNAME"));
        prop.put("mail.imap.password", System.getenv("MAIL_PASSWORD"));
        System.out.println(System.getenv("MAIL_USERNAME"));


        return prop;

    }


}
