package entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "actividades", schema = "gimnasio_jpa")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Actividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @OneToMany(mappedBy = "actividad")
    private List<Reserva> reservas = new ArrayList<>();

    public Actividade(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }


    public Actividade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<entidades.Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<entidades.Reserva> reservas) {
        this.reservas = reservas;
    }

}