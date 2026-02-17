package servicio;

import entidades.Cita;
import entidades.Mascota;
import entidades.Perro;
import jakarta.persistence.*;

import java.util.List;

public class ClinicaService {

    private EntityManagerFactory emf;

    public ClinicaService(){
        this.emf = Persistence.createEntityManagerFactory("pruebaGemini");
    }

    public double totalInvertidoPorDueño(int idDuenio){

        EntityManager em = emf.createEntityManager();

        TypedQuery<java.math.BigDecimal> q = em.createQuery(
                "SELECT coalesce(sum(c.coste), 0.0) " +
                        "FROM Cita c " +
                        "WHERE c.mascota.dueño.id = :idDuen", java.math.BigDecimal.class);

        q.setParameter("idDuen", idDuenio);

        java.math.BigDecimal resultado = q.getSingleResult();

        em.close();

        return resultado.doubleValue();
    }

    public List<Perro> listarSoloPerrosDeRaza(String raza){
        EntityManager em = emf.createEntityManager();

       Query q = em.createQuery("Select m from Mascota m where treat(m as Perro).raza = :razaParametro");

       q.setParameter("razaParametro", raza);

       List<Perro> mascotas = q.getResultList();

       em.close();

       return mascotas;

    }

    public Cita obtenerCitaConMascota(int idCita){
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("Select c from Cita c join fetch c.mascota where c.id = :idCita");
        q.setParameter("idCita", idCita);
        Cita c = (Cita) q.getSingleResult();
        em.close();
        return c;

    }




}
