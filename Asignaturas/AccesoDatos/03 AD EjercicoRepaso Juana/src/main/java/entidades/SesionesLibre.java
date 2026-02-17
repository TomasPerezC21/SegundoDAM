package entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "sesiones_libres", schema = "gimnasio_jpa")
@PrimaryKeyJoinColumn(name = "id")
public class SesionesLibre extends Actividade{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id", nullable = false)
    private Actividade actividades;

    @Column(name = "zona", nullable = false, length = 100)
    private String zona;

    @Column(name = "aforo_max", nullable = false)
    private Integer aforoMax;

    public SesionesLibre(String nombre, LocalDate fecha, String zona, Integer aforoMax) {
        super(nombre, fecha);
        this.zona = zona;
        this.aforoMax = aforoMax;
    }

    public SesionesLibre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Actividade getActividades() {
        return actividades;
    }

    public void setActividades(Actividade actividades) {
        this.actividades = actividades;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Integer getAforoMax() {
        return aforoMax;
    }

    public void setAforoMax(Integer aforoMax) {
        this.aforoMax = aforoMax;
    }

}