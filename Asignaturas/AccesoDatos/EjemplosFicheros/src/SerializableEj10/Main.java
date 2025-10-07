package SerializableEj10;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        //ejemplo9();
        ejemplo11();
    }

    public static void ejemplo9(){

        try {
            FileOutputStream fos = new FileOutputStream("productos.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Producto p1 = new Producto("producto1", 10);
            oos.writeObject(p1);
            Producto p2 = new Producto("producto2", 77);
            oos.writeObject(p2);
            Producto p3 = new Producto("producto3", 80);
            oos.writeObject(p3);

            oos.close();
            fos.close();

        } catch (IOException e) {
            System.out.println("Error de E/S a fichario binario " + e.getMessage());;
        }

    }

    public static void ejemplo11(){
        try {
            FileInputStream fis = new FileInputStream("productos.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Producto p1 = (Producto) ois.readObject();

            while(p1!=null){
                System.out.println(p1.getNombre());
            }

        } catch (IOException e) {
            System.out.println("Error de E/S a fichario binario " + e.getMessage());;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
