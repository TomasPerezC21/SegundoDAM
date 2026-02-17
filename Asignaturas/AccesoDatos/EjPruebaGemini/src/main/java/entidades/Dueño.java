package entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`dueños`", schema = "clinica_vet")
public class Dueño {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @OneToMany(mappedBy = "dueño")
    private List<Mascota> mascotas = new ArrayList<>();

    public Dueño() {
    }

    public Dueño(String telefono, String nombre, String dni) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.dni = dni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

}