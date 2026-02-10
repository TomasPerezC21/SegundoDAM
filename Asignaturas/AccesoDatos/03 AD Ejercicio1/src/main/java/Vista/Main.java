package Vista;

import Entidades.Empleado;
import Servicio.EmpleadoService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EmpleadoService logica = new EmpleadoService();
        Empleado empleado = new Empleado("Tomás Pérez", 500L, "Informática");
        //ejercicio1(logica, empleado);
//        ejercicio2(logica, 45);
//        ejercicio3(logica,21,500);
//        ejercicio4(logica, 870);
        ejercicio5(logica);
        ejercicio6(logica, "Tomás Pérez");
    }

    public static void ejercicio1(EmpleadoService es, Empleado e) {
       if(es.insertarEmpleado(e)){
            System.out.printf("Empleado insertado correctamente\n");
        }
    }

    public static void ejercicio2(EmpleadoService es, int idEmpleado) {

        Empleado em = es.getEmpleadoById(idEmpleado);
        if(em != null){
            System.out.printf("Empleado encontrado correctamente: \n");
            System.out.printf(em.toString());
        }else{
            System.out.println("No existe el empleado con el id: "+idEmpleado);
        }

    }

    public static void ejercicio3(EmpleadoService es, int idEmpleado, long salarioAumento) {
        if(es.modificarSalario(idEmpleado, salarioAumento)){
            System.out.println("Salario modificado correctamente\n");
        }else {
            System.out.println("Error al modificar salario.\n");
        }

    }

    public static void ejercicio4(EmpleadoService es, long salario) {
        List<Empleado> listaEmpleados = es.obtenerEmpleadosSalarioSuperior(salario);

        if(!listaEmpleados.isEmpty()){
            System.out.println("Empleados con salario superior a " + salario +" :");
            for(Empleado empleado : listaEmpleados){
                System.out.println(empleado.toString());
            }
        }else{
            System.out.println("No hay empleados que superen el salario de :" + salario);
        }

    }

    public static void ejercicio5(EmpleadoService es){
        List<Empleado> listaEmpleados = es.obtenerEmpleadosDepartamento();
        if(!listaEmpleados.isEmpty()){
            for(Empleado empleado : listaEmpleados){
                System.out.println(empleado.toString());
            }
        }
    }

    public static void ejercicio6(EmpleadoService es, String nombreEmpleado) {

        if(es.borrarEmpleado(nombreEmpleado)){
            System.out.println("Empleado borrado correctamente.\n");
        }else{
            System.out.println("Empleado no encontrado.\n");
        }


    }
}
