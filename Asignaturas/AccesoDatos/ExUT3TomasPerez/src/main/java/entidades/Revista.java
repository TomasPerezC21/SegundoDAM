package entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "revistas", schema = "biblioteca_jpa")
@PrimaryKeyJoinColumn(name = "id")
public class Revista extends Publicacione{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id", nullable = false)
    private Publicacione publicaciones;

    @Column(name = "issn", nullable = false, length = 20)
    private String issn;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Revista() {
    }

    public Revista(String titulo, LocalDate fechaPublicacion, String issn, Integer numero) {
        super(titulo, fechaPublicacion);
        this.issn = issn;
        this.numero = numero;
    }

    public Publicacione getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(Publicacione publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}