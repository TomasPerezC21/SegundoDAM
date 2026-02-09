package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "centro")
public class Centro {
    @Id
    @Column(name = "CodCen", nullable = false, length = 4)
    private String codCen;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodEmpDir", nullable = false)
    private Empleado codEmpDir;

    @Column(name = "NomCen", nullable = false, length = 30)
    private String nomCen;

    @Column(name = "DirCen", length = 50)
    private String dirCen;

    @Column(name = "PobCen", length = 15)
    private String pobCen;

    public String getCodCen() {
        return codCen;
    }

    public void setCodCen(String codCen) {
        this.codCen = codCen;
    }

    public Empleado getCodEmpDir() {
        return codEmpDir;
    }

    public void setCodEmpDir(Empleado codEmpDir) {
        this.codEmpDir = codEmpDir;
    }

    public String getNomCen() {
        return nomCen;
    }

    public void setNomCen(String nomCen) {
        this.nomCen = nomCen;
    }

    public String getDirCen() {
        return dirCen;
    }

    public void setDirCen(String dirCen) {
        this.dirCen = dirCen;
    }

    public String getPobCen() {
        return pobCen;
    }

    public void setPobCen(String pobCen) {
        this.pobCen = pobCen;
    }

}