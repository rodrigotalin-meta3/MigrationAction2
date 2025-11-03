filetype:java
package br.com.meta3.java.scaffold.domain.repositories;

import java.util.List;

import br.com.meta3.java.scaffold.domain.entities.Instituicao;
import br.com.meta3.java.scaffold.domain.entities.ArquivoRecadastramentoEstado;

/**
 * Repository interface for recadastramento related operations.
 */
public interface RecadastramentoRepository {

    /**
     * Fetch the list of institutions for recadastramento based on a user's code.
     *
     * @param usuarioCodigo the unique code identifying the user
     * @return list of institutions filtered by the user's state and municipality
     */
    List<Instituicao> findInstituicoesByUsuarioCodigo(String usuarioCodigo);

    /**
     * Legacy method to fetch institution records by state or municipality as per legacy DAO logic.
     *
     * @param login the login code (e.g., "9999" for state-level filtering)
     * @return list of ArquivoRecadastramentoEstado entries matching legacy criteria
     */
    List<ArquivoRecadastramentoEstado> mostraInstituicoesEstadoMunicipio(String login);

    // TODO: (REVIEW) Implement this interface in the infrastructure layer,
    // using Spring Data JPA or custom JPQL/SQL queries to replicate
    // legacy RecadastramentoDAO.mostraInstituicoesEstadoMunicipio functionality.
}