package br.com.meta3.java.scaffold.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

/**
 * Domain entity representing an institution for recadastramento.
 */
@Entity
@Table(name = "instituicoes") // TODO: (REVIEW) verify table name conventions / pluralization in DB schema
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "municipio", nullable = false)
    private String municipio;

    // Default constructor required by JPA
    public Instituicao() {
    }

    /**
     * Constructor for creating an institution instance.
     *
     * @param nome      the name of the institution
     * @param estado    the state where the institution is located
     * @param municipio the municipality where the institution is located
     */
    public Instituicao(String nome, String estado, String municipio) {
        this.nome = nome;
        this.estado = estado;
        this.municipio = municipio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    // TODO: (REVIEW) Implement equals, hashCode, toString as needed for entity comparison/logging
}
