filetype:java
package br.com.meta3.java.scaffold.api.dtos;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for recadastramento response carrying the list of institutions
 * and the calculated base year (ano base) for the user.
 */
public class RecadastramentoResponseDTO {

    @NotNull
    @NotEmpty
    private List<InstituicaoDTO> instituicoes;

    @NotNull
    private Integer anoBase;

    // Default constructor for serialization/deserialization
    public RecadastramentoResponseDTO() {
    }

    /**
     * Constructs a RecadastramentoResponseDTO.
     *
     * @param instituicoes list of institution DTOs available for recadastramento
     * @param anoBase      the base year for recadastramento
     */
    public RecadastramentoResponseDTO(List<InstituicaoDTO> instituicoes, Integer anoBase) {
        this.instituicoes = instituicoes;
        this.anoBase = anoBase;
    }

    public List<InstituicaoDTO> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<InstituicaoDTO> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public Integer getAnoBase() {
        return anoBase;
    }

    public void setAnoBase(Integer anoBase) {
        this.anoBase = anoBase;
    }

    @Override
    public String toString() {
        return "RecadastramentoResponseDTO{" +
            "instituicoes=" + instituicoes +
            ", anoBase=" + anoBase +
            '}';
    }

    // TODO: (REVIEW) Consider adding validation groups or splitting into separate Request/Response DTOs if needed.
}