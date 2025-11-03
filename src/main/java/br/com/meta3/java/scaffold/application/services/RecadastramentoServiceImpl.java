filetype:java
package br.com.meta3.java.scaffold.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.meta3.java.scaffold.domain.entities.Instituicao;
import br.com.meta3.java.scaffold.domain.entities.ArquivoRecadastramentoEstado;
import br.com.meta3.java.scaffold.domain.repositories.RecadastramentoRepository;
import br.com.meta3.java.scaffold.domain.repositories.UsuarioRepository;
import br.com.meta3.java.scaffold.domain.services.RecadastramentoService;

/**
 * Service implementation that orchestrates recadastramento operations,
 * delegating to repositories for data access and applying business rules.
 */
@Service
@Transactional(readOnly = true)
public class RecadastramentoServiceImpl implements RecadastramentoService {

    private final RecadastramentoRepository recadastramentoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RecadastramentoServiceImpl(
            RecadastramentoRepository recadastramentoRepository,
            UsuarioRepository usuarioRepository) {
        this.recadastramentoRepository = recadastramentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Instituicao> findInstituicoesByUsuarioCodigo(String usuarioCodigo) {
        // Delegates to repository to fetch institutions filtered by user's code
        return recadastramentoRepository.findInstituicoesByUsuarioCodigo(usuarioCodigo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateAnoBase(String usuarioCodigo) {
        // Delegates to repository applying legacy base year calculation logic
        return usuarioRepository.findAnoBaseByUsuarioCodigo(usuarioCodigo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArquivoRecadastramentoEstado> mostraInstituicoesEstadoMunicipio(String login) {
        // TODO: (REVIEW) Implement repository method to replicate legacy RecadastramentoDAO.mostraInstituicoesEstadoMunicipio
        // Delegates to repository for legacy-style institution fetching by state or municipality
        return recadastramentoRepository.mostraInstituicoesEstadoMunicipio(login);
    }
}