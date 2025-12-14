package Ej5RobotsFilosofos;

public class Robot implements Runnable {


    private int idRobot;
    private Object soldadorIzquierdo;
    private Object soldadorDerecho;

    public Robot(int idRobot, Object SoldadorIzquierdo, Object SoldadorDerecho) {
        this.idRobot = idRobot;
        this.soldadorIzquierdo = SoldadorIzquierdo;
        this.soldadorDerecho = SoldadorDerecho;
    }

    @Override
    public void run() {

        try {
            while (true) {
                // 1. PREPARAR LA PIEZA (Tiempo pensando/trabajando solo)
                System.out.println("Robot " + idRobot + " preparando pieza...");
                Thread.sleep((long) (Math.random() * 1000));

                // 2. DECIDIR EL ORDEN (La clave anti-deadlock)
                Object primerSoldador;
                Object segundoSoldador;

                if (idRobot % 2 == 0) {
                    // ROBOT PAR: Orden Normal
                    primerSoldador = soldadorIzquierdo;
                    segundoSoldador = soldadorDerecho;
                } else {
                    // ROBOT IMPAR: Orden Inverso (Rompe la simetría)
                    primerSoldador = soldadorDerecho;
                    segundoSoldador = soldadorIzquierdo;
                }

                // 3. INTENTAR COGER LOS SOLDADORES (Sincronización Anidada)
                synchronized (primerSoldador) {
                    System.out.println("Robot " + idRobot + " tiene el PRIMER soldador.");

                    synchronized (segundoSoldador) {
                        System.out.println("Robot " + idRobot + " tiene AMBOS. Soldando... 🔥");

                        // Tiempo soldando (Sección Crítica)
                        Thread.sleep(500);
                    }
                    // Aquí suelta el segundo automáticamente
                }
                // Aquí suelta el primero automáticamente

                System.out.println("Robot " + idRobot + " terminó y soltó las herramientas.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
