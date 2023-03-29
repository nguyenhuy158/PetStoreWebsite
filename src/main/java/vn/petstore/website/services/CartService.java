package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.Cart;
import vn.petstore.website.model.Product;
import vn.petstore.website.repository.CartRepository;
import vn.petstore.website.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public List<Product> getCart(Long userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);

        List<Long> productIds = carts.stream().map(cart -> cart.getProductId()).toList();

        List<Product> products = productIds.stream().map(aLong -> productService.getProductById(aLong)).toList();

        return products;
    }

    public void removeProductInCart(String userId, String productId) {
    }

    public void addProductToCart(Long productId) {
        Cart cart = new Cart();
        cart.setUserId(userService.getCurrentUserId());
        cart.setProductId(productId);
        cartRepository.save(cart);
    }
}
