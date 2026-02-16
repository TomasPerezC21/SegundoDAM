package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "clases_dirigidas", schema = "gimnasio_jpa")
@PrimaryKeyJoinColumn(name = "id")
public class ClasesDirigida extends Actividade {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id", nullable = false)
    private Actividade actividades;

    @Column(name = "monitor", nullable = false, length = 120)
    private String monitor;

    @Column(name = "duracion_min", nullable = false)
    private Integer duracionMin;

    public ClasesDirigida() {

    }

    public ClasesDirigida(LocalDate fecha, String nombre, String monitor, Integer duracionMin) {
        super(fecha, nombre);
        this.monitor = monitor;
        this.duracionMin = duracionMin;
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

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public Integer getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }

}