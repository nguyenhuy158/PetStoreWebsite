package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.Cart;
import vn.petstore.website.model.CartDto;
import vn.petstore.website.repository.CartRepository;
import java.util.List;
import static vn.petstore.website.constances.Const.SHIPPING;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    private List<Cart> getCarts() {
        return cartRepository.findAllByUserId(userService.getCurrentUserId());
    }

    public List<CartDto> getCart() {
        List<Cart> carts = getCarts();

        List<CartDto> cartDtos = carts.stream()
                .map(cart -> new CartDto(productService.getProductById(cart.getProductId()), cart.getQuantity()))
                .toList();

        return cartDtos;
    }

    public Long getSubtotal() {
        List<Cart> carts = getCarts();

        carts.forEach(System.out::println);

        if (carts.size() != 0) {
            Long subTotal = carts.stream()
                    .map(cart -> cart.getQuantity() * productService.getProductById(cart.getProductId()).getPrice())
                    .reduce((aLong, aLong2) -> aLong + aLong2).get();
            return subTotal;
        } else {
            return 0L;
        }
    }

    public Double getTax() {
        return getSubtotal() * 0.05;
    }

    public Double getGrandTotal() {
        return getSubtotal() + getTax() + SHIPPING;
    }

    public void addProductToCart(Long productId) {
        // check is product is existed
        List<Cart> allByUserIdAndProductId = cartRepository.findAllByUserIdAndProductId(userService.getCurrentUserId(),
                productId);
        Cart cart;
        if (allByUserIdAndProductId.size() != 0) {
            cart = allByUserIdAndProductId.get(0);
            cart.setQuantity(cart.getQuantity() + 1);

            System.out.println(cart.toString());
            System.out.println("cart exited");
        } else {
            cart = new Cart();
            cart.setUserId(userService.getCurrentUserId());
            cart.setProductId(productId);
        }
        cartRepository.save(cart);
    }

    public void removeProductToCart(Long productId) {
        List<Cart> allByUserIdAndProductId = cartRepository.findAllByUserIdAndProductId(userService.getCurrentUserId(),
                productId);
        if (allByUserIdAndProductId.size() != 0) {
            Cart cart = allByUserIdAndProductId.get(0);
            cartRepository.delete(cart);
        }
    }

    public void decrementProductToCart(Long productId) {
        // check is product is existed
        List<Cart> allByUserIdAndProductId = cartRepository.findAllByUserIdAndProductId(userService.getCurrentUserId(),
                productId);
        if (allByUserIdAndProductId.size() != 0) {
            Cart cart = allByUserIdAndProductId.get(0);

            if (cart.getQuantity() == 1) {
                removeProductToCart(productId);
                return;
            }
            cart.setQuantity(cart.getQuantity() - 1);

            cartRepository.save(cart);
            System.out.println(cart.toString());
            System.out.println("cart exited");
        }
    }

    public void incrementProductToCart(Long productId) {
        // check is product is existed
        List<Cart> allByUserIdAndProductId = cartRepository.findAllByUserIdAndProductId(userService.getCurrentUserId(),
                productId);
        if (allByUserIdAndProductId.size() != 0) {
            Cart cart = allByUserIdAndProductId.get(0);
            cart.setQuantity(cart.getQuantity() + 1);

            cartRepository.save(cart);
            System.out.println(cart.toString());
            System.out.println("cart exited");
        }
    }
}
