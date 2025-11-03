filetype:java
package br.com.meta3.java.scaffold.domain.services;

import br.com.meta3.java.scaffold.domain.entities.Usuario;

/**
 * Service interface defining business operations for user data.
 */
public interface UsuarioService {

    /**
     * Fetches a user entity based on its unique code.
     *
     * @param codigo the unique code identifying the user
     * @return Usuario entity corresponding to the given code
     */
    Usuario findByCodigo(String codigo);

    /**
     * Determines the base year (ano base) for recadastramento associated with a user's code.
     *
     * @param codigo the unique code identifying the user
     * @return the base year for recadastramento
     */
    int calculateAnoBase(String codigo);

    // TODO: (REVIEW) Implement this interface in application.services to delegate to
    // UsuarioRepository.findAnoBaseByUsuarioCodigo and any necessary business rules.
}