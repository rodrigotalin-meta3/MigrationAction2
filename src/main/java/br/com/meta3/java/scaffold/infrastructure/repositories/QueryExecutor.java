filetype:java
package br.com.meta3.java.scaffold.infrastructure.repositories;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * Utility to execute native SQL queries and return results as list of Object arrays.
 * Refactored to use Spring's JdbcTemplate for connection management and resource cleanup.
 */
@Component
public class QueryExecutor {

    private final JdbcTemplate jdbcTemplate;

    public QueryExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Executes the given native SQL and maps each row to an Object[].
     *
     * @param sql the native SQL query to execute
     * @return list of rows, each represented as an Object array of column values
     */
    public List<Object[]> executeQuery(String sql) {
        // TODO: (REVIEW) Ensure SQL passed here is safe and parameters are properly handled if needed.
        return jdbcTemplate.query(sql, new RowMapper<Object[]>() {
            @Override
            public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                Object[] columns = new Object[columnCount];
                // Map each column in the current row to the Object array
                for (int i = 0; i < columnCount; i++) {
                    // JDBC column indexes are 1-based
                    columns[i] = rs.getObject(i + 1);
                }
                return columns;
            }
        });
    }
}