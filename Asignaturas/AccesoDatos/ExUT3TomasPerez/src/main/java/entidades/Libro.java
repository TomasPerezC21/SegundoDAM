package entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "libros", schema = "biblioteca_jpa")
@PrimaryKeyJoinColumn(name = "id")
public class Libro extends Publicacione {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id", nullable = false)
    private Publicacione publicaciones;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "num_paginas", nullable = false)
    private Integer numPaginas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libro() {
    }

    public Libro(String titulo, LocalDate fechaPublicacion, String isbn, Integer numPaginas) {
        super(titulo, fechaPublicacion);
        this.isbn = isbn;
        this.numPaginas = numPaginas;
    }

    public Publicacione getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(Publicacione publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

}