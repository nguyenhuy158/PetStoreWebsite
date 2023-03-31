package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.Cart;
import vn.petstore.website.model.CartDto;
import vn.petstore.website.model.Transaction;
import vn.petstore.website.model.TransactionDetail;
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

    @Autowired
    TransactionService transactionService;

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

    public void addProductToCartWithQuantity(Long productId, Long quantity) {
        // check is product is existed
        List<Cart> allByUserIdAndProductId = cartRepository.findAllByUserIdAndProductId(userService.getCurrentUserId(),
                productId);
        Cart cart;
        if (allByUserIdAndProductId.size() != 0) {
            cart = allByUserIdAndProductId.get(0);
            cart.setQuantity(cart.getQuantity() + quantity);
        } else {
            cart = new Cart();
            cart.setUserId(userService.getCurrentUserId());
            cart.setProductId(productId);
            cart.setQuantity(quantity + 0L);
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
        }
    }

    public boolean isHasProduct() {
        List<Cart> carts = getCurrentCarts();

        if (carts.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    private List<Cart> getCurrentCarts() {
        return cartRepository.findAllByUserId(userService.getCurrentUserId());
    }

    public void checkout() {
        List<Cart> carts = getCurrentCarts();

        Transaction transaction = new Transaction();
        List<TransactionDetail> transactionDetails = carts.stream().map(arg0 -> new TransactionDetail(
                transaction,
                productService.getProductById(arg0.getProductId()),
                arg0.getQuantity(),
                productService.getProductById(arg0.getProductId()).getPrice())).toList();

        transaction.setUser(userService.getCurrentUser());
        transaction.setTransactionDetailList(transactionDetails);

        transactionService.checkout(transaction);
        // remove products in cart after checkout
        carts.forEach(arg0 -> cartRepository.delete(arg0));
    }

}
