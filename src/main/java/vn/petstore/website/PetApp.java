package vn.petstore.website;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.emun.Role;
import vn.petstore.website.model.User;
import vn.petstore.website.repository.UserRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class PetApp implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(PetApp.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        User ntqhuy2k2 = userRepository.findByUsername("ntqhuy2k2");
        // System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
        // System.out.println(ntqhuy2k2);
        if (ntqhuy2k2 == null) {
            User user = new User();
            user.setUsername("ntqhuy2k2");
            user.setPassword(passwordEncoder.encode("ntqhuy2k2"));
            user.setName("Nguyen Huy");
            user.setPhone("0837377855");
            user.setAddress("Quan 7, HCM");
            user.setEmail("ntqhuy2k2@gmail.com");
            user.setRole(Role.USER);
            userRepository.save(user);
            System.out.println(user);
        }

        User admin = userRepository.findByUsername("admin");
        if (admin == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setName("Nguyen Huy");
            user.setPhone("0837377855");
            user.setAddress("Quan 7, HCM");
            user.setEmail("ntqhuy2k2@gmail.com");
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            System.out.println(user);
        }

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM product");
            resultSet.next();
            int count = resultSet.getInt("count");
            System.out.println("Number of products: " + count);

            if (count == 0) {
                String sql = new String(Files.readAllBytes(Paths.get("src\\main\\docker\\insert-data.sql")),
                        StandardCharsets.UTF_8);
                String[] queries = sql.split(";");

                for (String query : queries) {
                    if (!query.trim().isEmpty()) {
                        try {
                            statement.executeUpdate(query);
                        } catch (SQLException e) {
                            System.out.println("[DB] " + e.getMessage());
                            System.out.println("[DB] Error executing query: " + query);
                        }
                    }
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("[DB] " + e.getMessage());
            System.out.println("[DB] Unable to connect to the database. Please check your connection settings.");
        }
    }
}
