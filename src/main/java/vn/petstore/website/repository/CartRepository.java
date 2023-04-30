package vn.petstore.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vn.petstore.website.model.Cart;

@Component
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);

    List<Cart> findAllByUserIdAndProductId(Long userId, Long productId);

}
