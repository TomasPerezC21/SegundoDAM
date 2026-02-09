package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "habemp")
public class Habemp {
    @EmbeddedId
    private HabempId id;

    @MapsId("codEmp")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodEmp", nullable = false)
    private Empleado codEmp;

    @MapsId("codHab")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodHab", nullable = false)
    private Habilidad codHab;

    @Column(name = "NivHab")
    private Byte nivHab;

    public HabempId getId() {
        return id;
    }

    public void setId(HabempId id) {
        this.id = id;
    }

    public Empleado getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(Empleado codEmp) {
        this.codEmp = codEmp;
    }

    public Habilidad getCodHab() {
        return codHab;
    }

    public void setCodHab(Habilidad codHab) {
        this.codHab = codHab;
    }

    public Byte getNivHab() {
        return nivHab;
    }

    public void setNivHab(Byte nivHab) {
        this.nivHab = nivHab;
    }

}