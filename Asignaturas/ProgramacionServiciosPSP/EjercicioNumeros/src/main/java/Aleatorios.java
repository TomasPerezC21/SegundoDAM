public class Aleatorios {

    public static void main(String[] args) {

        int numeroMin = Integer.parseInt(args[0]);
        int numeroMax = Integer.parseInt(args[1]);

        int aleatorio = (int) (Math.floor(Math.random()*(numeroMax-numeroMin+1) + numeroMin));

        System.out.println("Aleatorio: " + aleatorio);


    }



}
