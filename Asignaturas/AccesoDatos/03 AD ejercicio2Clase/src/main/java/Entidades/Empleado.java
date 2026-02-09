package Entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodEmp", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CodDep")
    private Departamento codDep;

    @Column(name = "ExTelEmp", length = 9)
    private String exTelEmp;

    @Column(name = "FecInEmp")
    private LocalDate fecInEmp;

    @Column(name = "FecNaEmp")
    private LocalDate fecNaEmp;

    @Column(name = "NifEmp", length = 9)
    private String nifEmp;

    @Column(name = "NomEmp", length = 40)
    private String nomEmp;

    @Column(name = "NumHi")
    private Integer numHi;

    @Column(name = "SalEmp", precision = 12, scale = 2)
    private BigDecimal salEmp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Departamento getCodDep() {
        return codDep;
    }

    public void setCodDep(Departamento codDep) {
        this.codDep = codDep;
    }

    public String getExTelEmp() {
        return exTelEmp;
    }

    public void setExTelEmp(String exTelEmp) {
        this.exTelEmp = exTelEmp;
    }

    public LocalDate getFecInEmp() {
        return fecInEmp;
    }

    public void setFecInEmp(LocalDate fecInEmp) {
        this.fecInEmp = fecInEmp;
    }

    public LocalDate getFecNaEmp() {
        return fecNaEmp;
    }

    public void setFecNaEmp(LocalDate fecNaEmp) {
        this.fecNaEmp = fecNaEmp;
    }

    public String getNifEmp() {
        return nifEmp;
    }

    public void setNifEmp(String nifEmp) {
        this.nifEmp = nifEmp;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public Integer getNumHi() {
        return numHi;
    }

    public void setNumHi(Integer numHi) {
        this.numHi = numHi;
    }

    public BigDecimal getSalEmp() {
        return salEmp;
    }

    public void setSalEmp(BigDecimal salEmp) {
        this.salEmp = salEmp;
    }

}