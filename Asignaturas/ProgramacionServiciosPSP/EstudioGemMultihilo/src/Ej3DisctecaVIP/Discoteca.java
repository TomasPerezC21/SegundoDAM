package Ej3DisctecaVIP;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Discoteca {


    private Semaphore semaforo;

    public Discoteca() {
        this.semaforo = new Semaphore(3,true);
    }

    public void entrar(String nombrePersona){

        try {
            System.out.println(nombrePersona + " está esperando en la puerta...");


            semaforo.acquire();

            System.out.println(nombrePersona + " ENTRA a la pista. (Huecos restantes en la disco: " + semaforo.availablePermits() + ")");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void salir(String nombrePersona) throws InterruptedException {

        System.out.println(nombrePersona + " sale de la discoteca.");
        semaforo.release();
    }


}
