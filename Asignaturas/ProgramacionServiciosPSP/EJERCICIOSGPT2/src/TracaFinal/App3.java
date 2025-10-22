package TracaFinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce nombre fichero: ");
        String fichero = sc.nextLine();


        HashMap<String,Integer> notasAlumnos = new HashMap<>();

        String alumno;
        int nota;

        while(true){
            System.out.println("Introduce el nombre del alumno: ");
            alumno = sc.nextLine();

            if(alumno.equalsIgnoreCase("fin")){
                break;
            }
            System.out.println("Introduce la nota del alumno: ");
            nota = sc.nextInt();
            sc.nextLine();

            notasAlumnos.put(alumno,nota);
        }

//        for (HashMap.Entry<String, Integer> entry : notasAlumnos.entrySet()) {
//            System.out.println("Alumno: " + entry.getKey() + ", Nota: " + entry.getValue());
//        }

        ArrayList<Integer> notas = new ArrayList<>();
        ArrayList<String> alumnos = new ArrayList<>();
        for (HashMap.Entry<String, Integer> entry : notasAlumnos.entrySet()) {
                notas.add(entry.getValue());
                alumnos.add(entry.getKey());
        }

//        for(int i=0;i<notas.size();i++){
//            System.out.println(notas.get(i));
//            System.out.println(alumnos.get(i));
//        }

        StringBuilder alumnosString = new StringBuilder();
        for(int i=0;i<alumnos.size();i++){
            alumnosString.append(alumnos.get(i)).append(" ");
        }
        alumnosString.deleteCharAt(alumnosString.length()-1);

        String rutaApp1 = "src/TracaFinal/App1.java";

        StringBuilder notasString = new StringBuilder();
        for(int i=0;i<notas.size();i++){
            notasString.append(notas.get(i)).append(" ");
        }
        notasString.deleteCharAt(notasString.length()-1);

        ProcessBuilder pb = new ProcessBuilder("java", rutaApp1, alumnosString.toString(),notasString.toString(), fichero);

        try {
            Process p = pb.start();
            p.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
