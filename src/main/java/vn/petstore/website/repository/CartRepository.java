package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import vn.petstore.website.model.Cart;

import java.util.List;

@Component
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);

    List<Cart> findAllByUserIdAndProductId(Long userId, Long productId);
}
