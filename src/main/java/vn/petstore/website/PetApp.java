package vn.petstore.website;

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
    }
}
