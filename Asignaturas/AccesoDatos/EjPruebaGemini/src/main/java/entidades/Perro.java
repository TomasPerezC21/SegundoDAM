package entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "perros", schema = "clinica_vet")
@PrimaryKeyJoinColumn(name = "id")
public class Perro extends Mascota {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Mascota mascotas;

    @Column(name = "raza", length = 50)
    private String raza;

    @Column(name = "chip_id", length = 20)
    private String chipId;

    public Perro() {
    }

    public Perro(String nombre, LocalDate fechaNacimiento, String raza, String chipId) {
        super(nombre, fechaNacimiento);
        this.raza = raza;
        this.chipId = chipId;
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

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

}