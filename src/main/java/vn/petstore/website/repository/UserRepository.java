package vn.petstore.website.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import vn.petstore.website.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    public Long countById(Long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String encodedPassword);

    Page<User> findAllByUsernameContains(String username, Pageable pageable);

    // Page<Product> findAllByColorContains(String color, Pageable pageable);
    // Page<Product> findAllByBrandContains(String brand, Pageable pageable);

    Page<User> findAllByPhoneContains(String phone, Pageable pageable);
}
