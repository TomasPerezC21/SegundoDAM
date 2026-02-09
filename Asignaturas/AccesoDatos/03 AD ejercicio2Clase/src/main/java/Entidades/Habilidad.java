package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "habilidad")
public class Habilidad {
    @Id
    @Column(name = "CodHab", nullable = false, length = 5)
    private String codHab;

    @Column(name = "DesHab", length = 30)
    private String desHab;

    public String getCodHab() {
        return codHab;
    }

    public void setCodHab(String codHab) {
        this.codHab = codHab;
    }

    public String getDesHab() {
        return desHab;
    }

    public void setDesHab(String desHab) {
        this.desHab = desHab;
    }

}