package br.com.meta3.java.scaffold.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

/**
 * Domain entity representing a user.
 */
@Entity
@Table(name = "usuarios") // TODO: (REVIEW) verify table name conventions / pluralization in DB schema
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    // Default constructor required by JPA
    public Usuario() {
    }

    public Usuario(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    // No setter for id; it's auto-generated
    // public void setId(Long id) { this.id = id; }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // TODO: (REVIEW) Add equals, hashCode, toString as needed

}
