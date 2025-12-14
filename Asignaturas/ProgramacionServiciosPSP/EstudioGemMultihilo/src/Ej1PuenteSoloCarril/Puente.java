package Ej1PuenteSoloCarril;

public class Puente {

    public synchronized void cruzar(String nombreCoche){

        System.out.println(nombreCoche + " esta cruzando...");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(nombreCoche + " acaba de cruzar el puente.");

    }



}
