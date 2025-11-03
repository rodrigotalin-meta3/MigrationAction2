package br.com.meta3.java.scaffold.domain.services;

import java.util.List;

import br.com.meta3.java.scaffold.domain.entities.Instituicao;
import br.com.meta3.java.scaffold.domain.entities.ArquivoRecadastramentoEstado;

/**
 * Service interface defining business operations for recadastramento.
 */
public interface RecadastramentoService {

    /**
     * Fetch the list of institutions available for recadastramento based on a user's code.
     *
     * @param usuarioCodigo the unique code identifying the user
     * @return list of institutions filtered by the user's state and municipality
     */
    List<Instituicao> findInstituicoesByUsuarioCodigo(String usuarioCodigo);

    /**
     * Determine the base year (ano base) for recadastramento associated with a user's code.
     *
     * @param usuarioCodigo the unique code identifying the user
     * @return the base year for recadastramento
     */
    int calculateAnoBase(String usuarioCodigo);

    /**
     * Legacy method to fetch institution records by state or municipality as per legacy DAO logic.
     *
     * @param login the login code (e.g., "9999" for state-level filtering)
     * @return list of ArquivoRecadastramentoEstado entries matching legacy criteria
     */
    List<ArquivoRecadastramentoEstado> mostraInstituicoesEstadoMunicipio(String login);

    // TODO: (REVIEW) Implement this interface in application/services to invoke legacy logic
    // via the appropriate repository or custom query, mapping results to ArquivoRecadastramentoEstado.
}
