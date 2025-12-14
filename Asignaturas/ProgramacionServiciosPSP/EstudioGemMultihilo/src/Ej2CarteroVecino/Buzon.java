package Ej2CarteroVecino;

public class Buzon {

    private boolean buzonLleno;
    private String mensaje;

    public Buzon() {
        this.buzonLleno = false;
    }

    public synchronized void depositar(String carta) throws InterruptedException {

        while(buzonLleno){
            wait();
        }

        this.mensaje = carta;
        System.out.println("Buzon lleno.");
        this.buzonLleno = true;
        notifyAll();
    }

    public synchronized void recoger() throws InterruptedException {

        while(!buzonLleno){
            wait();
        }

        System.out.println("Vecino lee su carta: " + this.mensaje);
        this.buzonLleno = false;
        notifyAll();
    }

}
