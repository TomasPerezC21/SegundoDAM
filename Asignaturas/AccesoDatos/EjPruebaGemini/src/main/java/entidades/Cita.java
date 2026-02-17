package entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "citas", schema = "clinica_vet")
@NamedQuery(name = "Citas.Costosas", query = "Select c from Cita c where c.coste >:minimo")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @Column(name = "fecha_cita")
    private Instant fechaCita;

    @Column(name = "coste", precision = 10, scale = 2)
    private BigDecimal coste;

    @Column(name = "diagnostico", columnDefinition = "TEXT")
    private String diagnostico;

    public Cita() {
    }

    public Cita(Mascota mascota, Instant fechaCita, BigDecimal coste, String diagnostico) {
        this.mascota = mascota;
        this.fechaCita = fechaCita;
        this.coste = coste;
        this.diagnostico = diagnostico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Instant getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Instant fechaCita) {
        this.fechaCita = fechaCita;
    }

    public BigDecimal getCoste() {
        return coste;
    }

    public void setCoste(BigDecimal coste) {
        this.coste = coste;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }


    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", mascota=" + mascota +
                ", fechaCita=" + fechaCita +
                ", coste=" + coste +
                ", diagnostico='" + diagnostico + '\'' +
                '}';
    }
}