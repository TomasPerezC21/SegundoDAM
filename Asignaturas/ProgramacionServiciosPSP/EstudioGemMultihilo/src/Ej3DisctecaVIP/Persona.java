package Ej3DisctecaVIP;

public class Persona implements Runnable {

    private String nombrePersona;
    private Discoteca discoteca;

    public Persona(String nombrePersona, Discoteca discoteca) {
        this.nombrePersona = nombrePersona;
        this.discoteca = discoteca;
    }

    @Override
    public void run() {

        discoteca.entrar(nombrePersona);

        try {
            System.out.println(nombrePersona + " esta bailando en la discoteca.");
            Thread.sleep(2500L);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                discoteca.salir(nombrePersona);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
