package Servicio;

import Entidades.Empleado;
import jakarta.persistence.*;

import java.util.List;

public class EmpleadoService {

    private EntityManagerFactory emf;

    public EmpleadoService(){
        emf = Persistence.createEntityManagerFactory("empresaPU");
    }


    public boolean insertarEmpleado(Empleado e){

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException ex){
            em.close();
            return false;
        }
    }

    public Empleado getEmpleadoById(int id){
        EntityManager em = emf.createEntityManager();
        Empleado empleado = em.find(Empleado.class, id);
        em.close();
        return empleado;
    }

    public boolean modificarSalario(int id, long salarioAumentar){
        EntityManager em = emf.createEntityManager();

        Empleado empleado = em.find(Empleado.class, id);
        if (empleado != null){
            em.getTransaction().begin();
            empleado.setSalario(empleado.getSalario()+salarioAumentar);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        return false;
    }

    public List<Empleado> obtenerEmpleadosSalarioSuperior(long salarioSuperior){
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select e from Empleado e where salario>:salarioP", Empleado.class);
        q.setParameter("salarioP", salarioSuperior);
        List<Empleado> empleados = q.getResultList();
        em.close();
        return empleados;

    }

    public List<Empleado> obtenerEmpleadosDepartamento(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("empleado.ventas", Empleado.class);
        List<Empleado> listaEmpleados = q.getResultList();
        em.close();
        return listaEmpleados;
    }

    public boolean borrarEmpleado(String nombre){
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select e from Empleado e where e.nombre = :parametro", Empleado.class);
        q.setParameter("parametro", nombre);

        List<Empleado> listaEmpleados = q.getResultList();

        if (listaEmpleados.isEmpty()){
            em.close();
            return false;
        }else{
            em.getTransaction().begin();

            for (Empleado empleado : listaEmpleados) {
                em.remove(empleado);
            }
            em.getTransaction().commit();
            em.close();
            return true;
        }

    }

}
