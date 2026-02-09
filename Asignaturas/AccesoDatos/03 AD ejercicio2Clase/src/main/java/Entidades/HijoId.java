package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HijoId implements Serializable {
    private static final long serialVersionUID = -9123441695923356195L;
    @Column(name = "CodEmp", nullable = false)
    private Integer codEmp;

    @Column(name = "NumHij", nullable = false)
    private Integer numHij;

    public Integer getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(Integer codEmp) {
        this.codEmp = codEmp;
    }

    public Integer getNumHij() {
        return numHij;
    }

    public void setNumHij(Integer numHij) {
        this.numHij = numHij;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HijoId entity = (HijoId) o;
        return Objects.equals(this.codEmp, entity.codEmp) &&
                Objects.equals(this.numHij, entity.numHij);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEmp, numHij);
    }

}