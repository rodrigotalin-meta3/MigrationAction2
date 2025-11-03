package br.com.meta3.java.scaffold.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.meta3.java.scaffold.domain.entities.Instituicao;
import br.com.meta3.java.scaffold.domain.entities.ArquivoRecadastramentoEstado;
import br.com.meta3.java.scaffold.domain.repositories.RecadastramentoRepository;
import br.com.meta3.java.scaffold.infrastructure.repositories.QueryExecutor;

@Repository
public class SpringDataRecadastramentoRepository implements RecadastramentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final QueryExecutor queryExecutor;

    @Autowired
    public SpringDataRecadastramentoRepository(EntityManager entityManager,
                                               QueryExecutor queryExecutor) {
        this.entityManager = entityManager;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public List<Instituicao> findInstituicoesByUsuarioCodigo(String usuarioCodigo) {
        // TODO: (REVIEW) Apply filtering by usuarioCodigo once business logic is defined
        return entityManager.createQuery(
                "SELECT i FROM Instituicao i", Instituicao.class)
            .getResultList();
    }

    @Override
    public List<ArquivoRecadastramentoEstado> mostraInstituicoesEstadoMunicipio(String login) {
        // Base legacy SQL without filter
        String baseQuery =
            "SELECT codigosec, nome, cgc, bairro " +
            "FROM alunos.alu_mec_tacom";

        // Determine operator and use parameter binding for threshold
        String operator = "9999".equals(login) ? "=" : "<";
        int threshold = 7;

        // Build finalQuery with placeholder instead of manual concatenation of threshold
        String finalQuery = baseQuery
            + " WHERE LENGTH(codigosec) " + operator + " ?"
            + " AND cod_titular IS NOT NULL"
            + " AND ano_base_exclusao IS NULL"
            + " ORDER BY codigosec ASC";

        // Use prepared query to bind 'threshold' parameter - avoids SQL injection/vector concatenation
        // TODO: (REVIEW) Ensure QueryExecutor.executePreparedQuery supports parameter binding correctly
        List<Object[]> results = queryExecutor.executePreparedQuery(finalQuery, threshold);

        List<ArquivoRecadastramentoEstado> list = new ArrayList<>();
        for (Object[] row : results) {
            ArquivoRecadastramentoEstado entry = new ArquivoRecadastramentoEstado();
            if (row.length > 0 && row[0] != null) {
                entry.setCodigosec(row[0].toString());
            }
            if (row.length > 1 && row[1] != null) {
                entry.setNome(row[1].toString());
            }
            if (row.length > 2 && row[2] != null) {
                // TODO: (REVIEW) Ensure this maps legacy 'cgc' column correctly to entity CNPJ field
                entry.setCnpj(row[2].toString());
            }
            if (row.length > 3 && row[3] != null) {
                entry.setBairro(row[3].toString());
            }
            list.add(entry);
        }
        return list;
    }
}
