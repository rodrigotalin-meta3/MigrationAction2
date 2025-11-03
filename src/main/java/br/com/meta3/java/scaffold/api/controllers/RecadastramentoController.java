filetype:java
package br.com.meta3.java.scaffold.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meta3.java.scaffold.api.dtos.ArquivoRecadastramentoEstadoDTO;
import br.com.meta3.java.scaffold.api.dtos.InstituicaoDTO;
import br.com.meta3.java.scaffold.api.dtos.RecadastramentoResponseDTO;
import br.com.meta3.java.scaffold.domain.entities.ArquivoRecadastramentoEstado;
import br.com.meta3.java.scaffold.domain.entities.Instituicao;
import br.com.meta3.java.scaffold.domain.services.RecadastramentoService;

/**
 * REST controller for recadastramento operations.
 * Exposes endpoints to fetch the list of institutions available for recadastramento,
 * the calculated base year (ano base), and legacy state/municipality records.
 */
@RestController
@RequestMapping("/api/recadastramento")
public class RecadastramentoController {

    private final RecadastramentoService recadastramentoService;

    @Autowired
    public RecadastramentoController(RecadastramentoService recadastramentoService) {
        this.recadastramentoService = recadastramentoService;
    }

    /**
     * GET  /instituicoes?usuarioCodigo={codigo} : Retrieve institutions and ano base for recadastramento.
     *
     * @param usuarioCodigo the code identifying the user for whom to fetch data
     * @return RecadastramentoResponseDTO containing list of InstituicaoDTO and anoBase
     */
    @GetMapping("/instituicoes")
    public RecadastramentoResponseDTO getInstituicoesAndAnoBase(
            @RequestParam("usuarioCodigo") String usuarioCodigo) {

        // Fetch domain entities
        List<Instituicao> entidades = recadastramentoService.findInstituicoesByUsuarioCodigo(usuarioCodigo);

        // Map entities to DTOs
        List<InstituicaoDTO> dtos = entidades.stream()
                .map(InstituicaoDTO::fromEntity)
                .collect(Collectors.toList());

        // Calculate base year as per legacy logic
        int anoBase = recadastramentoService.calculateAnoBase(usuarioCodigo);

        return new RecadastramentoResponseDTO(dtos, anoBase);
    }

    /**
     * GET  /estado-municipio?login={login} : Retrieve legacy institution records
     * filtered by state or municipality based on legacy RecadastramentoDAO logic.
     *
     * @param login the login code (e.g., "9999" for state-level filtering)
     * @return list of ArquivoRecadastramentoEstadoDTO representing legacy records
     */
    @GetMapping("/estado-municipio")
    public List<ArquivoRecadastramentoEstadoDTO> getEstadoMunicipio(
            @RequestParam("login") String login) {

        // Fetch legacy records via service, delegating to repository native query
        List<ArquivoRecadastramentoEstado> entidades =
                recadastramentoService.mostraInstituicoesEstadoMunicipio(login);

        // Map legacy domain entities to DTOs
        // TODO: (REVIEW) Ensure mapping matches legacy "cgc" column to DTO.getCnpj()
        return entidades.stream()
                .map(ArquivoRecadastramentoEstadoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // TODO: (REVIEW) Consider retrieving usuarioCodigo and login from authentication context instead of request parameters.
    // TODO: (REVIEW) Add error handling for invalid or missing parameters.
}