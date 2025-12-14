package Ej1PuenteSoloCarril;

public class Coche implements Runnable {

    private String nombreCoche;
    private Puente puente;

    public Coche(String nombreCoche, Puente puente){
        this.nombreCoche = nombreCoche;
        this.puente = puente;
    }

    @Override
    public void run() {
        puente.cruzar(nombreCoche);
    }
}
