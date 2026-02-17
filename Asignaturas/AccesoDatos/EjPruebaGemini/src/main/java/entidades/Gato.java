package entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "gatos", schema = "clinica_vet")
@PrimaryKeyJoinColumn(name = "id")
public class Gato extends Mascota {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Mascota mascotas;

    @Column(name = "es_casero")
    private Boolean esCasero;

    public Gato() {
    }

    public Gato(String nombre, LocalDate fechaNacimiento, Boolean esCasero) {
        super(nombre, fechaNacimiento);
        this.esCasero = esCasero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mascota getMascotas() {
        return mascotas;
    }

    public void setMascotas(Mascota mascotas) {
        this.mascotas = mascotas;
    }

    public Boolean getEsCasero() {
        return esCasero;
    }

    public void setEsCasero(Boolean esCasero) {
        this.esCasero = esCasero;
    }

}