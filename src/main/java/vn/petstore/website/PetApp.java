package vn.petstore.website;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("ntqhuy2k2");
        user.setPassword(passwordEncoder.encode(" "));
        userRepository.save(user);
        System.out.println(user);
    }
}
