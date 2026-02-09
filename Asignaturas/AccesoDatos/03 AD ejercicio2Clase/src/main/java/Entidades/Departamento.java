package Entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "CodDep", nullable = false, length = 5)
    private String codDep;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodEmpDir", nullable = false)
    private Empleado codEmpDir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CodDepDep")
    private Departamento codDepDep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CodCen")
    private Centro codCen;

    @Column(name = "NomDep", nullable = false, length = 40)
    private String nomDep;

    @Column(name = "PreAnu", precision = 12, scale = 2)
    private BigDecimal preAnu;

    @Lob
    @Column(name = "TiDir")
    private String tiDir;

    public String getCodDep() {
        return codDep;
    }

    public void setCodDep(String codDep) {
        this.codDep = codDep;
    }

    public Empleado getCodEmpDir() {
        return codEmpDir;
    }

    public void setCodEmpDir(Empleado codEmpDir) {
        this.codEmpDir = codEmpDir;
    }

    public Departamento getCodDepDep() {
        return codDepDep;
    }

    public void setCodDepDep(Departamento codDepDep) {
        this.codDepDep = codDepDep;
    }

    public Centro getCodCen() {
        return codCen;
    }

    public void setCodCen(Centro codCen) {
        this.codCen = codCen;
    }

    public String getNomDep() {
        return nomDep;
    }

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }

    public BigDecimal getPreAnu() {
        return preAnu;
    }

    public void setPreAnu(BigDecimal preAnu) {
        this.preAnu = preAnu;
    }

    public String getTiDir() {
        return tiDir;
    }

    public void setTiDir(String tiDir) {
        this.tiDir = tiDir;
    }

}