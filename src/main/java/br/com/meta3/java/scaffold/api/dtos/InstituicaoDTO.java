filetype:java
package br.com.meta3.java.scaffold.api.dtos;

import br.com.meta3.java.scaffold.domain.entities.Instituicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for transferring institution data in API responses.
 */
public class InstituicaoDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String estado;

    @NotBlank
    private String municipio;

    // Default constructor for serialization/deserialization
    public InstituicaoDTO() {
    }

    /**
     * Constructs an InstituicaoDTO.
     *
     * @param id        the unique identifier of the institution
     * @param nome      the name of the institution
     * @param estado    the state where the institution is located
     * @param municipio the municipality where the institution is located
     */
    public InstituicaoDTO(Long id, String nome, String estado, String municipio) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.municipio = municipio;
    }

    /**
     * Factory method to create DTO from entity.
     *
     * @param instituicao the domain entity
     * @return corresponding DTO or null if entity is null
     */
    public static InstituicaoDTO fromEntity(Instituicao instituicao) {
        if (instituicao == null) {
            return null;
        }
        return new InstituicaoDTO(
            instituicao.getId(),
            instituicao.getNome(),
            instituicao.getEstado(),
            instituicao.getMunicipio()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "InstituicaoDTO{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", estado='" + estado + '\'' +
            ", municipio='" + municipio + '\'' +
            '}';
    }

    // TODO: (REVIEW) If used in request payloads, consider splitting into separate Request/Response DTOs
}