package br.com.consigned.consignedsimulatorservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    private final DataSource dataSource;

    public JdbcConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SimpleJdbcCall simpleJdbcCall() {
        return new SimpleJdbcCall(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
