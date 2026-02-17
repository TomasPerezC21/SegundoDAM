package servicio;

import entidades.Prestamo;
import entidades.Publicacione;
import entidades.Socio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    private EntityManagerFactory emf;

    public BibliotecaService() {
        emf = Persistence.createEntityManagerFactory("examenJPA");
    }

  public boolean insertarPrestamo(int idPublicacion, int idSocioPrestamo){

        EntityManager em = emf.createEntityManager();


        Publicacione pub = em.find(Publicacione.class, idPublicacion);
        Socio soc =  em.find(Socio.class, idSocioPrestamo);

        if (pub != null && soc != null) {
            em.getTransaction().begin();

            Prestamo p = new Prestamo(pub, soc, LocalDate.now(), null);

            em.persist(p);
            em.getTransaction().commit();
            return true;
        }else{
            return false;
        }
    }

    public List<Prestamo> obtenerPrestamos() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("PrestamosActivos");

        return query.getResultList();

    }

    public List<Prestamo> obtenerPrestamosSocio(int idSocio) {
        EntityManager em = emf.createEntityManager();

        Socio socio  = em.find(Socio.class, idSocio);

        List<Prestamo> prestamos = new ArrayList<>();

        if (socio != null) {

            Query q = em.createQuery("Select p from Prestamo p where p.socio = :socio");
            q.setParameter("socio", socio);
            prestamos = q.getResultList();

        }else{
            System.out.println("Socio no encontrado en la base de datos.");
        }

        return prestamos;

    }


    public boolean cerrarConexion(){
        emf.close();
        return true;
    }

}
