filetype:java
package br.com.meta3.java.scaffold.api.dtos;

import br.com.meta3.java.scaffold.domain.entities.ArquivoRecadastramentoEstado;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for transferring legacy ArquivoRecadastramentoEstado entries
 * containing codigosec, nome, cnpj, and bairro fields.
 */
public class ArquivoRecadastramentoEstadoDTO {

    @NotBlank
    private String codigosec;

    @NotBlank
    private String nome;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String bairro;

    // Default constructor for serialization/deserialization
    public ArquivoRecadastramentoEstadoDTO() {
    }

    /**
     * Constructs an ArquivoRecadastramentoEstadoDTO.
     *
     * @param codigosec the code of the institution or municipality
     * @param nome      the name of the institution
     * @param cnpj      the CNPJ (CGC) of the institution
     * @param bairro    the neighborhood of the institution
     */
    public ArquivoRecadastramentoEstadoDTO(String codigosec, String nome, String cnpj, String bairro) {
        this.codigosec = codigosec;
        this.nome = nome;
        this.cnpj = cnpj;
        this.bairro = bairro;
    }

    /**
     * Factory method to create DTO from domain entity.
     *
     * @param entity the ArquivoRecadastramentoEstado domain entity
     * @return corresponding DTO or null if entity is null
     */
    public static ArquivoRecadastramentoEstadoDTO fromEntity(ArquivoRecadastramentoEstado entity) {
        if (entity == null) {
            return null;
        }
        // TODO: (REVIEW) Ensure that entity.getCnpj() matches legacy "cgc" column mapping
        return new ArquivoRecadastramentoEstadoDTO(
            entity.getCodigosec(),
            entity.getNome(),
            entity.getCnpj(),
            entity.getBairro()
        );
    }

    public String getCodigosec() {
        return codigosec;
    }

    public void setCodigosec(String codigosec) {
        this.codigosec = codigosec;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "ArquivoRecadastramentoEstadoDTO{" +
            "codigosec='" + codigosec + '\'' +
            ", nome='" + nome + '\'' +
            ", cnpj='" + cnpj + '\'' +
            ", bairro='" + bairro + '\'' +
            '}';
    }
}