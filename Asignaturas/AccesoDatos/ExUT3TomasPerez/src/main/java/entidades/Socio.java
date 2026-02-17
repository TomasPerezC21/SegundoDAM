package entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "socios", schema = "biblioteca_jpa")
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 12)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "email", nullable = false, length = 180)
    private String email;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Socio() {
    }

    public Socio(String dni, String nombre, String email, LocalDate fechaAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.fechaAlta = fechaAlta;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}