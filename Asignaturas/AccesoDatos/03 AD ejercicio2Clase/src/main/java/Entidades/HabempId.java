package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HabempId implements Serializable {
    private static final long serialVersionUID = -5550092451299359475L;
    @Column(name = "CodEmp", nullable = false)
    private Integer codEmp;

    @Column(name = "CodHab", nullable = false, length = 5)
    private String codHab;

    public Integer getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(Integer codEmp) {
        this.codEmp = codEmp;
    }

    public String getCodHab() {
        return codHab;
    }

    public void setCodHab(String codHab) {
        this.codHab = codHab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HabempId entity = (HabempId) o;
        return Objects.equals(this.codEmp, entity.codEmp) &&
                Objects.equals(this.codHab, entity.codHab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEmp, codHab);
    }

}