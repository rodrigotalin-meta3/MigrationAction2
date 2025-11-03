package br.com.meta3.java.scaffold.domain.repositories;

/**
 * Repository interface for user related operations.
 */
public interface UsuarioRepository {

    /**
     * Fetch the base year (ano base) associated with the given user's code.
     *
     * @param usuarioCodigo the unique code identifying the user
     * @return the base year for recadastramento
     */
    int findAnoBaseByUsuarioCodigo(String usuarioCodigo);

    // TODO: (REVIEW) Implement this interface in the infrastructure layer,
    // using Spring Data JPA or custom JPQL/SQL query to replicate
    // legacy UsuarioDAO.retornaAnoBase functionality.
}
