public class main {

    public static void main(String[] args) {

        EjemploConcurrente o1 = new EjemploConcurrente("Guillermo");
        EjemploConcurrente o2 = new EjemploConcurrente("Antonio");

        o1.start();
        o2.start();

    }


}
