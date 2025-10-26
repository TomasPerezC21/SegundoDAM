package Ejercicio2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cuántos usuarios quieres dar de alta: ");
        int numUsuarios =  sc.nextInt();
        sc.nextLine();

        ArrayList<String> usuarios= new ArrayList();

        String nick;
        String fechaNac;
        String contra;

        for (int i = 0; i < numUsuarios; i++) {
            System.out.println("Introduce el nick: ");
            nick = sc.nextLine();
            System.out.println("Introduce la fecha de nacimiento: (dd/MM/yyyy): ) ");
            fechaNac = sc.nextLine();
            System.out.println("Introduce la contraseña: (8 caracteres con letras y numeros) )");
            contra = sc.nextLine();
            usuarios.add(nick + " " + fechaNac + " " + contra);
        }

        String rutaApp2 = "src/Ejercicio2/App2.java";

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp2);
        //le paso cada usuario a app2
        for (int i = 0; i < numUsuarios; i++) {

            try {
                Process p = pb.start();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                bw.write(usuarios.get(i));
                bw.flush();
                bw.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        pb.inheritIO();
//        try {
//            Process p2 = pb.start();
//            BufferedReader br = new BufferedReader(new InputStreamReader(p2.getInputStream()));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }


}
