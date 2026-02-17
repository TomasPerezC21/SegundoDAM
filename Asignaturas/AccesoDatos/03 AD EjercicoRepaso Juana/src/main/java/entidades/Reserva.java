package entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@NamedQuery(
        name="ReservasActivas",
        query = "select r from Reserva r  join fetch r.cliente join fetch r.actividad where r.fechaCancelacion is null"
)
@Table(name = "reservas", schema = "gimnasio_jpa")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividade actividad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "fecha_cancelacion")
    private LocalDate fechaCancelacion;

    public Reserva(Actividade actividad, Cliente cliente, LocalDate fechaReserva, LocalDate fechaCancelacion) {
        this.actividad = actividad;
        this.cliente = cliente;
        this.fechaReserva = fechaReserva;
        this.fechaCancelacion = fechaCancelacion;
    }

    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Actividade getActividad() {
        return actividad;
    }

    public void setActividad(Actividade actividad) {
        this.actividad = actividad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "actividad=" + actividad.getNombre() +
                ", cliente=" + cliente.getNombre() +
                ", fechaReserva=" + fechaReserva +
                ", fechaCancelacion=" + fechaCancelacion +
                '}';
    }
}