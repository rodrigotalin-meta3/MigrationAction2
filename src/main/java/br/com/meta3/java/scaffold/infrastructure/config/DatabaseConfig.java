filetype:java
package br.com.meta3.java.scaffold.infrastructure.config;

import java.sql.SQLException;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Configuration for selecting and configuring the JDBC DataSource
 * based on the 'app.datasource.tipobanco' property.
 */
@Configuration
public class DatabaseConfig {

    @Value("${app.datasource.tipobanco}")
    private String tipoBanco;

    // Oracle-specific properties
    @Value("${app.datasource.oracle.url:}")
    private String oracleUrl;
    @Value("${app.datasource.oracle.username:}")
    private String oracleUsername;
    @Value("${app.datasource.oracle.password:}")
    private String oraclePassword;

    // SQL Server-specific properties
    @Value("${app.datasource.sqlserver.host:}")
    private String sqlHost;
    @Value("${app.datasource.sqlserver.port:1434}")
    private int sqlPort;
    @Value("${app.datasource.sqlserver.database:}")
    private String sqlDatabase;
    @Value("${app.datasource.sqlserver.username:}")
    private String sqlUsername;
    @Value("${app.datasource.sqlserver.password:}")
    private String sqlPassword;

    /**
     * Creates a DataSource bean, switching between OracleDataSource and
     * SQLServerDataSource based on the 'tipoBanco' setting.
     *
     * @return configured DataSource
     * @throws SQLException if DataSource initialization fails
     */
    @Bean
    public DataSource dataSource() throws SQLException {
        if ("oracle".equalsIgnoreCase(tipoBanco)) {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL(oracleUrl);
            ds.setUser(oracleUsername);
            ds.setPassword(oraclePassword);
            return ds;
        } else if ("sqlserver".equalsIgnoreCase(tipoBanco)) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName(sqlHost);
            ds.setPortNumber(sqlPort);
            ds.setDatabaseName(sqlDatabase);
            ds.setUser(sqlUsername);
            ds.setPassword(sqlPassword);
            return ds;
        }
        // TODO: (REVIEW) Expand support for additional databases if needed
        throw new IllegalArgumentException("Unsupported tipoBanco: " + tipoBanco);
    }

    /**
     * JdbcTemplate configured with the selected DataSource.
     *
     * @param dataSource the DataSource bean
     * @return JdbcTemplate for database operations
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}