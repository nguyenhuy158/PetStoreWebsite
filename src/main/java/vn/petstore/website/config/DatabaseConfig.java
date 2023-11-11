package vn.petstore.website.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseConfig {
    @Autowired
    // private DataSource dataSource;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Set your database properties here
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/PetStore?createDatabaseIfNotExist=true");
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

    // @PostConstruct
    // public void initialize() {
    //     try (Connection connection = dataSource.getConnection();
    //             Statement statement = connection.createStatement()) {

    //         ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM product");
    //         resultSet.next();
    //         int count = resultSet.getInt("count");

    //         if (count == 0) {
    //             String sql = new String(Files.readAllBytes(Paths.get("src\\main\\docker\\insert-data.sql")));
    //             String[] queries = sql.split(";");

    //             for (String query : queries) {
    //                 if (!query.trim().isEmpty()) {
    //                     statement.executeUpdate(query);
    //                 }
    //             }
    //         }

    //     } catch (SQLException | IOException e) {
    //         System.out.println("[DB] " + e.getMessage());
    //         System.out.println("[DB] Unable to connect to the database. Please check your connection settings.");
    //     }
    // }

}