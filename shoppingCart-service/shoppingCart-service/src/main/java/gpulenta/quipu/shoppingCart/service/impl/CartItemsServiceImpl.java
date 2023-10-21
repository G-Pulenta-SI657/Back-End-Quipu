package gpulenta.quipu.shoppingCart.service.impl;

import gpulenta.quipu.shoppingCart.client.ProductClient;
import gpulenta.quipu.shoppingCart.client.UserClient;
import gpulenta.quipu.shoppingCart.model.CartItems;
import gpulenta.quipu.shoppingCart.model.Product;
import gpulenta.quipu.shoppingCart.repository.CartItemsRepository;
import gpulenta.quipu.shoppingCart.service.CartItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartItemsServiceImpl implements CartItemsService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductClient productClient;

    private final UserClient userClient;

    @Autowired
    public CartItemsServiceImpl(CartItemsRepository cartItemsRepository, ProductClient productClient, UserClient userClient) {
        this.cartItemsRepository = cartItemsRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    @Override
    public List<CartItems> getAllCartItems() {
        List<CartItems> cartItems = cartItemsRepository.findAll();
        for (CartItems cartItem : cartItems) {
            ResponseEntity<Product> productResponse = productClient.getProductById(cartItem.getProductId());
            if (productResponse.getStatusCode() == HttpStatus.OK) {
                cartItem.setProduct(productResponse.getBody());
            }
        }
        return cartItems;
    }

    @Override
    public CartItems save(CartItems cartItems) {
        return cartItemsRepository.save(cartItems);
    }

    @Override
    public CartItems update(CartItems cartItems) {
        return null;
    }

    @Override
    public void delete(Long id) {
        cartItemsRepository.deleteById(id);
    }

    @Override
    public List<CartItems> findByShoppingCart_Id(Long id) {
        List<CartItems> cartItems = cartItemsRepository.findByShoppingCart_Id(id);
        for (CartItems cartItem : cartItems) {
            ResponseEntity<Product> productResponse = productClient.getProductById(cartItem.getProductId());
            if (productResponse.getStatusCode() == HttpStatus.OK) {
                cartItem.setProduct(productResponse.getBody());
            }
        }
        return cartItems;
    }

    @Override
    public CartItems findById(Long id) {
        CartItems cartItems = cartItemsRepository.findById(id).orElse(null);
        if (cartItems != null) {
            cartItems.setProduct(productClient.getProductById(cartItems.getProductId()).getBody());
        }
        return cartItems;
    }
}
