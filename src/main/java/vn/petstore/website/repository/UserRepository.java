package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import vn.petstore.website.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String encodedPassword);
}
