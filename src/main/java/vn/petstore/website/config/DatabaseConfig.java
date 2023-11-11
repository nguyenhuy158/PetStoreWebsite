package vn.petstore.website.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Set your database properties here
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/PetStore?createDatabaseIfNotExist=true&characterEncoding=utf8&collation=utf8_unicode_ci");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("[DB] " + e.getMessage());
            System.out.println("[DB] Unable to connect to the database. Please check your connection settings.");
        }

        return dataSource;
    }
}