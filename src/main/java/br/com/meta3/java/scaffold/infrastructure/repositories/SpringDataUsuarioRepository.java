package br.com.meta3.java.scaffold.infrastructure.repositories;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.meta3.java.scaffold.domain.repositories.UsuarioRepository;

/**
 * Spring Data JPA implementation for UsuarioRepository.
 */
@Repository
public class SpringDataUsuarioRepository implements UsuarioRepository {

    private static final Logger LOG = LoggerFactory.getLogger(SpringDataUsuarioRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Fetch the base year (ano base) for recadastramento associated with a user's code.
     * Implements legacy retornaAnoBase logic using two native queries against
     * parametros_sis_digitacao and parametros_sis_digitacao_ind tables,
     * and fallback date-based logic if no parameters are found.
     *
     * @param usuarioCodigo the unique code identifying the user (used as cod_escola)
     * @return the base year for recadastramento
     */
    @Override
    public int findAnoBaseByUsuarioCodigo(String usuarioCodigo) {
        int anoBase = 0;

        // parse usuarioCodigo to codEscola; fallback to 0 if invalid
        int codEscola;
        try {
            codEscola = Integer.parseInt(usuarioCodigo);
        } catch (NumberFormatException ex) {
            LOG.warn("Invalid usuarioCodigo '{}' for codEscola parsing; defaulting to 0", usuarioCodigo);
            codEscola = 0;
        }

        // Query 1: global parameter
        String sqlGlobal = "select nvl(par.ano_base,0) ano_base "
                + "from alunos.parametros_sis_digitacao par "
                + "where par.status = 1";
        try {
            Query q = entityManager.createNativeQuery(sqlGlobal);
            Object result = q.getSingleResult();
            if (result != null) {
                // result can be BigDecimal or Number
                anoBase = ((Number) result).intValue();
            }
        } catch (Exception ex) {
            LOG.error("Error querying parametros_sis_digitacao for ano_base", ex);
        }

        // Query 2: individual parameter for specific cod_escola
        String sqlIndividual = "select pi.ano_base "
                + "from alunos.parametros_sis_digitacao_ind pi "
                + "where pi.status = 1 "
                + "and pi.dt_ini_parametro <= trunc(sysdate) "
                + "and pi.dt_fim_parametro >= trunc(sysdate) "
                + "and pi.cod_escola = :codEscola "
                + "order by pi.data_movimeto_par";
        try {
            Query q2 = entityManager.createNativeQuery(sqlIndividual);
            q2.setParameter("codEscola", codEscola);
            @SuppressWarnings("unchecked")
            List<Object> rows = q2.getResultList();
            for (Object row : rows) {
                if (row != null) {
                    anoBase = ((Number) row).intValue();
                }
            }
        } catch (Exception ex) {
            LOG.error("Error querying parametros_sis_digitacao_ind for ano_base, codEscola={}", codEscola, ex);
        }

        // Fallback date-based logic if no parameter found
        if (anoBase == 0) {
            LocalDate now = LocalDate.now();
            int currentYear = now.getYear();
            String monthDay =
                    String.format("%02d%02d", now.getMonthValue(), now.getDayOfMonth());
            // Check if between Nov 17 and Dec 31
            if (monthDay.compareTo("1117") >= 0 && monthDay.compareTo("1231") <= 0) {
                anoBase = currentYear + 1;
            } else {
                anoBase = currentYear;
            }
        }

        return anoBase;
    }
}
