public class EjemploConcurrente extends Thread {

    private String nombreHilo;

    public EjemploConcurrente(String nombreHilo) {
        this.nombreHilo = nombreHilo;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo " + nombreHilo + " - paso " + i);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        }
    }

}
