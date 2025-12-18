package Ej2Hospital;

import java.util.ArrayList;
import java.util.Random;

public class Cirujia implements Runnable {
    Random random = new Random();

    private int idCirugia;
    private String tipoCirugia;
    private int duracionQuirofano;
    private int duracionEquipoMedico;
    private Object quirofano;
    private Object equipoMedico;

    private ArrayList<String> tiposCirujia;

    public Cirujia(int idCirujia, Object quirofano, Object equipoMedico) {
        this.idCirugia = idCirujia;
        this.tiposCirujia = obtenerTiposCirujia();
        this.tipoCirugia = getTipoCirujia(random);
        this.duracionQuirofano = getDuracionQuirofano(random);
        this.duracionEquipoMedico = (duracionQuirofano/2500);
        this.quirofano = quirofano;
        this.equipoMedico = equipoMedico;
    }

    @Override
    public void run() {

        try {
            while (true) {

                System.out.println("Cirugia " + idCirugia + " se está preparando...");
                Thread.sleep(duracionQuirofano);

                Object primero;
                Object segundo;

                // para evitar el bloqueo se asignan los objetos en base al id (par, impar)
                if (idCirugia % 2 == 0) {

                    primero = quirofano;
                    segundo = equipoMedico;
                } else {

                    primero = equipoMedico;
                    segundo = quirofano;
                }

                // Intentamos que la cirujia tenga ambos objetos (quiro y equipo medico)
                synchronized (primero) {
                    System.out.println("Cirugia " + idCirugia + " tiene quirofano asignado.");

                    synchronized (segundo) {
                        System.out.println("Cirugia " + idCirugia + " tiene quirofano y equipo medico asignados. Empiza la cirujia.");


                        Thread.sleep(duracionEquipoMedico);
                    }

                }


                System.out.println("Cirujia " + idCirugia + " ha terminado. El quirofano y el equipo médico están disponibles para otra cirujia.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> obtenerTiposCirujia(){
        ArrayList<String> tipos = new ArrayList<>();

        tipos.add("Apendictomías");
        tipos.add("Oftalmología");
        tipos.add("Traumatología");
        tipos.add("Ortopedia");
        return tipos;
    }

    public String getTipoCirujia(Random rand) {
        return tiposCirujia.get(rand.nextInt(tiposCirujia.size()));
    }

    public int getDuracionQuirofano(Random rand) {
        return rand.nextInt(500, 1501);
    }

}
