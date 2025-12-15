package Ej1FabricaVehiculos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Concesionario {


    private int cantMaxCoches = 3;

    private int cantMaxMotos = 6;
    private ConcurrentLinkedDeque<Coche> coches;
    private ConcurrentLinkedDeque<Moto> motos;

    public Concesionario(){
        this.coches = new ConcurrentLinkedDeque<>();
        this.motos = new ConcurrentLinkedDeque<>();
    }

    public synchronized void addCoches(Coche coche){

        //si no hay hueco se lanza mensaje de error.
        while (coches.size() == cantMaxCoches){

            System.out.println("No caben más coches en la tienda. Hay " + coches.size() + " disponibles." );
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        //Si hay hueco se añade otro coche a la lista y se muestra por pantalla
        coches.add(coche);
        System.out.println("Hay disponible un nuevo coche: " + coche);

        //MUY IMPORTANTE
        notifyAll();
    }

    //lo mismo pero para las motos
    public synchronized void addMotos(Moto moto){
        while (motos.size() == cantMaxMotos){
            System.out.println("No caben más motos en la tienda. Hay " + motos.size() + " disponibles." );
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        motos.add(moto);
        System.out.println("Hay disponible una nueva moto: " + moto);

        //MUY IMPORTANTE AVISAR DE QUE HAY STOCK!
        notifyAll();
    }

    public synchronized void entregarCoches(){

        while (coches.isEmpty()){

            System.out.println("No hay coches disponibles. Cliente debe esperar");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        System.out.println("El cliente se lleva el coche: " + coches.getFirst());
        coches.removeFirst();

        //MUY IMPORTANTE AVISAR
        notifyAll();

    }

    public synchronized void entregarMotos(){
        while (motos.isEmpty()){
            System.out.println("No hay motos disponibles. Cliente debe esperar");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("El cliente se lleva la moto: " + motos.getFirst());
        motos.removeFirst();
        //MUY IMPORTANTE
        notifyAll();

    }


}
