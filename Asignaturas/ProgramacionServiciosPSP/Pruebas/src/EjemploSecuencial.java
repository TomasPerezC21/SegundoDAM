public class EjemploSecuencial {

    public static void main(String[] args) {

        System.out.println("Inicio del programa");

        for (int i = 1; i <= 5; i++) {

            System.out.println("\tNúmero " + i);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Fin del programa");



    }



}
