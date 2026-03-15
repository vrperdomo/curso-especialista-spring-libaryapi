package io.github.vrperdomo.libaryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.driver-class-name}")
    String driver;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public DataSource hikariDataSoource(){

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);

        hikariConfig.setMaximumPoolSize(10); // Máximo de conexões liberadas
        hikariConfig.setMinimumIdle(1); // Tamanho inicial do pool
        hikariConfig.setPoolName("libary-db-pool");
        hikariConfig.setMaxLifetime(600000); // 600 mil ms (10 minutos)
        hikariConfig.setConnectionTimeout(100000); // Timeout para conseguir uma conexão
        hikariConfig.setConnectionTestQuery("select 1"); // Query de teste

        return new HikariDataSource(hikariConfig);
    }
}
