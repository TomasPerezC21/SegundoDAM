package front;

import logica.Electronico;
import logica.Inventario;
import logica.Ropa;
import logica.ProductoNoInventarioException;

import java.util.Arrays;
import java.util.Scanner;

public class AppTienda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventario inventario = new Inventario();

        boolean seguir = true;

        do {
            System.out.println("\n====== BIENVENIDO ======");
            System.out.println("1. Añadir productos al inventario.");
            System.out.println("2. Vender productos.");
            System.out.println("3. Reponer productos.");
            System.out.println("4. Mostrar productos y valor total del inventario.");
            System.out.println("5. Salir");

            int opcion;
            while (true) {
                System.out.print("Elige una opción (1-5): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); // consumir salto
                    if (opcion >= 1 && opcion <= 5) break;
                } else {
                    sc.nextLine();
                }
                System.out.println("Opción inválida.");
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("Tipo de producto: ");
                    System.out.println("1. Electrónico.");
                    System.out.println("2. Ropa.");

                    int tipo;
                    while (true) {
                        System.out.print("Selecciona (1-2): ");
                        if (sc.hasNextInt()) {
                            tipo = sc.nextInt();
                            sc.nextLine();
                            if (tipo == 1 || tipo == 2) break;
                        } else {
                            sc.nextLine();
                        }
                        System.out.println("Valor inválido.");
                    }

                    System.out.println("IDs ocupados:");
                    int[] ids = inventario.getIds();
                    System.out.println(Arrays.toString(ids));


                    int id;
                    while (true) {
                        System.out.print("Introduzca un ID disponible (entero positivo): ");
                        if (!sc.hasNextInt()) {
                            sc.nextLine();
                            System.out.println("Debe ser un número entero.");
                            continue;
                        }
                        id = sc.nextInt();
                        sc.nextLine();
                        if (id <= 0) {
                            System.out.println("El ID debe ser positivo.");
                            continue;
                        }
                        boolean ocupado = false;
                        for (int j : ids) {
                            if (j == id) {
                                ocupado = true;
                                break;
                            }
                        }
                        if (ocupado) {
                            System.out.println("Ese ID ya está ocupado. Vuelva a intentarlo.");
                        } else {
                            break;
                        }
                    }


                    String nombre;
                    while (true) {
                        System.out.print("Nombre del producto: ");
                        nombre = sc.nextLine();
                        if (nombre.trim().isEmpty()) {
                            System.out.println("El nombre no puede estar vacío.");
                            continue;
                        }
                        if (nombre.matches("\\d+")) {
                            System.out.println("El nombre no puede ser solo números.");
                            continue;
                        }
                        break;
                    }


                    double precio;
                    while (true) {
                        System.out.print("Precio del producto (> 0): ");
                        if (sc.hasNextDouble()) {
                            precio = sc.nextDouble();
                            sc.nextLine();
                            if (precio > 0) break;
                        } else {
                            sc.nextLine();
                        }
                        System.out.println("Precio inválido.");
                    }

                    int cantidad;
                    while (true) {
                        System.out.print("Cantidad del producto (>= 0): ");
                        if (sc.hasNextInt()) {
                            cantidad = sc.nextInt();
                            sc.nextLine();
                            if (cantidad >= 0) break;
                        } else {
                            sc.nextLine();
                        }
                        System.out.println("Cantidad inválida.");
                    }

                    if (tipo == 1) {
                        System.out.print("Marca del producto: ");
                        String marca = sc.nextLine();

                        int garantia;
                        while (true) {
                            System.out.print("Garantía (meses, >= 0): ");
                            if (sc.hasNextInt()) {
                                garantia = sc.nextInt();
                                sc.nextLine();
                                if (garantia >= 0) break;
                            } else {
                                sc.nextLine();
                            }
                            System.out.println("Garantía inválida.");
                        }

                        Electronico e = new Electronico(id, nombre, precio, cantidad, marca, garantia);
                        inventario.insertarProducto(e);
                        System.out.println("Producto electrónico añadido correctamente.");
                    } else {
                        System.out.print("Talla: ");
                        String talla = sc.nextLine();
                        System.out.print("Material: ");
                        String material = sc.nextLine();

                        Ropa r = new Ropa(id, nombre, precio, cantidad, talla, material);
                        inventario.insertarProducto(r);
                        System.out.println("Ropa añadida correctamente.");
                    }
                }

                case 2 -> {
                    System.out.println("\n--- Vender producto ---");
                    System.out.print("ID del producto: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Cantidad a vender: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    try {
                        inventario.venderProducto(id, cantidad);
                    } catch (ProductoNoInventarioException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 3 -> {
                    System.out.println("\n--- Reponer producto ---");
                    System.out.print("ID del producto: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Cantidad a añadir: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    try {
                        inventario.reponerProducto(id, cantidad);
                    } catch (ProductoNoInventarioException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.println("=== Inventario ===");
                    System.out.println(inventario.mostrarInventario());
                    System.out.println(" === VALOR TOTAL ===");
                    System.out.println(inventario.calcularValorTotal());
                }

                case 5 -> {
                    seguir = false;
                    System.out.println("¡Hasta luego!");
                }
            }

        } while (seguir);
    }
}
