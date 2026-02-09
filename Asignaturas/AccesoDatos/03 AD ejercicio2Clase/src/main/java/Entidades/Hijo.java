package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "hijo")
public class Hijo {
    @EmbeddedId
    private HijoId id;

    @MapsId("codEmp")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CodEmp", nullable = false)
    private Empleado codEmp;

    @Column(name = "FecNaHi")
    private LocalDate fecNaHi;

    @Column(name = "NomHi", length = 40)
    private String nomHi;

    public HijoId getId() {
        return id;
    }

    public void setId(HijoId id) {
        this.id = id;
    }

    public Empleado getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(Empleado codEmp) {
        this.codEmp = codEmp;
    }

    public LocalDate getFecNaHi() {
        return fecNaHi;
    }

    public void setFecNaHi(LocalDate fecNaHi) {
        this.fecNaHi = fecNaHi;
    }

    public String getNomHi() {
        return nomHi;
    }

    public void setNomHi(String nomHi) {
        this.nomHi = nomHi;
    }

}